package Code.Run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;
import java.util.ArrayList;
/** EL SIGUIENTE CODIGO CREA UN ARCHIVO.TXT QUE CONTIENE LOS RESULADOS DEL ANALISIS REALIZADO EN GATE*/
public class FileRelations {
	 public File file=null;
	 public Reader filereader=null;
	 public BufferedReader br =null;
	 public String arrayInfoBD[]=null;
	 public ArrayList<String> array_list = null;
	 public int i=0;
	 public String in_file;
	 public String out_file;
	 
	 public FileRelations(String out_file) {
		// TODO Auto-generated constructor stub
		//file = new File("Relations_info.txt");
		file = new File(out_file);
		array_list = new ArrayList<String>();
	}
	 /**ESTE METODO CREA EL ARCHIVO "Relations_info.txt" EL CUAL CONTENDRA EL TEXTO A INSERTAR A LA BASE DE DATOS*/
	public void crateFilesRelations() {
		try {
			/**LAS SIGUIENTES LINEAS ANALIZA SI EXISTE UN ARCHIVO CREADO EN CASO DE QUE YA EXISTA LO BORRA Y CREA UNO NUEVO.*/
		     if (file.createNewFile())
		     {
		     }
		      else{
		    	  file.delete();/**ESTE METODO PERMITE ELIMINAR ARCHVIOS PREVIOS GENERADOS YA EXISTENTES*/
			      file.createNewFile();/**ESTE METODO CREA UN NUEVO ARCHIVO*/
			  }
		     }catch(IOException ioe) {
		      ioe.printStackTrace();
		     }
	}
	/**ESTE METODO NOS PERMITIRA LEER EL ARCHIVO DE CONFIGURACION PARA ASI OBTENER LOS VALORES DE DIRECCION, USUARIO Y PASSWORD
	 * PARA PODER CREAR LA CONECCION Y OBTENER LA RUTA DE LOS ARCHIVOS ANALISADOS*/
	public void ReadFile(String in_file){
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      i=0;
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	    	  /** APERTURA DEL FICHERO Y CREACION DE BUFFEREDREADER PARA PODER HACER UNA LECTURA COMODA (DISPONER DEL METODO READLINE()).*/
	         //archivo = new File ("Configure.txt");
	    	  archivo = new File (in_file);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	       
	         /** Lectura del fichero*/
	         String linea;
	         while((linea=br.readLine())!=null)
	         {
	        	 StringTokenizer st = new StringTokenizer(linea, "=");
	        	 while(st.hasMoreTokens()) {

	        		   st.nextToken();
	        		   array_list.add(st.nextToken());
	        	 }
	        	
	         }
	            
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	    	  /**EN EL FINALLY CERRAMOS EL FICHERO, PARA ASEGURARNOS QUE SE CIERRA TANTO SI TODO VA BIEN COMO SI SALTA UNA EXCEPCION.*/
	         try{                    
	            if( null != fr ){  
	               fr.close();    
	            }                  
	         }catch (Exception e2){
	            e2.printStackTrace();
	         }
	      }
	}
	/** ESTE METDODO NOS PERMITE OBTNER EL NOMBRE EL USUARIO DEL ARCHIVO DE CONFIGURACION*/
	public String getUser(){
		
		return array_list.get(0).toString();
	}
	/** ESTE METDODO NOS PERMITE OBTNER LA CONTRASENA ESTABLECIDA EN EL ARCHIVO DE CONFIGURACION*/
	public String getPassword(){
		return array_list.get(1).toString();
	}
	/** ESTE METDODO NOS PERMITE OBTNER LA DIRECCION INDICADA EN EL ARCHIVO DE CONFIGURACION*/
	public String getAddressFile(){
		return array_list.get(2).toString(); 
	}
	/** ESTE METDODO NOS PERMITE OBTNER EL NOMBRE DE LA BASE DE DATOS INDICADA EN EL ARCHIVO DE CONFIGURACION*/
	public String getDBname(){
		String addressfile=array_list.get(3).toString();
		return addressfile;
	}
}
