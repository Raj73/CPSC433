package trunk;
import java.util.AbstractList;
import java.util.TreeSet;
import java.util.LinkedList;

import trunk.Predicate.ParamType;

public class Room
{
	boolean small;
	boolean medium;
	boolean large;
	
	String name;

	LinkedList<String> closeWith = new LinkedList<String>();

	Room(String Rname)
	{
		name = Rname;
	}


	boolean evaluateRoom(String Rname)
	{
		if (name.compareTo(Rname) == 0)
			return true;

		return false;
	}

	// Small Room asserts and qeuries
	void assertSmall(String Rname)
	{
		if (name.compareTo(Rname) == 0)
			small = true;
	}

	boolean evaluateSmall(String Rname)
	{
		if ((name.compareTo(Rname) == 0) && (small == true))
			return true;

		return false;
	}

  	// Medium Room asserts and qeuries
  	void assertMedium(String Rname)
	{
		if (name.compareTo(Rname) == 0)
			medium = true;
	}

	boolean evaluateMedium(String Rname)
	{
		if ((name.compareTo(Rname) == 0) && (medium == true))
			return true;

		return false;
	}

  	// Large Room asserts and qeuries
 	void assertLarge(String Rname)
	{
		if (name.compareTo(Rname) == 0)
			large = true;
	}

	boolean evaluateLarge(String Rname)
	{
		if ((name.compareTo(Rname) == 0) && (large == true))
			return true;

		return false;
	}

	
	// Works with asserts and qeuries (singular)
	void assertCloseWith(String Rname, String Rname2)
	{
		if ((name.compareTo(Rname) == 0) && (!evaluateCloseWith(Rname, Rname2)))
			closeWith.add(Rname2);
	}
	
	boolean evaluateCloseWith(String Rname, String Rname2)
	{
		if (name.compareTo(Rname) == 0)
		{
			int size = closeWith.size();
			
			for (int i = 0; i < size; i++)
			{
				if (closeWith.get(i).compareTo(Rname2) == 0)
						return true;
			}
			return false;
		}
		
		return false;
	}
}
