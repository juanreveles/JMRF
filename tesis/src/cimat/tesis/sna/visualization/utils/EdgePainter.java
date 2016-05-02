package cimat.tesis.sna.visualization.utils;

import org.apache.commons.collections15.Transformer;



import cimat.tesis.sna.patterns.general.EdgeData;
import java.awt.Color;
import java.awt.Paint;
import java.util.Hashtable;
public class EdgePainter implements Transformer<Integer, Paint>
{
	private Hashtable<Integer, EdgeData> Edge_Table;
	private Color color;


	public EdgePainter(Hashtable<Integer, EdgeData> edgeTable)
	{
		Edge_Table = edgeTable;
	}


	public Paint transform(Integer v) {
		// TODO Auto-generated method stub
		this.color = Color.black;
		String edge_type = Edge_Table.get(v).getEdgeType();
		if(Edge_Table.containsKey(v) == true){
			this.color = Color.black;
			if(edge_type != null){
				if (edge_type.equals("ASSOCIATION")){
					return this.color = Color.green;
				} else {
					if (edge_type.equals( "REALIZATION")){
						this.color = Color.BLUE;
					} else {
						if (edge_type.equals("COMPOSITION")){
							this.color = Color.PINK;
						} else {
							if (edge_type.equals("GENERALIZATION")){
								color = Color.red;
							} else {
								if (edge_type.equals("DEPENDENCY")){
									color = Color.yellow;
								} 
								if (edge_type.equals("AGGREGATION")){
									color = Color.cyan;
								} }
						}
					}
				}
			}
		} return this.color;
	}
}



