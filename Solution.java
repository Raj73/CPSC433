package cpsc433;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Vector;

public class Solution {
	
private Environment env;
private Vector<Person> people;
private Vector<Room> rooms;
private Vector<Assignment> assignList = new Vector<Assignment>();
private Node Head;
private Vector<Person> secretary;
private Vector<Person> smoker;
private Vector<Person> hacker;
private Vector<Person> researcher;
private Vector<Person> manager;
private Vector<Person> groupHead;
private Vector<Person> projectHead;
private Vector<Room> largeRooms;
private Vector<Room> medRooms;
private Vector<Room> smRooms;
private Node currentNode = null;
private PriorityQueue<Node> solutions = new PriorityQueue<Node>();
private PriorityQueue<Node> queueNodes = new PriorityQueue<Node>();


public Solution(Environment e){
	env = e;
	people = env.getMyPeople();
	rooms = env.getRoomNames();
	secretary = env.getSecretary();
	smoker = env.getSmoker();
	hacker = env.getHacker();
	researcher = env.getResearcher();
	manager = env.getManager();
	groupHead = env.getGrouphead();
	projectHead = env.getProjectHeads();
	largeRooms = env.getLargeRooms();
	medRooms = env.getMediumRooms();
	smRooms = env.getSmallRooms();
	Head = new Node(env);
}

//Vector<Vector<Room>> rooms = new Vector<Vector<Room>>();

/*
//deprecated
public void addSolution(Vector<Room> rms){
	Vector<Room> solution = new Vector<Room>();
	
	for(int i = 0; i< rms.size(); i++){
		Room room = new Room(rms.get(i));
		solution.addElement(room);
	}
	rooms.addElement(solution);
}
*/

/*
public void printSol(int index){
	Room rm;
	for(int i = 0; i < rooms.get(index).size(); i++){
		rm = rooms.get(index).get(i);
		System.out.println("Room " + rm.getName() + " Person 1: " + rm.getPeople().get(0).getName());
		System.out.println("Room " + rm.getName() + " Person 1: " + rm.getPeople().get(1).getName());
	}
	System.out.println("--------------------end of rooms-------------");
}
*/

public void changeAssign(Environment env){
	env.getRoomNames().get(0).removeAssignPerson(env.getMyPeople().get(0), "C5110");
	
	env.getRoomNames().get(1).removeAssignPerson(env.getMyPeople().get(1), "C5113");
	
	env.getRoomNames().get(0).assertAssignPerson(env.getMyPeople().get(1), "C5110");
	
	env.getRoomNames().get(1).assertAssignPerson(env.getMyPeople().get(0), "C5113");
	
}

public void transition(){
	if(currentNode == null)
		currentNode = Head;
	PriorityQueue<Node> mytemp;
	Node tempNode;
	int projectedGoodness;

	if(!currentNode.isComplete()){
		mytemp = currentNode.makeChildren();
		while(mytemp.peek() != null){
			tempNode = mytemp.poll();
			projectedGoodness = tempNode.getGoodness();
			projectedGoodness = projectedGoodness + (tempNode.getCurrentPeople().size() * 10); //20 is just an arbritary number, projected goodness
			tempNode.setGoodness(projectedGoodness);
			queueNodes.add(tempNode);
		}
		}
	else{
		goodness(currentNode);
		solutions.add(currentNode);
		
	}
		currentNode = queueNodes.poll();
		
	
}

public void goodness(Node currentNode)
{
	Vector<Assignment> data = currentNode.getData();
	Room room;
	Person person1;
	Person person2;
	int penalty = 0;
	
	for(int ID = 0; ID < data.size(); ID++)
	{
		room = data.get(ID).getRoom();
		person1 = data.get(ID).getPerson1();
		person2 = data.get(ID).getPerson2();
		
		if (person1 == null)
			continue;
			
		System.out.println("**********************************");
		System.out.println("room" + room.getName());
		System.out.println("person 1:" + person1.getName());
		if (person2 != null) System.out.println("person 2:" + person2.getName());
		System.out.println("**********************************");
		
		Vector<Integer> closeRooms = new Vector<Integer>();
		
		//get data indexes of all the rooms that are close with the current room
		for(int i = 0; i < data.size(); i++)
		{
			for(int j = 0; j < room.getCloseWith().size(); j++)
			{
				if(data.get(i).getPerson1() != null && data.get(i).getRoom().evaluateRoom(room.getCloseWith().get(j).getName()))
					closeRooms.addElement(i);
			}
		}
		
		if (person1.getHeadsGroup() != null)
		{
			if (!room.getLarge())
			{
				penalty = penalty - 40;	//c1
				System.out.println("c1");
			}
			
			int membersCloseToo = 0;
			
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1() != null && person1.getGroup().evaluateGroup(data.get(closeRooms.get(i)).getPerson1().getGroup().getName()))
					membersCloseToo++;
				if (data.get(closeRooms.get(i)).getPerson2() != null)
				{
					if (person1.getGroup().evaluateGroup(data.get(closeRooms.get(i)).getPerson2().getGroup().getName()))
						membersCloseToo++;
				}
			}
			
			penalty = penalty - ((person1.getGroup().getPeople().size() - 1 - membersCloseToo) * 2);	//c2
			System.out.println("c2");
			
			penalty = penalty - 30;
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getSecratary() && data.get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty + 30;	//c3
					System.out.println("c3");
					break;
				}
				
				else if (data.get(closeRooms.get(i)).getPerson2() != null)
				{
					if (data.get(closeRooms.get(i)).getPerson2().getSecratary() && data.get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty + 30;	//c3
						System.out.println("c3");
						break;
					}
				}
			}
		}
		
		if (person1.getHeadsProject() != null)
		{
			int membersCloseToo = 0;
			
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if(data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getProject()!= null){
					if (person1.getProject().evaluateProject(data.get(closeRooms.get(i)).getPerson1().getProject().getName()))
						membersCloseToo++;
				}
					
				if (data.get(closeRooms.get(i)).getPerson2() != null && data.get(closeRooms.get(i)).getPerson2().getProject()!= null)
				{
					if (person1.getProject().evaluateProject(data.get(closeRooms.get(i)).getPerson2().getProject().getName()))
						membersCloseToo++;
				}
			}
			
			penalty = penalty - ((person1.getProject().getProjectMembers().size() - 1 - membersCloseToo) * 2); //c8
			System.out.println("c8");
			
			if (person1.getProject().isLarge())
			{
				penalty = penalty - 30;
				for (int i = 0; i < closeRooms.size(); i++)
				{
					if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getSecratary() && data.get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getProject().getName()))
					{
						penalty = penalty + 30;	//c9
						System.out.println("c9");
						break;
					}
					
					else if (data.get(closeRooms.get(i)).getPerson2() != null)
					{
						if (data.get(closeRooms.get(i)).getPerson2().getSecratary() && data.get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
						{
							penalty = penalty + 30;	//c9
							System.out.println("c9");
							break;
						}
					}
				}
				
				for (int i = 0; i < closeRooms.size(); i++)
				{
					if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && data.get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty - 10;	//c10
						System.out.println("c10");
						break;
					}
				}
			}
		}
		
		if (person1.getSecratary())
		{
			if (person2 != null)
			{
				if (!person2.getSecratary())
				{
					penalty = penalty - 5;	//c4
					System.out.println("c4");
				}
			}
		}
		
		else if (person2 != null)
		{
			if (!person1.getSecratary() && person2.getSecratary())
			{
				penalty = penalty - 5;	//c4
				System.out.println("c4");
			}
		}
		
		if (person1.getManager())
		{
			int membersCloseToo = 0;
			
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1() != null && person1.getGroup().evaluateGroup(data.get(closeRooms.get(i)).getPerson1().getGroup().getName()))
					membersCloseToo++;
					
				if (data.get(closeRooms.get(i)).getPerson2() != null)
				{
					if (person1.getGroup().evaluateGroup(data.get(closeRooms.get(i)).getPerson2().getGroup().getName()))
						membersCloseToo++;
				}
			}
			
			penalty = penalty - ((person1.getGroup().getPeople().size() - 1 - membersCloseToo) * 2); //c7
			System.out.println("c7");
			
			penalty = penalty - 30;
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getSecratary() && data.get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty + 30;	//c5
					System.out.println("c5");
					break;
				}
				
				else if (data.get(closeRooms.get(i)).getPerson2() != null)
				{
					if (data.get(closeRooms.get(i)).getPerson2().getSecratary() && data.get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty + 30;	//c5
						System.out.println("c5");
						break;
					}
				}
			}
			
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && !data.get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty - 10;	//c6
					System.out.println("c6");
					break;
				}
			}
		}
		
		if (person1.getSmoker())
		{
			if (person2 != null)
			{
				if (!person2.getSmoker())
				{
					penalty = penalty - 50; //11
					System.out.println("c11");
				}
			}
		}
		
		else if (!person1.getSmoker() && person2 != null)
		{
			if (person2.getSmoker())
			{
				penalty = penalty - 50; //11
				System.out.println("c11");
			}
		}
		
		// C 13 may reciprocate?
		
		if (person2 != null)
		{
			if (!person1.getSecratary() && !person2.getSecratary())
			{
				if (person1.getHacker())
				{
					if (!person2.getHacker())
					{
						penalty = penalty - 4;
						System.out.println("c13");
					}
				}
				
				else if (person2.getHacker())
				{
					penalty = penalty - 4;
					System.out.println("c13");
				}
			}
		}
		
		if (room != null)
		{
			if (person1 != null && person2 != null)
			{
				penalty = penalty - 8;
				System.out.println("c14");
			}
		}
		
		if (room != null)
		{
			if (person1 != null && person2 != null)
			{
				if(!person1.evaluateWorksWith(person1.getName(), person2.getName()))
				{
					penalty = penalty - 6;
					System.out.println("c15");
				}
			}
		}
		
		if (room.getSmall())
		{
			if (person1 != null && person2 != null)
			{
				penalty = penalty - 50;
				System.out.println("c16");
			}
		}
		
		currentNode.setGoodness(-penalty);
	}
}

	public String hardConstraints(){
		
		@SuppressWarnings("unused")
		int heads = 0;
		boolean constraint1 = true;
		boolean constraint2 = true;
		boolean constraint3 = true;
		boolean constraint4 = true;
		String result = "";
		
		/* for finding the hard constraints
		
		For each person
			If manager add 1 to rooms_required
			If head add 1 to rooms_required
			Else add .5 to rooms_required
	
		If float(rooms) <= rooms_required*/
		for(int i = 0; i < env.getMyPeople().size(); i++){
			if(env.getMyPeople().get(i).getAssignedRoom() == null){
				constraint1 = false;
			}
			if(!env.getMyPeople().get(i).getAssigned()){
				constraint2 = false;
			}
			if(env.getMyPeople().get(i).getManager() ||env.getMyPeople().get(i).getHeadsGroup() != null || env.getMyPeople().get(i).getHeadsProject() != null){
					for(int j = 0; j < env.getRoomNames().size();j++){
						if(env.getRoomNames().get(j).getName().equals(env.getMyPeople().get(i).getAssignedRoom())){
							if(env.getRoomNames().get(j).getPeople().size() > 1)
								constraint4 =false;
						}
					}
			}
		}
		for(int i = 0; i < env.getRoomNames().size();i++){
			if(env.getRoomNames().get(i).getPeople().size() > 2)
					constraint3 =false;
			
		}
		
		if(!constraint1)
			result = result + "Hard constraint 1 was not met \n";
		if(!constraint2)
			result = result + "Hard constraint 2 was not met \n";
		if(!constraint3)
			result = result + "Hard constraint 3 was not met \n";
		if(!constraint4)
			result = result + "Hard constraint 4 was not met \n";
		return result;
	}

public Node getSolution(){
	return solutions.poll();
}
public Node checkSolution(){
	return solutions.peek();
}
public Node getCurrentNode() {
	return currentNode;
}

public int treeSize(){
	return queueNodes.size();
}
}