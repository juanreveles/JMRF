package cimat.tesis.sna.visualization.utils;


import org.apache.commons.collections15.Transformer;

import cimat.tesis.sna.patterns.general.NodeData;

import java.awt.Color;
import java.awt.Paint;
import java.util.Hashtable;

public class VertexPainter implements Transformer<Integer, Paint>
{
    
	public final Color[] similar_colors =
    	{
    		new Color(216, 134, 134), //Clients of Facade
    		new Color(135, 137, 211),
    		new Color(134, 206, 189),
    		new Color(206, 176, 134),
    		new Color(194, 204, 134),
    		new Color(145, 214, 134),
    		new Color(133, 178, 209),
    		new Color(103, 148, 255),
    		new Color(60, 220, 220),
    		new Color(30, 250, 100)
    		
    	};
	
	private Hashtable<Integer, NodeData> node_data_table;

    public VertexPainter(Hashtable<Integer,NodeData> nodeDataTable)
    {
        node_data_table = nodeDataTable;
    }

	@Override
	public Paint transform(Integer v) {
		// TODO Auto-generated method stub
		String node_type = node_data_table.get(v).getType();
		if(node_data_table.containsKey(v) == true){
			
			if(node_type != null){
				
				if(node_type.equals("FN") == true){
					return similar_colors[0];
				}else{
					
					if(node_type.equals("FC") == true){
						return similar_colors[1];
					}else{
						
						if(node_type.equals("FS") == true){
							return similar_colors[2];
						}else{
							//return Color.red;
							if(node_type.equals("AT") == true){
								return Color.BLACK;
							}else{
								//return Color.red;
								if(node_type.equals("AC") == true){
									return Color.BLUE;
								}else{
									//return Color.red;
									if(node_type.equals("AA") == true){
										return Color.CYAN;
									}else{
										return Color.red;
									}
								}
							}
						}
						
					}
					
				} 
			}else{
				return Color.red;
			}
		}else{
			return Color.BLUE;
		}
		
	}
}
