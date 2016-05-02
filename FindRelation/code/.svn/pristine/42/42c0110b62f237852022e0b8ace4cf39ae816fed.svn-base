package cimat.tesis.sna.io.files;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	
	DocumentBuilderFactory factory = null;
	Document documento = null;
	
	
	Collection<Map<String,Map<String,String>>> c_atributes;
	Node root = null;
	
	public XMLReader(String xml_file_name){
		
		this.c_atributes= new HashSet<Map<String,Map<String,String>>>();
		
		this.factory = DocumentBuilderFactory.newInstance ();
		
		DocumentBuilder builder = null;
		
	
		  
		try {
			builder = factory.newDocumentBuilder();
			 documento = builder.parse( new File( xml_file_name ) );
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   		
	}
	
	public ArrayList<Node> getChildNodes(Node node, int node_type){
		NodeList aux_node = null;
		try{
			aux_node = node.getChildNodes();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		ArrayList<Node> a_nodes = new ArrayList<Node>();
		for(int i = 0; i<aux_node.getLength(); i++){			
			if(aux_node.item(i).getNodeType() == node_type){
				a_nodes.add(aux_node.item(i));
			}			
		}
		
		return a_nodes;	
	}
	
	public Node getRootNode(){
		Node first_child = null;
		try{
			first_child = documento.getFirstChild();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		return first_child;
	}
	
	public Collection<Map<String, Map<String, String>>> getAttributes(Node node){
		
		NamedNodeMap attribs = node.getAttributes();
		Map<String, String> map_values = new HashMap<String, String>();
		for(int i = 0; i<attribs.getLength(); i++){			
			map_values.put(attribs.item(i).getNodeName(), attribs.item(i).getNodeValue());			
		}
		Map<String, Map<String,String>> map_attributes = new HashMap<String,Map<String,String>>();
		map_attributes.put(node.getNodeName(), map_values);
		
		this.c_atributes.add(map_attributes);
		//System.out.println(this.c_atributes);
		return this.c_atributes;
	}
	
	//public Map<String,String> getAttribs(String node_name){
	public Map<String,String> getAttribs(String node_name){
		
		Map<String, String> map_attribs = new HashMap<String,String>();
		
		
		
		for(Iterator<Map<String, Map<String, String>>> it = this.c_atributes.iterator(); it.hasNext();){
			
			Map<String, Map<String,String>> map_nodes = it.next();			
			
			if(map_nodes.containsKey(node_name)){
				//System.out.println(map_nodes.get(node_name));
				map_attribs.putAll(map_nodes.get(node_name));
			}
		}
		
		return map_attribs;
	}
	
	public void read(){
		
			this.root = this.getRootNode();
			//System.out.println(this.root);
		
			ArrayList root_childs = this.getChildNodes(this.root, Node.ELEMENT_NODE);		
		
			for(Iterator<Node> iterator = root_childs.iterator(); iterator.hasNext(); ){
				Node aux_node = iterator.next();
				this.getAttributes(aux_node);
			}
		
	}
	
	public static void main(String args[]){
		if(args.length == 1){
			XMLReader xml_reader = new XMLReader(args[0]);
			xml_reader.read();
			System.out.println(xml_reader.c_atributes);
		}else{
			//DefaultOperation
			XMLReader xml_reader = new XMLReader("Configuration.xml");
			xml_reader.read();
			System.out.println(xml_reader.c_atributes);
		}						
	}
}
