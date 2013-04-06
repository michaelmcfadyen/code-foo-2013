import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;



public class WordSearch {
	
	static char[][] puzzle;
	static int rows;	
	static int columns;

	public static void main(String args[]) throws FileNotFoundException{

		File wordsearchFile = new File(System.getProperty("user.dir")+"/word-search.txt");
		
		Scanner columnScanner = new Scanner(wordsearchFile);
		Scanner rowScanner = new Scanner (wordsearchFile);
		
		rows = 0;
		columns = 0;
		
		Scanner lineScanner = new Scanner(columnScanner.nextLine());

		//getting the dimensions of the wordsearch

		while(lineScanner.hasNext()){
			lineScanner.next();
			columns++;
		}
		while(!rowScanner.nextLine().isEmpty()){
			rows++;
		}
		System.out.println(rows);
		System.out.println(columns);
		puzzle = new char[rows][columns];

		setUp(wordsearchFile);
		
		//retrieve one word at a time from text and search for it in array
		//if we find a match for first letter we need to check all 8(start with four) directions
	
		//for(int i =0 ; i < rows; i++){
		//	for(int j = 0; j< columns;j++){
		//		System.out.println(puzzle[i][j]);
		//	}
		//}
		
		String word;
		boolean found;
		rowScanner.nextLine();
		while(rowScanner.hasNextLine()){
			found = false;
			word = rowScanner.nextLine();
			word = word.replaceAll("\\s","");
			word = word.toLowerCase();
			char[] wordArray = word.toCharArray();
			
searchloop:		for(int i = 0 ; i < rows ; i++){
				for(int j = 0 ; j < columns ; j++){
					if(puzzle[i][j] == wordArray[0]){
						if(searchAllDir(i,j,wordArray)){
							found = true;
							System.out.println(word+" found starting at ("+i+","+j+")");
							break searchloop;	//found the word, so no need to continue looking
						}
					}
				}
			}	
			if(!found)
				System.out.println(word+" not found");
		}
		
	}
	public static char[][] setUp(File file) throws FileNotFoundException{
		//creating a 2D array to represent wordsearch.
		//initialising array to match format in text
		Scanner fileScanner = new Scanner(file);
		
		int i = 0;
		int j = 0;
		String line = fileScanner.nextLine();
		while(!line.isEmpty()){
			Scanner scanner = new Scanner(line);
			while(scanner.hasNext()){
				puzzle[i][j] = scanner.next().charAt(0);
				j++;
			}
			j = 0;
			i++;
			line = fileScanner.nextLine();
		}
		return puzzle;
	}

	//initially only search four directions, north, east , south, west.
	public static boolean searchAllDir(int x, int y, char[] wordToSearch){
		Point north = Direction.NORTH.fromPos(x,y);
		Point south = Direction.SOUTH.fromPos(x,y);
		Point east = Direction.EAST.fromPos(x,y);
		Point west = Direction.WEST.fromPos(x,y);

		if(searchOneDir(north, Direction.NORTH, wordToSearch))
			return true;
		if(searchOneDir(south, Direction.SOUTH, wordToSearch))
			return true;
		if(searchOneDir(east, Direction.EAST, wordToSearch))
			return true;
		if(searchOneDir(west, Direction.WEST, wordToSearch))
			return true;

		return false; //no match found
		
	}

	public static boolean searchOneDir(Point p, Direction d, char[] wordToSearch){
		int i = 1;
		while(i < wordToSearch.length){
			if(isValid(p) && wordToSearch[i] == puzzle[p.x][p.y]){
				p = d.fromPos(p.x,p.y);
				i++;
			}
			else
				return false; //mismatch in chars, word not found
		}
		return true; //word found
	}

	public static boolean isValid(Point p){
		if(p.x >= 0 && p.x < rows && p.y >= 0 && p.y < columns){return true;}
		else{return false;}
	}

	public enum Direction {
	NORTH, SOUTH, EAST, WEST;

	public Point fromPos(int x, int y) {
		switch (this) {
		case NORTH:
			return new Point(x - 1, y);
		case SOUTH:
			return new Point(x + 1, y);
		case WEST:
			return new Point(x, y - 1);
		default:
			return new Point(x, y + 1);
		}
	}

}
}
