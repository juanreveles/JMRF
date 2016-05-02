package cimat.tesis.sna.patterns.general;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import cimat.tesis.sna.graph.Sistema;
import edu.uci.ics.jung.graph.Graph;

/**
 * Clase que proporciona diversos métodos que ayudarán de manera
 * general a la b&uacute;squeda de diversos patrones del libro de Erich Gamma
 * @author ISC Juan Manuel Mauricio Zamarr&oacute;n
 * @version 0.1 
 * */

public abstract class Searcher {	
	
	protected boolean isInCollection(Collection<Integer> collection, Integer node_searched){
		boolean response = false;		
		response = collection.contains(node_searched);		
		return response;
	}
	
	static  public NodeData getVertexByName(Graph<NodeData,EdgeData> g, String name){
		NodeData mv = null;
		
		for(Iterator<NodeData> it = g.getVertices().iterator(); it.hasNext();){
			
			NodeData mv_local = it.next();
			
			if(mv_local.getName().equals(name)){
				
				mv = mv_local;				
				break;
			}
		}
				
		return mv;
	}
	
	
	protected boolean isInElementCollection(Collection<Map<Integer, Collection<Integer>>> collection, 
			Integer node_searched){
		boolean response = false;
		
		Map <Integer, Collection<Integer>> c_element = new HashMap<Integer, Collection<Integer>>();
				
		for(Iterator<Map<Integer, Collection<Integer>>> iterator = collection.iterator(); iterator.hasNext();){
			
			c_element.putAll(iterator.next());
			
			if(c_element.containsValue(node_searched) || c_element.containsKey(node_searched)){
				response = true;
				break;
			}
		}
		
		return response;
	}
	
	/**
	 * Establece Una coleción de nodos de tipo string a una propiedad
	 * @param property	Nombre de la collección de cadenas destino
	 * @param collection		Nombre de la collección de cadenas fuente
	 * */
	protected void setNodes(Collection<Integer> property, Collection<Integer> collection){
		
		if(!property.isEmpty()){			
			property.clear();
		}else{
			//No limpiamos nada
		}
		
		property.addAll(collection);
	}	
	
	/**
	 * Retorna el nombre del tipo de relación de una arista
	 * @param 	edge_name	Nombre de la arista
	 * @return				Nombre del tipo de relación de una arista  
	 * */
	
	/*protected String getTypeName(MyEdge edge_name){
		return edge_name.substring(0, edge_name.indexOf('.'));
	}*/
	
//	/** 
//	 * Retorna un ArrayList con el nombre de cada v&eacute;rtice y su grado
//	 * @param grafo		Contiene el conjuto de v&eacute;rtices y aristas
//	 * @param sorted	Bandera que si est&aacute; activa ordena cada v&eacute;rtice con su grado 
//	 * 					de manera ascendente
//	 * @return ArrayList de v&eacute;rtices y su grado
//	 * */
//	protected ArrayList<MyVertexDegree> getArrayVertexDegree(
//			Graph<NodeData, EdgeData> grafo, boolean sorted){
//		
//		Collection<NodeData> vertices = grafo.getVertices();
//		
//		ArrayList<MyVertexDegree> a_vertices = new ArrayList<MyVertexDegree>(); 
//		
//		for(Iterator<NodeData> sv_iterator = vertices.iterator();sv_iterator.hasNext();){
//        	
//        	NodeData s_vertex = sv_iterator.next();        	        
//        	int i_vertex_degree = grafo.degree(s_vertex);
//        	//System.out.println(i_vertex_degree);
//        	a_vertices.add(new MyVertexDegree(s_vertex, i_vertex_degree));
//        	
//        }
//		
//		DegreeComparator dc = new DegreeComparator();
//        
//		if(sorted == true)
//			Collections.sort(a_vertices,dc);
//        
//        return a_vertices;
//        
//	}
	
	
	protected int getNumberOfRelations(	Collection<Integer> 	in_edges, 
										String 					s_relation_name)
	{


		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();

		int relation_counter = 0;

		for(Iterator<Integer> iterator = in_edges.iterator(); iterator.hasNext();){
			int i_edge = iterator.next();

			String edge_type = edgeTable.get(i_edge).getEdgeType();
			
//			System.out.println(edge_type);

			if(edge_type.equals(s_relation_name) == true){
				relation_counter++;
			}else{
				//Do nothing
			}

		}

		return relation_counter;
	}
	
	
	/**
	 * Retorna el n&uacute;mero de relaciones de un nodo en especial
	 * @param	in_edges			Conjunto de ar&iacute;stas de un v&eacute;rtice
	 * @param	relation_names	Nombres de las relaciones que seran buscadas
	 * @return	El n&uacute;mero de aristas de relation_names contenidas en edges
	 * */
	protected int getNumberOfRelations(	Collection<Integer> 	in_edges, 
										Collection<String> 		c_relation_names)
	{
		
		
		Hashtable<Integer, EdgeData> edgeTable = Sistema.getCreator().getGraph().getEdgeTable();
		
		int relation_counter = 0;
				
		for(Iterator<Integer> iterator = in_edges.iterator(); iterator.hasNext();){
			int i_edge = iterator.next();
			
			String edge_type = edgeTable.get(i_edge).getEdgeType();
			
			if(c_relation_names.contains(edge_type) == true){
				relation_counter++;
			}else{
				//Do nothing
			}
			
		}
		
		return relation_counter;
	}
}
