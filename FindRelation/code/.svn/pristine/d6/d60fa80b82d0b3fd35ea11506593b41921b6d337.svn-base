package cimat.tesis.sna.patterns.facade;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.visualization.View;

public class FacadeListener implements ItemListener{
	
	
	private Collection<Integer> 		c_vertices 	= null;	
	private View v = null;
	private YSparseMultigraph ys = Sistema.getCreator().getGraph();
	
	public void delVertices(){
		Collection<Integer> c = new HashSet<Integer>();
		c.addAll(ys.getVertices());
		
		for(Iterator<Integer> it = c.iterator(); it.hasNext();){
			int v = it.next(); 
			if(!c_vertices.contains(v)){
				ys.removeVertex(v);
			}
		}
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
	
	public FacadeListener(View v){
		this.v = v;
		this.c_vertices = new HashSet<Integer>();
		
	}	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
		if(e.getStateChange() == 1){
			
			try {				
				FacadeSearcher searcher =  new FacadeSearcher(Sistema.getCreator().getGraph());
//				
				searcher.setFacadeNodes(searcher.extractFacadeNodes(Sistema.getCreator().getGraph()));
//			    
				if(searcher.getFacadeNodes().size() > 0){
					
					searcher.setClients(searcher.extractClients(Sistema.getCreator().getGraph()));
					searcher.setSubsystem(searcher.extractSubSystem(Sistema.getCreator().getGraph()));
					
					c_vertices.addAll(searcher.getFacadeNodes());
					this.addMapToVertices(searcher.getFacadeNodes(),searcher.getClients());
					this.addMapToVertices(searcher.getFacadeNodes(),searcher.getSubsystem());
					
					this.delVertices();				
					
					v.changeColor(searcher.getFacadeNodes(), "FN");
					
					v.changeColorCollection(searcher.getFacadeNodes(), searcher.getClients(), "FC");
					
					v.changeColorCollection(searcher.getFacadeNodes(), searcher.getSubsystem(), "FS");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
