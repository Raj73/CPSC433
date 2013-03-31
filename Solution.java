package cpsc433;
import java.util.TreeSet;
import java.util.Vector;

public class Solution {
	
private Environment env;
private Vector<Person> people;
private Vector<Room> rooms;
private Vector<Assignment> assign;
private Node Head = new Node();
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

public void createSolution(){
	// i think we got away from what an or tree is suppose to be last night
	//we should either go from the perspective of the room or person
	//assign every person to that room if looking from the perspective of
	//a room then check the goodness
	Room room;
	Person person;
	
	for(int i = 0; i < groupHead.size(); i++){
		person = groupHead.get(i);
		room = assignHead(person);
		Assignment a = new Assignment(room, person);
	}

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
		if(env.getMyPeople().get(i).getAssigned() > 1){
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
}
