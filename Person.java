import java.util.LinkedList;

public class Person extends Entity
{
	// Person qualities
	boolean secretary;
	boolean researcher;
	boolean manager;
	boolean smoker;
	boolean hacker;
	int assigned = 0;
	String group;
	String project;
	String headsGroup;
	String headsProject;
	String assignedRoom = null;
	LinkedList<String> worksWith = new LinkedList<String>();
	
	// Room qualities
	
	// Group qualities
	
	// Project qualities
	
	Person(String name){
		super(name);
	}
	
	
	boolean evaluatePerson(String Ename)
	{
		if (getName().compareTo(Ename) == 0)
			return true;
			
		return false;
	}
	
	// Secretary asserts and qeuries
	void assertSecretary(String Ename)
	{
		if (getName().compareTo(Ename) == 0)
			secretary = true;
	}
	
	boolean evaluateSecretary(String Ename)
	{
		if ((getName().compareTo(Ename) == 0) && (secretary == true))
			return true;
		
		return false;
	}
	
	// Researcher asserts and qeuries
	void assertResearcher(String Ename)
	{
		if (getName().compareTo(Ename) == 0)
			researcher = true;
	}
	
	boolean evaluateResearcher(String Ename)
	{
		if ((getName().compareTo(Ename) == 0) && (researcher == true))
			return true;
		
		return false;
	}
	
	// Manager asserts and qeuries
	void assertManager(String Ename)
	{
		if (getName().compareTo(Ename) == 0)
			manager = true;
	}
	
	boolean evaluateManager(String Ename)
	{
		if ((getName().compareTo(Ename) == 0) && (manager == true))
			return true;
		
		return false;
	}
	
	// Smoker asserts and qeuries
	void assertSmoker(String Ename)
	{
		if (getName().compareTo(Ename) == 0)
			smoker = true;
	}
	
	boolean evaluateSmoker(String Ename)
	{
		if ((getName().compareTo(Ename) == 0) && (smoker == true))
			return true;
		
		return false;
	}
	
	// Hacker asserts and qeuries
	void assertHacker(String Ename)
	{
		if (getName().compareTo(Ename) == 0)
			hacker = true;
	}
	
	boolean evaluateHacker(String Ename)
	{
		if ((getName().compareTo(Ename) == 0) && (hacker == true))
			return true;
		
		return false;
	}
	
	// Group asserts and qeuries
	void assertInGroup(String Ename, String Gname)
	{
		if ((getName().compareTo(Ename) == 0))
			group = Gname;
	}
	
	boolean evaluateInGroup(String Ename, String Gname)
	{
		if ((getName().compareTo(Ename) == 0) && (group.compareTo(Gname) == 0))
			return true;
		
		return false;
	}
	
	// Project asserts and qeuries
	void assertInProject(String Ename, String Pname)
	{
		if ((getName().compareTo(Ename) == 0))
			project = Pname;
	}
	
	boolean evaluateInProject(String Ename, String Pname)
	{
		if ((getName().compareTo(Ename) == 0) && (project.compareTo(Pname) == 0))
			return true;
		
		return false;
	}
	
	// Heads Group asserts and qeuries
	void assertHeadsGroup(String Ename, String Gname)
	{
		if ((getName().compareTo(Ename) == 0))
			headsGroup = Gname;
	}
	
	boolean evaluateHeadsGroup(String Ename, String Gname)
	{
		if ((getName().compareTo(Ename) == 0) && (headsGroup.compareTo(Gname) == 0))
			return true;
		
		return false;
	}	
	
	// Heads Project asserts and qeuries
	void assertHeadsProject(String Ename, String Pname)
	{
		if ((getName().compareTo(Ename) == 0))
			headsProject = Pname;
	}
	
	boolean evaluateHeadsProject(String Ename, String Pname)
	{
		if ((getName().compareTo(Ename) == 0) && (headsProject.compareTo(Pname) == 0))
			return true;
		
		return false;
	}
	
	// Works with asserts and qeuries (singular)
	void assertWorksWith(String Ename, String Ename2)
	{
		if ((Ename2.compareTo(Ename) != 0) && (!evaluateWorksWith(Ename, Ename2)))
			worksWith.add(Ename2);
	}
	
	boolean evaluateWorksWith(String Ename, String Ename2)
	{
		if (getName().compareTo(Ename) == 0)
		{
			int size = worksWith.size();
			
			for (int i = 0; i < size; i++)
			{
				if (worksWith.get(i).compareTo(Ename2) == 0)
						return true;
			}
			return false;
		}
		
		return false;
	}
	
	void assertAssignedRoom(String Ename, String Rname)
	{
		if (getName().compareTo(Ename) == 0)
			assignedRoom = Rname;
		assigned++;
	}
	
	boolean evaluateAssignedRoom(String Ename, String Rname)
	{
		if ((getName().compareTo(Ename) == 0) && (assignedRoom.compareTo(Rname) == 0))
			return true;
		
		return false;
	}
}
