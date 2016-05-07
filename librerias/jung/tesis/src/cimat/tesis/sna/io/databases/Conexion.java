package cimat.tesis.sna.io.databases;

import java.sql.*;

import cimat.tesis.sna.io.files.ConfigurationReader;

public class Conexion {
	
	public Connection con  = null;
    public Statement  st   = null;  
    public String     sql  = null;
    public String 	 user =null;
    public String 	 password =null;        		
    public String dbName =null;
    public String dbDirectory = "C:\\Database\\";
    public String ruta = null;
    private String driverType = null;
    private String driverPath = null;
    
    public Conexion(ConfigurationReader cfg_reader) {		
    	System.out.println("Conectandose a la base de datos...");
    	
    	this.user=cfg_reader.getUser();
    	System.out.println("user: " + this.user);
    	this.password=cfg_reader.getPassword();
    	System.out.println("password: " + this.password);
    	this.dbName=cfg_reader.getName();
    	System.out.println("database: " + this.dbName);
    	//this.ruta=cfg_reader.getFileRootPath() + cfg_reader.getName();
    	this.driverPath = cfg_reader.getDriverPath();
    	this.driverType = cfg_reader.getDriverType();
    	this.ruta=this.driverPath + this.dbName;
	}
    
    
    /**EL METODO LOADING NOS PERMITE CARGAR EL DRIVER PARA LA CONECCION CON MYSQL.*/
   public void loadDriver(){
	   System.out.println("* Starting...");
       try
       {
    	   /**CARGAMOS EL DRIVER NECESARIO PARA LA CONECCION.*/
           //Class.forName("org.gjt.mm.mysql.Driver"); //Cargando el driver
    	   Class.forName(this.driverType); //Cargando el driver
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
    //public Connection conexionDB()
   public Connection getConnection()
    {
        if(this.con == null){
    	
        	try
        	{   this.loadDriver();
        		// Conecta a la base de datos 
        		con = DriverManager.getConnection(ruta,user,password); 
        		System.out.println("* Connection created...");
            
        	}
        	catch(Exception e){
        		System.err.println("Error: " + e.getMessage());  
        	}
        }else{
        	System.out.println("Driver is loaded yet...");
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
    
    public ResultSet getResultSet(String s_query){
    	ResultSet r = null;
    	try{
    		Connection connection = this.getConnection();
    		Statement statement = connection.createStatement();
    		r = statement.executeQuery(s_query);
    		
    		//System.out.println("ResultSet: " + r.getFetchSize());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	    	
    	return r;
    }
   
    public ResultSet getVertex(){
    	return this.getResultSet("SELECT * FROM v_vertices;");
    }
    
    public ResultSet getRelations(){
    	return this.getResultSet("SELECT * FROM v_relations");
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
   /* CON EXTENSION .TXT*/
    /*
    public  void insertData()
    {
        // Insert data  
        try 
        {      	
            //insertar los campos a la tabla class relatons
        	String rutaS=System.getProperty("user.dir").toString();
        	String nuevaruta;
        	nuevaruta=rutaS.replaceAll("\\\\", "/");
        	LAS SIGUIENTE LINEAS DE CODIGO PERMITEN CARGAR LOS DATOS QUE FUERON REALIZADOS POR GATE A UNA BASE DE DATOS EXISTENTE
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
    }*/
        	
        	
    /**METODO PRINCIPAL QUE CONTIENE LA EJECUCION DE CONECCION CON MYSQL*/
    public void OpenConection(){
    	loadDriver();/**MANDA LLAMAR EL METODO QUE CARGARA LA CONECCION CON MYSQL*/
    	//conexionDB();/**LLAMA EL METODO QUE CARGARA LA BASE DE DATOS DE MYSQL*/
    	getConnection();
    	//createStatement();/**LLAMA EL METODO QUE DEFINE SINTAXIS MYSQL*/
    	//deleteRows();/**LLAMA EL METODO QUE LIMPIA LA TABLA QUE SE UTILIZARA DE MYSQL*/
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
	
	/*public Conexion(){
		 // Se mete todo en un try por los posibles errores de MySQL
        try
        {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            
            // Se obtiene una conexión con la base de datos. Hay que
            // cambiar el usuario "root" y la clave "la_clave" por las
            // adecuadas a la base de datos que estemos usando.
            Connection conexion = DriverManager.getConnection (
                "jdbc:mysql://localhost/prueba","root", "la_clave");
            
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            
            // Se realiza la consulta. Los resultados se guardan en el 
            // ResultSet rs
            ResultSet rs = s.executeQuery ("select * from persona");
            
            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next())
            {
                System.out.println (rs.getInt ("Id") + " " + rs.getString (2)+ 
                    " " + rs.getDate(3));
            }
            
            // Se cierra la conexión con la base de datos.
            conexion.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}*/
}
