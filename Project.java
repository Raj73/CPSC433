import java.util.AbstractList;
import java.util.TreeSet;

public class Project extends Entity
{
  boolean large;

  void Project()


	boolean evaluateProject(String Pname)
	{
		if (name.compareTo(Pname) == 0)
			return true;

		return false;
	}

  void assertLarge(String Pname)
	{
		if (name.compareTo(Pname) == 0)
			large = true;
	}

  boolean evaluateProject(String Pname)
	{
		if ((name.compareTo(Pname) == 0) && (large == true))
			return true;

		return false;
	}

}
