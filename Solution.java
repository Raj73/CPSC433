package cpsc433;
import java.util.PriorityQueue;
import java.util.Vector;

public class Solution {
	
private Environment env;
private Node Head;
private Node currentNode = null;
private PriorityQueue<Node> queueNodes = new PriorityQueue<Node>();
private Node solution = null;
private int solutions = 0;


public Solution(Environment e){
	env = e;
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
			projectedGoodness = projectedGoodness + (tempNode.getCurrentPeople().size()*10); 	//just an arbritary number, projected goodness, 2 or 1 may cause out of memory
			tempNode.setGoodness(projectedGoodness);
			
			if(tempNode.isComplete()){
				goodness(tempNode);
				solutions ++;
				if(solution ==null)
					solution = tempNode;
				else if(tempNode.getGoodness() < solution.getGoodness())
					solution = tempNode;
				
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
	
	/*for loop that loops through all the rooms and subtracts every penalty from the total for given assignments */
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
		
		/* if the person in position 1 is a group head we need to check constraints 1, 2 and 3*/
		if (person1.getHeadsGroup() != null)
		{
			/*check if room is large */
			if (!room.getLarge())
			{
				penalty = penalty - 40;	//c1
			}			
			int membersCloseToo = 0;
			
			/*check rooms that are close to this one, and compare number of group members in the
			  rooms to the number of group members - 1 (subtract the person we are checking) */
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
			
			/*check rooms that are close to this one, and check if any members are secretaries */
			  
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
		
		/* if the person in position 1 is a project head we need to check constraints 8, 9 and 10*/
		if (person1.getHeadsProject() != null)
		{
			int membersCloseToo = 0;
			
			/*check rooms that are close to this one, and compare number of project members in the
			  rooms to the number of project members - 1 (subtract the person we are checking) */
			  
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
				/*check rooms that are close to this one, and check if any members are secretaries 
				  (if room is large) */
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
				
				/*check rooms that are close to this one, and check if any members are the leader
				of the persons group (if room is large) */
				  
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
		
		/*check if the person is a secretary, and apply constraint 4*/
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
		
		/* if the person in position 1 is a manager we need to check constraints 5, 6 and 7*/
		if (person1.getManager())
		{
			int membersCloseToo = 0;
			
			/*check rooms that are close to this one, and compare number of group members in the
			  rooms to the number of group members - 1 (subtract the person we are checking) */
			  
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
			
			/*check rooms that are close to this one, and check if any members are secretaries 
			  (if room is large) */
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
			
			/*check rooms that are close to this one, and check if any members are leaders 
			  of this persons group (if room is large) */
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (data.get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && !data.get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty - 10;	//c6
					break;
				}
			}
		}	
		
		/*check if the person is a smoker, and apply constraint 11 if needed*/
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
		
		if (room != null)
		{
			if (person1 != null && person2 != null && person2.getProject() != null && person1.getProject() != null)
			{
				if(person1.evaluateInProject(person1.getName(), person2.getProject().getName()))
				{
					penalty = penalty - 14;
				}
			}
		}
		
		/*check if the person in position one and two are hackers, and apply constraint 13 if needed*/
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
		
		/*check if two people share an office, and apply constraint 14 if needed*/
		if (room != null)
		{
			if (person1 != null && person2 != null)
			{
				penalty = penalty - 8;
			}
		}
		
		/*check if two people in an office work together, and apply constraint 15 if needed*/
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
		
		/*check if two people share a small office, and apply constraint 16 if needed*/
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

	public boolean solvable(){
		
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
		
		
		if(env.getMyPeople().size() != 0){
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
		}
		else{
			solvable = false;
			System.out.println("#########Problem is not solvable##########");
		}
		
		if(env.getRoomNames().size() < roomsRequired){
			solvable = false;
			System.out.println("#########Problem is not solvable##########");
		}
		return solvable;
	}

public Node getSolition(){
	return solution;
}

public int getSolutionSize(){
	return solutions;
}

public Node getCurrentNode() {
	return currentNode;
}

public int treeSize(){
	return queueNodes.size();
}
}
