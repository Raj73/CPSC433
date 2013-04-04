package cpsc433;

import java.util.PriorityQueue;
import java.util.Vector;

public class Node implements Comparable<Node> {
	private Node parent;
	private Assignment current;
	private Vector<Person> currentPeople = new Vector<Person>();
	private Vector<Assignment> data = new Vector<Assignment>();
	private PriorityQueue<Node> children;
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
	
	public PriorityQueue<Node> makeChildren(){
		PriorityQueue<Node> newChildren = new PriorityQueue<Node>();
		Person p = currentPeople.get(0);
		
		System.out.println("*********************Person: " + p.getName());
		
		
		for(int i = 0; i < data.size(); i++){
			if(p.getHeadsGroup() != null || p.getHeadsProject() != null || p.getManager()){		// only put heads into empty rooms
				if(data.get(i).getPerson1() == null){
					Assignment a = new Assignment(data.get(i));
					a.assertPerson(p);
					a.setHead(true);
					Node temp = new Node(a, data, currentPeople);
					temp.setParent(this);
					goodness(temp);
					newChildren.add(temp);
				}
			}
			else if(!data.get(i).isHead()&& data.get(i).getPerson2() == null && !data.get(i).isHead()){		//dont make child if head already in room
//				System.out.println(data.get(i).isHead());
				Assignment a = new Assignment(data.get(i));
				a.assertPerson(p);
				Node temp = new Node(a, data, currentPeople);
				temp.setParent(this);
				goodness(temp);
				newChildren.add(temp);
			}
		}
		children = newChildren;
		return newChildren;
	}
	
	public Node selectChild(){
		setTraveled();
		Node node;
		
		node = children.poll();
		if(children.peek() != null){
			System.out.println("2nd best assignment " + children.peek().getCurrent().toString());
		}		
		return node;
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
	
	public void goodness(Node currentNode)		//calculate the projected goodness of the assignment
	{
		Room room = currentNode.getCurrent().getRoom();
		Person person1 = currentNode.getCurrent().getPerson1();
		Person person2 = currentNode.getCurrent().getPerson2();
		int penalty = 0;
		
		if(person1.getHeadsGroup() != null){			//first person a group head
			if (room.getMedium() || room.getSmall()){
				penalty += 40;
			}
		}
		else if(person1.getResearcher())				//a researcher and not group head
		{
			if(room.getLarge())
				penalty += 40;
			else if(room.getSmall())
				penalty += 25;
		}
		
		if(person2 == null){					// only one person in the room
			if(!person1.getManager() || person1.getHeadsProject() == null){		//prioritize putting non-heads into their own room
				penalty += 20;
			}
		}
		
		if(person2 != null){						//two people in a room
			penalty += 4;							//better to have your own office
			if((person1.getSmoker() && !person2.getSmoker()) || (person2.getSmoker() && !person1.getSmoker())){		//both should be smokers
				penalty += 50;
			}
			if((person1.getSecratary() && !person2.getSecratary()) || (person1.getSecratary() && !person2.getSecratary())){		//both should be secratary
				penalty += 10;
			}
			if(person1.getProject() != null && person2.getProject() != null){
				if(person1.getProject().equals(person2.getProject())){
					penalty += 7;			//shared rooms should not be in the same project
				}
			}
			if(person1.getWorksWith().size() < 0 && person2.getWorksWith().size() < 0){
				for(int i = 0; i < person1.getWorksWith().size(); i++){
					if(person1.getWorksWith().get(i).equals(person2)){
						break;			//people need to work together
					}
				}
			}
			
			if(room.getSmall()){
				penalty += 25;		//two people shouldnt share a small room
			}
		}
		currentNode.setGoodness(penalty);
	}

	@Override
	public int compareTo(Node other) {
		return getGoodness() - other.getGoodness();
	}
	
}
