package Code.Run;

import java.sql.*;
/** EL SIGUIENTE CODIGO CREA UNA CONECCION, ABRIR, CERRAR Y LLENAR UNA BASE DE DATOS EXISTENTE*/
public class Conexion{
	public Connection con  = null;
    public Statement  st   = null;  
    public String     sql  = null;
    public String 	 user =null;
    public String 	 password =null;        		
    public String dbName =null;
    public String dbDirectory = "C:\\Database\\";
    public String ruta = null;
    
    public Conexion(String user, String password, String dbName ) {
		// TODO Auto-generated constructor stub
    	this.user=user;
    	this.password=password;
    	this.dbName=dbName;
    	ruta="jdbc:mysql://localhost:3306/"+dbName;
	}
    /**EL METODO LOADING NOS PERMITE CARGAR EL DRIVER PARA LA CONECCION CON MYSQL.*/
   public void loading(){
	   System.out.println("* Starting...");
       try
       {
    	   /**CARGAMOS EL DRIVER NECESARIO PARA LA CONECCION.*/
           Class.forName("org.gjt.mm.mysql.Driver"); //Cargando el driver
           System.out.println("* Loading driver...");
 
       } 
       catch(Exception e)
       {  
           System.err.println("ERROR: Al carcagar el driver.");
           e.printStackTrace();
           return;  
       } 
   		}
   /**EL SIGUIENTE METODO CONTIENE LA CONECCION NECESARIA CON LA BASE DE DATOS YA EXISTENTE EN MYSQL*/
    public Connection conexionDB()
    {
        try
        {   // Conecta a la base de datos 
            con = DriverManager.getConnection(ruta,user,password); 
            System.out.println("* Creating HSQLDB connection...");
            
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());  
        }
       return con;
    }
    /**EL METODO createStatement PERMITE DEFINIR LA SINTAXIS NECESARIA PARA LA EJECUCION DE LAS SENTENCIAS DE MYSQL*/
    public void createStatement()
    {
        try
        {
            st  = con.createStatement(); 
            System.out.println("* Creating Statement ...");
        }
        catch (Exception e)
        {  
            System.err.println("Error: createStatement: " + e.getMessage());   
            return;
        }
    }
    
   
    /**EL SIGUIENTE METODO CIERRA LA BASE DE DATOS ABIERTA CON LOS CAMBIOS GENERADOS*/
    public  void closeDB()
    {
        // Save temporal data and close
        try
        { 
        	
            con.close();
            System.out.println("* Closing  connection...");
            System.out.println("***ANALYSIS SUCCESSFUL***");
            System.out.println("***VER LOS RESULTADOS EN EL ARCHIVO 'Relations_info.txt' LOCALIZADO EN LA RAIZ DEL PROYECTO ***");
            
        }
        catch(Exception e)
        {
            System.out.println("Error: save temporal data" + e.getMessage());
        }
 
        
    }
    /**EL METODO inserData REALIZA LA INSERCION DE LOS RESULTADOS DEL ANALISIS REALIZADOS MEDIANTE UN ARCHIVO EXTERNO
    CON EXTENSION .TXT*/
    public  void insertData()
    {
        // Insert data  
        try 
        {      	
            //insertar los campos a la tabla class relatons
        	String rutaS=System.getProperty("user.dir").toString();
        	String nuevaruta;
        	nuevaruta=rutaS.replaceAll("\\\\", "/");
        	/**LAS SIGUIENTE LINEAS DE CODIGO PERMITEN CARGAR LOS DATOS QUE FUERON REALIZADOS POR GATE A UNA BASE DE DATOS EXISTENTE*/
        	//sql = "LOAD DATA LOCAL INFILE '"+nuevaruta+"/Relations_info.txt'"+" into table class_relations FIELDS TERMINATED BY ' 'LINES TERMINATED BY '\\r\\n';" ; 
        	//sql = "LOAD DATA LOCAL INFILE '"+nuevaruta+"/Relations_info.txt'"+" into table CLASS_RELATIONS FIELDS TERMINATED BY ' 'LINES TERMINATED BY '\\r\\n';" ;
        	sql = "LOAD DATA LOCAL INFILE '"+nuevaruta+"/Relations_info.txt'"+" into table CLASS_RELATIONS FIELDS TERMINATED BY ' 'LINES TERMINATED BY '\\n';" ;
           st.execute(sql);
           System.out.println("* Inserting Data...");
        }
        catch (Exception e)
        {  
            System.err.println("Warning: error on insert data: " + e.getMessage());   
            e.printStackTrace();
            return; 
        } 
    }
    /**METODO PRINCIPAL QUE CONTIENE LA EJECUCION DE CONECCION CON MYSQL*/
    public void OpenConection(){
    	loading();/**MANDA LLAMAR EL METODO QUE CARGARA LA CONECCION CON MYSQL*/
    	conexionDB();/**LLAMA EL METODO QUE CARGARA LA BASE DE DATOS DE MYSQL*/
    	createStatement();/**LLAMA EL METODO QUE DEFINE SINTAXIS MYSQL*/
    	deleteRows();/**LLAMA EL METODO QUE LIMPIA LA TABLA QUE SE UTILIZARA DE MYSQL*/
    }
    /**EL SIGUIENTE METODO LIMPIAMOS LOS REGISTROS QUE SE ENCUENTRAN DENTRO DE LA TABLA class_relations, PARA REALIZAR UNA INSERCION NUEVA DE DATOS:*/
    public void deleteRows(){
    	
        try {
        	//sql = "DELETE FROM class_relations;"; 
        	sql = "DELETE FROM CLASS_RELATIONS;";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: Deleting Rows of class_relations "+e.getMessage());
			
		}
    }
    
    
   }//conexion