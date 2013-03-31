package cpsc433;
import java.util.Vector;

public class Group extends Entity
{
	private Vector<Person> people = new Vector<Person>();
    private	Vector<Person> heads = new Vector<Person>();			//remember to add to environment
	
	public Vector<Person> getPeople() {
		return people;
	}

	public void setPeople(Vector<Person> people) {
		this.people = people;
	}

	public Vector<Person> getHeads() {
		return heads;
	}

	public void setHeads(Vector<Person> heads) {
		this.heads = heads;
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
	void romoveHead(Person p){
		heads.remove(p);
	}
	
	boolean evaluateGroup(String Gname)
	{
		if (getName().compareTo(Gname) == 0)
			return true;

		return false;
	}

}
