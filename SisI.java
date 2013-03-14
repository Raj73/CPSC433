package trunk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class SisI {
	public static void main(String[] args) {
		String fileName = "Zero";
		int numberofheads =0;
		
		final Environment env = Environment.get();
		if (args.length>0) {
			fileName = args[0];
			env.fromFile(fileName);
		}
		
		for (int i = 0; i < env.myPeople.size(); i++){
			System.out.println("Person " + i + env.myPeople.get(i).name);
			System.out.println("	Group " + env.myPeople.get(i).group);
			System.out.println("	Project " + env.myPeople.get(i).project);
		}
		for (int i = 0; i < env.roomNames.size(); i++){
			System.out.println("Room " + i + env.roomNames.get(i).name);
		}
		for (int i = 0; i < env.groupNames.size(); i++){
			System.out.println("Group " + i + env.groupNames.get(i).name);
		}
		for (int i = 0; i < env.projectNames.size(); i++){
			System.out.println("Project " + i + env.projectNames.get(i).name);
		}
		
		toFile(env, fileName);
	}
	
	
	public static void toFile(Environment env, String fileName){
		double numberOfroomsNeeded = 0;
		double numberOfRooms = 0;
		
		
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(fileName+".out", true));
			String name;
			String worksWith = "";
			String closeWith = "";
			
			writer.write("// -- Environment ------------------\n");
			writer.write("// People \n");
			for (int i = 0; i < env.myPeople.size(); i++){
				numberOfroomsNeeded = numberOfroomsNeeded + .5;
				name = env.myPeople.get(i).name;
				writer.write("person(" + name +")\n");
				
				if (env.myPeople.get(i).evaluateResearcher(name))
					writer.write("researcher(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateSecretary(name))
					writer.write("secretary(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateManager(name)){
					writer.write("manager(" + name +")\n");
					numberOfroomsNeeded = numberOfroomsNeeded + .5;
				}
				if (env.myPeople.get(i).evaluateSmoker(name))
					writer.write("smoker(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateHacker(name))
					writer.write("hacker(" + name +")\n");
					
				if (env.myPeople.get(i).group != null)
					writer.write("group(" + name + ", " + env.myPeople.get(i).group + ")\n");
				
				if (env.myPeople.get(i).project != null)
					writer.write("project(" + name + ", " + env.myPeople.get(i).project + ")\n");
					
				if (env.myPeople.get(i).headsGroup != null){
					numberOfroomsNeeded = numberOfroomsNeeded + .5;
					writer.write("heads-group(" + name + ", " + env.myPeople.get(i).group + ")\n");
				}
				if (env.myPeople.get(i).headsProject != null){
					numberOfroomsNeeded = numberOfroomsNeeded + .5;
					writer.write("heads-project(" + name + ", " + env.myPeople.get(i).project + ")\n");
				}
				if (env.myPeople.get(i).worksWith.size() > 0)
				{
					worksWith = env.myPeople.get(i).worksWith.get(0);
					for (int j = 1; j < env.myPeople.get(i).worksWith.size(); j++){
						worksWith = worksWith + ", " + env.myPeople.get(i).worksWith.get(j);
					}
				}
				
				if (worksWith != "")
				{
					writer.write("works-with(" + name + ", {" + worksWith + "})\n");
					worksWith = "";
				}
			}
			writer.write("// Room \n");
			for (int i = 0; i < env.roomNames.size(); i++){
				numberOfRooms = numberOfRooms++;
				name = env.roomNames.get(i).name;
				writer.write("room (" + env.roomNames.get(i).name + ")\n");
				
				if (env.roomNames.get(i).evaluateLarge(name))
					writer.write("large-room(" + name +")\n");
					
				if (env.roomNames.get(i).evaluateMedium(name))
					writer.write("medium-room(" + name +")\n");
					
				if (env.roomNames.get(i).evaluateSmall(name))
					writer.write("small-room(" + name +")\n");
					
				if (env.roomNames.get(i).closeWith.size() > 0)
				{
					closeWith = env.roomNames.get(i).closeWith.get(0);
					for (int j = 1; j < env.roomNames.get(i).closeWith.size(); j++){
						closeWith = closeWith + ", " + env.roomNames.get(i).closeWith.get(j);
					}
				}
				
				if (closeWith != "")
				{
					writer.write("close-with(" + name + ", {" + closeWith + "})\n");
					closeWith = "";
				} 
			}
			writer.write("// Groups \n");
			for (int i = 0; i < env.groupNames.size(); i++){
				writer.write("group(" + env.groupNames.get(i).name + ")\n");
			}
			writer.write("// Projects \n");
			for (int i = 0; i < env.projectNames.size(); i++){
				writer.write("project(" + env.projectNames.get(i).name + ")\n");
				
				if (env.projectNames.get(i).evaluateLarge(env.projectNames.get(i).name))
					writer.write("large-project(" + env.projectNames.get(i).name +")\n");
			}
			
			/* for finding the hard constraints
			
			For each person
				If manager add 1 to rooms_required
				If head add 1 to rooms_required
				Else add .5 to rooms_required

			If float(rooms) <= rooms_required*/

			writer.write("// -- END Environment ------------------ \n");
			writer.close();
		}
		catch(IOException err){
			System.err.println("File could not be created!!");
		}
	}
}
