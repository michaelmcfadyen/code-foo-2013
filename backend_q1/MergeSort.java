import java.util.ArrayList;

public class MergeSort {
	private familyMember[] temp;
	private familyMember[] tree;
	
	public familyMember[] sort(ArrayList<familyMember> tree){
		int len = tree.size();
		temp = new familyMember[len];
		this.tree = new familyMember[len];
		for(int i = 0 ; i < len; i++){
			this.tree[i] = tree.get(i);
		}
		mergesort(0, len - 1);
		return this.tree;
	}
	
	private void mergesort(int low, int high){
		if(low < high){
			int middle = (high + low) / 2;
			mergesort(low,middle);
			mergesort(middle + 1, high);
			merge(low, middle, high);
		}

	}
	private void merge(int low, int middle, int high){
		for(int i = low; i <= high; i++){
			temp[i] = tree[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high){
			if(temp[i].generation() <= temp[j].generation()){
				tree[k] = temp[i];
				i++;
			}
			else{
				tree[k] = temp[j];
				j++;
			}
			k++;
		}
		while(i <= middle){
			tree[k] = temp[i];
			i++;
			k++;
		}
		while(j <= high){
			tree[k] = temp[j];
			j++;
			k++;
		}
	}
}