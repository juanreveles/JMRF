package cimat.tesis.sna.patterns.strategy;

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


public class StrategySearcherV2 extends Searcher{
	
	private Collection<Integer> c_target_nodes 	= null;
	private Collection<Integer> c_nodes_s	= null;
	private Collection<Integer> c_nodes_cs 	= null;
	private Collection<Integer> c_nodes_cl 	= null;
	private Collection<Integer> c_nodes_co 	= null;
	private YSparseMultigraph 	g 			= null;
	
	
	
	public StrategySearcherV2(){
	
		this.c_target_nodes = new HashSet<Integer>();
		this.c_nodes_s = new HashSet<Integer>();
		this.c_nodes_cs= new HashSet<Integer>();
		this.c_nodes_cl= new HashSet<Integer>();
		this.c_nodes_co= new HashSet<Integer>();		
		this.g = Sistema.getCreator().getGraph();
	}
	
	public  Collection<Integer> extractTargetNodes(){
		
		 Collection<Integer> target_nodes = new HashSet<Integer>();
	        
	        
	        int rea_degree 	=	0;
	        
	        int gen_degree 	=	0;
	        
	        int agr_degree 	=	0;
	        
	        for(Iterator<Integer> it = this.g.getVertices().iterator(); it.hasNext();){
	        	
	        	int current_node = it.next();
	        	        	
	        	Collection<Integer> in_edges = this.g.getInEdges(current_node);
	        	        		        	
	           	if((in_edges.size() > 1)){	        		
	        			        		
	           		rea_degree 	= this.getNumberOfRelations(	in_edges,"REALIZATION");
	        		
	        		gen_degree 	= this.getNumberOfRelations(	in_edges,"GENERALIZATION");
	        		
	        		agr_degree		= this.getNumberOfRelations(	in_edges,"AGGREGATION");
	        	
	        			        				        		
	        	}	        	
	        	
	                  	
	        
	        	if((rea_degree > 1 || gen_degree > 1) && (agr_degree == 0)){  		        		        		
	        		   		 
		        		target_nodes.add(current_node);	
	        	}
	        	
	        	rea_degree	=0;
	        	
	        	agr_degree = 0;
	        	
	        	gen_degree=0;

	        }
		
	        
		return target_nodes;
		
	}
	

	public void extraerStrategy(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getC_target_nodes().iterator(); it.hasNext();){
				
			    int current_node = it.next();    
			    
			    Collection<Integer> nodes_cs	= new HashSet<Integer>();
			    
			    Collection<Integer> target_co	= new HashSet<Integer>();
			    
			    Collection<Integer> in_edges = this.g.getInEdges(current_node);
        		
			    for(Iterator<Integer> iti_edges = in_edges.iterator(); iti_edges.hasNext();){
					
        			int i_edge = iti_edges.next();					
						
        			String edge_type = edgeTable.get(i_edge).getEdgeType();
					
        			if((edge_type.equals("GENERALIZATION") == true) || (edge_type.equals("REALIZATION") == true)){
    					
        				if(this.isElement(this.g.getSource(i_edge))){
        					nodes_cs.add(this.g.getSource(i_edge));
        				}
    				}	 
				}
			    
			        				
				    int node_cs=nodes_cs.iterator().next();
	    			   	   			    
	    			Collection<Integer> in_edges_cs = this.g.getInEdges(node_cs);
	            		
	            	for(Iterator<Integer> iti_edges_cs = in_edges_cs.iterator(); iti_edges_cs.hasNext();){
	    				
	            		int i_edge_cs = iti_edges_cs.next();					
	    					
	            		String edge_type = edgeTable.get(i_edge_cs).getEdgeType();
	            			    					
	            		if(edge_type.equals("DEPENDENCY") == true || edge_type.equals("ASSOCIATION") == true){
	        
	            			int  nodo_target= this.g.getSource(i_edge_cs);
	            			
	            			Collection<Integer> out_edg_co = this.g.getOutEdges(nodo_target);
	            			
	            			int ass_sal_co 	=  this.getNumberOfRelations(	out_edg_co,"ASSOCIATION");
	            			
	            			int dep_cen_sal_co 	=  this.getNumberOfRelations(	out_edg_co,"DEPENDENCY");
	            			
	            			if((ass_sal_co >= nodes_cs.size())&& (dep_cen_sal_co >= nodes_cs.size()) && !(this.isInCollection(target_co, nodo_target))){
	            			
	            			//if(((ass_sal_co >= nodes_cs.size()) || (dep_cen_sal_co >= nodes_cs.size())) && !(this.isInCollection(target_co, nodo_target))){	            				
	            				
	            				if(this.isElement(nodo_target)){
	            					target_co.add(nodo_target);
	            				}
	            			} 
	        			}	 
	            	}	
	            	
			    int co_det=0;
			    
			    for(Iterator<Integer> itco = target_co.iterator(); itco.hasNext();){
    				
				    int node_tco=itco.next();
				    int considencias=0;
				    for(Iterator<Integer> itcs = nodes_cs.iterator(); itcs.hasNext();){
				    	int nodecs=itcs.next();
					    if(this.g.isSuccessor(node_tco, nodecs)){
					    	considencias++;
					    }
				   }
				    //if(considencias >= nodes_cs.size()){
				    if(considencias == nodes_cs.size()){
				    	co_det++;
				    	if(this.isElement(node_tco)){
				    		this.c_nodes_co.add(node_tco);
				    	}
				    }
			    }
			    
        		if (co_det > 0){
        			if(this.isElement(current_node)){
        				this.c_nodes_s.add(current_node);
        			}
        		}
        	}		
	}
	
	public void extraerElementos(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getC_nodes_s().iterator(); it.hasNext();){
				
			    int current_node = it.next();
			    
			    Collection<Integer> in_edges = this.g.getInEdges(current_node);
        		
        		for(Iterator<Integer> iti_edges = in_edges.iterator(); iti_edges.hasNext();){
					
        			int i_edge = iti_edges.next();					
						
        			String edge_type = edgeTable.get(i_edge).getEdgeType();
					
        			if((edge_type.equals("GENERALIZATION") == true) || (edge_type.equals("REALIZATION") == true)){
    					
        				if(this.isElement(this.g.getSource(i_edge))){
        					this.c_nodes_cs.add(this.g.getSource(i_edge));
        				}
        			        				
    				}	 
				}
        		
        	}		
	}

     	        		        
	
	public void extraerSubElementos(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.c_nodes_co.iterator(); it.hasNext();){
			
			int nodo_con = it.next();
			
			Collection<Integer> in_edges = this.g.getInEdges(nodo_con);
			
			for(Iterator<Integer> it_edges_i = in_edges.iterator(); it_edges_i.hasNext();){
				
				int i_edge_i = it_edges_i.next();
				
				String edge_type = edgeTable.get(i_edge_i).getEdgeType();
									
				if(edge_type.equals("GENERALIZATION") == true || edge_type.equals("REALIZATION") == true){
					
					if(this.isElement(this.g.getSource(i_edge_i))){
						this.c_nodes_co.add(this.g.getSource(i_edge_i));
					}
							

				}				
			}			
		}
		
		for(Iterator<Integer> it = this.c_nodes_co.iterator(); it.hasNext();){
								
			int nodo_con = it.next();
			
			Collection<Integer> in_edges = this.g.getInEdges(nodo_con);
			
			for(Iterator<Integer> it_edges_i = in_edges.iterator(); it_edges_i.hasNext();){
				
				int i_edge_i = it_edges_i.next();
				
				String edge_type = edgeTable.get(i_edge_i).getEdgeType();
									
				if(edge_type.equals("DEPENDENCY") == true || edge_type.equals("ASSOCIATION") == true){
						
					if(this.isElement(this.g.getSource(i_edge_i))){
						this.c_nodes_cl.add(this.g.getSource(i_edge_i));		
					
					}
				}				
			}			
		}
	}


	public Collection<Integer> getC_target_nodes() {
		return c_target_nodes;
	}

	public void setC_target_nodes(Collection<Integer> c_target_nodes) {
		this.c_target_nodes = c_target_nodes;
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

	public Collection<Integer> getC_nodes_co() {
		return c_nodes_co;
	}

	public void setC_nodes_co(Collection<Integer> c_nodes_co) {
		this.c_nodes_co = c_nodes_co;
	}
	
	public Collection<Integer> getAll_nodes() {
		return this.g.getVertices();
	}

	private boolean isElement(Integer node_searched){
		
		if(!this.isInCollection(this.c_nodes_s, node_searched) && !this.isInCollection(this.c_nodes_cs, node_searched) && !this.isInCollection(this.c_nodes_co, node_searched) && !this.isInCollection(this.c_nodes_cl, node_searched)){
			
			return true;
			
		}
				
		return false;
	}
	
}

