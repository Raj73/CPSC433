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
			
			writer.write("// -- Environment ------------------\n");
			writer.write("// People \n");
			for (int i = 0; i < env.myPeople.size(); i++){
				
			}
			writer.write("// Room \n");
			for (int i = 0; i < env.roomNames.size(); i++){
				System.out.println("Room " + i + env.roomNames.get(i).name);
			}
			writer.write("// Groups \n");
			for (int i = 0; i < env.groupNames.size(); i++){
				System.out.println("Group " + i + env.groupNames.get(i).name);
			}
			writer.write("// Projects \n");
			for (int i = 0; i < env.projectNames.size(); i++){
				System.out.println("Project " + i + env.projectNames.get(i).name);
			}
			writer.write("// -- END Environment ------------------ \n");
			writer.close();
		}
		catch(IOException err){
			System.err.println("File could not be created!!");
		}
	}
}
