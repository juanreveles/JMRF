package cimat.tesis.sna.patterns.composite;

import java.awt.event.ItemEvent;

import cimat.tesis.sna.patterns.general.GenericListener;
import cimat.tesis.sna.visualization.View;

public class CompositeListener extends GenericListener{

	public CompositeListener(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		long tiempoInicio = System.currentTimeMillis();
		
		if(e.getStateChange() == 1){
			System.out.println("Ejecutndo composite");
			ComposteSearcher cs = new ComposteSearcher();
			
			System.out.println(cs.extractTargetNodes());
			
			long totalTiempo = System.currentTimeMillis() - tiempoInicio;			
			
			System.out.println("Analisis time: " + totalTiempo);
		}
	}
}
