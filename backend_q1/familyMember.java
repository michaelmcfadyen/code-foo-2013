
public class familyMember {
	String mother;
	String father;
	String forename;
	String surname;
	ArrayList<String> children;

	public familyMember(String m, String f, String fore, String sur){
		mother = m;
		father = f;
		forename = fore;
		surname = sur;
		children = new ArrayList<String>();
	}
	public void setForename(String f){
		forename = f;
	}
	public void setSurname(String s){
		surname = s;
	}
	public int generation(){
		int gen = 0;
		if(mother != null && father !=null){
			int mgen = mother.generation(); 
			int fgen = father.generation();
			if(mgen > fgen)
				gen = 1+mgen;
			else
				gen = 1+fgen;
		}
		return gen;
	}
	public String[] getChildren(){
		return children;
	}
	public void addChild(String child){
		children.add(child);
	}
	public int noDescendants(){
		int descedants = children.length;
		for(int i = 0 ; i < children.length; i++){
			descendants += children.get(i).noDescendants;
		}
		return descendants;
	}
	



}
