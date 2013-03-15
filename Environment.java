
import java.util.TreeSet;
import java.util.LinkedList;


public class Environment extends PredicateReader implements SisyphusPredicates {

	private static final Environment myEnvir = new Environment("myenvironment");
	
	@SuppressWarnings("unused")
	private static final String Object = null;
	LinkedList<Person> myPeople  = new LinkedList<Person>();
	LinkedList<Group> groupNames = new LinkedList<Group>();
	LinkedList<Room> roomNames = new LinkedList<Room>();
	LinkedList<Project> projectNames = new LinkedList<Project>(); 
	public Environment(PredicateReader p) {
		super(p);
		
	}
	public Environment(String name)
	{
		super(name);
	}
	public static Environment get(){
		return myEnvir;
	}

	@Override
	public void a_person(String p) {
		if(!e_person(p)){
		Person aperson = new Person(p);
		myPeople.add(aperson);
		}
	}

	@Override
	public boolean e_person(String p) {
		
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				return true;
		}
		return false;
	}

	@Override
	public void a_secretary(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertSecretary(p);
		}
		
	}

	@Override
	public boolean e_secretary(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateSecretary(p))
				return true;
		}		
		return false;
	}

	@Override
	public void a_researcher(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertResearcher(p);
		}
		
	}

	@Override
	public boolean e_researcher(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateResearcher(p))
				return true;
		}	
		return false;
	}

	@Override
	public void a_manager(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertManager(p);
		}
		
	}

	@Override
	public boolean e_manager(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateManager(p))
				return true;
		}
		return false;
	}

	@Override
	public void a_smoker(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertSmoker(p);
		}

		
	}

	@Override
	public boolean e_smoker(String p) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateSmoker(p))
				return true;
		}
		return false;
	}

	@Override
	public void a_hacker(String p) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertHacker(p);
		}

	}

	@Override
	public boolean e_hacker(String p) {
		
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateHacker(p))
				return true;
		}
		return false;
	}

	@Override
	public void a_in_group(String p, String grp) {
		if(!e_person(p))
				a_person(p);
		
		
		else{
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertInGroup(p, grp);
		}
		}
	}

	@Override
	public boolean e_in_group(String p, String grp) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateInGroup(p, grp))
				return true;
		}
		return false;
	}

	@Override
	public void a_group(String p, String grp) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertInGroup(p, grp);
		}
		int temp =0;
		while((temp <groupNames.size()) && !groupNames.get(temp).evaluateGroup(grp)){
			temp++;
		}
		if (temp == groupNames.size()){
			  groupNames.add(new Group(grp));
		}
	}

	@Override
	public boolean e_group(String p, String grp) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateInGroup(p, grp))
				return true;
		}
		return false;
	}

	@Override
	public void a_in_project(String p, String prj) {
		if(!e_project(p,prj))
		a_project(p,prj);
	}

	@Override
	public boolean e_in_project(String p, String prj) {
		return e_project(p,prj);
	}

	@Override
	public void a_project(String p, String prj){
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertInProject(p, prj);
		}
		int temp =0;
		while((temp <projectNames.size()) && !projectNames.get(temp).evaluateProject(prj)){
			temp++;
		}
		if (temp == projectNames.size()){
			  projectNames.add(new Project(prj));
		}
		
	}

	@Override
	public boolean e_project(String p, String prj) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateInProject(p, prj))
				return true;
		}
		return false;
	}		

	@Override
	public void a_heads_group(String p, String grp) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertHeadsGroup(p, grp);
		}
		int temp =0;
		while((temp <groupNames.size()) && !groupNames.get(temp).evaluateGroup(grp)){
			temp++;
		}
		if (temp == groupNames.size()){
			  groupNames.add(new Group(grp));
		}
	}

	@Override
	public boolean e_heads_group(String p, String grp) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateHeadsGroup(p, grp))
				return true;
		}
		return false;
	}
	

	@Override
	public void a_heads_project(String p, String prj) {
		if(!e_person(p))
			a_person(p);
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertHeadsProject(p, prj);
		}
		int temp =0;
		while((temp <projectNames.size()) && !projectNames.get(temp).evaluateProject(prj)){
			temp++;
		}
		if (temp == projectNames.size()){
			  projectNames.add(new Project(prj));
		}
		
	}

	@Override
	public boolean e_heads_project(String p, String prj) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateHeadsProject(p, prj))
				return true;
		}
		return false;
	}
	@Override
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

	@Override
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

	@Override
	public void a_works_with(String p, String p2) {
		if(!e_person(p))
			a_person(p);
		if(!e_person(p2))
			a_person(p2);
		
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p))
				((Person) myPeople.get(i)).assertWorksWith(p, p2);
		}
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluatePerson(p2)){
				if(myPeople.get(i).evaluateWorksWith(p2, p))
				((Person) myPeople.get(i)).assertWorksWith(p2, p);
			}
		}
	}

	@Override
	public boolean e_works_with(String p, String p2) {
		for(int i =0;i<myPeople.size();i++){
			if(myPeople.get(i).evaluateWorksWith(p, p2))
				return true;
		}
		return false;
	}

	@Override
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

	@Override
	public boolean e_assign_to(String p, String room) {
		for(int i = 0; i < myPeople.size();i++){
			if(myPeople.get(i).evaluateAssignedRoom(p, room))
				return true;
		}
		return false;
	}

	@Override
	public void a_room(String r) {
		if(!e_room(r)){
		Room aroom = new Room(r);
		roomNames.add(aroom);
		}
	}

	@Override
	public boolean e_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				return true;
		}
		return false;     
	}

	@Override
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

	@Override
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

	@Override
	public void a_close(String room, TreeSet<Pair<Predicate.ParamType, Object>> set) {
		String room2;
		
		while (!set.isEmpty())
		{
			room2 = (String) set.pollFirst().getValue();
			a_close(room, room2);
		}
	}

	@Override
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

	@Override
	public void a_large_room(String r) {
		if(!e_room(r))
			a_room(r);
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				((Room) roomNames.get(i)).assertLarge(r);
		}
		
	}

	@Override
	public boolean e_large_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateLarge(r))
				return true;
		}		
		return false;
	}

	@Override
	public void a_medium_room(String r) {
		if(!e_room(r))
			a_room(r);
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				((Room) roomNames.get(i)).assertMedium(r);
		}
		
	}

	@Override
	public boolean e_medium_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateMedium(r))
				return true;
		}		
		return false;
	}

	@Override
	public void a_small_room(String r) {
		if(!e_room(r))
			a_room(r);
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateRoom(r))
				((Room) roomNames.get(i)).assertSmall(r);
		}
		
	}

	@Override
	public boolean e_small_room(String r) {
		for(int i =0;i<roomNames.size();i++){
			if(roomNames.get(i).evaluateSmall(r))
				return true;
		}		
		return false;
	}

	@Override
	public void a_group(String g) {
		if(!e_group(g)){
		Group agroup = new Group(g);
		groupNames.add(agroup);
		}
	}

	@Override
	public boolean e_group(String g) {
		for(int i =0;i<groupNames.size();i++){
			if(groupNames.get(i).evaluateGroup(g))
				return true;
		}
		return false;
	}

	@Override
	public void a_project(String p) {
		if(!e_project(p)){
		Project aproject = new Project(p);
		projectNames.add(aproject);
		}
	}

	@Override
	public boolean e_project(String p) {
		
		for(int i =0;i<projectNames.size();i++){
			if(projectNames.get(i).evaluateProject(p))
				return true;
		}
		return false;
	}

	@Override
	public void a_large_project(String prj) {
		if(!e_project(prj))
			a_project(prj);
		for(int i =0;i<projectNames.size();i++){
			if(projectNames.get(i).evaluateProject(prj))
				((Project) projectNames.get(i)).assertLarge(prj);
		}
		
	}

	@Override
	public boolean e_large_project(String prj) {
		for(int i =0;i<projectNames.size();i++){
			if(projectNames.get(i).evaluateLarge(prj))
				return true;
		}		
		return false;
	}
	
	}