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

public void transition(){
	if(currentNode == null)
		currentNode = Head;
	PriorityQueue<Node> mytemp;
	Node tempNode;
	int projectedGoodness;

	
		mytemp = currentNode.makeChildren();
		while(mytemp.peek() != null){
			tempNode = mytemp.poll();
			projectedGoodness = tempNode.getGoodness();
			projectedGoodness = projectedGoodness + (tempNode.getCurrentPeople().size()*10); //just an arbritary number, projected goodness, 2 or 1 may cause out of memory
			tempNode.setGoodness(projectedGoodness);
			
			if(tempNode.isComplete()){
				goodness(tempNode);
				solutions.add(tempNode);
				
			}
			else{
			queueNodes.add(tempNode);
		}
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
			
			penalty = penalty - ((person1.getGroup().getPeople().size() - 1 - membersCloseToo) * 2);
			
			penalty = penalty - 30;
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getSecratary() && data.get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty + 30;	//c3
					break;
				}
				
				else if (data.get(closeRooms.get(i)).getPerson2() != null)
				{
					if (data.get(closeRooms.get(i)).getPerson2().getSecratary() && data.get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty + 30;	//c3
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
			
			penalty = penalty - ((person1.getProject().getProjectMembers().size() - 1 - membersCloseToo) * 5); //c8			
			if (person1.getProject().isLarge())
			{
				penalty = penalty - 10;
				for (int i = 0; i < closeRooms.size(); i++)
				{
					if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getSecratary() && data.get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty + 10;	//c9
						break;
					}
					
					else if (data.get(closeRooms.get(i)).getPerson2() != null)
					{
						if (data.get(closeRooms.get(i)).getPerson2().getSecratary() && data.get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
						{
							penalty = penalty + 10;	//c9
							break;
						}
					}
				}
				
				penalty = penalty - 10;
				
				for (int i = 0; i < closeRooms.size(); i++)
				{
					if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && data.get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty + 10;	//c10
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
				}
			}
		}
		
		else if (person2 != null)
		{
			if (!person1.getSecratary() && person2.getSecratary())
			{
				penalty = penalty - 5;	//c4
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
			
			penalty = penalty - 20;
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1() != null && data.get(closeRooms.get(i)).getPerson1().getSecratary() && data.get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty + 20;	//c5
					break;
				}
				
				else if (data.get(closeRooms.get(i)).getPerson2() != null)
				{
					if (data.get(closeRooms.get(i)).getPerson2().getSecratary() && data.get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty + 20;	//c5
						break;
					}
				}
			}			
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && !data.get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty - 10;	//c6
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
				}
			}
		}	
		else if (!person1.getSmoker() && person2 != null)
		{
			if (person2.getSmoker())
			{
				penalty = penalty - 50; //11
			}
		}	
		if (person2 != null)
		{
			if (!person1.getSecratary() && !person2.getSecratary())
			{
				if (person1.getHacker())
				{
					if (!person2.getHacker())
					{
						penalty = penalty - 4;
					}
				}
				
				else if (person2.getHacker())
				{
					penalty = penalty - 4;
				}
			}
		}
		
		if (room != null)
		{
			if (person1 != null && person2 != null)
			{
				penalty = penalty - 8;
			}
		}
		
		if (room != null)
		{
			if (person1 != null && person2 != null)
			{
				if(!person1.evaluateWorksWith(person1.getName(), person2.getName()))
				{
					penalty = penalty - 6;
				}
			}
		}
		
		if (room.getSmall())
		{
			if (person1 != null && person2 != null)
			{
				penalty = penalty - 50;
			}
		}		
		currentNode.setGoodness(-penalty);
	}
}

	public boolean hardConstraints(){
		
		@SuppressWarnings("unused")
		int heads = 0;
		float roomsRequired = 0;
		boolean solvable = true;
		
		/* for finding the hard constraints
		
		For each person
			If manager add 1 to rooms_required
			If head add 1 to rooms_required
			Else add .5 to rooms_required
	
		If float(rooms) <= rooms_required*/
		for(int i = 0; i < env.getMyPeople().size(); i++){
			if(env.getMyPeople().get(i).getHeadsGroup() != null || env.getMyPeople().get(i).getHeadsProject() != null){
				roomsRequired += 1;
			}
			else if(env.getMyPeople().get(i).getManager()){
				roomsRequired += 1;
			}
			else{
				roomsRequired += 0.5;
			}
		}
		
		if(env.getRoomNames().size() < roomsRequired){
			solvable = false;
			System.out.println("#########Hard constraints cannot be met###########");
		}
		return solvable;
	}

public Node getSolution(){
	return solutions.poll();
}
public Node checkSolution(){
	return solutions.peek();
}
public int getSolutionSize(){
	return solutions.size();
}

public Node getCurrentNode() {
	return currentNode;
}

public int treeSize(){
	return queueNodes.size();
}
}