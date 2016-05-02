package cimat.tesis.sna.patterns.adapter;

import java.awt.event.ItemEvent;

import javax.swing.JComboBox;

import cimat.tesis.sna.patterns.general.GenericListener;
import cimat.tesis.sna.visualization.View;

public class AdapterListener extends GenericListener{

	public AdapterListener(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
		long tiempoInicio = System.currentTimeMillis();
		
		if(e.getStateChange() == 1){
		
		  JComboBox c= (JComboBox)e.getSource(); 
		 
		  if (c.getSelectedIndex() == 3) {
		
			  System.out.println("Ejecutando analisis adapter...");
			
			AdapterSearcher as = new AdapterSearcher();
			
			as.setTargetNodes(as.extractTargetNodes());
			
			if(as.getTargetNodes().size() > 0){
		
				as.extractClientsAndAdapters();
				
				long totalTiempo = System.currentTimeMillis() - tiempoInicio;
				
				System.out.println("Analisis time: " + totalTiempo);
				
				System.out.println("Adapters nodes: " + as.getAdapters().toString());
				
				System.out.println("Number of adapters: " + as.getAdapters().size());
				
				System.out.println("Client nodes: " + as.getClients().toString());
				
				System.out.println("Target nodes: " + as.getTargetNodes());
			}//fin if
		}//fin if
	  }
	}
}
