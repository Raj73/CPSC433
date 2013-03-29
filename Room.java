package cpsc433;
import java.util.Vector;


/**
 * Contains the date that a room may hold
 * @author Bryce
 *
 */
public class Room extends Entity
{
	//variables
	private boolean small = false;
	private boolean medium = false;
	private boolean large = false;
	private Vector<Person> people = new Vector<Person>();
	private Vector<Room> closeWith = new Vector<Room>();

/**
 * Constructor that takes a string and calls the constructor of the super class
 * @param Rname a name of a room
 */
 
	Room(String Rname)
	{
		super(Rname);
	}
	Room(Room room){
		super(room.getName());
		small = room.getSmall();
		medium = room.getMedium();
		large = room.getLarge();
		people = room.getPeople();
		
		
		
		
		
	}

	public boolean getSmall()
	{
		return small;
	}
	
	public boolean getMedium()
	{
		return medium;
	}
	
	public boolean getLarge()
	{
		return large;
	}
	
	public Vector<Person> getPeople()
	{
		return people;
	}
	
	public Vector<Room> getCloseWith()
	{
		return closeWith;
	}
	
/**
 * Evaluates a room against the current room
 * @param Rname name of a room
 * @return true if the room name given is the same as the current room false otherwise
 */
	boolean evaluateRoom(String Rname)
	{
		if (getName().compareTo(Rname) == 0)
			return true;

		return false;
	}

/**
 * Assigns the value of the size of the room to small
 * @param Rname the name of the room that should be changed to small
 */
	void assertSmall(String Rname)
	{
		if (getName().compareTo(Rname) == 0)
			small = true;
			medium = false;
			large = false;
	}
	/**
	 * Checks if the room is small and checks the passed in name against the name of the room
	 * @param Rname name of the room to be checked
	 * @return true if the room has the same name and is small
	 */
	boolean evaluateSmall(String Rname)
	{
		if ((getName().compareTo(Rname) == 0) && (small == true))
			return true;

		return false;
	}

/**
 * Assigns the value of the size of the room to medium
 * @param Rname the name of the room that should be changed to large
 */
  	void assertMedium(String Rname)
	{
		if (getName().compareTo(Rname) == 0)
			medium = true;
			small = false;
			large = false;
	}
  	/**
	 * Checks if the room is medium and checks the passed in name against the name of the room
	 * @param Rname name of the room to be checked
	 * @return true if the room has the same name and is medium
	 */
	boolean evaluateMedium(String Rname)
	{
		if ((getName().compareTo(Rname) == 0) && (medium == true))
			return true;

		return false;
	}

	/**
	 * Assigns the value of the size of the room to large
	 * @param Rname the name of the room that should be changed to large
	 */
 	void assertLarge(String Rname)
	{
		if (getName().compareTo(Rname) == 0)
			large = true;
			medium = false;
			small = false;
	}
 	/**
	 * Checks if the room is large and checks the passed in name against the name of the room
	 * @param Rname name of the room to be checked
	 * @return true if the room has the same name and is large
	 */
	boolean evaluateLarge(String Rname)
	{
		if ((getName().compareTo(Rname) == 0) && (large == true))
			return true;

		return false;
	}

	
/**
 * Takes a the name of the person and a person that they work with and adds that they work with 
 * to a list of other people that they are close with
 * @param Rname name of the person 
 * @param Rname2 name of the co worker
 */
	void assertCloseWith(String Rname, Room Rname2)
	{
		if ((Rname2.getName().compareTo(Rname) != 0) && (!evaluateCloseWith(Rname, Rname2.getName())))
			closeWith.addElement(Rname2);
	}
	
	
	/**
	 * Evaluate 
	 * @param Rname
	 * @param Rname2
	 * @return
	 */
	boolean evaluateCloseWith(String Rname, String Rname2)
	{
		if (getName().compareTo(Rname) == 0)
		{
			int size = closeWith.size();
			
			for (int i = 0; i < size; i++)
			{
				if (closeWith.get(i).getName().compareTo(Rname2) == 0)
						return true;
			}
			return false;
		}
		
		return false;
	}
	
	boolean evaluatePerson(){
		return true;
	}

	public void assertAssignPerson(Person p, String room) {
		if (getName().compareTo(room) == 0){
			people.addElement(p);
		}
	}
	public void removeAssignPerson(Person p, String room){
		if(getName().compareTo(room) == 0){
			people.removeElement(p);
			
		}
	}
}
