
public class Solution {

public Solution(){
	
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
					if(env.roomNames.get(j).name.equals(env.myPeople.get(j).assignedRoom))
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
