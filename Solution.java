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

public Node transition(Node currentNode){
	if(!currentNode.isComplete()){
		if(!currentNode.isTraveled())
			currentNode.makeChildren();
		Node bestChild = currentNode.selectChild();
		if(bestChild == null){
			return currentNode.getParent();
		}
		else{
			return bestChild;
		}
	}
	else{
		solutions.add(currentNode);
	}
	return currentNode.getParent();
}


public void createSolution(){
	// i think we got away from what an or tree is suppose to be last night
	//we should either go from the perspective of the room or person
	//assign every person to that room if looking from the perspective of
	//a room then check the goodness
//	Room room;
//	Person person;
	currentNode = Head;
//	Node bestNode = Head;
//	int backedout = 0;
	
	for(int j=0;j<Head.getCurrentPeople().size();j++){
		System.out.println(Head.getCurrentPeople().get(j).getName());
	}
	
	for(int i =0; i < 10000 ; i++){
		currentNode = transition(currentNode);
		System.out.println("current assign \n"+ currentNode.getCurrent().toString());
		System.out.println("-------------");
		for(int j =0;j<currentNode.getData().size();j++){
			System.out.println(currentNode.getData().get(j).toString());
		}
		for(int j=0;j<currentNode.getCurrentPeople().size();j++){
			System.out.println(currentNode.getCurrentPeople().get(j).getName());
		}
	}
	System.out.println("********************solution((((((((((((((((((((((((((((((((((((((");
	for(int i = 0; i<solutions.peek().getData().size(); i++){
		System.out.println(solutions.peek().getData().get(i).toString());
	}
	
//	for(int j = 0; j < rooms.size(); j++){
//		Assignment a = new Assignment(rooms.get(j));
//		assignList.addElement(a);
//	}
//	currentNode.setData(assignList);			//hackjob
	
//	while(!people.isEmpty()){								//loops  until all people have been assigned to a room
/*		Person p = people.get(0);
		for(int i = 0; i < assignList.size(); i++){
			if(p.getHeadsGroup() != null || p.getHeadsProject() != null || p.getManager()){
				if(assignList.get(i).getPerson1() == null){
					Assignment a = new Assignment(assignList.get(i));
					a.assertPerson(p);
					Node temp = new Node(a, assignList, people);
					temp.setParent(currentNode);
					goodness(temp);
					currentNode.addChild(temp);
				}
			}
			else if(!assignList.get(i).isHead()&& assignList.get(i).getPerson2() == null){
				Assignment a = new Assignment(assignList.get(i));
				a.assertPerson(p);
				Node temp = new Node(a, assignList, people);
				temp.setParent(currentNode);
				goodness(temp);
				currentNode.addChild(temp);
			}
		}*/
/*		
		
		while(currentNode.getChildern().size() == 0){
			currentNode = currentNode.getParent();
			for(int i = 0; i < currentNode.getChildern().size(); i++){
				if(currentNode.getChildern().get(i).isTraveled()){
					currentNode.getChildern().removeElementAt(i);
					break;
				}
			}
			people = currentNode.getCurrentPeople();
			assignList = currentNode.getData();
			backedout++;
		}
		bestNode = currentNode.getChildern().get(0);
*/
//		while(bestNode == null){												//take the alt path since node has no children
//			currentNode = currentNode.getParent();
//			System.out.println("alt");
//			people = currentNode.getCurrentPeople();
//			assign = currentNode.getData();
//			for(int i = 0; i < currentNode.getChildern().size(); i++){
//				if(currentNode.getChildern().get(i).isTraveled()){
//					currentNode.getChildern().removeElementAt(i);
//					break;
//				}
//			}
//			bestNode = currentNode.getChildern().get(0);
//		}
/*		for(int i = 1; i < currentNode.getChildern().size(); i++){
			if(bestNode.getGoodness() < currentNode.getChildern().get(i).getGoodness() && !currentNode.getChildern().get(i).isTraveled()){
				bestNode = currentNode.getChildern().get(i);
			}
		}
		for(int i = 0; i < assignList.size(); i++){
			if(assignList.get(i).getRoom().equals(bestNode.getCurrent().getRoom())){
				assignList.set(i, bestNode.getCurrent());
				break;
			}
		}
		currentNode = bestNode;
		currentNode.setTraveled();
		people.removeElementAt(0);
		//System.out.println(currentNode.getCurrent().toString());
		System.out.println("people left: " + people.size());
		for(int i=0;i<currentNode.getData().size();i++){
			System.out.println(currentNode.getData().get(i).toString());
		}
		System.out.println("current assign \n"+ currentNode.getCurrent().toString());
		System.out.println("-------------");
		System.out.println("backed out: " + backedout);
		for(int i=0;i<people.size();i++){
			System.out.println(people.get(i).getName());
		}
		
	//}
	*/
	

/*	for(int i = 0; i < groupHead.size(); i++){
		person = groupHead.get(i);
		room = assignHead(person);
		Assignment a = new Assignment(room, person);
		assign.addElement(a);
		Node temp = new Node(a, assign);
		temp.setParent(currentNode);
		currentNode.addChild(temp);
		currentNode = temp;
		person.setAssignedRoom();
	}
	for(int i = 0; i < projectHead.size(); i++){
		person = projectHead.get(i);
		if(!person.getAssigned()){
			room = assignProjectHead(person);
			Assignment a = new Assignment(room, person);
			assign.addElement(a);
			Node temp = new Node(a, assign);
			temp.setParent(currentNode);
			currentNode.addChild(temp);
			currentNode = temp;
			person.setAssignedRoom();
		}
	}
*/
	
}

/**
 * 
 * 
 * @param person
 * @return
 */
private Room assignProjectHead(Person person){
	Room candidate = null;
	if(person.getHeadsProject().isLarge()){
		Group g = person.getGroup();
			for(int i = 0; i < assignList.size(); i++){
				Person p = assignList.get(i).getPerson1();
				Room r;
				if(p.getHeadsGroup().equals(g)){
					r = assignList.get(i).getRoom();
					
				}
			}
	}
	
	
	if(!smRooms.isEmpty()){
		candidate = smRooms.get(0);
		smRooms.removeElementAt(0);
		return candidate;
	}
	
	
	return candidate;
}

//pop off the rooms from the vectors next time
private Room assignHead(Person person){
	Room candidate = null;
	if(!largeRooms.isEmpty()){
		for(int k = 0; k < largeRooms.size(); k++){
			candidate = largeRooms.get(k);
			for(int j = 0; j < candidate.getCloseWith().size(); j++){
				if(candidate.getCloseWith().get(j).getPeople().isEmpty() || candidate.getCloseWith().get(j).getPeople().get(0).getHeadsGroup() == null){
					largeRooms.removeElementAt(k);
					return candidate;
				}
			}
		}
	}
	else if(!smRooms.isEmpty()){
		candidate = smRooms.get(0);
		smRooms.removeElementAt(0);
		return candidate;
	}
	else{
		candidate = medRooms.get(0);
		medRooms.removeElementAt(0);
		return candidate;		
	}
	return candidate;
}

/*
public int softConstraints(Environment env){
	int penalty = 0;
	//this is soft constraint one
	for(int i = 0; i < env.myPeople.size(); i++){
		if(env.myPeople.get(i).headsGroup != null){
			int headRoomIndex = 0;
			for(headRoomIndex = 0; headRoomIndex < env.roomNames.size(); headRoomIndex++){
				if(env.roomNames.get(headRoomIndex).getName().equals(env.myPeople.get(headRoomIndex).assignedRoom)){
					if(!env.roomNames.get(headRoomIndex).large){
						penalty = -40;
					}
					break;
				}
			}
			//soft 2 here
			String group = env.myPeople.get(i).headsGroup.getName();
			int groupIndex = 0;
			while((groupIndex <env.groupNames.size()) && !env.groupNames.get(groupIndex).evaluateGroup(group)){
				groupIndex++;
			}
			
			for(int person = 0; person < env.groupNames.get(groupIndex).people.size(); person++){
				int wherethepersonis = env.roomNames.get(headRoomIndex).closeWith.indexOf(env.groupNames.get(groupIndex).people.get(person).assignedRoom);
				if(wherethepersonis == -1)
					penalty += -2;
						
			}
		}
	}
	for(int i =0; i< env.roomNames.size();i++){
		
		for(int j = 0; j < env.roomNames.get(i).people.size(); j++){	//go through all the rooms
			Room room = env.roomNames.get(i);
			//Constraint 3
			if(room.people.get(j).headsGroup != null){		//find the group heads
				Person p = env.roomNames.get(i).people.get(j);
				Group group = p.group;
				boolean secClose = false;
				for(int k = 0; k < group.people.size(); k++){	//go through all the people in the group
					if(group.people.get(k).secretary){
						if (room.closeWith.contains(group.people.get(k).assignedRoom))
							secClose = true;
					}
				}
				if(!secClose){
					penalty += -30;
				}
			}
			
			//soft constraint 4
			if(room.people.get(j).secretary){	//find all the secretaries
				if(room.people.size() == 1)
					penalty += -5;
				else if(j == 0){
					if(!room.people.get(1).secretary){
						penalty += -5;
					}
				}
				else if(j == 1){
					if(!room.people.get(0).secretary)
					penalty += -5;
				}
				
			}
			if(room.people.get(j).manager){		//find all the managers
				Person p = env.roomNames.get(i).people.get(j);
				Group group = p.group;
				boolean secClose = false;
				boolean headClose = false;
				if(p.group != null){
					for(int k = 0; k < group.people.size(); k++){		//go through all the ppl in the group
						//soft contrain 5
						if(group.people.get(k).secretary){			//find the secretary
							if (room.closeWith.contains(group.people.get(k).assignedRoom))
								secClose = true;
						}
						//soft constraint 6
						if(group.heads != null){		//find the group heads
							//for();
							if (group.people.get(k).compareTo(p) == 0 || room.closeWith.contains(group.people.get(k).assignedRoom))
								headClose = true;
						}
					}
				}
				if(!secClose){
					penalty += -20;
				}
				if(!headClose){
					penalty += -20;
				}
				
				
			}
			
			
		}
		
			
	}
	return penalty;
}
*/

public void goodness(Node currentNode)
{
	Room room = currentNode.getCurrent().getRoom();
	Person person1 = currentNode.getCurrent().getPerson1();
	Person person2 = currentNode.getCurrent().getPerson2();
	int penalty = 0;
	
	Vector<Integer> closeRooms = new Vector<Integer>();
	
	//get data indexes of all the rooms that are close with the current room
	for(int i = 0; i < currentNode.getData().size(); i++)
	{
		for(int j = 0; j < room.getCloseWith().size(); j++)
		{
			if(currentNode.getData().get(i).getPerson1() != null && currentNode.getData().get(i).getRoom().evaluateRoom(room.getCloseWith().get(j).getName()))
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
//			System.out.println(person1.getName());
//			System.out.println(person1.getGroup().getName());
//			System.out.println(currentNode.getData().get(closeRooms.get(i)).getRoom().getName());

			if (person1.getGroup().evaluateGroup(currentNode.getData().get(closeRooms.get(i)).getPerson1().getGroup().getName()))
				membersCloseToo++;
			if (currentNode.getData().get(closeRooms.get(i)).getPerson2() != null)
			{
				if (person1.getGroup().evaluateGroup(currentNode.getData().get(closeRooms.get(i)).getPerson2().getGroup().getName()))
					membersCloseToo++;
			}
		}
		
		penalty = penalty - ((person1.getGroup().getPeople().size() - membersCloseToo) * 2);	//c2
		
		
		for (int i = 0; i < closeRooms.size(); i++)
		{
			if (currentNode.getData().get(closeRooms.get(i)).getPerson1().getSecratary() && currentNode.getData().get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
			{
				penalty = penalty - 30;	//c3
				break;
			}
			
			else if (currentNode.getData().get(closeRooms.get(i)).getPerson2() != null)
			{
				if (currentNode.getData().get(closeRooms.get(i)).getPerson2().getSecratary() && currentNode.getData().get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty - 30;	//c3
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
			if(currentNode.getData().get(closeRooms.get(i)).getPerson1().getProject()!= null){
				if (person1.getProject().evaluateProject(currentNode.getData().get(closeRooms.get(i)).getPerson1().getProject().getName()))
					membersCloseToo++;
			}
				
			if (currentNode.getData().get(closeRooms.get(i)).getPerson2() != null && currentNode.getData().get(closeRooms.get(i)).getPerson2().getProject()!= null)
			{
				if (person1.getProject().evaluateProject(currentNode.getData().get(closeRooms.get(i)).getPerson2().getProject().getName()))
					membersCloseToo++;
			}
		}
		
		penalty = penalty - ((person1.getProject().getProjectMembers().size() - membersCloseToo) * 2); //c8
		
		if (person1.getProject().isLarge())
		{
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (currentNode.getData().get(closeRooms.get(i)).getPerson1().getSecratary() && currentNode.getData().get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getProject().getName()))
				{
					penalty = penalty - 30;	//c9
					break;
				}
				
				else if (currentNode.getData().get(closeRooms.get(i)).getPerson2() != null)
				{
					if (currentNode.getData().get(closeRooms.get(i)).getPerson2().getSecratary() && currentNode.getData().get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
					{
						penalty = penalty - 30;	//c9
						break;
					}
				}
			}
			
			for (int i = 0; i < closeRooms.size(); i++)
			{
				if (currentNode.getData().get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && currentNode.getData().get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty - 10;	//c10
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
			if (person1.getGroup().evaluateGroup(currentNode.getData().get(closeRooms.get(i)).getPerson1().getGroup().getName()))
				membersCloseToo++;
				
			if (currentNode.getData().get(closeRooms.get(i)).getPerson2() != null)
			{
				if (person1.getGroup().evaluateGroup(currentNode.getData().get(closeRooms.get(i)).getPerson2().getGroup().getName()))
					membersCloseToo++;
			}
		}
		
		penalty = penalty - ((person1.getGroup().getPeople().size() - membersCloseToo) * 2); //c7
		
		for (int i = 0; i < closeRooms.size(); i++)
		{
			if (currentNode.getData().get(closeRooms.get(i)).getPerson1().getSecratary() && currentNode.getData().get(closeRooms.get(i)).getPerson1().getGroup().evaluateGroup(person1.getGroup().getName()))
			{
				penalty = penalty - 30;	//c5
				break;
			}
			
			else if (currentNode.getData().get(closeRooms.get(i)).getPerson2() != null)
			{
				if (currentNode.getData().get(closeRooms.get(i)).getPerson2().getSecratary() && currentNode.getData().get(closeRooms.get(i)).getPerson2().getGroup().evaluateGroup(person1.getGroup().getName()))
				{
					penalty = penalty - 30;	//c5
					break;
				}
			}
		}
		
		for (int i = 0; i < closeRooms.size(); i++)
		{
			if (currentNode.getData().get(closeRooms.get(i)).getPerson1().getHeadsGroup() != null && currentNode.getData().get(closeRooms.get(i)).getPerson1().getHeadsGroup().evaluateGroup(person1.getGroup().getName()))
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
				penalty = penalty - 50;
			}
		}
	}
	
	else if (!person1.getSmoker() && person2 != null)
	{
		if (person2.getSmoker())
		{
			penalty = penalty - 50;
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
					penalty = penalty - 2;
				}
			}
			
			else if (person2.getHacker())
			{
				penalty = penalty - 2;
			}
		}
	}
	
	if (room != null)
	{
		if (person1 != null && person2 != null)
		{
			penalty = penalty - 4;
		}
	}
	
	if (room != null)
	{
		if (person1 != null && person2 != null)
		{
			if(person1.evaluateWorksWith(person1.getName(), person2.getName()))
			{
				penalty = penalty - 3;
			}
		}
	}
	
	if (room.getSmall())
	{
		if (person1 != null && person2 != null)
		{
			penalty = penalty - 25;
		}
	}
	
	currentNode.setGoodness(penalty);
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
	return currentNode;
}

}
