
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
			String group = env.myPeople.get(i).headsGroup;
			int groupIndex = 0;
			while((groupIndex <env.groupNames.size()) && !env.groupNames.get(groupIndex).evaluateGroup(group)){
				groupIndex++;
			}
			for(int person = 0; person < env.groupNames.get(groupIndex).people.size(); person++){
				System.out.println("person " + person + "groupIndex " + groupIndex);
				System.out.println(env.groupNames.get(groupIndex).people.size());
				if(env.roomNames.get(headRoomIndex).closeWith.indexOf(env.groupNames.get(groupIndex).people.get(person).assignedRoom) == -1)
					penalty += -2;
				//if(env.roomNames.get(headRoomIndex).closeWith)
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
					if(env.roomNames.get(j).getName().equals(env.myPeople.get(j).assignedRoom))
							constraint4 =false;
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
