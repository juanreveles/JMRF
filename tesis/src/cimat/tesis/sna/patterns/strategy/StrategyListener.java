package cimat.tesis.sna.patterns.strategy;

import java.awt.event.ItemEvent;
//import java.sql.SQLException;
import java.util.Collection;
import javax.swing.JComboBox;
//import javax.swing.JFrame;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.patterns.strategy.StrategySearcher;
import cimat.tesis.sna.patterns.strategy.StrategySearcherV2;
import cimat.tesis.sna.patterns.general.GenericListener;
import cimat.tesis.sna.visualization.View;

public class StrategyListener extends GenericListener{
	
	
	public StrategyListener(View v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
		
		
		
		if(e.getStateChange() == 1){
	
		  StrategySearcher bs = new StrategySearcher();
			
		  v.changeColor(bs.getAll_nodes(), "N");
			
		  v.restartName(bs.getAll_nodes());	
			
		  JComboBox c= (JComboBox)e.getSource(); 
		  
		  if (c.getSelectedIndex() == 5) {
			
			StrategySearcherV2 bs2 = new StrategySearcherV2();
			  
			StrategySearcherV3 bs3 = new StrategySearcherV3();
														
			long tiempoInicio = System.currentTimeMillis();
			
			System.out.println("Ejecutando analisis del patron Strategy...");
			
			System.out.println("----------------------------------------------------");	
			
			bs3.setC_target_nodes(bs3.extractTargetNodes());
			
			bs3.extraerStrategy();
			
			bs2.setC_target_nodes(bs2.extractTargetNodes());
			
			bs2.extraerStrategy();
			
			bs.setC_target_nodes(bs.extractTargetNodes());
			
			bs.extraerStrategy();
			
			
						
			if(bs.getC_nodes_s().size() > 0 || bs2.getC_nodes_s().size() > 0 || bs3.getC_nodes_s().size() > 0){
				
				if(bs.getC_nodes_s().size()> 0){
					
					bs.extraerElementos();
					
					bs.extraerSubElementos();
					
				}
				
				if(bs2.getC_nodes_s().size() > 0){
					
					bs2.extraerElementos();
					
					bs2.extraerSubElementos();
					
				}
				
				if(bs3.getC_nodes_s().size() > 0){
					
					bs3.extraerElementos();
					
					bs3.extraerSubElementos();
					
				} 
				
				long totalTiempo = System.currentTimeMillis() - tiempoInicio;
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Total_Strategy:" +(bs.getC_nodes_cs().size()+ bs2.getC_nodes_cs().size() + bs3.getC_nodes_cs().size()));
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Analisis time: " + totalTiempo);
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of Context: " + (bs.getC_nodes_co().size()+bs2.getC_nodes_co().size()+bs3.getC_nodes_co().size()));
				
				System.out.println("Context nodes: " + bs.getC_nodes_co() + bs2.getC_nodes_co() + bs3.getC_nodes_co());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of Strategy: " +( bs.getC_nodes_s().size() + bs2.getC_nodes_s().size() + bs3.getC_nodes_s().size()));
				
				System.out.println("Strategy nodes: " + bs.getC_nodes_s()+ bs2.getC_nodes_s()+ bs3.getC_nodes_s());
				
				System.out.println("----------------------------------------------------");

				System.out.println("Number of Client: " + ( bs.getC_nodes_cl().size() + bs2.getC_nodes_cl().size() + bs3.getC_nodes_cl().size()));
				
				System.out.println("Client nodes: " +bs.getC_nodes_cl() +bs2.getC_nodes_cl()+bs3.getC_nodes_cl());
				
				System.out.println("----------------------------------------------------");
				
				System.out.println("Number of ConcreteStrategy: " +( bs.getC_nodes_cs().size() + bs2.getC_nodes_cs().size() + bs3.getC_nodes_cs().size()));
				
				System.out.println("ConcreteStrategy nodes: " + bs.getC_nodes_cs()+ bs2.getC_nodes_cs()+ bs3.getC_nodes_cs());
				
				System.out.println("----------------------------------------------------");
				
			
				v.changeColor(bs.getC_nodes_s(), "FRA");
				
				v.changeColor(bs.getC_nodes_co(), "FN");
				
				v.changeColor(bs.getC_nodes_cl(), "FC");
				
			    v.changeColor(bs.getC_nodes_cs(), "FS");
			    			    
			    v.changeName(bs.getC_nodes_s(), "(Strategy)");
				
				v.changeName(bs.getC_nodes_co(), "(Context)");
				
				v.changeName(bs.getC_nodes_cl(), "(Client)");
				
			    v.changeName(bs.getC_nodes_cs(), "(Concrete Strategy)");
			    
			    v.changeColor(bs2.getC_nodes_cl(), "FC");
				
			    v.changeColor(bs2.getC_nodes_co(), "FN");
			    
			    v.changeColor(bs2.getC_nodes_s(), "FS");
			    
			    v.changeColor(bs2.getC_nodes_cs(), "FRA");
				
				v.changeName(bs2.getC_nodes_cl(), "(Client)");
				
				v.changeName(bs2.getC_nodes_cs(), "(Concrete Strategy)");
				
				v.changeName(bs2.getC_nodes_s(), "(Strategy)");
				
				v.changeName(bs2.getC_nodes_co(), "(Context)");
				
				v.changeColor(bs3.getC_nodes_cl(), "FC");
				
			    v.changeColor(bs3.getC_nodes_co(), "FN");
			    
			    v.changeColor(bs3.getC_nodes_s(), "FS");
			    
			    v.changeColor(bs3.getC_nodes_cs(), "FRA");
							
				v.changeName(bs3.getC_nodes_cl(), "(Client)");
				
				v.changeName(bs3.getC_nodes_cs(), "(Concrete Strategy)");
				
				v.changeName(bs3.getC_nodes_s(), "(Strategy)");
				
				v.changeName(bs3.getC_nodes_co(), "(Context)");
				
			    	
			    Collection<Integer> c_edge1 = Sistema.getCreator().getGraph().getEdges();
				
			    v.changeColorEdge(c_edge1);
				
			} else{
			
				System.out.println("Este sistem no implementa el patron Strategy!! ");
			
			}
			
		} else if(c.getSelectedIndex() == 0){
			
			StrategySearcherV2 bs2 = new StrategySearcherV2();
			
			v.changeColor(bs2.getAll_nodes(), "N");
			
			v.restartName(bs2.getAll_nodes());
			
			Collection<Integer> c_edge1 = Sistema.getCreator().getGraph().getEdges();
			
		    v.changeColorEdge(c_edge1);
				
		}
	  }
	}	 
}
