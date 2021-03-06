import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class MergeSort {
	HighScore[] temp;
	HighScore[] scoresList;
	
	public void sort(HighScore[] scoresls){
		int len = scoresls.length;
		temp = new HighScore[len];
		scoresList = scoresls;
		mergesort(0, len - 1);
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
			temp[i] = scoresList[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		while(i <= middle && j <= high){
			if(temp[i].getScore() >= temp[j].getScore()){
				scoresList[k] = temp[i];
				i++;
			}
			else{
				scoresList[k] = temp[j];
				j++;
			}
			k++;
		}
		while(i <= middle){
			scoresList[k] = temp[i];
			i++;
			k++;
		}
		while(j <= high){
			scoresList[k] = temp[j];
			j++;
			k++;
		}
	}
	//args - [file|user]
	public static void main(String args[]) throws FileNotFoundException{
		MergeSort m = new MergeSort();
		HighScore[] scores = new HighScore[0];
		if(args.length == 0){
			System.out.println("No argument provided [file|user]");
			System.exit(0);
		}
		else if(args[0].toLowerCase().compareTo("file") == 0)
			scores = fromFile();
		else if(args[0].toLowerCase().compareTo("user") == 0){
			scores = byHand();
		}
		else{
			System.out.println("Invalid command line argument[file|user]");
			System.exit(0);	
		}
		m.sort(scores);
		System.out.println("---------------------\nHIGHSCORES\n---------------------");
		for(HighScore h : scores){
			System.out.println(h.toString());
		}
	}
	public static HighScore[] byHand(){
		Scanner in = new Scanner(System.in);
		System.out.println("How many entries would you like to make?:");
		while(!in.hasNextInt()){
			in.next();
			System.out.println("How many entries would you like to make?:");
		}
		int size = in.nextInt();

		HighScore[] scores = new HighScore[size];

		for(int i = 0 ; i < size ; i++){
			System.out.println("Enter player name:");
			String name = in.next();
			System.out.println("Enter player's score:");
			while(!in.hasNextFloat()){	
				in.next();
				System.out.println("Enter player's score:");	
			}
			Float score = in.nextFloat();
			scores[i] = new HighScore(score,name);
		}
		return scores;
		
	}
	//File has to be formatted in following way:
	//One score name pair to a line 
	//score/name seperated by a space eg.
	//110.2 steve
	//22.3 BOB
	//etc  
	public static HighScore[] fromFile() throws FileNotFoundException{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter file name(current directory):");
		File file = new File(System.getProperty("user.dir")+"/"+in.next());
		while(!file.exists()){
			System.out.println("File not found\nEnter file name(current directory):");
			file = new File(System.getProperty("user.dir")+"/"+in.next());
		}
		Scanner fileScanner = new Scanner(file);
		int size = 0;
		while(fileScanner.hasNextLine()){
			size++;
			fileScanner.nextLine();
		}
		HighScore[] scores = new HighScore[size];
		fileScanner = new Scanner(file);
		int i = 0;
		while(fileScanner.hasNextLine()){
			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			Float score = lineScanner.nextFloat();
			String name = lineScanner.next();
			scores[i] = new HighScore(score, name);
			i++;
		}	
		return scores;

	}
}
