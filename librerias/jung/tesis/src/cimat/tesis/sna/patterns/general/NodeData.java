package cimat.tesis.sna.patterns.general;

public class NodeData {
	private int 		id;
	private String 		type;
	private String 		name;
	private String		path;
	
	public NodeData(int id, String name){
		this.id = id;
		this.type = null;
		this.name = name;
	}
	
	public NodeData(int id, String name, String type){
		this.id = id;
		this.type = type;
		this.name = name;
	}
	
	public NodeData(){
		this.id			= 0;
		this.type 		= null;
		this.name 		= "";		
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return "" + this.getId();
	}
	
	public String getCompleteName(){
		return this.path + this.name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
