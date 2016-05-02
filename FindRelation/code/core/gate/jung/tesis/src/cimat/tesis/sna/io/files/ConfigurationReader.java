package cimat.tesis.sna.io.files;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationReader {
	private String configuration_file;
	private Map<String,String> m_database_attributes;
	private Map<String,String> m_project_attributes;
	//private boolean b_readed;
	private static String a_defaults[] = {"Database", "Project"};
	
	public void setCfgPath(String path){
		this.configuration_file = path;
	}
	
	public String getDriverPath(){
		return this.getAttribute(this.m_database_attributes, "driver_path");
	}
	
	public String getDriverType(){
		return this.getAttribute(this.m_database_attributes, "driver_type");
	}
	
	public String getName(){
		return this.getAttribute(this.m_database_attributes, "name");
	}
	
	public String getUser(){
		return this.getAttribute(this.m_database_attributes, "user");
	}
	
	public String getPassword(){
		return this.getAttribute(this.m_database_attributes, "password");
	}
	
	public String getFileRootPath(){
		return this.getAttribute(this.m_project_attributes, "root_path");
	}
	
	private String getAttribute(Map<String,String> m_attrib, String name_attribute){
		
		String s_attribute = "";
		
		s_attribute = m_attrib.get(name_attribute);
		
		return s_attribute;
	}
	
	private boolean populateDatabaseAttribs(XMLReader xml_reader, String db_node_name){
		boolean ok = false;
		try{
			m_database_attributes = new HashMap<String,String>();
			//xml_reader.read();
			m_database_attributes.putAll(xml_reader.getAttribs(db_node_name));
			ok=true;
		}catch(Exception e){
			ok=false;
			e.printStackTrace();
		}
		return ok;
	}
	
	private boolean populateProjectAttribs(XMLReader xml_reader, String node_name){
		boolean ok = false;
		try{
			m_project_attributes = new HashMap<String,String>();
			m_project_attributes.putAll(xml_reader.getAttribs(node_name));			
			ok=true;
		}catch(Exception e){
			ok=false;
			e.printStackTrace();
		}
		return ok;
	}
	
	public ConfigurationReader(String configuration_file, String[] a_nodes){
		//System.out.println(configuration_file);
		XMLReader xml_reader = new XMLReader(configuration_file);
		xml_reader.read();
		this.populateDatabaseAttribs(xml_reader, a_nodes[0]);
		this.populateProjectAttribs(xml_reader, a_nodes[1]);		
	}
	
	public ConfigurationReader(){
		this("resources/Configuration.xml", a_defaults);		
	}	
	
	
	public static void main(String args[]){
		String s_path = "";
				
		if(args.length == 1){
			s_path = args[0];
		}else{
			s_path = "src/resources/Configuration.xml";
		}
		
		String a_nodes[] = new String[2];
		a_nodes[0] = "Database";
		a_nodes[1] = "Project";
		ConfigurationReader cnf_reader = new ConfigurationReader(s_path, a_nodes);
		//System.out.println(cnf_reader.getDatabaseName("name"));
		System.out.println(cnf_reader.getAttribute(cnf_reader.m_database_attributes, "name"));
		System.out.println(cnf_reader.getAttribute(cnf_reader.m_database_attributes, "user"));
		System.out.println(cnf_reader.getAttribute(cnf_reader.m_database_attributes, "password"));
		
		System.out.println(cnf_reader.getAttribute(cnf_reader.m_project_attributes, "root_path"));
		//System.out.println(cnf_reader.);
	}
}
