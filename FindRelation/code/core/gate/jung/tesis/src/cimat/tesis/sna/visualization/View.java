package cimat.tesis.sna.visualization;

//import Code.Run.Main;
import java.awt.BorderLayout;
import java.awt.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.io.general.GraphCreator;

import cimat.tesis.sna.patterns.general.EdgeData;
import cimat.tesis.sna.patterns.general.NodeData;

import edu.uci.ics.jung.algorithms.layout.SpringLayout;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;

import cimat.tesis.sna.patterns.facade.FacadeListener;

import cimat.tesis.sna.visualization.utils.NodeLabeller;
import cimat.tesis.sna.visualization.utils.VertexPainter;

public class View extends JApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultModalGraphMouse<NodeData,EdgeData> graphMouse;
	
	private JCheckBox check_facade = null;

	private JCheckBox check_composite =  null;
	
	private YSparseMultigraph g = null;
	
	public VisualizationViewer<Integer, Integer> vv = null;
	
	public static GraphCreator gc = null;
	
	public VertexPainter vp = null;
	
	public void changeColor(Collection<Integer> c_nodes, String s_node_type){		
		
		vv.getRenderContext().setVertexFillPaintTransformer(vp = new VertexPainter(Sistema.getCreator().getGraph().getNodeReverseTable()));
		
		if(c_nodes.size()>0){
			for(int node:c_nodes){
				if(s_node_type!=null){
					Sistema.getCreator().getGraph().getNodeReverseTable().get(node).setType(s_node_type);
				}
				
				vp.transform(node);				
			}
		}
    }
	
	public View() throws IOException, SQLException{
		
		this.g = Sistema.getCreator().getGraph();		

        vv = new VisualizationViewer<Integer,Integer>(new SpringLayout<Integer,Integer>(g));        
		
		graphMouse = new DefaultModalGraphMouse<NodeData,EdgeData>();
		
        JPanel jp_grafo = getGraphPanel();
        
        JPanel jp_control = getControlPanel();
        
        JFrame jf = new JFrame("Patterns Detection with SNA");
        
        jf.setLayout(new BorderLayout());
        
        jf.getContentPane().add(jp_grafo, BorderLayout.CENTER);
        jf.getContentPane().add(jp_control, BorderLayout.SOUTH);
        
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
	}
	
	private JPanel getControlPanel(){
		
		JPanel jp = new JPanel();
		
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));		
		
		jp.setBackground(Color.LIGHT_GRAY);
		
		check_facade = new JCheckBox("Facade");
		
		check_composite = new JCheckBox("Adapter");
	
		check_facade.addItemListener(new FacadeListener(this));	
		
		
		jp.add(graphMouse.getModeComboBox());
		jp.add(check_facade);
		jp.add(check_composite);
				
		jp.setSize(500, 500);
		
		return jp;
	}
	
	private JPanel getGraphPanel() throws IOException, SQLException
	{
		JPanel jp = new JPanel();
		
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		
		jp.setBackground(Color.WHITE);               
        
        vv = new VisualizationViewer<Integer,Integer>(new SpringLayout<Integer,Integer>(g));
               
        vv.getRenderContext().setVertexFillPaintTransformer(new PickableVertexPaintTransformer<Integer>(vv.getPickedVertexState(), Color.red, Color.yellow));
        
        vv.setGraphMouse(this.graphMouse);
        
        vv.getRenderContext().setVertexLabelTransformer(new NodeLabeller(Sistema.getCreator().getGraph().getNodeReverseTable()));
        
        vv.setBackground(Color.WHITE);
        
        jp.add(vv);
        
		return jp;
	}
	
	public void changeColorCollection(
			Collection<Integer> principal_nodes, Collection<Map<Integer, Collection<Integer>>> clients,
			String s_node_type) {
		
		vv.getRenderContext().setVertexFillPaintTransformer(vp = new VertexPainter(Sistema.getCreator().getGraph().getNodeReverseTable()));
		
		// TODO Auto-generated method stub
		for(Iterator<Integer> it = principal_nodes.iterator();it.hasNext();){
			
			int focal_node = it.next();
			
			for(Iterator<Map<Integer, Collection<Integer>>> it2 = clients.iterator(); it2.hasNext();){
				
				Map<Integer, Collection<Integer>> map_clients = it2.next();
				
				Collection<Integer> map_values = map_clients.get(focal_node);
				if(map_values != null){
					for(Iterator<Integer> it_values = map_values.iterator(); it_values.hasNext();){
						Integer node = it_values.next();
						if(s_node_type!=null){
							Sistema.getCreator().getGraph().getNodeReverseTable().get(node).setType(s_node_type);
						}
						
						vp.transform(node);
					}
				}
				
			}
		}
	}
	
	public VisualizationViewer<Integer, Integer> getVisualizationViewer(){
		return this.vv;
	}
	
	public static void main(String args[]) throws IOException, SQLException{
//		String a[] = new String[2];
//		a[0] = "/home/jmauricio/workspace/FindRelation/Configure.txt";
//		a[1] = "Relations_info.txt";
//		
//		long time_start, time_end;
//		time_start = System.currentTimeMillis();
//		Code.Run.Main.main(a);
//		time_end = System.currentTimeMillis();
//		System.out.println("Analysis Execution time: "+ ( time_end - time_start ) +" milliseconds");
		new View();
	}	
}
