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
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(fileName+".out", true));
			String name;
			String worksWith = "";
			String closeWith = "";
			
			writer.write("// -- Environment ------------------\n");
			writer.write("// People \n");
			for (int i = 0; i < env.myPeople.size(); i++){
				name = env.myPeople.get(i).name;
				writer.write("Person(" + name +")\n");
				
				if (env.myPeople.get(i).evaluateResearcher(name))
					writer.write("Researcher(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateSecretary(name))
					writer.write("Secretary(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateManager(name))
					writer.write("Manager(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateSmoker(name))
					writer.write("Smoker(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateHacker(name))
					writer.write("Hacker(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateSmoker(name))
					writer.write("Smoker(" + name +")\n");
					
				if (env.myPeople.get(i).group != null)
					writer.write("Group(" + name + ", " + env.myPeople.get(i).group + " )\n");
				
				if (env.myPeople.get(i).project != null)
					writer.write("Project(" + name + ", " + env.myPeople.get(i).project + " )\n");
					
				if (env.myPeople.get(i).headsGroup != null)
					writer.write("Heads-Group(" + name + ", " + env.myPeople.get(i).group + " )\n");
					
				if (env.myPeople.get(i).headsProject != null)
					writer.write("Heads-Project(" + name + ", " + env.myPeople.get(i).project + " )\n");
					
				for (int j = 0; j < env.myPeople.get(i).worksWith.size(); j++){
					worksWith = worksWith + env.myPeople.get(i).worksWith.get(j) + ", ";
				}
				
				if (worksWith != "")
				{
					writer.write("Works-With(" + name + ", {" + worksWith + "} )\n");
				}
			}
			writer.write("// Room \n");
			for (int i = 0; i < env.roomNames.size(); i++){
				writer.write("Room (" + env.roomNames.get(i).name + ")\n");
				
				if (env.roomNames.get(i).evaluateLarge(name))
					writer.write("Large-Room(" + name +")\n");
					
				if (env.roomNames.get(i).evaluateMedium(name))
					writer.write("Medium-Room(" + name +")\n");
					
				if (env.roomNames.get(i).evaluateSmall(name))
					writer.write("Small-Room(" + name +")\n");
					
				for (int j = 0; j < env.roomNames.get(i).closeWith.size(); j++){
					closeWith = closeWith + env.myPeople.get(i).closeWith.get(j) + ", ";
				}
				
				if (worksWith != "")
				{
					writer.write(""works"Close-With(" + name + ", {" + closeWith + "} )\n");
				}
			}
			writer.write("// Groups \n");
			for (int i = 0; i < env.groupNames.size(); i++){
				writer.write("Group(" + env.groupNames.get(i).name + ")\n");
			}
			writer.write("// Projects \n");
			for (int i = 0; i < env.projectNames.size(); i++){
				writer.write("Project(" + env.projectNames.get(i).name + ")\n");
				
				if (env.projectNames.get(i).evaluateLarge(name))
					writer.write("Large-Project(" + name +")\n");
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
