package cimat.tesis.sna.patterns.general;

public class EdgeData {
	private int id;
	private String name;
	private int source;
	private int target;
	//private EdgeType edge_type;
	private String edge_type;
	
	public EdgeData(int id, String name, String edge_type, int source, int target){
		this.id = id;
		this.name = name;
		this.source = source;
		this.target = target;
		this.edge_type = edge_type;
	}	
	
	public EdgeData(int id, String name, String edgeType){
		this.id = id;
		this.name = name;
		this.edge_type = edgeType;
	}
	
	public EdgeData(){
		setId(0);
		setName("");
		setEdge_type(null);
	}	

	public int getSource(){
		return this.source;
	}
	
	public int getTarget(){
		return this.target;
	}
	
	public void setSource(int source){
		this.source = source;
	}
	
	public void setTarget(int target){
		this.target = target;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdgeType() {
		return edge_type;
	}

	public void setEdge_type(String edge_type) {
		this.edge_type = edge_type;
	}
	
	public String toString(){
		
		return this.getEdgeType();
	}
}
