package trunk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SisI {
	public static void main(String[] args) {
		String fromFile;
		
		
		final Environment env = Environment.get();
		if (args.length>0) {
			fromFile = args[0];
			env.fromFile(fromFile);
		}
		
/*		try{
		
		BufferedReader stream = null;
		stream = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		stream.close();
		}
		catch (FileNotFoundException ex){
			System.err.println("Can't open file ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		for (int i = 0; i < env.myPeople.size(); i++){
			System.out.println("Person " + i + env.myPeople.get(i).name);
		}
		
	}
}
