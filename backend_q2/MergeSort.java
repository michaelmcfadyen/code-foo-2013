import java.util.ArrayList;


public class MergeSort {
	Float[] listToSort;
	Float[] temp;
	
	public void sort(ArrayList<Pair<Float,String>> scores){
		int len = scores.size();
		listToSort = new Float[len];
		for(int i = 0; i < scores.size(); i++){
			listToSort[i] = scores.get(i).getScore();
		}
		temp = new Float[len];
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
			temp[i] = listToSort[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high){
			if(temp[i] <= temp[j]){
				listToSort[k] = temp[i];
				i++;
			}
			else{
				listToSort[k] = temp[j];
				j++;
			}
			k++;
		}
		while(i <= middle){
			listToSort[k] = temp[i];
			i++;
			k++;
		}
		while(j <= high){
			listToSort[k] = temp[j];
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
		for(Float sc : m.listToSort){
			System.out.println(sc);
		}
		
		
	}
}