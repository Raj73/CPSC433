import java.util.AbstractList;
import java.util.TreeSet;

public class Group extends Entity
{
	void Group()


	boolean evaluateGroup(String Gname)
	{
		if (name.compareTo(Gname) == 0)
			return true;

		return false;
	}

}
