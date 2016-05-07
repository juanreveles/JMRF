package cimat.tesis.sna.patterns.adapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.patterns.general.EdgeData;
import cimat.tesis.sna.patterns.general.Searcher;

public class AdapterSearcher extends Searcher{
	
	private Collection<Integer> c_target_nodes 	= null;
	private Collection<Map<Integer, Collection<Integer>>> c_clients 		= 	null;
	private Collection<Map<Integer, Collection<Integer>>> c_adapters 		= 	null;
	
	private YSparseMultigraph 	g 				= null;
	
	
	public Collection<Map<Integer, Collection<Integer>>> getClients()
	{
		return this.c_clients;
	}
	
	public Collection<Map<Integer, Collection<Integer>>> getAdapters()
	{
		return this.c_adapters;
	}
	
	public void setTargetNodes(Collection<Integer> collection){
		super.setNodes(c_target_nodes, collection);
	}
	
	public Collection<Integer> getTargetNodes(){
		return this.c_target_nodes;
	}
	
	public AdapterSearcher(){
		this.c_target_nodes = new HashSet<Integer>();
		
		this.c_clients = new HashSet<Map<Integer, Collection<Integer>>>();
		this.c_adapters = new HashSet<Map<Integer, Collection<Integer>>>();
			
		this.g = Sistema.getCreator().getGraph();
	}
	
	public Collection<Integer> extractTargetNodes(){
		
		 Collection<Integer> target_nodes = new HashSet<Integer>();
	        
	        int assoc_degree 	= 	0;
	        int real_degree 	=	0;
	        //int out_degree		=	0;
	        
	        for(Iterator<Integer> it = this.g.getVertices().iterator(); it.hasNext();){
	        	
	        	int current_node = it.next();
	        	        	
	        	Collection<Integer> in_edges = this.g.getInEdges(current_node);
	        	
	        	if(in_edges.size()>0){	        		
	        		
	        		
	        		assoc_degree 	= this.getNumberOfRelations(	in_edges	, 	"AGGREGATION");
	        		
	        		real_degree 	= this.getNumberOfRelations(	in_edges	, 	"REALIZATION");
	        		
	        	}	        	
	        	
	        	
	        	if((assoc_degree > 0)	&& (real_degree > 0)){	        		        		
	        		target_nodes.add(current_node);	        		
	        	}	        		        
	        	assoc_degree	= 0;
	        	real_degree		= 0;
	        	//out_degree 		= 0;
	        }
		
		
		return target_nodes;
	}
	
	public void extractClientsAndAdapters(){
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		for(Iterator<Integer> it = this.getTargetNodes().iterator(); it.hasNext();){
			
			Map<Integer, Collection<Integer>> map_clients = new HashMap<Integer, Collection<Integer>>();
			Map<Integer, Collection<Integer>> map_adapters = new HashMap<Integer, Collection<Integer>>();
			
			Collection<Integer> clients = new HashSet<Integer>();
			Collection<Integer> adapters = new HashSet<Integer>();
			
			clients.clear();
			
			int current_node = it.next();
			
			Collection<Integer> in_edges = this.g.getInEdges(current_node);
			
			for(Iterator<Integer> it_edges = in_edges.iterator(); it_edges.hasNext();){
				int i_edge = it_edges.next();
				
				String edge_type = edgeTable.get(i_edge).getEdgeType();
//				System.out.println(edge_type);
				if(edge_type.equals("AGGREGATION") == true){
					
					
					clients.add(i_edge);
				}else{
					
					if(edge_type.equals("REALIZATION") == true){
						
						adapters.add(i_edge);
					}
				}				
			}
			
			if(clients.size() > 0 ){
				
				map_clients.put(current_node, clients);
				
				this.c_clients.add(map_clients);				
			}
			
			if(adapters.size() > 0){
				
				map_adapters.put(current_node, adapters);
				
				this.c_adapters.add(map_adapters);
			}
		}		
	}
}
