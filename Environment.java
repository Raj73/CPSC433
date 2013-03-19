package cpsc433;

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Vector;

/**
	The Environment class implements the SisyphusPredicates interface, and extends the functionality of
	PredicateReader. The purpose of this class is to read information given by predicates and stores the
	information based on the type of entity the predicate indicates.
*/
public class Environment extends PredicateReader implements SisyphusPredicates {

	private static final Environment myEnvir = new Environment("myenvironment");
	
	@SuppressWarnings("unused")
	private static final String Object = null;
	Vector<Person> myPeople  = new Vector<Person>();
	Vector<Group> groupNames = new Vector<Group>();
	Vector<Room> roomNames = new Vector<Room>();
	Vector<Project> projectNames = new Vector<Project>(); 
	
	/**
		A constructor that initializes the the environment
		
		@param: PredicateReader p
	*/
	public Environment(PredicateReader p) {
		super(p);
		
	}
	
	/**
		A constructor that initializes the the environment
		
		@param: String name
	*/
	public Environment(String name)
	{
		super(name);
	}
	
	/**
		function to retrieve the information in the environment
		
		@return: Environment myEnvir
	*/
	public static Environment get(){
		return myEnvir;
	}

	/**
		function to introduce a new person to the environment.
		takes in the name of the person in string form
		
		
		@param: String name
	*/
	public void a_person(String p) {
		if(!e_person(p)){
		Person aperson = new Person(p);
		myPeople.addElement(aperson);
		}
	}

	/**
		function to check the existance of a person in the environment.
		takes in the name of the person in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_person(String p) {
		
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				return true;
		}
		return false;
	}

	/**
		assert that a person is a secretary. if the person does not exist, create them.
		takes in the name of the person in string form.
		
		
		@param: String name
	*/
	public void a_secretary(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertSecretary(p);
		}
		
	}

	/**
		check if the specified person is a secretary.
		takes in the name of the person in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_secretary(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateSecretary(p))
				return true;
		}		
		return false;
	}

	/**
		assert that a person is a researcher. if the person does not exist, create them.
		takes in the name of the person in string form.
		
		
		@param: String name
	*/
	public void a_researcher(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertResearcher(p);
		}
		
	}

	/**
		check if the specified person is a researcher.
		takes in the name of the person in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_researcher(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateResearcher(p))
				return true;
		}	
		return false;
	}

	/**
		assert that a person is a manager. if the person does not exist, create them.
		takes in the name of the person in string form.
		
		
		@param: String name
	*/
	public void a_manager(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertManager(p);
		}
		
	}

	/**
		check if the specified person is a manager.
		takes in the name of the person in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_manager(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateManager(p))
				return true;
		}
		return false;
	}

	/**
		assert that a person is a smoker. if the person does not exist, create them.
		takes in the name of the person in string form.
		
		
		@param: String name
	*/
	public void a_smoker(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertSmoker(p);
		}

		
	}

	/**
		check if the specified person is a smoker.
		takes in the name of the person in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_smoker(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateSmoker(p))
				return true;
		}
		return false;
	}

	/**
		assert that a person is a hacker. if the person does not exist, create them.
		takes in the name of the person in string form.
		
		
		@param: String name
	*/
	public void a_hacker(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertHacker(p);
		}

	}

	/**
		check if the specified person is a hacker.
		takes in the name of the person in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_hacker(String p) {
		
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateHacker(p))
				return true;
		}
		return false;
	}

	/**
		assert that a person belongs to a group. if the person or group does not exist, create them.
		takes in the name of the person and group in string form.
		
		
		@param: String name
	*/
	public void a_in_group(String p, String grp) {
		a_group(p, grp);
	}

	/**
		check if the specified person is in the specified group.
		takes in the name of the person and the group in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_in_group(String p, String grp) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateInGroup(p, grp))
				return true;
		}
		return false;
	}

	/**
		assert that a person belongs to a group. if the person or group does not exist, create them.
		takes in the name of the person and group in string form.
		
		
		@param: String name
	*/
	public void a_group(String p, String grp) {
		int i = 0;
		Person person;
		if(!e_person(p))
			a_person(p);
		for(i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p)){
				break;
			}
		}
		
		person = myPeople.get(i);
		String oldGroup;
		
		int newGroup =0;
		while((newGroup <groupNames.size()) && !groupNames.get(newGroup).evaluateGroup(grp)){
			newGroup++;
		}
		if (newGroup == groupNames.size()){
			groupNames.addElement(new Group(grp));
			if(person.group == null){
				groupNames.get(newGroup).addPerson(person);
			}
			else{
				oldGroup = person.group.getName();
				int old = 0;
				while((old <groupNames.size()) && !groupNames.get(old).evaluateGroup(grp)){
					old++;
				}
				groupNames.get(old).removePerson(person);
				groupNames.get(newGroup).addPerson(person);  
			}
		}
		else{
			if(person.group == null){
				groupNames.get(newGroup).addPerson(person);
			}
			else{
				oldGroup = person.group.getName();
				int old = 0;
				while((old <groupNames.size()) && !groupNames.get(old).evaluateGroup(grp)){
					old++;
				}
				groupNames.get(old).removePerson(person);
				groupNames.get(newGroup).addPerson(person);
			}
		}
		person.assertInGroup(p, groupNames.get(newGroup));
	}

	/**
		check if the specified person is in the specified group.
		takes in the name of the person and the group in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_group(String p, String grp) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateInGroup(p, grp))
				return true;
		}
		return false;
	}

	/**
		assert that a person belongs to a project. if the person or project does not exist, create them.
		takes in the name of the person and project in string form.
		
		
		@param: String name
	*/
	public void a_in_project(String p, String prj) {
		if(!e_project(p,prj))
		a_project(p,prj);
	}

	/**
		check if the specified person is in the specified project.
		takes in the name of the person and the project in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_in_project(String p, String prj) {
		return e_project(p,prj);
	}

	/**
		assert that a person belongs to a project. if the person or project does not exist, create them.
		takes in the name of the person and project in string form.
		
		
		@param: String name
	*/
	public void a_project(String p, String prj){
		int pIndex = -1;
		
		if(!e_person(p))
			a_person(p);
		for(pIndex =0; pIndex<myPeople.size(); pIndex++){
			if(myPeople.get(pIndex).evaluatePerson(p))
			{
				break;
			}
		}
		int rIndex =0;
		while((rIndex <projectNames.size()) && !projectNames.get(rIndex).evaluateProject(prj)){
			rIndex++;
		}
		if (rIndex == projectNames.size()){
			projectNames.addElement(new Project(prj));
			projectNames.get(rIndex).addPerson(myPeople.get(pIndex));
		}
		
		else if (!projectNames.get(rIndex).projectMembers.contains(myPeople.get(pIndex)))
		{
			projectNames.get(rIndex).addPerson(myPeople.get(pIndex));
		}
		
		if (myPeople.get(pIndex).project != null)
		{
			String oldProject = myPeople.get(pIndex).project.getName();
			int temp = 0;
		
			while((temp <projectNames.size()) && !projectNames.get(temp).evaluateProject(oldProject)){
				temp++;
			}
			projectNames.get(temp).removePerson(myPeople.get(pIndex));
		}
		((Person) myPeople.get(pIndex)).assertInProject(p, projectNames.get(rIndex));
	}

	/**
		check if the specified person is in the specified project.
		takes in the name of the person and the project in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_project(String p, String prj) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateInProject(p, prj))
				return true;
		}
		return false;
	}		

	/**
		assert that a person heads a group. if the person or group does not exist, create them.
		takes in the name of the person and group in string form.
		
		
		@param: String name
	*/
	public void a_heads_group(String p, String grp) {
		int i = 0;
		Person person;
		if(!e_person(p))
			a_person(p);
		for(i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p)){
				break;
			}
		}
		
		person = myPeople.get(i);
		String oldGroup;
		
		int newGroup =0;
		while((newGroup <groupNames.size()) && !groupNames.get(newGroup).evaluateGroup(grp)){
			newGroup++;
		}
		if (newGroup == groupNames.size()){
			groupNames.addElement(new Group(grp));
			if(person.headsGroup == null){
				groupNames.get(newGroup).addPerson(person);
			}
			else{
				oldGroup = person.headsGroup.getName();
				int old = 0;
				while((old <groupNames.size()) && !groupNames.get(old).evaluateGroup(grp)){
					old++;
				}
				groupNames.get(old).removePerson(person);
				groupNames.get(newGroup).addPerson(person);  
			}
		}
		else{
			if(person.headsGroup == null){
				groupNames.get(newGroup).addPerson(person);
			}
			else{
				oldGroup = person.headsGroup.getName();
				int old = 0;
				while((old <groupNames.size()) && !groupNames.get(old).evaluateGroup(grp)){
					old++;
				}
				groupNames.get(old).removePerson(person);
				groupNames.get(newGroup).addPerson(person);
			}
		}
		person.assertInGroup(p, groupNames.get(newGroup));
		person.assertHeadsGroup(p, groupNames.get(newGroup));
	}

	/**
		check if the specified person head of the specified group.
		takes in the name of the person and the group in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_heads_group(String p, String grp) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateHeadsGroup(p, grp))
				return true;
		}
		return false;
	}
	

	/**
		assert that a person heads a project. if the person or project does not exist, create them.
		takes in the name of the person and project in string form.
		
		
		@param: String name
	*/
	public void a_heads_project(String p, String prj) {
		int pIndex = -1;
		
		if(!e_person(p))
			a_person(p);
		for(pIndex =0; pIndex<myPeople.size(); pIndex++){
			if(myPeople.get(pIndex).evaluatePerson(p))
			{
				break;
			}
		}
		int rIndex =0;
		while((rIndex <projectNames.size()) && !projectNames.get(rIndex).evaluateProject(prj)){
			rIndex++;
		}
		if (rIndex == projectNames.size()){
			projectNames.addElement(new Project(prj));
			projectNames.get(rIndex).addPerson(myPeople.get(pIndex));
		}
		
		else if (!projectNames.get(rIndex).projectMembers.contains(myPeople.get(pIndex)))
		{
			projectNames.get(rIndex).addPerson(myPeople.get(pIndex));
		}
		
		if (myPeople.get(pIndex).headsProject != null)
		{
			String oldProject = myPeople.get(pIndex).headsProject.getName();
			int temp = 0;
		
			while((temp <projectNames.size()) && !projectNames.get(temp).evaluateProject(oldProject)){
				temp++;
			}
			projectNames.get(temp).removePerson(myPeople.get(pIndex));
		}
		((Person) myPeople.get(pIndex)).assertInProject(p, projectNames.get(rIndex));
		((Person) myPeople.get(pIndex)).assertHeadsProject(p, projectNames.get(rIndex));
	}

	/**
		check if the specified person head of the specified project.
		takes in the name of the person and the project in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_heads_project(String p, String prj) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateHeadsProject(p, prj))
				return true;
		}
		return false;
	}
	
	/**
		assert that a person works with a set of people (reflexive). if the people do not exist, create them.
		takes in the names of the people in a set of strings.
		
		
		@param:String p, TreeSet<Pair<Predicate.@paramType, Object>> names
	*/
	public void a_works_with(String p, TreeSet<Pair<Predicate.ParamType, Object>> p2s) {
		if(!e_person(p))
			a_person(p);
		String p2;

		while (!p2s.isEmpty())
		{
			p2 = (String) p2s.pollFirst().getValue();
			a_works_with(p, p2);
		}
	}

	/**
		checks that a person works with a set of people (reflexive).
		takes in the names of the people in a set of strings.
		
		
		@param:String p, TreeSet<Pair<Predicate.@paramType, Object>> names
		@returns: boolean
	*/
	public boolean e_works_with(String p, TreeSet<Pair<Predicate.ParamType, Object>> p2s) {
		 String person2;
			
			while (!p2s.isEmpty())
			{
				person2 = (String) p2s.pollFirst().getValue();
				if (!e_works_with(p, person2))
					return false;
			}
			return true;
	}

	/**
		assert that a person works with another person (reflexive). if the people do not exist, create them.
		takes in the names of the people in a set of strings.
		
		
		@param: String p, String p2
	*/
	public void a_works_with(String p1, String p2) {
		int pIndex = -1;
		int pIndex2 = -1;
		
		for (int i = 0; i < myPeople.size(); i++)
		{
			if(myPeople.get(i).evaluatePerson(p1))
				pIndex = i;
			
			if (myPeople.get(i).evaluatePerson(p2))
				pIndex2 = i;
		}
		
		if(pIndex == -1 && pIndex2 == -1){
			a_person(p1);
			a_person(p2);
			myPeople.get(myPeople.size() - 2).assertWorksWith(p1, myPeople.get(myPeople.size() - 1));
			myPeople.get(myPeople.size() - 1).assertWorksWith(p2, myPeople.get(myPeople.size() - 2));	
		}
		else if(pIndex == -1){
			a_person(p1);
			myPeople.get(myPeople.size() - 1).assertWorksWith(p1, myPeople.get(pIndex2));
			myPeople.get(pIndex2).assertWorksWith(p2, myPeople.get(myPeople.size() - 1));	
		}
		else if (pIndex2 == -1){
			a_person(p2);
			myPeople.get(myPeople.size() - 1).assertWorksWith(p2, myPeople.get(pIndex));
			myPeople.get(pIndex).assertWorksWith(p1, myPeople.get(myPeople.size() - 1));	
		}
		else{
			myPeople.get(pIndex).assertWorksWith(p1, myPeople.get(pIndex2));
			myPeople.get(pIndex2).assertWorksWith(p2, myPeople.get(pIndex));
		}
	}

	/**
		checks that a person works with another person (reflexive).
		takes in the names of the people in a set of strings.
		
		
		@param:String p, String p2
		@returns: boolean
	*/
	public boolean e_works_with(String p, String p2) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateWorksWith(p, p2))
				return true;
		}
		return false;
	}

	/**
		assert that a person is assigned to a room. if the person or room do not exist, create them.
		takes in the names of the person and room as strings.
		
		
		@param: String p, String room
	*/
	public void a_assign_to(String p, String room) throws Exception {
		int pIndex = -1;
		int rIndex = -1;
		
		for(int i = 0; i<myPeople.size(); i++){
			if(myPeople.get(i).evaluatePerson(p)){
				pIndex = i;
				break;
			}
		}
		for(int i = 0; i < roomNames.size(); i++){
			if(roomNames.get(i).evaluateRoom(room)){
				rIndex = i;
				break;
			}
		}
		if(pIndex == -1 && rIndex == -1){
			a_person(p);
			a_room(room);
			myPeople.get(myPeople.size() - 1).assertAssignedRoom(p, room);
			roomNames.get(roomNames.size() - 1).assertAssignPerson(myPeople.get(myPeople.size() - 1), room);
		}
		else if(pIndex == -1 && rIndex != -1){
			a_person(p);
			myPeople.get(myPeople.size() - 1).assertAssignedRoom(p, room);
			roomNames.get(rIndex).assertAssignPerson(myPeople.get(myPeople.size() - 1), room);
		}
		else if(rIndex == -1 && pIndex != -1){
			a_room(room);
			myPeople.get(pIndex).assertAssignedRoom(p, room);
			roomNames.get(roomNames.size() - 1).assertAssignPerson(myPeople.get(pIndex), room);
		}
		else{
			myPeople.get(pIndex).assertAssignedRoom(p, room);
			roomNames.get(rIndex).assertAssignPerson(myPeople.get(pIndex), room);
		}
	}

	/**
		checks that a person is assigned to a specified room. 
		takes in the names of the person and room as strings.
		
		
		@param:String p, String room
		@returns: boolean
	*/
	public boolean e_assign_to(String p, String room) {
		for(int i = 0; i < myPeople.size();i++){
			if(myPeople.get(i).evaluateAssignedRoom(p, room))
				return true;
		}
		return false;
	}

	/**
		function to introduce a new room to the environment.
		takes in the name of the room in string form
		
		
		@param: String name
	*/
	public void a_room(String r) {
		if(!e_room(r)){
		Room aroom = new Room(r);
		roomNames.addElement(aroom);
		}
	}

	/**
		function to check the existance of a room in the environment.
		takes in the name of the room in string form, and returns true or false.
		
		
		@param: String name
		@return: boolean
	*/
	public boolean e_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				return true;
		}
		return false;     
	}

	/**
		specify that a room is close to a room. if one of the rooms dont exist create them.
		takes in the names of the rooms in string form
		
		
		@param: String room, String room2
	*/
	public void a_close(String room, String room2) {
			
		int r1 = -1;
		int r2 = -1;
		
		for (int i = 0; i < roomNames.size(); i++)
		{
			if(roomNames.get(i).evaluateRoom(room))
				r1 = i;
			
			if (roomNames.get(i).evaluateRoom(room2))
				r2 = i;
		}
		
		if(r1 == -1 && r2 == -1){
			a_room(room);
			a_room(room2);
			roomNames.get(roomNames.size() - 2).assertCloseWith(room, room2);
			roomNames.get(roomNames.size() - 1).assertCloseWith(room2, room);	
		}
		else if(r1 == -1){
			a_room(room);
			roomNames.get(roomNames.size() - 1).assertCloseWith(room, room2);
			roomNames.get(r2).assertCloseWith(room2, room);	
		}
		else if (r2 == -1){
			a_room(room2);
			roomNames.get(roomNames.size() - 1).assertCloseWith(room2, room);
			roomNames.get(r1).assertCloseWith(room, room2);	
		}
		else{
			roomNames.get(r1).assertCloseWith(room, room2);
			roomNames.get(r2).assertCloseWith(room2, room);
		}
	}

	/**
		verify that a room is close to a room.
		takes in the names of the rooms in string form
		
		
		@param: String room, String room2
		@return: boolean
	*/
	public boolean e_close(String room, String room2) {
		int r1 = -1;
		int r2 = -1;
		
		for (int i = 0; i < roomNames.size(); i++)
		{
			if(roomNames.get(i).evaluateRoom(room))
				r1 = i;
			
			if (roomNames.get(i).evaluateRoom(room2))
				r2 = i;
		}
		
		if (!(r1 == -1 || r2 == -1) && roomNames.get(r1).evaluateCloseWith(room, room2) && roomNames.get(r2).evaluateCloseWith(room2, room))
			return true;
			
		return false;
	}

	/**
		specify that a room is close to a set of rooms. if one of the rooms dont exist, create them.
		takes in the names of the rooms in string form
		
		
		@param: String room, String room2
	*/
	public void a_close(String room, TreeSet<Pair<Predicate.ParamType, Object>> set) {
		String room2;
		
		while (!set.isEmpty())
		{
			room2 = (String) set.pollFirst().getValue();
			a_close(room, room2);
		}
	}

	/**
		verify that a room is close to a room.
		takes in the names of the rooms in string form
		
		
		@param: String room, String room2
		@return: boolean
	*/
	public boolean e_close(String room, TreeSet<Pair<Predicate.ParamType, Object>> set) {
		String room2;
		
		while (!set.isEmpty())
		{
			room2 = (String) set.pollFirst().getValue();
			if (!e_close(room, room2))
				return false;
		}
		return true;
	}

	/**
		specify that a room is large. if the room does not exist, create it.
		takes in the name of the room in string form
		
		
		@param: String room
	*/
	public void a_large_room(String r) {
		if(!e_room(r))
			a_room(r);
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				((Room) roomNames.get(i)).assertLarge(r);
		}
		
	}

	/**
		verify that a room is large.
		takes in the name of the room in string form
		
		
		@param: String room
		@return: boolean
	*/
	public boolean e_large_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateLarge(r))
				return true;
		}		
		return false;
	}

	/**
		specify that a room is medium. if the room does not exist, create it.
		takes in the name of the room in string form
		
		
		@param: String room
	*/
	public void a_medium_room(String r) {
		if(!e_room(r))
			a_room(r);
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				((Room) roomNames.get(i)).assertMedium(r);
		}
		
	}

	/**
		verify that a room is medium.
		takes in the name of the room in string form
		
		
		@param: String room
		@return: boolean
	*/
	public boolean e_medium_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateMedium(r))
				return true;
		}		
		return false;
	}

	/**
		specify that a room is small. if the room does not exist, create it.
		takes in the name of the room in string form
		
		
		@param: String room
	*/
	public void a_small_room(String r) {
		if(!e_room(r))
			a_room(r);
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				((Room) roomNames.get(i)).assertSmall(r);
		}
		
	}

	/**
		verify that a room is small.
		takes in the name of the room in string form
		
		
		@param: String room
		@return: boolean
	*/
	public boolean e_small_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateSmall(r))
				return true;
		}		
		return false;
	}

	/**
		introduce a group exists in the environment. 
		takes in the name of the group in string form
		
		
		@param: String group
	*/
	public void a_group(String g) {
		if(!e_group(g)){
		Group agroup = new Group(g);
		groupNames.addElement(agroup);
		}
	}

	/**
		check that a group exists in the environment. 
		takes in the name of the group in string form
		
		
		@param: String group
		@return: boolean
	*/
	public boolean e_group(String g) {
		for(int i =0;i<groupNames.size();i++){
			if(groupNames.get(i).evaluateGroup(g))
				return true;
		}
		return false;
	}

	/**
		introduce a project exists in the environment. 
		takes in the name of the project in string form
		
		
		@param: String project
	*/
	public void a_project(String p) {
		if(!e_project(p)){
		Project aproject = new Project(p);
		projectNames.addElement(aproject);
		}
	}

	/**
		check that a project exists in the environment. 
		takes in the name of the project in string form
		
		
		@param: String project
		@return: boolean
	*/
	public boolean e_project(String p) {
		
		for(int i =0;i<projectNames.size();i++){
			if(projectNames.get(i).evaluateProject(p))
				return true;
		}
		return false;
	}

	/**
		specify a project is large. 
		takes in the name of the project in string form
		
		
		@param: String project
	*/
	public void a_large_project(String prj) {
		if(!e_project(prj))
			a_project(prj);
		for(int i =0;i<projectNames.size();i++){
			if(projectNames.get(i).evaluateProject(prj))
				((Project) projectNames.get(i)).assertLarge(prj);
		}
		
	}

	/**
		check that a project is large. 
		takes in the name of the project in string form
		
		
		@param: String project
		@return: boolean
	*/
	public boolean e_large_project(String prj) {
		for(int i =0;i<projectNames.size();i++){
			if(projectNames.get(i).evaluateLarge(prj))
				return true;
		}		
		return false;
	}
	
	}