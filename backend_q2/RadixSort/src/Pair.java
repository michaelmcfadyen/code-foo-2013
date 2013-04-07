
@SuppressWarnings("hiding")
public class Pair<Float,String> {
    private float score;
    private String name;

    public Pair(float s, String n){
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
}