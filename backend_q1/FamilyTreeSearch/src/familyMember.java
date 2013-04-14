import java.util.ArrayList;


public class familyMember {
	private familyMember mother;
	private familyMember father;
	private String forename;
	private String surname;
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
		this.sex = sex;
	}
	public void setForename(String f){
		forename = f;
	}
	public void setSurname(String s){
		surname = s;
	}
	public void setMother(familyMember m){
		mother = m;
	}
	public void setFather(familyMember f){
		father = f;
	}
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
	public SEX getSex(){
		return sex;
	}
	public int generation(){
		int gen = 0;
		int fgen;
		int mgen;
		if(mother == null && father == null);
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
		return getForename() + " " + getSurname();
	}
	
}