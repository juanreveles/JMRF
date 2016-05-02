package cimat.tesis.sna.patterns.general;

public enum EdgeDataType {
	ASOCIACION(1), AGREGACION(2), COMPOSICION(3), GENERALIZACION(4), REALIZACION(5);
	
	private int value;
	
	private EdgeDataType(int value){
		this.value = value;
	}
	
	public int get(){
		return this.value;
	}
	
	public String toString(){
		String s_type = "";
		switch(this){
			case ASOCIACION: 		s_type = "ASOCIACION";	break;
			case AGREGACION: 		s_type = "AGREGACION"; 		break;
			case COMPOSICION:		s_type = "COMPOSICION";		break;
			case GENERALIZACION:	s_type = "GENERALIZACION";	break;
			case REALIZACION:	s_type = "REALIZACION";	break;
		}
		
		return s_type;
	}
	
	public static void main(String []args){
		for(EdgeDataType vt: EdgeDataType.values()){
			System.out.println("Type: " + vt.toString());
		}
	}
}
