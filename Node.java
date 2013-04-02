package cpsc433;

import java.util.PriorityQueue;
import java.util.Vector;

public class Node implements Comparable<Node> {
	private Node parent;
	private Assignment current;
	private Vector<Person> currentPeople = new Vector<Person>();
	private Vector<Assignment> data = new Vector<Assignment>();
	private PriorityQueue<Node> children = new PriorityQueue<Node>();
	private int goodness;
	private boolean traveled = false;
	
	

	Node(Environment env){
		this.parent = null;		
		for(int i = 0; i < env.getMyPeople().size(); i++){			
			currentPeople.addElement(env.getMyPeople().get(i));
		}
		for(int j = 0; j < env.getRoomNames().size(); j++){
			Assignment a = new Assignment(env.getRoomNames().get(j));
			data.addElement(a);
		}
		goodness =0;
		
	}
	
	@SuppressWarnings("unchecked")
	Node(Assignment assign, Vector<Assignment> currentasign, Vector<Person> people){
		this.current = assign;
		for(int i = 0; i < currentasign.size(); i++){			
			data.addElement(new Assignment(currentasign.get(i)));
		}
		for(int i = 1; i < people.size(); i++){			
			currentPeople.addElement(people.get(i));
		}
		for(int i = 0; i < data.size(); i++){
			if(data.get(i).getRoom().equals(assign.getRoom())){
				data.set(i, assign);
				break;
			}
		}
		
	}
	
	public void makeChildren(){
		PriorityQueue<Node> newChildren = new PriorityQueue<Node>();
		Person p = currentPeople.get(0);
		
		for(int i = 0; i < data.size(); i++){
			if(p.getHeadsGroup() != null || p.getHeadsProject() != null || p.getManager()){
				if(data.get(i).getPerson1() == null){
					Assignment a = new Assignment(data.get(i));
					a.assertPerson(p);
					Node temp = new Node(a, data, currentPeople);
					temp.setParent(this);
					goodness(temp);
					newChildren.add(temp);
				}
			}
			else if(!data.get(i).isHead()&& data.get(i).getPerson2() == null){
				Assignment a = new Assignment(data.get(i));
				a.assertPerson(p);
				Node temp = new Node(a, data, currentPeople);
				temp.setParent(this);
				goodness(temp);
				newChildren.add(temp);
			}
			
		}
		
		children = newChildren;
	}
	
	public Node selectChild(){
		setTraveled();
		return children.poll();
	}
	
	public boolean isComplete(){
		return currentPeople.size() == 0;
	}
	
	public int getGoodness() {
		return goodness;
	}
	public void setTraveled() {
		this.traveled = true;
	}
	public Vector<Person> getCurrentPeople() {
		return currentPeople;
	}

	public boolean isTraveled() {
		return traveled;
	}

	public void setGoodness(int goodness) {
		this.goodness = goodness;
	}
	public void addChild(Node child){
		children.add(child);
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
	public PriorityQueue<Node> getChildern() {
		return children;
	}
	public void setChildern(PriorityQueue<Node> childern) {
		this.children = childern;
	}
	
	public void goodness(Node currentNode)
	{
		Room room = currentNode.getCurrent().getRoom();
		Person person1 = currentNode.getCurrent().getPerson1();
		Person person2 = currentNode.getCurrent().getPerson2();
		int penalty = 0;
		
		if(person1.getHeadsGroup() != null || person1.getHeadsProject() != null || person1.getManager()){
			if(person2 != null){
				penalty = 0;
			}
			else if (room.getLarge() && person1.getHeadsGroup() != null){
				penalty += 100;
			}
		}
		
		if(person1.evaluateResearcher(person1.getName()))
		{
			if(room.getMedium())
				penalty += 100;
			else if(room.getLarge())
				penalty += 0;
			else if(room.getSmall())
				penalty += 10;
		}
		currentNode.setGoodness(penalty);
	}

	@Override
	public int compareTo(Node other) {
		return getGoodness() - other.getGoodness();
	}
	
}
