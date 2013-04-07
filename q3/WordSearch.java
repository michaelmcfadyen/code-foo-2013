import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;



public class WordSearch {
	
	static char[][] puzzle;
	
	public static void main(String args[]) throws FileNotFoundException{

		File wordsearchFile = new File(System.getProperty("user.dir")+"/word-search.txt");
		
		Scanner columnScanner = new Scanner(wordsearchFile);
		Scanner rowScanner = new Scanner (wordsearchFile);
		
		Scanner lineScanner = new Scanner(columnScanner.nextLine());

		int rows = 0;
		int columns = 0;
		//get the dimensions of the wordsearch
		while(lineScanner.hasNext()){
			lineScanner.next();
			columns++;
		}
		while(!rowScanner.nextLine().isEmpty()){
			rows++;
		}

		puzzle = new char[rows][columns];

		setUp(wordsearchFile);
		
		String word;
		boolean found;
		rowScanner.nextLine();			//skip 'Words to find' line
		while(rowScanner.hasNextLine()){
			found = false;
			word = rowScanner.nextLine();
			word = word.replaceAll("\\s","");
			word = word.toLowerCase();
			char[] wordArray = word.toCharArray();
			
searchloop:		for(int i = 0 ; i < rows ; i++){
				for(int j = 0 ; j < columns ; j++){
					if(puzzle[i][j] == wordArray[0]){
						Direction d = searchAllDir(i,j,wordArray);
						if(d != null){
							found = true;
							System.out.println(word+" found starting at ("+i+","+j+") going " + d);
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

	//search all eight directions off of the initial letter
	//returns true if the word is found in one direction
	//returns false if the word is not found in any direction
	public static Direction searchAllDir(int x, int y, char[] wordToSearch){
		Point north = Direction.NORTH.fromPos(x,y);
		Point south = Direction.SOUTH.fromPos(x,y);
		Point east = Direction.EAST.fromPos(x,y);
		Point west = Direction.WEST.fromPos(x,y);
		Point northeast = Direction.NORTHEAST.fromPos(x,y);
		Point northwest = Direction.NORTHWEST.fromPos(x,y);
		Point southeast = Direction.SOUTHEAST.fromPos(x,y);
		Point southwest = Direction.SOUTHWEST.fromPos(x,y);

		if(searchOneDir(north, Direction.NORTH, wordToSearch))
			return Direction.NORTH;
		if(searchOneDir(south, Direction.SOUTH, wordToSearch))
			return Direction.SOUTH;
		if(searchOneDir(east, Direction.EAST, wordToSearch))
			return Direction.EAST;
		if(searchOneDir(west, Direction.WEST, wordToSearch))
			return Direction.WEST;
		if(searchOneDir(northeast, Direction.NORTHEAST, wordToSearch))
			return Direction.NORTHEAST;
		if(searchOneDir(northwest, Direction.NORTHWEST, wordToSearch))
			return Direction.NORTHWEST;
		if(searchOneDir(southeast, Direction.SOUTHEAST, wordToSearch))
			return Direction.SOUTHEAST;
		if(searchOneDir(southwest, Direction.SOUTHWEST, wordToSearch))
			return Direction.SOUTHWEST;

		return null; //no match found
		
	}

	//searches one of the directions until either a mismatch is found of the entire word is found
	//returns true if all letters are matched 
	//returns false if a mismatch is found
	public static boolean searchOneDir(Point p, Direction d, char[] wordToSearch){
		int i = 1;
		while(i < wordToSearch.length){
			if(isValid(p) && wordToSearch[i] == puzzle[p.x][p.y]){
				p = d.fromPos(p.x,p.y);
				i++;
			}
			else
				return false; 	//mismatch in chars, word not found
		}
		return true; 			//no mismatches and traversed full word, so word found
	}

	//checks whether a point is inside the boundary of the wordsearch
	//returns true if point is inside boundary
	//returns false if point is outside of boundary
	public static boolean isValid(Point p){
		if(p.x >= 0 && p.x < puzzle.length && p.y >= 0 && p.y < puzzle[0].length)
			return true;
		else
			return false;
	}

	public enum Direction {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;

	public Point fromPos(int x, int y) {
		switch (this) {
		case NORTH:
			return new Point(x - 1, y);
		case SOUTH:
			return new Point(x + 1, y);
		case WEST:
			return new Point(x, y - 1);
		case NORTHEAST:
			return new Point(x - 1, y + 1);
		case NORTHWEST:
			return new Point(x - 1, y - 1);
		case SOUTHEAST:
			return new Point(x + 1, y + 1);
		case SOUTHWEST:
			return new Point(x + 1, y - 1);
		default:
			return new Point(x, y + 1);
		}
	}

    }
}
