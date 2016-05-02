package cimat.tesis.sna.patterns.facade;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JComboBox;
import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.patterns.general.GenericListener;
import cimat.tesis.sna.visualization.View;

public class FacadeListener extends GenericListener{
	
	public FacadeListener(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
		
		if(e.getStateChange() == 1){
			  
		 JComboBox c= (JComboBox)e.getSource(); 
			 
	     if (c.getSelectedIndex() == 2) {
		
		   long tiempoInicio = System.currentTimeMillis();
			
			try {				
				FacadeSearcher searcher =  new FacadeSearcher(Sistema.getCreator().getGraph());
//				
				 System.out.println("Ejecutando analisis facade...");
				 
				searcher.setFacadeNodes(searcher.extractFacadeNodes(Sistema.getCreator().getGraph()));
//			    
				if(searcher.getFacadeNodes().size() > 0){
					
					searcher.setClients(searcher.extractClients(Sistema.getCreator().getGraph()));
					searcher.setSubsystem(searcher.extractSubSystem(Sistema.getCreator().getGraph()));
					
					long totalTiempo = System.currentTimeMillis() - tiempoInicio;
					
					System.out.println("Total_fachadas:" + searcher.getFacadeNodes().size());
					System.out.println("Analisis time: " + totalTiempo);
					
					c_vertices.addAll(searcher.getFacadeNodes());
					this.addMapToVertices(searcher.getFacadeNodes(),searcher.getClients());
					this.addMapToVertices(searcher.getFacadeNodes(),searcher.getSubsystem());
					
					
					
					v.changeColor(searcher.getFacadeNodes(), "FN");
														
					v.changeColorCollection(searcher.getFacadeNodes(), searcher.getClients(), "FC");
					
					v.changeColorCollection(searcher.getFacadeNodes(), searcher.getSubsystem(), "FS");
					Collection<Integer> c_edge1 = Sistema.getCreator().getGraph().getEdges();
					v.changeColorEdge(c_edge1);
				
				}
				else{
					System.out.println("Este sistem no implementa el patron Facade!! ");
				}
	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		//add else 
		else{
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
					
					//System.out.println(searcher.getFacadeNodes());
					//System.out.println("Facade nodes counter: " + searcher.getFacadeNodes().size());
					//System.out.println(searcher.getClients());
					//System.out.println(searcher.getSubsystem());
					//this.delVertices();				
					
					v.changeColor(searcher.getFacadeNodes(), "N");
					
					v.changeColorCollection(searcher.getFacadeNodes(), searcher.getClients(), "C");
					
					v.changeColorCollection(searcher.getFacadeNodes(), searcher.getSubsystem(), "S");
				}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
   }
}
