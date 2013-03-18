package cpsc433;
import java.util.Vector;

public class Project extends Entity

{
	boolean large;
	Vector<Person> projectMembers = new Vector<Person>();

	Project(String Pname)
	{
		super(Pname);
	}


	boolean evaluateProject(String Pname)
	{
		if (getName().compareTo(Pname) == 0)
			return true;

		return false;
	}

  	void assertLarge(String Pname)
	{
		if (getName().compareTo(Pname) == 0)
			large = true;
	}

  	boolean evaluateLarge(String Pname)
	{
		if ((getName().compareTo(Pname) == 0) && (large == true))
			return true;

		return false;
	}
	
	void addPerson(Person Ename)
	{
		projectMembers.addElement(Ename);
	}	
	
	void removePerson(Person Ename)
	{
		projectMembers.removeElement(Ename);
	}
}
