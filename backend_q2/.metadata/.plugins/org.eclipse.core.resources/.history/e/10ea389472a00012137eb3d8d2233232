import java.util.ArrayList;
import java.util.Map;


public class MergeSort {
	ArrayList<Pair<Float,String>> temp = new ArrayList<Pair<Float,String>>();
	ArrayList<Pair<Float,String>> s;
	
	public void sort(ArrayList<Pair<Float,String>> scores){
		s = scores;
		mergesort(0, scores.size() - 1);
	}
	
	private void mergesort(int low, int high){
		if(low < high){
			int middle = (low + high) / 2;
			mergesort(low,middle);
			mergesort(middle + 1, high);
			merge(low, middle, high);
		}
		

	}
	private void merge(int low, int middle, int high){
		for(int i = low; i <= high; i++){
			temp.add(i, s.get(i));
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high){
			if(temp.get(i).getScore() <= temp.get(j).getScore()){
				s.add(k, temp.get(i));
				s.remove(i+1);
				i++;
			}
			else{
				s.add(k, temp.get(j));
				s.remove(j+1);
				j++;
			}
			k++;
		}
		while(i <= middle){
			s.add(k, temp.get(i));
			s.remove(i+1);
			i++;
			k++;
		}
		while(j <= high){
			s.add(k, temp.get(j));
			s.remove(j+1);
			j++;
			k++;
		}
	}
	
	public static void main(String args[]){
		MergeSort m = new MergeSort();
		ArrayList<Pair<Float,String>> scores = new ArrayList<Pair<Float,String>>();
		scores.add(new Pair<Float, String>(210, "Joe"));
		scores.add(new Pair<Float, String>(20, "Joey"));
		scores.add(new Pair<Float, String>(12, "Phil"));
		scores.add(new Pair<Float, String>(133, "Steve"));
		scores.add(new Pair<Float, String>(78, "Mike"));
		scores.add(new Pair<Float, String>(78.7f, "Dave"));
		m.sort(scores);
		for(Pair p : m.s){
			System.out.println(p.getName()+":"+p.getScore());
		}
		
		
	}
}


