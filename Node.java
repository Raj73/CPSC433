package cpsc433;

import java.util.Vector;

public class Node {
	private Node parent;
	private Assignment current;
	private Vector<Assignment> data;
	private Vector<Node> childern = new Vector<Node>();
	
	Node(){
		
		this.parent = null;
		
	}
	
	@SuppressWarnings("unchecked")
	Node(Assignment asign, Vector<Assignment> currentasign){
		this.current = asign;
		this.data = (Vector<Assignment>) currentasign.clone();
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Assignment getCurrent() {
		return current;
	}
	public void setCurrent(Assignment current) {
		this.current = current;
	}
	public Vector<Assignment> getData() {
		return data;
	}
	public void setData(Vector<Assignment> data) {
		this.data = data;
	}
	public Vector<Node> getChildern() {
		return childern;
	}
	public void setChildern(Vector<Node> childern) {
		this.childern = childern;
	}

}
