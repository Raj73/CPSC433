package trunk;


import java.util.TreeSet;
import java.util.LinkedList;



public abstract class Environment extends PredicateReader implements SisyphusPredicates {

	
	
	@SuppressWarnings("unused")
	private static final String Object = null;
	LinkedList<Person> myPeople;
	LinkedList<Group> groupNames;
	LinkedList<Room> roomNames;
	LinkedList<Project> projectNames; 
	public Environment(PredicateReader p) {
		super(p);
		
	}

	@Override
	public void a_person(String p) {
		Person aperson = new Person(p);
		myPeople.add(aperson);
		
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
				((Person) myPeople.get(i)).assertInProject(p, grp);
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
		a_group(p,prj);
	}

	@Override
	public boolean e_in_project(String p, String prj) {
		return e_group(p,prj);
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
	public void a_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_works_with(String p, TreeSet<Pair<ParamType, Object>> p2s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_works_with(String p, String p2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_works_with(String p, String p2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_assign_to(String p, String room) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_assign_to(String p, String room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_close(String room, String room2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_close(String room, String room2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean e_close(String room, TreeSet<Pair<ParamType, Object>> set) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_large_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_large_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_medium_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_medium_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_small_room(String r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_small_room(String r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_group(String g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_group(String g) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_project(String p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_project(String p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void a_large_project(String prj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean e_large_project(String prj) {
		// TODO Auto-generated method stub
		return false;
	}

	}