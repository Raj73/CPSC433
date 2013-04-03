package cpsc433;
import java.util.Vector;


public class Assignment
{
	private Room room;
	private Person person1;
	private Person person2;
	private	boolean head;

	
	Assignment(){
		room =null;
		person1 =null;
		person2 = null;
		head = false;
	}
	Assignment(Assignment anAssignment){
		this.room = anAssignment.getRoom();
		this.person1 = anAssignment.getPerson1();
		this.person2 = anAssignment.getPerson2();
		this.head = anAssignment.isHead();
	}
	Assignment(Room aroom){
		this.room = aroom;
	}
	Assignment(Person person){
		person1 = person;
	}
	
	Assignment(Room room, Person person){
		this.room = room;
		person1 = person;
	}
	

	public void setRoom(Room r)
	{
		room = r;
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
	public void assertPerson(Person p){
		if(person1 == null){
			person1 = p;
			if((p.getHeadsGroup() != null )|| (p.getHeadsProject() != null )|| p.getManager())
				head = true;
				
		}
		else{
			
			person2 = p;
		}
	}
	
	public Person getPerson2()
	{
		return person2;
	}
	public boolean isHead() {
		return head;
	}
	public void setHead(boolean head) {
		this.head = head;
	}
	public String toString(){
		String mystring = "";
		if(person1 != null){
			mystring += String.format("assign-to(%s, %s)",person1.getName(),room.getName());

		
		}
		if(person2 !=null){
			mystring += "\n";
			mystring  += String.format("assign-to(%s, %s)",person2.getName(),room.getName());
		}
		
		
		return mystring;
		
	}
}