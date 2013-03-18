package cpsc433;

public class Solution {

public Solution(){
	
}
public int softConstraints(Environment env){
	int penalty = 0;
	
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
		
		for(int j = 0; j < env.roomNames.get(i).people.size(); j++){
			Room room = env.roomNames.get(i);
			//Constraint 3
			if(room.people.get(j).headsGroup != null){
				Person p = env.roomNames.get(i).people.get(j);
				Group group = p.group;
				boolean secClose = false;
				for(int k = 0; k < group.people.size(); k++){
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
			if(room.people.get(j).secretary){
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
			//soft contrain 5
			if(room.people.get(j).manager){
				Person p = env.roomNames.get(i).people.get(j);
				Group group = p.group;
				boolean secClose = false;
				boolean headClose = false;
				if(p.group != null){
					for(int k = 0; k < group.people.size(); k++){
						if(group.people.get(k).secretary){
							if (room.closeWith.contains(group.people.get(k).assignedRoom))
								secClose = true;
						}
						if(group.people.get(k).headsGroup != null){			//soft constraint 6
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


public String hardConstraints(Environment env){
	
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
	for(int i = 0; i < env.myPeople.size(); i++){
		if(env.myPeople.get(i).assignedRoom == null){
			constraint1 = false;
		}
		if(env.myPeople.get(i).assigned > 1){
			constraint2 = false;
		}
		if(env.myPeople.get(i).manager ||env.myPeople.get(i).headsGroup != null || env.myPeople.get(i).headsProject != null){
				for(int j = 0; j < env.roomNames.size();j++){
					if(env.roomNames.get(j).getName().equals(env.myPeople.get(i).assignedRoom)){
						if(env.roomNames.get(j).people.size() > 1)
							constraint4 =false;
					}
				}
		}
	}
	for(int i = 0; i < env.roomNames.size();i++){
		if(env.roomNames.get(i).people.size() > 2)
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
