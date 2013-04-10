
public class HighScore {
    private float score;
    private String name;

    public HighScore(float s, String n){
        score = s;
        name = n;
    }
    public float getScore(){ 
    	return score; 
    }
    public String getName(){ 	
    	return name; 
    }
    public void setScore(float s){ 
    	score = s; 
    }
    public void setName(String n){	
    	name = n; 
    }
    public java.lang.String toString(){
    	return getName() + ":" + getScore();
    }
}
