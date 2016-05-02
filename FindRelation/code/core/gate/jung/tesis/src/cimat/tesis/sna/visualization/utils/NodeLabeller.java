package cimat.tesis.sna.visualization.utils;

import java.util.Hashtable;

import org.apache.commons.collections15.Transformer;

import cimat.tesis.sna.patterns.general.NodeData;

public class NodeLabeller implements Transformer<Integer,String>{
	private Hashtable<Integer, NodeData> node_data_table;
	
	public NodeLabeller(Hashtable<Integer,NodeData> nodeDataTable){
		this.node_data_table = nodeDataTable;
	}

	@Override
	public String transform(Integer input) {
		// TODO Auto-generated method stub
		return this.node_data_table.get(input).getName();
	}
}
