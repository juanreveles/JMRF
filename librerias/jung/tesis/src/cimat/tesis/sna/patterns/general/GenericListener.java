package cimat.tesis.sna.patterns.general;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.visualization.View;

public class GenericListener implements ItemListener{

	protected Collection<Integer> 		c_vertices 	= null;	
	protected View v = null;
	protected YSparseMultigraph ys = Sistema.getCreator().getGraph();
	
	public GenericListener(View v){
		this.v = v;
		this.c_vertices = new HashSet<Integer>();	
	}	
	
	public void addMapToVertices(Collection<Integer> principal_nodes, Collection<Map<Integer, Collection<Integer>>> c){
		
		for(Iterator<Integer> i = principal_nodes.iterator(); i.hasNext();){
			
			int focal_node = i.next();
			
			for(Iterator<Map<Integer, Collection<Integer>>> it = c.iterator(); it.hasNext();){
				Map<Integer, Collection<Integer>> map_clients = it.next();
				
				Collection<Integer> map_values = map_clients.get(focal_node);
				
				
				if(map_values != null){
					this.c_vertices.addAll(map_values);
				}
			}
		}		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}
