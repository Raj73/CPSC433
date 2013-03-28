package cpsc433;
import java.util.Vector;


public class Assignment
{
	private Room room;
	private Person person1;
	private Person person2;

	
	Assignment(){
		room =null;
		person1 =null;
		person2 = null;
		
	}
	Assignment(Room aroom){
		this.room = aroom;
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
	
	public Person getPerson2()
	{
		return person2;
	}
}