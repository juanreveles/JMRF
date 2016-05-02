package cimat.tesis.sna.patterns.observer;


import java.util.Collection;
//import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
//import java.util.Map;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.patterns.general.EdgeData;
import cimat.tesis.sna.patterns.general.Searcher;


public class ObserverSearcher extends Searcher {
	
	private Collection<Integer> c_target_nodes 	= null;
	private Collection<Integer> c_nodes_ob 	= null;
	private Collection<Integer> c_nodes_co 	= null;
	private Collection<Integer> c_nodes_s	= null;
	private Collection<Integer> c_nodes_cs 	= null;
	private Collection<Integer> c_nodes_cl 	= null;
	private YSparseMultigraph 	g 				= null;
	
	
	public ObserverSearcher(){
	
		this.c_target_nodes = new HashSet<Integer>();
		this.c_nodes_ob = new HashSet<Integer>();
		this.c_nodes_co= new HashSet<Integer>();
		this.c_nodes_s= new HashSet<Integer>();
		this.c_nodes_cs= new HashSet<Integer>();	
		this.c_nodes_cl= new HashSet<Integer>();	
		this.g = Sistema.getCreator().getGraph();
	}
	
	
	public  Collection<Integer> extractTargetNodes(){
		
		 Collection<Integer> target_nodes = new HashSet<Integer>();
	        
	        int ass_degree 	= 	0;
	        int rea_degree 	=	0;
	        int agr_degree 	=	0;
	
	        
	        for(Iterator<Integer> it = this.g.getVertices().iterator(); it.hasNext();){
	        	
	        	int current_node = it.next();
	        	        	
	        	Collection<Integer> in_edges = this.g.getInEdges(current_node);
	        	
	        	Collection<Integer> out_edges = this.g.getOutEdges(current_node);
	        	
	        	
	        	if((in_edges.size()>0) || (out_edges.size()>1)){	        		
	        			        		
	        		ass_degree 	= this.getNumberOfRelations(in_edges,"DEPENDENCY") + this.getNumberOfRelations(	in_edges,"ASSOCIATION");
	        		        		
	        		rea_degree 	= this.getNumberOfRelations(	out_edges,"REALIZATION") ;
	        		
	        		agr_degree	= this.getNumberOfRelations(	out_edges,"AGGREGATION");
	        				        		
	        	}	        	
	        	
	                  	
	        
	        	if((agr_degree >= 1) && (rea_degree>= 1) && (ass_degree>= 0)){  		        		        		
	        		   		 
		        		target_nodes.add(current_node);	
	        	}
	        	
	        	ass_degree	=0;
	        	rea_degree	=0;
	        	agr_degree	=0;
	        	
	        }
		
		return target_nodes;
	}
	

	public void extraerConcretObserver(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getC_target_nodes().iterator(); it.hasNext();){
				
			    int current_node = it.next();    
			    
			    Collection<Integer> out_edges = this.g.getOutEdges(current_node);
        		
			    for(Iterator<Integer> iti_edges = out_edges.iterator(); iti_edges.hasNext();){
					
        			int i_edge = iti_edges.next();					
						
        			String edge_type = edgeTable.get(i_edge).getEdgeType();
					
        			if((edge_type.equals("AGGREGATION") == true)){
        				
    					
        				Collection<Integer> out_edges_cs=this.g.getOutEdges(this.g.getDest(i_edge));
        				
        				int rg_out_edge= this.getNumberOfRelations(	out_edges_cs,"REALIZATION") + this.getNumberOfRelations(	out_edges_cs,"GENERALIZATION") ; 
        				
        				if(rg_out_edge >= 1){  		        		        		
	        		   		 
        					if(this.isElement(current_node)){
          						
        						this.c_nodes_co.add(current_node);
        						
        					}
        					
        					if(this.isElement(this.g.getDest(i_edge))){
        					
        						this.c_nodes_cs.add(this.g.getDest(i_edge));
        								
        					}
        				}
        				
    				}
        			
			    }
			    
		}
				     
	}
	
	public void extraerElementos(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getC_nodes_co().iterator(); it.hasNext();){
				
			int current_node = it.next();
			
			Collection<Integer> in_edges = this.g.getInEdges(current_node);
           	Collection<Integer> out_edges = this.g.getOutEdges(current_node);
            		        			
      		
      		for(Iterator<Integer> iti_edges = in_edges.iterator(); iti_edges.hasNext();){
					
      			int i_edge = iti_edges.next();					
						
      			String edge_type = edgeTable.get(i_edge).getEdgeType();
					
      			if((edge_type.equals("DEPENDENCY") == true) || (edge_type.equals("ASSOCIATION") == true)){
					
      				if(this.isElement(this.g.getSource(i_edge))){
      		
      					this.c_nodes_cl.add(this.g.getSource(i_edge));
      				
      				}
					
      			}	 
			}
      		
      		for(Iterator<Integer> ito_edges = out_edges.iterator(); ito_edges.hasNext();){
  				
  				int o_edge = ito_edges.next();
  				
  				String edge_type_i = edgeTable.get(o_edge).getEdgeType();
  									
  				 if((edge_type_i.equals("REALIZATION") == true)){
  					
  					if(this.isElement(this.g.getDest(o_edge))){
  	  					
  						this.c_nodes_ob.add(this.g.getDest(o_edge));
      				}
      				
  				}
  				
  			}		
      		
      	}		
	}

   	        		        
	
	public void extraerSubElementos(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getC_nodes_cs().iterator(); it.hasNext();){
								
			int nodo_cs = it.next();
			
			Collection<Integer> out_edges_cs = this.g.getOutEdges(nodo_cs);
			
			Collection<Integer> in_edges_cs = this.g.getInEdges(nodo_cs);
			
			for(Iterator<Integer> it_edges = out_edges_cs.iterator(); it_edges.hasNext();){
				
				int i_edge_cs = it_edges.next();
				
				String edge_type_i = edgeTable.get(i_edge_cs).getEdgeType();
									
				if((edge_type_i.equals("REALIZATION") == true) || (edge_type_i.equals("GENERALIZATION") == true)){
						
					if(this.isElement(this.g.getDest(i_edge_cs))){
						
						this.c_nodes_s.add(this.g.getDest(i_edge_cs));		
					
					}
				}				
			}
			
			for(Iterator<Integer> it_edges = in_edges_cs.iterator(); it_edges.hasNext();){
				
				int i_edge_cs = it_edges.next();
				
				String edge_type_i = edgeTable.get(i_edge_cs).getEdgeType();
									
				if(edge_type_i.equals("DEPENDENCY") == true || edge_type_i.equals("ASSOCIATION") == true){
						
					if(this.isElement(this.g.getSource(i_edge_cs))){
						
						this.c_nodes_cl.add(this.g.getSource(i_edge_cs));		
					
					}
				}	
			}		
			
		}

	}

	private boolean isElement(Integer node_searched){
		
		if(!this.isClient(node_searched) && !this.isConcreteObserver(node_searched) && !this.isConcreteSubject(node_searched) && !this.isObserver(node_searched) && !this.isSubject(node_searched)){
			
			return true;
			
		}
				
		return false;
	}
	
	private boolean isConcreteObserver(Integer node_searched){
		return this.isInCollection(this.c_nodes_co, node_searched);
	}
	
	private boolean isObserver(Integer node_searched){
		return this.isInCollection(this.c_nodes_ob, node_searched);
	}
	
	private boolean isConcreteSubject(Integer node_searched){
		return this.isInCollection(this.c_nodes_cs, node_searched);
	}
	
	private boolean isSubject(Integer node_searched){
		return this.isInCollection(this.c_nodes_s, node_searched);
	}
	
	private boolean isClient(Integer node_searched){
		return this.isInCollection(this.c_nodes_cl, node_searched);
	}
	
	public Collection<Integer> getAll_nodes() {
		return this.g.getVertices();
	}
	
	public Collection<Integer> getC_target_nodes() {
		return c_target_nodes;
	}

	public void setC_target_nodes(Collection<Integer> c_target_nodes) {
		this.c_target_nodes = c_target_nodes;
	}

	public Collection<Integer> getC_nodes_ob() {
		return c_nodes_ob;
	}

	public void setC_nodes_ob(Collection<Integer> c_nodes_ob) {
		this.c_nodes_ob = c_nodes_ob;
	}

	public Collection<Integer> getC_nodes_co() {
		return c_nodes_co;
	}

	public void setC_nodes_co(Collection<Integer> c_nodes_co) {
		this.c_nodes_co = c_nodes_co;
	}

	public Collection<Integer> getC_nodes_s() {
		return c_nodes_s;
	}

	public void setC_nodes_s(Collection<Integer> c_nodes_s) {
		this.c_nodes_s = c_nodes_s;
	}

	public Collection<Integer> getC_nodes_cs() {
		return c_nodes_cs;
	}

	public void setC_nodes_cs(Collection<Integer> c_nodes_cs) {
		this.c_nodes_cs = c_nodes_cs;
	}

	public Collection<Integer> getC_nodes_cl() {
		return c_nodes_cl;
	}

	public void setC_nodes_cl(Collection<Integer> c_nodes_cl) {
		this.c_nodes_cl = c_nodes_cl;
	}
}

