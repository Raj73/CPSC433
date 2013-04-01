package cpsc433;

import java.util.Vector;

public class Node {
	private Node parent;
	private Assignment current;
	private Vector<Person> currentPeople = new Vector<Person>();
	private Vector<Assignment> data = new Vector<Assignment>();
	private Vector<Node> childern = new Vector<Node>();
	private int goodness;
	private boolean traveled = false;
	
	

	Node(){	
		this.parent = null;		
	}
	
	@SuppressWarnings("unchecked")
	Node(Assignment asign, Vector<Assignment> currentasign, Vector<Person> people){
		this.current = asign;
		for(int i = 0; i < currentasign.size(); i++){			
			data.addElement(new Assignment(currentasign.get(i)));
		}
		for(int i = 0; i < people.size(); i++){			
			currentPeople.addElement(people.get(i));
		}
		
	}
	public int getGoodness() {
		return goodness;
	}
	public void setTraveled() {
		this.traveled = true;
	}
	public boolean isTraveled() {
		return traveled;
	}

	public void setGoodness(int goodness) {
		this.goodness = goodness;
	}
	public void addChild(Node child){
		childern.addElement(child);
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
