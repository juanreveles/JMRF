package cimat.tesis.sna.patterns.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.patterns.general.Searcher;
import cimat.tesis.sna.patterns.utils.MyVertexDegree;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class AdapterSearcher extends Searcher{
	private Collection<Integer> c_adapter_nodes;
	private Collection<Map<Integer,Collection<Integer>>> c_targets;
	private Collection<Map<Integer,Collection<Integer>>> c_clients;
	
	protected void setClients(Collection<Map<Integer,Collection<Integer>>> clients){
		this.c_clients.addAll(clients);
	}
	
	protected Collection<Map<Integer,Collection<Integer>>> getClientes(){
		return this.c_clients;
	}
	
	protected void setTargets(Collection<Map<Integer,Collection<Integer>>> targets){
		this.c_targets.addAll(targets);
	}
	
	protected Collection<Map<Integer,Collection<Integer>>> getTargets(){
		return this.c_targets;
	}
	
	public AdapterSearcher(){
		this.c_adapter_nodes = new HashSet<Integer>();
		this.c_targets = new HashSet<Map<Integer,Collection<Integer>>>();
		this.c_clients = new HashSet<Map<Integer,Collection<Integer>>>();
	}
	
	protected void setAdapterNodes(Collection<Integer> adapter_nodes){
		this.c_adapter_nodes.addAll(adapter_nodes);
	}
	
	protected Collection<Integer> getAdapterNodes(){
		return this.c_adapter_nodes;
	}
	
	public Collection<Integer> extractAdapterNodes(YSparseMultigraph grafo){
		
		 Collection<Integer> adapter_nodes = new HashSet<Integer>();
	        
	        int in_degree 	= 	0;
	        int out_degree 	=	0;
	        
	        for(Iterator<Integer> it = grafo.getVertices().iterator(); it.hasNext();){
	        	
	        	int current_node = it.next();
	        	        	
	        	Collection<Integer> in_edges = grafo.getInEdges(current_node);
	        	        	
	        	Collection<Integer> out_edges = grafo.getOutEdges(current_node);
	        	
	        	if((in_edges.size()>0) && (out_edges.size()>0)){
	        		
	        		Collection<String> c_relation_names = new HashSet<String>();
	        		
	        		c_relation_names.add("ASSOCIATION");
	        		c_relation_names.add("AGREGATION");
	        		c_relation_names.add("DEPENDENCY");
	        		
	        		
	        		in_degree 	= this.getNumberOfRelations(	in_edges	, 	c_relation_names);
	        		out_degree 	= this.getNumberOfRelations(	out_edges	, 	c_relation_names);        		
	        	}
	        	
	        	
	        	if( ((in_degree > 0)	&& (out_degree > 0)) && 
	        		((in_degree + out_degree) == 	(	grafo.getInEdges(	current_node).size() +
	        										grafo.getOutEdges(	current_node).size()	
	        									)
	        		)
	        	){
	        		adapter_nodes.add(current_node);
	        	}
	        	
	        	in_degree	= 0;
	        	out_degree	= 0;
	        }
		
		
		return adapter_nodes;
	}
	
	
	public Collection<Map<Integer, Collection<Integer>>> extractTargets(Graph<Integer, Integer> grafo){
		Collection<Map<Integer,Collection<Integer>>> targets = new HashSet<Map<Integer,Collection<Integer>>>();
		Collection<Integer> adapter_nodes = this.getAdapterNodes();
		
		for(Iterator<Integer> iterator = adapter_nodes.iterator();iterator.hasNext();){
			Integer facade_node = iterator.next();
			
			Map<Integer, Collection<Integer>> map_targets = new HashMap<Integer, Collection<Integer>>();
			Collection<Integer> cs_targets = new HashSet<Integer>();
			cs_targets.addAll(grafo.getPredecessors(facade_node));
			map_targets.put(facade_node, cs_targets);
			
			targets.add(map_targets);
		}
		
		return targets;
	}
	
	public Collection<Map<Integer, Collection<Integer>>> extractAdaptee(Graph<Integer, Integer> grafo){
		Collection<Map<Integer,Collection<Integer>>> adaptees = new HashSet<Map<Integer,Collection<Integer>>>();
		Collection<Integer> adapter_nodes = this.getAdapterNodes();
		
		for(Iterator<Integer> iterator = adapter_nodes.iterator();iterator.hasNext();){
			Integer facade_node = iterator.next();
			
			Map<Integer, Collection<Integer>> map_adaptees = new HashMap<Integer, Collection<Integer>>();
			Collection<Integer> cs_adaptees = new HashSet<Integer>();
			cs_adaptees.addAll(grafo.getSuccessors(facade_node));
			map_adaptees.put(facade_node, cs_adaptees);
			
			adaptees.add(map_adaptees);
		}
		
		return adaptees;
	}
	
	public Collection<Map<Integer, Collection<Integer>>> extractClients(Graph<Integer, Integer> grafo){
		Collection<Map<Integer,Collection<Integer>>> clients = new HashSet<Map<Integer,Collection<Integer>>>();
		
		Collection<Map<Integer,Collection<Integer>>> targets = this.getTargets();
				
		//System.out.println(targets);
		
		for(Iterator <Map<Integer,Collection<Integer>>> iterator = targets.iterator();iterator.hasNext();){		
			
			Map<Integer, Collection<Integer>> map_targets = new HashMap<Integer, Collection<Integer>>();
			map_targets.putAll(iterator.next());			
			
			for(Iterator <Collection<Integer>> it_targets = map_targets.values().iterator(); it_targets.hasNext();){
				Collection<Integer> _targets = it_targets.next();
				int n_predecessors = 0;
				
				for(Iterator<Integer> it_values = _targets.iterator(); it_values.hasNext();){
					Integer s_target = it_values.next();
					n_predecessors = grafo.getPredecessorCount(s_target);
					Collection <Integer> hs_predecesors = new HashSet<Integer>();
					hs_predecesors.addAll(grafo.getPredecessors(s_target));
//					int n_association = this.getNumberOfRelations(grafo.getInEdges(s_target), "ASSOCIATION");
					
//					if(n_predecessors == n_association){
//						//System.out.println("ok");
//						Map<Integer, Collection<Integer>> map_clients = new HashMap<Integer, Collection<Integer>>();
//						map_clients.put(s_target, hs_predecesors);
//						clients.add(map_clients);
//					}
				}
			}
			
			//targets.add(map_targets);
		}
		
		return clients;
	}
	
	public static void main(Integer []args){
		
	}

}
