
public class Group
{
	String name;
	Group(String name){
		this.name = name;
		
	
	}
	

	boolean evaluateGroup(String Gname)
	{
		if (name.compareTo(Gname) == 0)
			return true;

		return false;
	}

}
