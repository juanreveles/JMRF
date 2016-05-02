package Code.Run;

import java.util.Properties;

/**CLASE PRINCIPAL DE LA APLICACION*/
public class Main {
 	public static String nameCorpus="corpus";
	public static String exfilter="java";
	public static String in_file;
	public static String out_file;
	
	public static void main(String [] args){
			//System.out.println(args[1]);
		Properties props = System.getProperties();
		props.setProperty("gate.home", "C:\\Users\\gera\\Documents\\Tesis\\FindRelation\\Gate utils\\");
		//props.setProperty("gate.home", "C:\\Users\\gera\\Documents\\Tesis\\yapadet\\code\\core\\gate\\FindRelation\\Gate utils\\");
		//props.setProperty("gate.home", "/home/jmauricio/workspace/FindRelation/Gate utils/");
		
		
		if(args.length == 2){
				Main.in_file  = args[0];
				Main.out_file = args[1];
				//String out_file = "Relations_info.txt";
				FileRelations filerelations = new FileRelations(Main.out_file);
				filerelations.ReadFile(Main.in_file);
				
				String addressFile=filerelations.getAddressFile();/**CON ESTE METODO LE ENVIAMOS LA DIRECCION DE LA RUTA DONDE SE ENCUENTRA NUESTRO DOCUMENTO A ANALIZAR*/
				//System.out.println();
				/**CON ESTE METODO LLAMAMOS LA CLASE "ConectionSQL Y LE ENVIAMOS COMO PARAMETOS EL USUARIO, LA CONTRASENA Y EL NOMBRE DE LA BASE DEATOS*/
				GateRunning relacion =new GateRunning(addressFile);
				Conexion conexion=new Conexion(filerelations.getUser(),filerelations.getPassword(),filerelations.getDBname());
				filerelations.crateFilesRelations();
				relacion.executeAnalisis(exfilter,nameCorpus);/**MANDAMOS LLAMAR EL METODO PARA ANALIZAR EL ARCHIVO*/
				conexion.OpenConection();/**LLAMAMOS EL METODO PARA ABRIR LA CONECCION A LA BASE DE DATOS*/
				conexion.insertData();/**LLAMAMOS EL METODO PARA INSERTAR DATOS EN LA BASE DE DATOS*/
				conexion.closeDB();/**LLAMAMOS EL METODO PARA CERRAR LA CONECCION A LA BASE DE DATOS*/
			}else{
				System.out.println("Verifique la configuración de GATE HOME");
				//System.out.println("El formato correcto para llamar el programa es: java -jar ");
			}
		}

}
