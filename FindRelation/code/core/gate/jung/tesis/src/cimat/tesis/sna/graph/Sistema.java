package cimat.tesis.sna.graph;

import java.sql.SQLException;

import cimat.tesis.sna.io.general.GraphCreator;

public class Sistema {
	
	static GraphCreator gc = null;
	
	public static GraphCreator getCreator(){
		if(gc == null){
			try {
				System.out.println("Graph was created...");
				gc = new GraphCreator();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return gc;
	}
}
