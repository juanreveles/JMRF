package cimat.tesis.sna.patterns.observer;



import java.awt.event.ItemEvent;
//import java.sql.SQLException;
import java.util.Collection;
import javax.swing.JComboBox;
//import javax.swing.JFrame;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.patterns.observer.ObserverSearcher;
import cimat.tesis.sna.patterns.general.GenericListener;
import cimat.tesis.sna.visualization.View;

public class ObserverListener extends GenericListener{
	
	
	public ObserverListener(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
		
		
		
		if(e.getStateChange() == 1){
		
		  JComboBox c= (JComboBox)e.getSource(); 
		  
		  if (c.getSelectedIndex() == 6) {
			
			ObserverSearcher os = new ObserverSearcher();
					
														
			long tiempoInicio = System.currentTimeMillis();
			
			System.out.println("Ejecutando analisis del patron Strategy...");
			
			System.out.println("----------------------------------------------------");	
			
			os.setC_target_nodes(os.extractTargetNodes());
			
			os.extraerConcretObserver();
			
			
			if( os.getC_nodes_co().size() > 0){
				
				
				if(os.getC_nodes_co().size() > 0){
					
					os.extraerElementos();
					os.extraerSubElementos();
						
				}
				
																				
				long totalTiempo = System.currentTimeMillis() - tiempoInicio;
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Total_Observer:" +(os.getC_nodes_ob().size()));
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Analisis time: " + totalTiempo);
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of Observer: " + (os.getC_nodes_ob().size()));
				
				System.out.println("Observer nodes: " + os.getC_nodes_ob());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of Subject: " +( os.getC_nodes_s().size()));
				
				System.out.println("Subject nodes: " + os.getC_nodes_s());
				
				System.out.println("----------------------------------------------------");

				System.out.println("Number of Client: " + ( os.getC_nodes_cl().size()));
				
				System.out.println("Client nodes: " +  os.getC_nodes_cl());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of ConcreteSubject: " +(os.getC_nodes_cs().size()));
				
				System.out.println("ConcreteSubject nodes: " + os.getC_nodes_cs());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of ConcreteObserver: " +( os.getC_nodes_co().size()));
				
				System.out.println("ConcreteObserver nodes: " +  os.getC_nodes_co());
				
				System.out.println("----------------------------------------------------");
			
				
			    v.changeColor(os.getC_nodes_ob(), "FRA");
				
				v.changeColor(os.getC_nodes_s(), "FN");
				
				v.changeColor(os.getC_nodes_co(), "FC");
				
			    v.changeColor(os.getC_nodes_cs(), "FS");
			    
			    v.changeColor(os.getC_nodes_cl(), "FI");
			    			    
			    v.changeName(os.getC_nodes_ob(), "(Observer)");
				
				v.changeName(os.getC_nodes_s(), "(Subject)");
				
				v.changeName(os.getC_nodes_co(), "(Concrete Observer)");
				
			    v.changeName(os.getC_nodes_cs(), "(Concrete Subject)");
			    
			    v.changeName(os.getC_nodes_cl(), "(Client)");
			    
			    Collection<Integer> c_edge1 = Sistema.getCreator().getGraph().getEdges();
				
			    v.changeColorEdge(c_edge1);
				
			} else{
			
				System.out.println("Este sistem no implementa el patron Observer!! ");
			
			}
		
		}
	  }
	}	 
}
