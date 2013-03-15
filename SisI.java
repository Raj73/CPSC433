import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class SisI {
	public static void main(String[] args) {
		String fileName = "Zero";
		int numberofheads =0;
		Scanner console = new Scanner(System.in);
		
		final Environment env = Environment.get();
		if (args.length>0) {
			fileName = args[0];
		}
		else{
			System.out.println("Please enter a valid file name");
			fileName = console.nextLine();
		}
		env.fromFile(fileName);
		
		for (int i = 0; i < env.myPeople.size(); i++){
			System.out.println("Person " + i + env.myPeople.get(i).getName());
			System.out.println("	Group " + env.myPeople.get(i).group);
			System.out.println("	Project " + env.myPeople.get(i).project);
			System.out.println("assigned " + env.myPeople.get(i).assignedRoom);
		}
		for (int i = 0; i < env.roomNames.size(); i++){
			System.out.println("Room " + i + env.roomNames.get(i).getName());
		}
		for (int i = 0; i < env.groupNames.size(); i++){
			System.out.println("Group " + i + env.groupNames.get(i).getName());
		}
		for (int i = 0; i < env.projectNames.size(); i++){
			System.out.println("Project " + i + env.projectNames.get(i).getName());
		}
		
		toFile(env, fileName);
		
		System.out.println(new Solution().hardConstraints(env));
		System.out.println("penalty: " + new Solution().softConstraints(env));
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
				name = env.myPeople.get(i).getName();
				writer.write("person(" + name +")\n");
				
				if (env.myPeople.get(i).evaluateResearcher(name))
					writer.write("researcher(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateSecretary(name))
					writer.write("secretary(" + name +")\n");
					
				if (env.myPeople.get(i).evaluateManager(name)){
					writer.write("manager(" + name +")\n");
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
					writer.write("heads-group(" + name + ", " + env.myPeople.get(i).group + ")\n");
				}
				if (env.myPeople.get(i).headsProject != null){
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
				name = env.roomNames.get(i).getName();
				writer.write("room (" + env.roomNames.get(i).getName() + ")\n");
				
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
					writer.write("close(" + name + ", {" + closeWith + "})\n");
					closeWith = "";
				} 
			}
			writer.write("// Groups \n");
			for (int i = 0; i < env.groupNames.size(); i++){
				writer.write("group(" + env.groupNames.get(i).getName() + ")\n");
			}
			writer.write("// Projects \n");
			for (int i = 0; i < env.projectNames.size(); i++){
				writer.write("project(" + env.projectNames.get(i).getName() + ")\n");
				
				if (env.projectNames.get(i).evaluateLarge(env.projectNames.get(i).getName()))
					writer.write("large-project(" + env.projectNames.get(i).getName() +")\n");
			}

			writer.write("// -- END Environment ------------------ \n");
			writer.close();
		}
		catch(IOException err){
			System.err.println("File could not be created!!");
		}
	}
}
