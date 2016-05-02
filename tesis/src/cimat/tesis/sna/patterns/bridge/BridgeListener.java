package cimat.tesis.sna.patterns.bridge;

import java.awt.event.ItemEvent;
//import java.sql.SQLException;
import java.util.Collection;
import javax.swing.JComboBox;
//import javax.swing.JFrame;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.patterns.bridge.BridgeSearcher;
import cimat.tesis.sna.patterns.general.GenericListener;
import cimat.tesis.sna.visualization.View;

public class BridgeListener extends GenericListener{
	
	
	
	public BridgeListener(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
		
		
		
		if(e.getStateChange() == 1){
	
	     // JFrame ventanaM = new JFrame("Nodos del patron Bridge");; 
		  //ventanaM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  //ventanaM.setSize(290, 150);
			
		  JComboBox c= (JComboBox)e.getSource(); 
		  
		  if (c.getSelectedIndex() == 1) {
						
			BridgeSearcher bs = new BridgeSearcher();
			
			v.changeColor(bs.getAll_nodes(), "N");

			v.restartName(bs.getAll_nodes());

			long tiempoInicio = System.currentTimeMillis();
			
			System.out.println("Ejecutando analisis bridge...");
			
			System.out.println("----------------------------------------------------");
			
			bs.setTargetNodes(bs.extractTargetNodes());
			
			bs.extraerAbstraccion();
			
			if(bs.getC_target_nodes_a().size() > 0){
				
				bs.extraerElementos();
								
				bs.extraerSubElementos();
				
				long totalTiempo = System.currentTimeMillis() - tiempoInicio;
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Total_bridge:" +bs.getC_target_nodes_ci().size());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Analisis time: " + totalTiempo);
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of RefinedAbstraction: " + bs.getC_target_nodes_ra().size());
				
				System.out.println("RefinedAbstraction nodes: " + bs.getC_target_nodes_ra());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of Abstraction: " + bs.getC_target_nodes_a().size());
				
				System.out.println("Abstraction nodes: " + bs.getC_target_nodes_a());
				
				System.out.println("----------------------------------------------------");

				System.out.println("Number of Client: " + bs.getC_target_nodes_c().size());
				
				System.out.println("Client nodes: " +bs.getC_target_nodes_c() );
								
				System.out.println("----------------------------------------------------");

				System.out.println("Number of Implementor: " + bs.getTargetNodesImplemetor().size());
				
				System.out.println("Implementor nodes: " + bs.getTargetNodesImplemetor());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of ConcreteImplementor: " + bs.getC_target_nodes_ci().size());
				
				System.out.println("ConcreteImplementor nodes: " + bs.getC_target_nodes_ci());

				System.out.println("----------------------------------------------------");
				
				v.changeColor(bs.getC_target_nodes_ra(), "FRA");
				
				v.changeColor(bs.getC_target_nodes_a(), "FN");
				
				v.changeColor(bs.getTargetNodesImplemetor(), "FI");
				
				v.changeColor(bs.getC_target_nodes_c(), "FC");
				
			    v.changeColor(bs.getC_target_nodes_ci(), "FS");
			    
			    
			    v.changeName(bs.getC_target_nodes_ra(), "(Refined Abstraction)");
				
				v.changeName(bs.getC_target_nodes_a(), "(Abstraction)");
				
				v.changeName(bs.getTargetNodesImplemetor(), "(Implementor)");
				
				v.changeName(bs.getC_target_nodes_c(), "(Client)");
				
			    v.changeName(bs.getC_target_nodes_ci(), "(ConcreteImplementor)");		
				
				Collection<Integer> c_edge1 = Sistema.getCreator().getGraph().getEdges();
				
				v.changeColorEdge(c_edge1);
				
				//ventanaM.setVisible(true);  
					
			} else{
				System.out.println("Este sistem no implementa el patron Bridge!! ");
			}//fin if
		}
	  }
	}	 
}
