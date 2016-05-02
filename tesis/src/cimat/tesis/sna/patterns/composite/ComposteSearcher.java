package cimat.tesis.sna.patterns.composite;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import cimat.tesis.sna.graph.Sistema;
import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.patterns.general.Searcher;

public class ComposteSearcher extends Searcher{
	
	private YSparseMultigraph 	g 				= null;
	
	public ComposteSearcher(){
		this.g = Sistema.getCreator().getGraph();
	}
	
	public Collection<Integer> extractTargetNodes(){
		
		 Collection<Integer> target_nodes = new HashSet<Integer>();
	        	       
	        int gener_degree 	=	0;
	        int aggre_degree	=	0;
	        //int out_degree		=	0;
	        
	        for(Iterator<Integer> it = this.g.getVertices().iterator(); it.hasNext();){
	        	
	        	int current_node = it.next();
	        	        	
	        	Collection<Integer> out_edges = this.g.getOutEdges(current_node);
	        	
	        	if(out_edges.size()>0){
	        			        		
	        		gener_degree 	= this.getNumberOfRelations(	out_edges	, 	"GENERALIZATION");
	        		aggre_degree 	= this.getNumberOfRelations(	out_edges	, 	"AGGREGATION");
	        	}
	        	
	        	
	        	if((gener_degree > 0) && (aggre_degree > 0)){	        		        		
	        		target_nodes.add(current_node);	        		
	        	}
	        	
	        	aggre_degree		= 0;
	        	gener_degree		= 0;
	        }
		
		System.out.println(target_nodes);
		return target_nodes;
	}
}
