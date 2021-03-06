import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LetterSwap {
	private static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
											'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
											'u', 'v', 'w', 'x', 'y', 'z' };
	public ArrayList<String> dictionary;	//stores all legal three letter words
	public String start;
	public String end;
	public ArrayList<String> visited = new ArrayList<String>();	//visited contains all words that we know we cannot make the end word from
	boolean VERBOSE = false;		//used for testing purposes
	
	public ArrayList<String> findPath(String start, String end){
		char[] array = start.toCharArray();
		ArrayList<String> stages = new ArrayList<String>();
		stages.add(start);
		
		if(start.compareTo(end) == 0)
			return stages;			

		//iterate three time, one for each possible change you can make to the string
		for(int i = 0 ; i < start.length(); i++){
			//iterate over all chars of string each time
			for(int j = 0; j < start.length(); j++){
				//if char differs from char in end word, replace it 
				if(array[j] != end.charAt(j)){
					array[j] = end.charAt(j);
					if(VERBOSE)
						System.out.println("test word: " + new String(array));
					if(dictionary.contains(new String(array))){
						stages.add(new String(array));
						if(VERBOSE)
							System.out.println("Is word: " + new String(array));
					}
					//change char back to previous char if word is not legal(not in dictionary)
					else{
						array[j] = start.charAt(j);
					}
				}
			}
		}//make at most three changes to obtain end word
		//if our string and end string do not match, we need to find alternative path to end string
		if(new String(array).compareTo(end) != 0){
			if(VERBOSE)
				System.out.println("Failed. Look for alternative path.");
			visited.add(start);
			ArrayList<String> temp = changeLetter(array, end);
			if(temp == null)				//no solution found
				stages = null;
			else
				stages.addAll(temp);
		}
		else{
			if(VERBOSE)
				System.out.println("Success");
		}
		return stages;
	}
	
	//swaps out each char that is different to the end string with every letter in the alphabet
	//if this creates a valid word, then find the path between the new word created and the end word
	//keep track of the shortest path found to the end word
	//keep track of all the words that have been visited and do not provide a path to the end word directly
	private ArrayList<String> changeLetter(char[] array, String end){
		int smallest = Integer.MAX_VALUE;
		ArrayList<String> shortestPath = new ArrayList<String>();
		String currword = new String(array);
		ArrayList<String> makeWords = new ArrayList<String>();
			
		for(int i = 0 ; i < array.length; i++){
			if(array[i] != end.charAt(i)){
				for(char c: alphabet){
					if(array[i] != c){
						array[i] = c;
					if(dictionary.contains(new String(array)) && !visited.contains(new String(array))){
						ArrayList<String> temp = findPath(new String(array),end);
						if(temp != null && temp.size() < smallest){
							shortestPath = temp;
							smallest = shortestPath.size();
							if(VERBOSE)
								System.out.println("Size: " + temp.size());
						}
					}
					array[i] = currword.charAt(i);
					}
				}
			}
		}
		if(shortestPath.size() == 1 || shortestPath.isEmpty()){		//no solution found
			visited.add(new String(array));
			return null;
		}
		for(String s : shortestPath){
			if(visited.contains(s)){
				visited.remove(visited.indexOf(s));
			}
		}
		return shortestPath;	
	}
	public void setStart(String s){
		start = s;
	}
	public void setEnd(String e){
		end = e;
	}
	private void resultsToString(ArrayList<String> results){
		if(results == null){
			System.out.println("No solution found.");
		}
		else{
		//print out results
		System.out.println("Shortest Path");
		for(int i = 0; i < results.size(); i++){
			if(i == results.size()-1)
				System.out.printf("%s", results.get(i));
			else
				System.out.printf("%s -> ", results.get(i));
		}
		System.out.printf("\nIn %d moves.\n",results.size()-1);
		}
	}

	public static void main(String args[]) throws FileNotFoundException{
		LetterSwap s = new LetterSwap();

		//read in all legal three letter words into dictionary
		File file = new File(System.getProperty("user.dir")+"/three-letter-words.txt");
		Scanner lineScanner = new Scanner(file);
		s.dictionary = new ArrayList<String>();
		while(lineScanner.hasNextLine()){
			s.dictionary.add(lineScanner.next().toLowerCase());
		}

		//get two words from user
		//check if they are in dictionary(valid)
		Scanner input = new Scanner(System.in);
		System.out.println("Enter starting word:");
		String start = input.next().toLowerCase();
		while(!s.dictionary.contains(start)){
			System.out.println("Enter a valid starting word:");
			start = input.next().toLowerCase();
		}
		s.setStart(start);
		System.out.println("Enter ending word:");
		String end = input.next().toLowerCase();
		while(!s.dictionary.contains(end)){
			System.out.println("Enter a valid ending word:");
			end = input.next().toLowerCase();
		}
		s.setEnd(end);

		ArrayList<String> stages = s.findPath(start, end);
		s.resultsToString(stages);

	}
}
