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

  	// Works with asserts and qeuries (sets)
	void assertCloseWith(String Rname, TreeSet<Pair<ParamType,Object>> r2s)
	{
		if (name.compareTo(Rname) == 0)
		{
			int size = r2s.size();
			Pair input = r2s.pollFirst();

			for (int i = 0; i < size; i++)
			{
				closeWith.add((String) input.getValue());
				input = r2s.pollFirst();
			}
		}
	}

	boolean evaluateCloseWith(String Rname, TreeSet<Pair<ParamType,Object>> r2s)
	{
		if (name.compareTo(Rname) == 0)
		{
			int inputSize = r2s.size();
			int currentSize = closeWith.size();
			int found = 0;
			Pair input = r2s.pollFirst();

			for (int i = 0; i < inputSize; i++)
			{
				for (int j = 0; j < currentSize; j++)
				{
					if (closeWith.get(j).compareTo((String) input.getValue()) == 0)
					{
						found++;
						continue;
					}
				}
				input = r2s.pollFirst();
			}

			if (found == inputSize)
				return true;
		}

		return false;
	}
	
	// Works with asserts and qeuries (singular)
	void assertCloseWith(String Rname, String Rname2)
	{
		if (name.compareTo(Rname) == 0)
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
