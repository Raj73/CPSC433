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
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//The name of the variable for the file name 
		String fileName = "Zero";
		//Scanner for the console
		Scanner console = new Scanner(System.in);
		
		long startTime = System.currentTimeMillis();
		int transCount = 0;
		int bestGoodness = 999999999;
		Node bestNode = null;
		Node tempNode = null;
		int linesRead = 0;
		double givenTime = 60000;
		
		final Environment env = Environment.get();
		
		//if arguments in command line were greater than one
		if (args.length>0) {
			
			if(args.length == 2)
				givenTime = Integer.parseInt(args[1]);
			
			fileName = args[0];
			linesRead = env.fromFile(fileName);
			linesRead /= 10;
			double runTime = givenTime * 0.98;
			Solution s = new Solution(env);
			
			if(runTime <= 0){
				runTime = givenTime - 2;
			}
			
			if(s.solvable()){
				for(;; transCount++){
					s.transition();
					tempNode = s.getSolition();
					if(tempNode != null){
						if(bestNode == null){
							bestNode = tempNode;
							System.out.println("New Best at transition: " + transCount);
						}
						else if(tempNode.getGoodness() < bestNode.getGoodness()){
							bestNode = tempNode;
							System.out.println("New Best at transition: " + transCount);
						}
					}
					if ((System.currentTimeMillis() - startTime) > runTime) break;
					if((s.getCurrentNode() == null)||(s.treeSize() == 0 && s.getCurrentNode().getCurrentPeople().size() == 0)){
						System.out.println("***********Tree fully traversed***********");
						break;
					}
				}
				System.out.println("");
				System.out.println("Transitions done: " + transCount);
			}
			if(bestNode == null){
				System.out.println("***********No solution found******************");
			}
			else{
				System.out.println("Solutions generated: " + (s.getSolutionSize()+1));
				System.out.println("------Best Assignments--------");
				for(int i = 0; i < bestNode.getData().size(); i++){
					System.out.println(bestNode.getData().get(i).toString());
				}
				System.out.println("The goodness of this solution: -" + bestNode.getGoodness());
				
				s.goodness(bestNode);
				toFile2(env, fileName, bestNode, transCount, s.getSolutionSize()+1, startTime);		//create the output file
			}
/*			
			System.out.println("Total People: " + env.getMyPeople().size());
			System.out.println("Total Rooms: " + env.getRoomNames().size());
			System.out.println("");
			System.out.println("secretary");
			for(int i = 0; i < env.getSecretary().size(); i++){
				System.out.println(env.getSecretary().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("smokers");
			for(int i = 0; i < env.getSmoker().size(); i++){
				System.out.println(env.getSmoker().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("hackers");
			for(int i = 0; i < env.getHacker().size(); i++){
				System.out.println(env.getHacker().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("researchers");
			for(int i = 0; i < env.getResearcher().size(); i++){
				System.out.println(env.getResearcher().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("managers");
			for(int i = 0; i < env.getManager().size(); i++){
				System.out.println(env.getManager().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("project heads");
			for(int i = 0; i < env.getProjectHeads().size(); i++){
				System.out.println(env.getProjectHeads().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("group heads");
			for(int i = 0; i < env.getGrouphead().size(); i++){
				System.out.println(env.getGrouphead().get(i).getName());
			}
			System.out.println("");
			
			System.out.println("groups");
			for(int i = 0; i < env.getGroupNames().size(); i++){
				System.out.println(env.getGroupNames().get(i).getName());
				System.out.println("members:");
				for(int j = 0; j<env.getGroupNames().get(i).getPeople().size(); j++){
					System.out.println(env.getGroupNames().get(i).getPeople().get(j).getName());
				}
				System.out.println("");
			}
			*/
			System.out.println("Total Time: " + (System.currentTimeMillis() - startTime) + "ms");
		}
	}

	/**
	 * Outputs the assignments of the best answer into a file using the name <fileName>.out
	 * @param env
	 * @param fileName
	 * @param solution
	 * @param transitions
	 * @param solGenerated
	 * @param startTime
	 */
	public static void toFile2(Environment env, String fileName, Node solution, int transitions, int solGenerated, long startTime){
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(fileName+".out", true));
			writer.write("// Solution to " + fileName);
			
			for(int i = 0; i < solution.getData().size(); i++){
				if(solution.getData().get(i).toString().compareTo("") != 0){
					writer.write("\n" + solution.getData().get(i).toString());
				}
			}
			writer.write("\n");
			writer.write("// The goodness of this solution: -" + solution.getGoodness() + "\n");
			writer.write("// Transitions taken: " + transitions + "\n");
			writer.write("// Solutions Generated: " + solGenerated + "\n");
			writer.write("// Total People: " + env.getMyPeople().size() + "\n");
			writer.write("// Total Rooms: " + env.getRoomNames().size() + "\n");
			writer.write("// Total time: " + (System.currentTimeMillis() - startTime));
			writer.close();
		}
		catch(IOException err){
			System.err.println("File could not be created!!");
		}
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
					closeWith = env.getRoomNames().get(i).getCloseWith().get(0).getName();
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
