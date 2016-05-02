package cimat.tesis.sna.patterns.facade;

import java.sql.SQLException;

import java.util.Collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Map;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;

import cimat.tesis.sna.patterns.general.Searcher;

//import java.util.List;
//import cimat.tesis.sna.patterns.utils.MyVertexDegree;

/**
 * Clase encargada de buscar una fachada en un grafo.
 * Las relaciones de entrada de un nodo fachada deben ser: 
 * 	1.	Direccionales, apuntando hacia el nodo fachada
 * 	2.	De tipo asociación, realización o herencia
 * 	Nota:	Pueden tener relaciones de realización o herencia
 * 			debido a que forzosamente éste tipo de nodo debe ser concreto
 * 			para que pueda ser instanciada y pueda enviar llamadas
 * 			a las clases del subsistema.
 * 
 * Las relaciones de salida de un nodo fachada deben ser:
 * 	1.	Direccionales, del nodo fachada hacia afuera
 * 	2.	De tipo asociación
 * 
 * @author ISC Juan Manuel Mauricio Zamarr&oacute;n
 * @version 0.1
 * */

public class FacadeSearcher extends Searcher{	
	
	private YSparseMultigraph 								g 				= 	null;
	private Collection<Integer> 							c_facade_nodes	=	null;
	private Collection<Map<Integer, Collection<Integer>>> c_clients 		= 	null;
	private Collection<Map<Integer, Collection<Integer>>> c_subsystem 	=	null;
	private Collection<Integer>							c_aux_subsystem	= 	null;	
	
	/**
	 * Este constructor instanc&iacute;a: 	this.c_facade_nodes 
	 * 										this.c_facade_clients
	 * @throws SQLException 
	 * */
	public FacadeSearcher(YSparseMultigraph ySparseMultigraph) throws SQLException{
		this.c_facade_nodes 	= new HashSet<Integer>();
		this.c_clients 			= new HashSet<Map<Integer, Collection<Integer>>>();
		this.c_subsystem 		= new HashSet<Map<Integer, Collection<Integer>>>();
		this.c_aux_subsystem 	= new HashSet<Integer>();	
		
		this.g = Sistema.getCreator().getGraph();
	}
	
	/**
	 * @return 	Conjunto de un conjunto nombres de nodos que fueron detectados
	 * 			como nodos cliente. 
	 * */
	public Collection<Map<Integer, Collection<Integer>>> getClients(){
		return this.c_clients;
	}
	
	/**
	 * Llena la propiedad c_facade_clients
	 * @param c_clients	Conjunto de clientes de los nodos facade
	 * */
	public void setClients(Collection<Map<Integer, Collection<Integer>>> c_clients){
				
		if(!this.c_clients.isEmpty()){			
			this.c_clients.clear();
		}else{
			//No limpiamos nada
		}
			
		this.c_clients.addAll(c_clients);
	}
	
	/**
	 * Llena la propiedad c_facade_nodes
	 * @param collection	Conjunto de nodos facade
	 * */	
	public void setFacadeNodes(Collection<Integer> collection){		
		super.setNodes(this.c_facade_nodes, collection);
	}
	
	/**
	 * @return Retorna un conjunto de nodos de tipo fachada
	 * */
	public Collection<Integer> getFacadeNodes(){
		return this.c_facade_nodes;
	}
	
	
	/**
	 * Extrae los clientes de los nodos facade
	 * @param grafo	El grafo del cual se obtendr&aacute;n los nodos que son fachadas
	 * */
	public Collection<Map<Integer, Collection<Integer>>> extractClients(YSparseMultigraph grafo){
		Collection<Map<Integer, Collection<Integer>>> facade_clients = new HashSet<Map<Integer, Collection<Integer>>>();
		Collection<Integer> facade_nodes = this.getFacadeNodes();
		
		for(Iterator<Integer> iterator = facade_nodes.iterator();iterator.hasNext();){
			int facade_node = iterator.next();
			
			Map<Integer, Collection<Integer>> map_clients = new HashMap<Integer, Collection<Integer>>();
			Collection<Integer> clients = new HashSet<Integer>();
			clients.addAll(grafo.getPredecessors(facade_node));
			map_clients.put(facade_node, clients);
			
			facade_clients.add(map_clients);
		}
		
		return facade_clients;
	}
	
	
	/**
	 * @param grafo	El grafo del cual se obtendr&aacute;n los nodos que son fachadas
	 * @return Un conjunto de cadenas que fueron detectadas como nodos fachada.
	 * */
	public Collection<Integer> extractFacadeNodes(YSparseMultigraph grafo){
		        
        Collection<Integer> facade_nodes = new HashSet<Integer>();
        
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
//        		System.out.println(current_node);
        		facade_nodes.add(current_node);
        	}
        	
        	in_degree	= 0;
        	out_degree	= 0;
        }
        
		return facade_nodes;
	}
	
	private boolean isInAuxSubsystem(Integer node_searched){
		return this.isInCollection(this.c_aux_subsystem, node_searched);
	}
	
	private boolean isClientNode(Integer node_searched){
		return this.isInElementCollection(this.c_clients, node_searched);
	}
	
	private boolean isFacadeNode(Integer node_searched){
		return this.isInCollection(this.c_facade_nodes, node_searched);
	}
	
	private Collection<Integer> getRecursiveSubsystem(Integer node, YSparseMultigraph grafo){
		Collection<Integer> sucesores = new HashSet<Integer>(); 
		
		sucesores.addAll(grafo.getSuccessors(node));
		
		if(sucesores.size()>0){			
			for(Iterator<Integer> iterator = sucesores.iterator(); iterator.hasNext();){
								
				Integer current = iterator.next();
				
				if(!this.isInAuxSubsystem(current)){
					if(!this.isClientNode(current) &&
					   !this.isFacadeNode(current)
					){
						
						this.c_aux_subsystem.add(current);
						this.getRecursiveSubsystem(current, grafo);
					}else{
						
					}
				}else{
										
				}
			}
		}
		return this.c_aux_subsystem;		
	}
	
	public Collection<Map<Integer, Collection<Integer>>> extractSubSystem(YSparseMultigraph grafo){
		Collection<Map<Integer, Collection<Integer>>> subsystem = new HashSet<Map<Integer, Collection<Integer>>>();
		Collection <Integer> facade_nodes = this.getFacadeNodes(); 
		
		for(Iterator<Integer> iterator = facade_nodes.iterator(); iterator.hasNext();){
			
			this.c_aux_subsystem.clear();
			Integer facade_node = iterator.next();
			Map<Integer, Collection<Integer>> map_subsystem = new HashMap<Integer, Collection<Integer>>();
			Collection<Integer> subsystem_nodes = new HashSet<Integer>();
			subsystem_nodes.addAll(this.getRecursiveSubsystem(facade_node, grafo));
			map_subsystem.put(facade_node, subsystem_nodes);
			subsystem.add(map_subsystem);			
		}		
		return subsystem;
	}
	
	public void setSubsystem(Collection<Map<Integer, Collection<Integer>>> c_subsystem){
		this.c_subsystem.addAll(c_subsystem);
	}
	
	public Collection<Map<Integer, Collection<Integer>>> getSubsystem(){
		return this.c_subsystem;
	}
	
	
	protected void setGraph(YSparseMultigraph g){
		this.g = g;
	}	
}
