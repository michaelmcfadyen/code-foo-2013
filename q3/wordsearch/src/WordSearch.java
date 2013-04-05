import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class WordSearch {
	
	
	public static void main(String args[]) throws FileNotFoundException{
		String[][] puzzle;

		File wordsearchFile = new File(System.getProperty("user.dir")+"/word-search.txt");
		
		Scanner columnScanner = new Scanner(wordsearchFile);
		Scanner rowScanner = new Scanner (wordsearchFile);
		
		int rows = 0;
		int columns = 0;
		
		Scanner lineScanner = new Scanner(columnScanner.nextLine());
		
		//getting the dimensions of the wordsearch
		while(lineScanner.hasNext()){
			columns++;
		}
		while(!rowScanner.nextLine().isEmpty()){
			rows++;
		}
		puzzle = new String[rows][columns];

		puzzle = setUp(puzzle,wordsearchFile);
		
		//retrieve one word at a time from text and search for it in array
		//if we find a match for first letter we need to check all 8(start with four) directions
		String word;
		
		for(int i =0 ; i < rows; i++){
			for(int j = 0; j< columns;j++){
				System.out.println(puzzle[i][j]);
			}
		}
		
	}
	public static String[][] setUp(String[][] puzzle, File file) throws FileNotFoundException{
		//creating a 2D array to represent wordsearch.
		//initialising array to match format in text
		Scanner fileScanner = new Scanner(file);
		
		int i = 0;
		int j = 0;
		String line = fileScanner.nextLine();
		while(!line.isEmpty()){
			Scanner scanner = new Scanner(line);
			while(scanner.hasNext()){
				puzzle[i][j] = scanner.next();
				j++;
			}
			j = 0;
			i++;
			line = fileScanner.nextLine();
		}
		return puzzle;
	}
}
