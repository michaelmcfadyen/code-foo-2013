import java.util.ArrayList;


public class familyMember {
	private familyMember mother;
	private familyMember father;
	private String forename;
	private String surname;
	private familyMember spouse;
	private ArrayList<familyMember> children;
	private SEX sex;

	public familyMember(familyMember m, familyMember f, String fore, String sur, SEX s){
		mother = m;
		father = f;
		forename = fore;
		surname = sur;
		children = new ArrayList<familyMember>();
		if(!m.getChildren().contains(this));
			m.addChild(this);
		if(!f.getChildren().contains(this))
			f.addChild(this);
		sex = s;
	}
	public familyMember(String f, String s,SEX sex){
		forename = f;
		surname = s;
		mother = null;
		father = null;
		children = new ArrayList<familyMember>();
		spouse = null;
		this.sex = sex;
	}
	public familyMember(String f, String s,familyMember sp,SEX sex){
		forename = f;
		surname = s;
		mother = null;
		father = null;
		children = sp.getChildren();
		spouse = sp;
		sp.setSpouse(this);
		this.sex = sex;
	}
	//mutator methods
	public void setForename(String f){
		forename = f;
	}
	public void setSurname(String s){
		surname = s;
	}
	public void setMother(familyMember m){
		mother = m;
		if(!m.getChildren().contains(this))
			m.addChild(this);
	}
	public void setFather(familyMember f){
		father = f;
		if(!f.getChildren().contains(this))
			f.addChild(this);
	}
	public void setSpouse(familyMember s){
		spouse = s;
		if(s.getSpouse() == null)
			s.setSpouse(this);
	}
	//accessor methods
	public String getForename(){
		return forename;
	}
	public String getSurname(){
		return surname;
	}
	public familyMember getFather(){
		return father;
	}
	public familyMember getMother(){
		return mother;
	}
	public familyMember getSpouse(){
		return spouse;
	}
	public SEX getSex(){
		return sex;
	}
	//generation is dynamic and allows for older generations to be added to the family tree
	public int generation(){
		int gen = 0;
		int fgen;
		int mgen;
		if(mother == null && father == null){
			familyMember spouse = this.getSpouse();
			if(spouse == null || spouse.getFather() == null && spouse.getMother() == null){
				return 0;
			}
			else
				gen = spouse.generation();
			
		}
		else if(mother == null){
			fgen = father.generation();
			gen = 1 + fgen;
		}
		else if(father == null){
			mgen= mother.generation();
			gen = 1 + mgen;
		}
		else{
			mgen = mother.generation();
			fgen = father.generation();
			if(mgen > fgen)
				gen = 1 + mgen;
			else
				gen = 1 + fgen;
		}
		return gen;
	}
	public ArrayList<familyMember> getChildren(){
		return children;
	}
	public void addChild(familyMember child){
		children.add(child);
		if(getSex() == SEX.MALE && getFather() == null)
			child.setFather(this);
		else if(getMother() == null)
			child.setMother(this);
		else ;
	}
	public int noDescendants(){
		int descendants = children.size();
		for(int i = 0 ; i < children.size(); i++){
			descendants += children.get(i).noDescendants();
		}
		return descendants;
	}
	public String toString(){
		return getForename() + " " + getSurname() + " - Generation: " + generation() + " - Sex: "+getSex()+
				" - Parents: "+getMother() + " & "+getFather();
	}
	public String nameToString(){
		return getForename() + " " + getSurname();
	}
}