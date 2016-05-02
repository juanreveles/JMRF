package cimat.tesis.sna.patterns.general;

public enum VertexType {
	CLASE(1), CLASE_ABSTRACTA(2), INTERFACE(3);
	
	private int value;
	
	private VertexType(int value){
		this.value = value;
	}
	
	public int get(){
		return this.value;
	}
	
	public String toString(){
		String s_type = "";
		switch(this){
			case CLASE: 			s_type = "CLASE"; 			break;
			case CLASE_ABSTRACTA: 	s_type = "CLASE_ABSTRACTA"; break;
			case INTERFACE: 		s_type = "INTERFACE"; 		break;
		}
		
		return s_type;
	}
	
	public static void main(String []args){
		for(VertexType vt: VertexType.values()){
			System.out.println("Type: " + vt.toString());
		}
	}
}
