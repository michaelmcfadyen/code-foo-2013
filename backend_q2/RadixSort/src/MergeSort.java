import java.util.ArrayList;


public class MergeSort {
	int [] temp;
	int[] index;
	ArrayList<Pair<Float,String>> scores;
	
	public void sort(ArrayList<Pair<Float,String>> scores){
		int len = scores.size();
		index = new int[len];
		for(int i = 0; i < len; i++){
			index[i] = i;
		}
		temp = new int[len];
		this.scores = scores;
		mergesort(0, len - 1);
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
			temp[i] = index[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high){
			if(scores.get(temp[i]).getScore() <= scores.get(temp[j]).getScore()){
				index[k] = temp[i];
				i++;
			}
			else{
				index[k] = temp[j];
				j++;
			}
			k++;
		}
		while(i <= middle){
			index[k] = temp[i];
			i++;
			k++;
		}
		while(j <= high){
			index[k] = temp[j];
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
		for(int i : m.index){
			System.out.println(scores.get(i).toString());
		}
		
		
	}
}