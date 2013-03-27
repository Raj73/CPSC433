package cpsc433;
import java.util.Vector;


public class Assignment
{
	private Room room;
	private Person person1;
	private Person person2;
	private Vector<Assignment> children = new Vector<Assignment>();
	private Assignment parent;
	
	Assignment(){
		
	}
	
	public void setRoom(Room r)
	{
		room = r;
	}
	
	public Vector<Assignment> getChildren() {
		return children;
	}

	public void setChildren(Vector<Assignment> children) {
		this.children = children;
	}

	public Assignment getParent() {
		return parent;
	}

	public void setParent(Assignment parent) {
		this.parent = parent;
	}

	public Room getRoom()
	{
		return room;
	}
	
	public void setPerson1(Person p)
	{
		person1 = p;
	}
	
	public Person getPerson1()
	{
		return person1;
	}
	
	public void setPerson2(Person p)
	{
		person2 = p;
	}
	
	public Person getPerson2()
	{
		return person2;
	}
}