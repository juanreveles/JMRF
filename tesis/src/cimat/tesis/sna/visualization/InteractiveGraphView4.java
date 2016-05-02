/*
 * InteractiveGraphView3.java
 *
 * Created on March 20, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 20, 2007 Grotto Networking
 */

package cimat.tesis.sna.visualization;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map;


import javax.swing.JFrame;



import org.apache.commons.collections15.functors.ConstantTransformer;
import org.apache.commons.collections15.functors.MapTransformer;
import org.apache.commons.collections15.map.LazyMap;


/**
 * This class shows how to create you own customized graph mouse 
 * just by adding the desired plugins to a PluggableGraphMouse object.
 * This was done to show how to create a mouse with less functionality
 * than a DefaultModalGraph mouse and to illustrate the power of
 * the plugin concept.
 * @author Dr. Greg M. Bernstein
 */
public class InteractiveGraphView4 {
    Graph<Integer, String> g;
    
    //VisualizationViewer<Integer,String> vv;
    
    
    /** Creates a new instance of SimpleGraphView */
    public InteractiveGraphView4() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        g = new SparseMultigraph<Integer, String>();
        // Add some vertices and edges
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3); 
        g.addEdge("Edge-A", 1, 2, EdgeType.DIRECTED); 
        g.addEdge("Edge-B", 2, 3); 
        
        //this.change(vv);
    }
    
    Map<Integer,Paint> vertexPaints = 
    		LazyMap.<Integer,Paint>decorate(new HashMap<Integer,Paint>(),
    				new ConstantTransformer(Color.gray));
    
    Map<String,Paint> edgePaints =
    		LazyMap.<String,Paint>decorate(new HashMap<String,Paint>(),
    				new ConstantTransformer(Color.blue));
    
    public final Color[] similarColors =
    	{
    		new Color(216, 134, 134),
    		new Color(135, 137, 211),
    		new Color(134, 206, 189),
    		new Color(206, 176, 134),
    		new Color(194, 204, 134),
    		new Color(145, 214, 134),
    		new Color(133, 178, 209),
    		new Color(103, 148, 255),
    		new Color(60, 220, 220),
    		new Color(30, 250, 100)
    		
    	};
    
    public void change2(final VisualizationViewer<Integer,String> vv){
    	vv.getRenderContext().setEdgeDrawPaintTransformer(MapTransformer.<String,Paint>getInstance(edgePaints));
    	//vv.getRenderContext().setEdgeFillPaintTransformer(MapTransformer.<String, Paint>getInstance(edgePaints));
    	
    	Collection<String> edges = g.getEdges();
    	
    	edgePaints.put("Edge-A", similarColors[0]);
    	edgePaints.put("Edge-B", similarColors[2]);
    }
    
    public void change(final VisualizationViewer<Integer,String> vv){
    	vv.getRenderContext().setVertexFillPaintTransformer(MapTransformer.<Integer,Paint>getInstance(vertexPaints));
    	
    	Collection<Integer> vertices = g.getVertices();
    	//int i = 0;
    	vertexPaints.put(3,similarColors[1]);
    	
    	/*for(Iterator<Integer> it = set.iterator(); i<set.size(); i++){
    		int b = it.next();
    		System.out.println(b);
    	}*/
    	
    	/*for (int v : vertices) {
			vertexPaints.put(v, similarColors[3]);
			//vertexPaints.put(0, value);
		}*/
    	/*
		vv.getRenderContext().setVertexDrawPaintTransformer(new Transformer<Integer,Paint>() {
			@Override
			public Paint transform(Integer v) {
				// TODO Auto-generated method stub
				
				if(vv.getPickedVertexState().isPicked(v)) {
					return Color.cyan;
				} else {
					return Color.BLACK;
				}
				
				//return null;
			}
		});*/
		
		//vv.repaint();
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InteractiveGraphView4 sgv = new InteractiveGraphView4(); // Creates the graph...
        
        // Layout<V, E>, VisualizationViewer<V,E>
        Layout<Integer, String> layout = new CircleLayout<Integer, String>(sgv.g);
        layout.setSize(new Dimension(300,300));
        VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
        //vv.getRenderContext().set
        // Create our "custom" mouse here. We start with a PluggableGraphMouse
        // Then add the plugins you desire.
        
        PluggableGraphMouse gm = new PluggableGraphMouse(); 
        gm.add(new TranslatingGraphMousePlugin(MouseEvent.BUTTON1_MASK));
        gm.add(new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, 1.1f, 0.9f));
        
        vv.setGraphMouse(gm); 
        JFrame frame = new JFrame("Interactive Graph View 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
        
       
        
        sgv.change(vv);  
        sgv.change2(vv);
    }
    
}
