import java.util.AbstractList;
import java.util.TreeSet;

public class Room extends Entity
{
  // Person qualities
	boolean small;
	boolean medium;
	boolean large;

	LinkedList<String> close;

	// Room qualities

	// Group qualities

	// Project qualities

	void Room()


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

  
