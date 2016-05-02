package cimat.tesis.sna.patterns.bridge;

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


public class BridgeSearcher extends Searcher {
	
	private Collection<Integer> c_target_nodes 	= null;
	private Collection<Integer> c_nodes_i 	= null;
	private Collection<Integer> c_nodes_ci 	= null;
	private Collection<Integer> c_nodes_c 	= null;
	private Collection<Integer> c_nodes_a 	= null;
	private Collection<Integer> c_nodes_ra 	= null;
	private YSparseMultigraph 	g 				= null;
	
	
	public BridgeSearcher(){
	
		this.c_target_nodes = new HashSet<Integer>();
		this.c_nodes_i = new HashSet<Integer>();
		this.c_nodes_ci= new HashSet<Integer>();
		this.c_nodes_c= new HashSet<Integer>();
		this.c_nodes_a= new HashSet<Integer>();	
		this.c_nodes_ra= new HashSet<Integer>();	
		this.g = Sistema.getCreator().getGraph();
	}
	
	public  Collection<Integer> extractTargetNodes(){
		
		 Collection<Integer> target_nodes = new HashSet<Integer>();
	        
	        int rg_degree_out 	= 	0;
	        int rg_degree_in 	=	0;
	        int agr_degree 	=	0;
	
	        
	        for(Iterator<Integer> it = this.g.getVertices().iterator(); it.hasNext();){
	        	
	        	int current_node = it.next();
	        	        	
	        	Collection<Integer> in_edges = this.g.getInEdges(current_node);
	        	
	        	Collection<Integer> out_edges = this.g.getOutEdges(current_node);
	        	
	        	
	        	if((in_edges.size()>0) || (out_edges.size()>0)){	        		
	        			        		
	        		rg_degree_out 	= this.getNumberOfRelations(	out_edges,"REALIZATION") + this.getNumberOfRelations(	out_edges,"GENERALIZATION");
	        		        		
	        		rg_degree_in 	= this.getNumberOfRelations(	in_edges,"REALIZATION") + this.getNumberOfRelations(in_edges,"GENERALIZATION");
	        		
	        		agr_degree		= this.getNumberOfRelations(	out_edges,"AGGREGATION");
	        				        		
	        	}	        	
	        	
	                  	
	        
	        	if((agr_degree >= 1) && (rg_degree_in >= 1) && (rg_degree_out == 0)){  		        		        		
	        		   		 
		        		target_nodes.add(current_node);	
	        	}
	        	
	        	rg_degree_in	=0;
	        	agr_degree		=0;
	        	rg_degree_out	=0;

	        }
	        
		return target_nodes;
		
	}
	

	public void extraerAbstraccion(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getTargetNodes().iterator(); it.hasNext();){
				
			    int current_node = it.next();
			   
			    Collection<Integer> in_edges = this.g.getInEdges(current_node);
             	
			    int bandera=0;
			    
        		for(Iterator<Integer> iti_edges = in_edges.iterator(); iti_edges.hasNext();){
					
        			int i_edge = iti_edges.next();	
        									
        			String edge_type = edgeTable.get(i_edge).getEdgeType();
					
        			if((edge_type.equals("DEPENDENCY") == true) || (edge_type.equals("ASSOCIATION") == true)){
					     				
        				 Collection<Integer> out_edges = this.g.getOutEdges(this.g.getSource(i_edge));
        				 
        				 int rg_sal =  this.getNumberOfRelations(out_edges,"REALIZATION") + this.getNumberOfRelations(out_edges,"GENERALIZATION");
        				
        				 if(rg_sal > 0){
        				
        						 bandera++;
        						 
	        			 } 
	        		}     		
	        	}
		        		
        		if(bandera==0){	
        			if(!this.isAbstractNode(current_node)){
	      				
						 this.c_nodes_a.add(current_node);
						 
					 }
        		}
		}
	}

	public void extraerElementos(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getC_target_nodes_a().iterator(); it.hasNext();){
				
			    int current_node = it.next();
			
			    Collection<Integer> in_edges = this.g.getInEdges(current_node);
             	Collection<Integer> out_edges = this.g.getOutEdges(current_node);
              		        			
        		
        		for(Iterator<Integer> iti_edges = in_edges.iterator(); iti_edges.hasNext();){
					
        			int i_edge = iti_edges.next();					
						
        			String edge_type = edgeTable.get(i_edge).getEdgeType();
					
        			if((edge_type.equals("DEPENDENCY") == true) || (edge_type.equals("ASSOCIATION") == true)){
					
        				if(!this.isAbstractNode(this.g.getSource(i_edge)) && !this.isConcretImplemNode(this.g.getSource(i_edge)) && !this.isRefiAbstracNode(this.g.getSource(i_edge)) && !this.isImplemNode(this.g.getSource(i_edge))){
        						this.c_nodes_c.add(this.g.getSource(i_edge));
        				}
					
        			}else if((edge_type.equals("GENERALIZATION") == true) || (edge_type.equals("REALIZATION") == true)){
    					
        				if(!this.isAbstractNode(this.g.getSource(i_edge)) && !this.isConcretImplemNode(this.g.getSource(i_edge)) && !this.isClientNode(this.g.getSource(i_edge)) && !this.isImplemNode(this.g.getSource(i_edge))){
        					this.c_nodes_ra.add(this.g.getSource(i_edge));
        				}
        				
    				}	 
				}
        		
        		for(Iterator<Integer> ito_edges = out_edges.iterator(); ito_edges.hasNext();){
    				
    				int o_edge = ito_edges.next();
    				
    				String edge_type_i = edgeTable.get(o_edge).getEdgeType();
    									
    				if(edge_type_i.equals("AGGREGATION") == true ){
    						
    					if(!this.isAbstractNode(this.g.getDest(o_edge)) && !this.isRefiAbstracNode(this.g.getDest(o_edge)) && !this.isClientNode(this.g.getDest(o_edge)) && !this.isConcretImplemNode(this.g.getDest(o_edge))){
    						
    						this.c_nodes_i.add(this.g.getDest(o_edge));		
    					}
    				}				
    			}		
        		
        	}		
	} 	        		        
	
	public void extraerSubElementos(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getTargetNodesImplemetor().iterator(); it.hasNext();){
								
			int nodo_imp = it.next();
			
			Collection<Integer> in_edges_imp = this.g.getInEdges(nodo_imp);
			
			for(Iterator<Integer> it_edges_i = in_edges_imp.iterator(); it_edges_i.hasNext();){
				
				int i_edge_i = it_edges_i.next();
				
				String edge_type_i = edgeTable.get(i_edge_i).getEdgeType();
									
				if((edge_type_i.equals("REALIZATION") == true) || (edge_type_i.equals("GENERALIZATION") == true)){
						
					if(!this.isAbstractNode(this.g.getSource(i_edge_i)) && !this.isRefiAbstracNode(this.g.getSource(i_edge_i)) && !this.isClientNode(this.g.getSource(i_edge_i)) && !this.isImplemNode(this.g.getSource(i_edge_i))){
	
						this.c_nodes_ci.add(this.g.getSource(i_edge_i));		
					
					}
				}				
			}			
		}
		
		for(Iterator<Integer> it = this.getC_target_nodes_ra().iterator(); it.hasNext();){
			
			int nodo_ra = it.next();
			
			Collection<Integer> in_edges_imp = this.g.getInEdges(nodo_ra);
			
			for(Iterator<Integer> it_edges_ra = in_edges_imp.iterator(); it_edges_ra.hasNext();){
				
				int i_edge_ra = it_edges_ra.next();
				
				String edge_type_i = edgeTable.get(i_edge_ra).getEdgeType();
									
				if(edge_type_i.equals("DEPENDENCY") == true || edge_type_i.equals("ASSOCIATION") == true){
						
					if(!this.isAbstractNode(this.g.getSource(i_edge_ra)) && !this.isConcretImplemNode(this.g.getSource(i_edge_ra)) && !this.isRefiAbstracNode(this.g.getSource(i_edge_ra)) && !this.isImplemNode(this.g.getSource(i_edge_ra))){
						
						this.c_nodes_c.add(this.g.getSource(i_edge_ra));		
					
					}
				}	
			}			
		}
	}

	
	public void setTargetNodesImplemetor(Collection<Integer> collection){
		super.setNodes(c_nodes_i, collection);
	}
	
	public Collection<Integer> getTargetNodesImplemetor(){
		return this.c_nodes_i;
	}
	
	public void setTargetNodes(Collection<Integer> collection){
		super.setNodes(c_target_nodes, collection);
	}
	
	public Collection<Integer> getTargetNodes(){
		return this.c_target_nodes;
	}

	public Collection<Integer> getC_target_nodes_ci() {
		return c_nodes_ci;
	}

	public void setC_target_nodes_ci(Collection<Integer> c_target_nodes_ci) {
		this.c_nodes_ci = c_target_nodes_ci;
	}

	public Collection<Integer> getC_target_nodes_c() {
		return c_nodes_c;
	}

	public void setC_target_nodes_c(Collection<Integer> c_nodes_c) {
		this.c_nodes_c = c_nodes_c;
	}

	
	public void setC_target_nodes_ra(Collection<Integer> c_target_nodes_ra) {
		this.c_nodes_ra = c_target_nodes_ra;
	}
	
	public Collection<Integer> getC_target_nodes_ra() {
		return c_nodes_ra;
	}

	public Collection<Integer> getC_target_nodes_a() {
		return c_nodes_a;
	}
	
	public void setC_target_nodes_a(Collection<Integer> c_nodes_a) {
		this.c_nodes_a = c_nodes_a;
	}
	
	private boolean isAbstractNode(Integer node_searched){
		return this.isInCollection(this.c_nodes_a, node_searched);
	}
	
	private boolean isRefiAbstracNode(Integer node_searched){
		return this.isInCollection(this.c_nodes_ra, node_searched);
	}
	
	private boolean isClientNode(Integer node_searched){
		return this.isInCollection(this.c_nodes_c, node_searched);
	}
	
	private boolean isImplemNode(Integer node_searched){
		return this.isInCollection(this.c_nodes_i, node_searched);
	}
	
	private boolean isConcretImplemNode(Integer node_searched){
		return this.isInCollection(this.c_nodes_ci, node_searched);
	}
	
	public Collection<Integer> getAll_nodes() {
		return this.g.getVertices();
	}
}
