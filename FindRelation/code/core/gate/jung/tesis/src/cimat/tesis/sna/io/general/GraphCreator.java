package cimat.tesis.sna.io.general;

import java.sql.ResultSet;
import java.sql.SQLException;


import cimat.tesis.sna.graph.YSparseMultigraph;
import cimat.tesis.sna.io.files.ConfigurationReader;
import cimat.tesis.sna.io.databases.Conexion;
import cimat.tesis.sna.patterns.general.EdgeData;
import cimat.tesis.sna.patterns.general.NodeData;


public class GraphCreator {
	ResultSet rs;	
	YSparseMultigraph g;	
	
	private void populateVertices(Conexion db) throws SQLException{
		rs = db.getVertex();
		
		int i = 1;
		while(this.rs.next()){		
			g.addVertex(new NodeData(i, this.rs.getString("Vertex").toUpperCase()));
			i++;
		}
	}
	
	private void populateEdges(Conexion db) throws SQLException{
		rs = db.getRelations();
		
		int i = 1;
		while(this.rs.next()){
			
			String relation_name = this.rs.getString("RelationName").toUpperCase();
						
			String s_source = this.rs.getString("Source").toUpperCase();
			
			int id_source = g.getNodeTable().get(s_source).getId();
						
			String target = this.rs.getString("Target").toUpperCase();
			
			int id_target = g.getNodeTable().get(target).getId();
			
			EdgeData ed = new EdgeData(i, "", relation_name, id_source, id_target);
			
			g.addEdge(ed);
			
			i++;
		}		
	}	
	
	public YSparseMultigraph getGraph(){
		return g;		
	}
	
	public GraphCreator() throws SQLException{
		
		ConfigurationReader cfg_reader= new ConfigurationReader();						
		Conexion db = new Conexion(cfg_reader);

		g =  new YSparseMultigraph();
				
		this.populateVertices(	db	);
				
		this.populateEdges	(	db	);		
	}
	
	public static void main(String args[]) throws SQLException{
		new GraphCreator();
	}
}