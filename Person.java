import java.util.AbstractList;
import java.util.TreeSet;

public class Person extends Entity
{
	// Person qualities
	boolean secretary;
	boolean researcher;
	boolean manager;
	boolean smoker;
	boolean hacker;
	String group;
	String project;
	String headsGroup;
	String headsProject;
	String assignedRoom;
	LinkedList<String> worksWith;
	
	// Room qualities
	
	// Group qualities
	
	// Project qualities
	
	void Person()
	
	
	boolean evaluatePerson(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			return true;
			
		return false;
	}
	
	// Secretary asserts and qeuries
	void assertSecretary(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			secretary = true;
	}
	
	boolean evaluateSecretary(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (secretary == true))
			return true;
		
		return false;
	}
	
	// Researcher asserts and qeuries
	void assertResearcher(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			researcher = true;
	}
	
	boolean evaluateResearcher(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (researcher == true))
			return true;
		
		return false;
	}
	
	// Manager asserts and qeuries
	void assertManager(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			manager = true;
	}
	
	boolean evaluateManager(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (manager == true))
			return true;
		
		return false;
	}
	
	// Smoker asserts and qeuries
	void assertSmoker(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			smoker = true;
	}
	
	boolean evaluateSmoker(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (smoker == true))
			return true;
		
		return false;
	}
	
	// Hacker asserts and qeuries
	void assertHacker(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			hacker = true;
	}
	
	boolean evaluateHacker(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (hacker == true))
			return true;
		
		return false;
	}
	
	// Group asserts and qeuries
	void assertInGroup(String Ename, String Gname)
	{
		if ((name.compareTo(Ename) == 0))
			group = Gname;
	}
	
	boolean evaluateInGroup(String Ename, String Gname)
	{
		if ((name.compareTo(Ename) == 0) && (group.compareTo(Gname) == 0))
			return true;
		
		return false;
	}
	
	// Project asserts and qeuries
	void assertInProject(String Ename, String Pname)
	{
		if ((name.compareTo(Ename) == 0))
			project = Pname;
	}
	
	boolean evaluateInProject(String Ename, String Pname)
	{
		if ((name.compareTo(Ename) == 0) && (project.compareTo(Pname) == 0))
			return true;
		
		return false;
	}
	
	// Heads Group asserts and qeuries
	void assertHeadsGroup(String Ename, String Gname)
	{
		if ((name.compareTo(Ename) == 0))
			headsGroup = Gname;
	}
	
	boolean evaluateHeadsGroup(String Ename, String Gname)
	{
		if ((name.compareTo(Ename) == 0) && (headsGroup.compareTo(Gname) == 0))
			return true;
		
		return false;
	}	
	
	// Heads Project asserts and qeuries
	void assertHeadsProject(String Ename, String Pname)
	{
		if ((name.compareTo(Ename) == 0))
			headsProject = Pname;
	}
	
	boolean evaluateHeadsGroup(String Ename, String Gname)
	{
		if ((name.compareTo(Ename) == 0) && (headsProject.compareTo(Pname) == 0))
			return true;
		
		return false;
	}
	
	// Works with asserts and qeuries
	void assertWorksWith(String Ename, TreeSet<Pair<ParamType,Object>> p2s)
	{
		if ((name.compareTo(Ename) == 0))
			headsProject = Pname;
	}
	
	boolean evaluateWorksWith(String Ename, String Gname)
	{
		if ((name.compareTo(Ename) == 0) && (headsProject.compareTo(Pname) == 0))
			return true;
		
		return false;
	}
	