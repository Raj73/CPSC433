package cpsc433;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Main class that runs the program if more than one argument is put in command line then method
 * will take the first arugment and try to read it as a file. If no arguments are passed then
 * a file name will be asked for to check the "goodness" of the solution
 * @author Bryce
 *
 */

public class SisyphusI {
	public static void main(String[] args) {
		//The name of the variable for the file name 
		String fileName = "Zero";
		//Scanner for the console
		Scanner console = new Scanner(System.in);
		
		final Environment env = Environment.get();
		Solution s = new Solution();
		
		//if arguments in command line were greater than one
		if (args.length>0) {
			fileName = args[0];
			env.fromFile(fileName);
			System.out.println(new Solution().hardConstraints(env));
		}
		else{
			System.out.println("Please enter a valid file name");
			fileName = console.nextLine();
			env.fromFile(fileName);
			s.addSolution(env.getRoomNames());
			s.printSol(0);
			s.changeAssign(env);
			s.addSolution(env.getRoomNames());
			s.printSol(0);
			s.printSol(1);
		}
		//output the environment to a file
		toFile(env, fileName);
		
	}
	
/**
 * 	Out puts all the data to a file thats name is that file name .out.
 * @param env The data that is needed to be outputted to the file
 * @param fileName the destination of the file to be written to
 */
	
	
	public static void toFile(Environment env, String fileName){
		
		
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(fileName+".out", true));
			String name;
			String worksWith = "";
			String closeWith = "";
			
			writer.write("// -- Environment ------------------\n");
			writer.write("// People \n");
			for (int i = 0; i < env.getMyPeople().size(); i++){
				
				//write out people and their attributes
				name = env.getMyPeople().get(i).getName();
				writer.write("person(" + name +")\n");
				
				if (env.getMyPeople().get(i).evaluateResearcher(name))
					writer.write("researcher(" + name +")\n");
					
				if (env.getMyPeople().get(i).evaluateSecretary(name))
					writer.write("secretary(" + name +")\n");
					
				if (env.getMyPeople().get(i).evaluateManager(name)){
					writer.write("manager(" + name +")\n");
				}
				if (env.getMyPeople().get(i).evaluateSmoker(name))
					writer.write("smoker(" + name +")\n");
					
				if (env.getMyPeople().get(i).evaluateHacker(name))
					writer.write("hacker(" + name +")\n");
					
				if (env.getMyPeople().get(i).getGroup() != null)
					writer.write("group(" + name + ", " + env.getMyPeople().get(i).getGroup().getName() + ")\n");
				
				if (env.getMyPeople().get(i).getProject() != null)
					writer.write("project(" + name + ", " + env.getMyPeople().get(i).getProject().getName() + ")\n");
					
				if (env.getMyPeople().get(i).getHeadsGroup() != null){
					writer.write("heads-group(" + name + ", " + env.getMyPeople().get(i).getHeadsGroup().getName() + ")\n");
				}
				if (env.getMyPeople().get(i).getHeadsProject() != null){
					writer.write("heads-project(" + name + ", " + env.getMyPeople().get(i).getHeadsProject().getName() + ")\n");
				}
				if (env.getMyPeople().get(i).getWorksWith().size() > 0)
				{
					worksWith = env.getMyPeople().get(i).getWorksWith().get(0).getName();
					for (int j = 1; j < env.getMyPeople().get(i).getWorksWith().size(); j++){
						worksWith = worksWith + ", " + env.getMyPeople().get(i).getWorksWith().get(j).getName();
					}
				}
				
				if (worksWith != "")
				{
					writer.write("works-with(" + name + ", {" + worksWith + "})\n");
					worksWith = "";
				}
			}
			//writes out rooms and their attributes
			writer.write("// Room \n");
			for (int i = 0; i < env.getRoomNames().size(); i++){
				name = env.getRoomNames().get(i).getName();
				writer.write("room (" + env.getRoomNames().get(i).getName() + ")\n");
				
				if (env.getRoomNames().get(i).evaluateLarge(name))
					writer.write("large-room(" + name +")\n");
					
				if (env.getRoomNames().get(i).evaluateMedium(name))
					writer.write("medium-room(" + name +")\n");
					
				if (env.getRoomNames().get(i).evaluateSmall(name))
					writer.write("small-room(" + name +")\n");
					
				if (env.getRoomNames().get(i).getCloseWith().size() > 0)
				{
					closeWith = env.getRoomNames().get(i).getCloseWith().get(0);
					for (int j = 1; j < env.getRoomNames().get(i).getCloseWith().size(); j++){
						closeWith = closeWith + ", " + env.getRoomNames().get(i).getCloseWith().get(j);
					}
				}
				
				if (closeWith != "")
				{
					writer.write("close(" + name + ", {" + closeWith + "})\n");
					closeWith = "";
				} 
			}
			
			//writes out the group names 
			writer.write("// Groups \n");
			for (int i = 0; i < env.getRoomNames().size(); i++){
				writer.write("group(" + env.getRoomNames().get(i).getName() + ")\n");
			}
			//writes out the project names
			writer.write("// Projects \n");
			for (int i = 0; i < env.getProjectNames().size(); i++){
				writer.write("project(" + env.getProjectNames().get(i).getName() + ")\n");
				
				if (env.getProjectNames().get(i).evaluateLarge(env.getProjectNames().get(i).getName()))
					writer.write("large-project(" + env.getProjectNames().get(i).getName() +")\n");
			}
			
			writer.write("// -- END Environment ------------------ \n");
			//Close the file writer
			writer.close();
		}
		catch(IOException err){
			System.err.println("File could not be created!!");
		}
	}
}
