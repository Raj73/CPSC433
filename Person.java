import java.util.AbstractList<E>

public class Person extends Entity
{
	boolean secretary;
	boolean researcher;
	boolean manager;
	boolean smoker;
	boolean hacker;
	String headsGroup;
	String headsProject;
	String assignedRoom;
	LinkedList<String> worksWith;
	
	evaluatePerson(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			return true;
			
		return false;
	}
	
	// Secretary asserts and qeuries
	assertSecretary(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			secretary = true;
	}
	
	evaluateSecretary(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (secretary == true;))
			return true;
		
		return false;
	}
	
	// Researcher asserts and qeuries
	assertResearcher(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			researcher = true;
	}
	
	evaluateResearcher(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (researcher == true;))
			return true;
		
		return false;
	}
	
	// Manager asserts and qeuries
	assertManager(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			manager = true;
	}
	
	evaluateManager(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (manager == true;))
			return true;
		
		return false;
	}
	
	// Smoker asserts and qeuries
	assertSmoker(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			smoker = true;
	}
	
	evaluateSmoker(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (smoker == true;))
			return true;
		
		return false;
	}
	
	// Hacker asserts and qeuries
	assertHacker(String Ename)
	{
		if (name.compareTo(Ename) == 0)
			hacker = true;
	}
	
	evaluateHacker(String Ename)
	{
		if ((name.compareTo(Ename) == 0) && (hacker == true;))
			return true;
		
		return false;
	}
	
	