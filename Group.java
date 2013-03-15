import java.util.Vector;

public class Group extends Entity
{
	Vector<Person> people = new Vector<Person>();
	
	Group(String name){
		super(name);
	}
	
	void addPerson(Person p){
		people.addElement(p);
	}
	
	void removePerson(Person p){
		people.removeElement(p);
	}
	
	boolean evaluateGroup(String Gname)
	{
		if (getName().compareTo(Gname) == 0)
			return true;

		return false;
	}

}
