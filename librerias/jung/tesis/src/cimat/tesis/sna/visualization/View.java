package cimat.tesis.sna.visualization;

//import Code.Run.Main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.io.general.GraphCreator;

import cimat.tesis.sna.patterns.general.EdgeData;
import cimat.tesis.sna.patterns.general.NodeData;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout2;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;

import cimat.tesis.sna.patterns.adapter.AdapterListener;
import cimat.tesis.sna.patterns.composite.CompositeListener;
import cimat.tesis.sna.patterns.facade.FacadeListener;

import cimat.tesis.sna.visualization.utils.EdgePainter;
import cimat.tesis.sna.visualization.utils.NodeLabeller;
import cimat.tesis.sna.visualization.utils.VertexPainter;

public class View extends JApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private DefaultModalGraphMouse<NodeData,EdgeData> graphMouse;

	private JCheckBox check_facade = null;

	private JCheckBox check_adapter =  null;

	private JCheckBox check_composite =  null;

	private YSparseMultigraph g = null;

	public VisualizationViewer<Integer, Integer> vv = null;

	public static GraphCreator gc = null;

	public VertexPainter vp = null;

	JPanel cardPanel;


	//private Color color;
	public EdgePainter ep = null;

	int i = 1;
	int i2 = 1;

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

	//Method for Change Color Edge

	public void changeColorEdge(Collection<Integer> c_edge){		

		vv.getRenderContext().setEdgeDrawPaintTransformer(ep = new EdgePainter(Sistema.getCreator().getGraph().getEdgeTable()));
		vv.getRenderContext().setArrowFillPaintTransformer(ep = new EdgePainter(Sistema.getCreator().getGraph().getEdgeTable()));
		if(c_edge.size()>0){
			if(c_edge!= null){
				for(Iterator<Integer> it_values = c_edge.iterator(); it_values.hasNext();){
					Integer edge = it_values.next();
					Sistema.getCreator().getGraph().getEdgeTable().get(edge).getEdgeType();
					ep.transform(edge);	

				}
			}	
		}
	}
	JPanel panel2 = getControlPanel1();
	JFrame jf = new JFrame("Patterns Detection with SNA");

	public View() throws IOException, SQLException{

		this.g = Sistema.getCreator().getGraph();	


		vv = new VisualizationViewer<Integer,Integer>(new SpringLayout<Integer,Integer>(g));        

		graphMouse = new DefaultModalGraphMouse<NodeData,EdgeData>();

		JPanel jp_grafo = getGraphPanel();

		JPanel jp_control = getControlPanel();
		JMenuBar menubar = getControlJMenuBar();
		JPanel pv = getcontrolPanel2();
		pv.getSize();
		//  JPanel panel2 = getControlPanel1();

		//Cambiar por una FlowLayout
		jp_control.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
		jp_control.setBackground(Color.WHITE);


		jf.setLayout(new BorderLayout());

		jf.getContentPane().add(jp_grafo, BorderLayout.CENTER);
		jf.getContentPane().add(jp_control, BorderLayout.SOUTH);
		jf.getContentPane().add(menubar, BorderLayout.NORTH);
		jf.getContentPane().add(panel2, BorderLayout.EAST);
		jf.getContentPane().add(pv, BorderLayout.WEST);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}

	private JPanel getControlPanel(){

		JPanel jp = new JPanel();

		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));		

		jp.setBackground(Color.LIGHT_GRAY);

		check_facade = new JCheckBox("Facade");
		check_adapter = new JCheckBox("Adapter");
		check_composite = new JCheckBox("Composite");

		check_facade.addItemListener(new FacadeListener(this));	
		check_adapter.addItemListener(new AdapterListener(this));
		check_composite.addItemListener(new CompositeListener(this));


		jp.add(graphMouse.getModeComboBox());

		jp.add(check_facade);
		jp.add(check_adapter);
		jp.add(check_composite);
		//jp.setSize(500, 500);

		return jp;
	}





	//Panel Edge
	JPanel Principal;


	private JPanel getControlPanel1(){
		//TheMeaningEdgesAndArrows a = new TheMeaningEdgesAndArrows();
		Principal  = new JPanel();

		JPanel secondCard = new JPanel(new GridLayout(6,2));
		secondCard.setBorder(new TitledBorder("The meaning of the colors:"));
		ImageIcon asso = new ImageIcon("resources/Association.png");
		ImageIcon reali = new ImageIcon("resources/Realization.png");
		ImageIcon comp = new ImageIcon("resources/Composition.png");
		ImageIcon gene = new ImageIcon("resources/Generalization.png");
		ImageIcon depen = new ImageIcon("resources/Dependency.png");
		ImageIcon agg = new ImageIcon("resources/Aggregation.png");


		//Create Labels
		JLabel association = new JLabel(asso);
		JLabel association1 = new JLabel("ASSOCIATION      ");
		association1.setForeground(Color.green);


		JLabel realization = new JLabel(reali);
		JLabel realization1 = new JLabel("REALIZATION      ");
		realization1.setForeground(Color.blue);


		JLabel composition = new JLabel(comp);
		JLabel composition1 = new JLabel("COMPOSITION     ");
		composition1.setForeground(Color.pink);


		JLabel generalization = new JLabel(gene);
		JLabel generalization1 = new JLabel("GENERALIZATION");
		generalization1.setForeground(Color.red);


		JLabel dependency = new JLabel(depen);
		JLabel dependency1 = new JLabel("DEPENDENCY      ");
		dependency1.setForeground(Color.yellow);


		JLabel aggregation = new JLabel(agg);
		JLabel aggregation1 = new JLabel("AGGREGATION    ");
		aggregation1.setForeground(Color.cyan);

		secondCard.add(association);
		secondCard.add(association1);
		secondCard.add(realization);
		secondCard.add(realization1);
		secondCard.add(composition);
		secondCard.add(composition1);
		secondCard.add(generalization);
		secondCard.add(generalization1);
		secondCard.add(dependency);
		secondCard.add(dependency1);
		secondCard.add(aggregation);
		secondCard.add(aggregation1);
		secondCard.setBackground(Color.WHITE);

		Principal.add(secondCard);
		Principal.setBackground(Color.WHITE);
		Principal.setVisible(false);

		return Principal;

	}

	private JMenuBar getControlJMenuBar(){
		JMenuBar menuBar=new JMenuBar();

		/*NOMBRE DE LOS MENUS*/
		// establecer menú Archivo y sus elementos de menú

		JMenu menu2=new JMenu("Actions"); menu2.setMnemonic('e');
		/*ELEMENTOS DEL MENU1*/
		JMenuItem menuItem1= new JMenuItem("Pattern analyzer");
		//elementoAcerca.setMnemonic('c');
		JMenuItem menuItem2= new JMenuItem("Layout configuration");
		JMenuItem menuItem3= new JMenuItem("Minings");
		JMenuItem menuItem4= new JMenuItem("Exit");
		//SE AGREGAN AL menu1
		menu2.add(menuItem1);
		menuItem1.addActionListener(new ActionListener(){
			// mostrar cuadro de diálogo de mensaje cuando se seleccione Acerca de...
			public void actionPerformed( ActionEvent evento ){

			}
		});
		menu2.add(menuItem2);
		menuItem2.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent evento ){
				if ( i2 == 1){
					pv.setVisible(true);

					i2++;
				} else {pv.setVisible(false);
				i2--; }

			}
		});
		menu2.add(menuItem3);
		menuItem3.addActionListener(new ActionListener(){



			public void actionPerformed( ActionEvent evento ){

				if ( i == 1){
					Principal.setVisible(true);
					// cards.next(cardPanel);
					i++;
				} else {Principal.setVisible(false);
				// cards.next(cardPanel); 
				i--; }


			}
		});
		menu2.add(menuItem4);
		menuItem4.addActionListener(new ActionListener(){

			public void actionPerformed( ActionEvent evento ){
				System.exit(-1);
			}
		});
		menuBar.add(menu2);
		return menuBar;
	}
	JPanel pv = new JPanel();
	@SuppressWarnings({ "static-access", "deprecation" })
	private JPanel getcontrolPanel2(){
		Class[] combos = getCombos();
		final JComboBox jcb = new JComboBox(combos);
		// use a renderer to shorten the layout name presentation
		jcb.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				String valueString = value.toString();
				valueString = valueString.substring(valueString.lastIndexOf('.')+1);
				return super.getListCellRendererComponent(list, valueString, index, isSelected,
						cellHasFocus);

			}
		});
		pv.add(jcb);
		pv.setBackground(Color.WHITE);
		pv.setVisible(false);

		return pv;
	}


	private static Class<? extends Layout>[] getCombos()
	{
		List<Class<? extends Layout>> layouts = new ArrayList<Class<? extends Layout>>();
		layouts.add(KKLayout.class);
		layouts.add(FRLayout.class);
		layouts.add(CircleLayout.class);
		layouts.add(SpringLayout.class);
		layouts.add(SpringLayout2.class);
		layouts.add(ISOMLayout.class);
		return layouts.toArray(new Class[0]);
	}

	private JPanel getGraphPanel() throws IOException, SQLException
	{
		JPanel jp = new JPanel();

		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));


		jp.setBackground(Color.LIGHT_GRAY);               

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
				System.out.println(map_clients.toString());
				Collection<Integer> map_values = map_clients.get(focal_node);
				if(map_values != null){
					for(Iterator<Integer> it_values = map_values.iterator(); it_values.hasNext();){
						Integer node = it_values.next();
						if(s_node_type!=null){
							
							if(Sistema.getCreator().getGraph().getNodeReverseTable().containsKey(node)){
								Sistema.getCreator().getGraph().getNodeReverseTable().get(node).setType(s_node_type);
								vp.transform(node);	
							}
						}

						
					}
				}

			}
		}
	}

	public VisualizationViewer<Integer, Integer> getVisualizationViewer(){
		return this.vv;
	}

	public static void main(String args[]) throws IOException, SQLException{
		new View();
	}	
}
