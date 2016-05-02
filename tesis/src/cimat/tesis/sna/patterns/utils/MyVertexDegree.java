package cimat.tesis.sna.patterns.utils;

import cimat.tesis.sna.patterns.general.NodeData;

public class MyVertexDegree {
	private NodeData vertex;
	private int degree;
	
	public MyVertexDegree(NodeData s_vertex, int i_vertex_degree){
		this.vertex = s_vertex;
		this.degree = i_vertex_degree;
	}
	
	public void setVertex(NodeData v){
		this.vertex = v;
	}
	
	public void setDegree(int d){
		this.degree = d;
	}
	
	public NodeData getVertex(){
		return this.vertex;
	}
	
	public int getDegree(){
		return this.degree;
	}
	
	public String toString(){
		return this.vertex.toString() + "-" + this.degree;
	}
}
