package cpsc433;
import java.util.Vector;

public class Group extends Entity
{
	private Vector<Person> people = new Vector<Person>();
    private	Vector<Person> heads = new Vector<Person>();			//remember to add to environment
	
	public Vector<Person> getPeople() {
		return people;
	}

	public Vector<Person> getHeads() {
		return heads;
	}

	public void addHead(Person head) {
		heads.addElement(head);
	}

	Group(String name){
		super(name);
	}
	
	void addPerson(Person p){
		people.addElement(p);
	}
	
	void removePerson(Person p){
		people.removeElement(p);
	}
	void removeHead(Person p){
		heads.remove(p);
	}
	
	boolean evaluateGroup(String Gname)
	{
		if (getName().compareTo(Gname) == 0)
			return true;

		return false;
	}

}
