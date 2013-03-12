public class Project

{
	boolean large;
	String name;

	Project(String Pname)
	{
		name = Pname;
	}


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

  	boolean evaluateLarge(String Pname)
	{
		if ((name.compareTo(Pname) == 0) && (large == true))
			return true;

		return false;
	}

}
