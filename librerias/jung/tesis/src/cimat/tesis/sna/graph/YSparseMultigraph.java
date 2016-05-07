package cimat.tesis.sna.graph;

import java.util.Hashtable;

import cimat.tesis.sna.patterns.general.EdgeData;
import cimat.tesis.sna.patterns.general.NodeData;

import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class YSparseMultigraph extends SparseMultigraph<Integer, Integer>{	
	
	private static final long serialVersionUID = 1L;	
	private Hashtable<String, NodeData> nodeTable = null;
	private Hashtable<Integer, NodeData> nodeReverseTable = null;
	private Hashtable<Integer, EdgeData> edgeTable = null;
	
	public YSparseMultigraph(){
		
		nodeTable = new Hashtable<String, NodeData>();
		
		setNodeReverseTable(new Hashtable<Integer, NodeData>());
		
		edgeTable = new Hashtable<Integer, EdgeData>();				
		
	}
	
	public Hashtable<String, NodeData> getNodeTable(){
		return this.nodeTable;
	}
	
	public Hashtable<Integer, EdgeData> getEdgeTable(){
		return this.edgeTable;
	}
	
	public boolean addVertex(NodeData node) {
		
		//nodeTable.put(node.getId(), node);
		nodeTable.put(node.getName().toUpperCase(), node);
		nodeReverseTable.put(node.getId(), node);
		return super.addVertex(node.getId());
	}
	
	public boolean addEdge(EdgeData ed) {
		edgeTable.put(ed.getId(), ed);
		return super.addEdge(ed.getId(), ed.getSource(), ed.getTarget(), EdgeType.DIRECTED);
	}
	
	public Hashtable<Integer, NodeData> getNodeReverseTable() {
		return nodeReverseTable;
	}

	public void setNodeReverseTable(Hashtable<Integer, NodeData> nodeReverseTable) {
		this.nodeReverseTable = nodeReverseTable;
	}
	
}