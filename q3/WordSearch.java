import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;



public class WordSearch {
	
	//This solution works specifically with the .txt file you provided. Certain criteria in the .txt need to be met.
	//
	//There needs to be at least a space between each character in the puzzle.
	//There needs to be an empty line followed by a word that is not to be searched eg. "Words to find:" between the
	//puzzle and the words to find.
	//
	//This program outputs the starting point of the word and the direction in which it can be found.
	//
	//X coordinate refers to rows
	//Y coordinate refers to columns 
	//Origin is top left hand corner
	//
	//Michael McFadyen 
	

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
			word = rowScanner.nextLine().replaceAll("\\s", "").toLowerCase();	//remove spaces and convert to lower case
			char[] wordArray = word.toCharArray();
			
searchloop:		for(int i = 0 ; i < rows ; i++){
				for(int j = 0 ; j < columns ; j++){
					if(puzzle[i][j] == wordArray[0]){
						Direction d = searchAllDir(i,j,wordArray);
						if(d != null){
							found = true;
							System.out.println(word+" found starting at ("+i+","+j+") going " + d);
							break searchloop;	//found the word, so no need to continue looking
						}	//remove this to find all occurences of word, instead of just first occurence
					}
				}//column iterator
			}//row iterator	
			if(!found)
				System.out.println(word+" not found");
		}
		
	}
	//copy the wordsearch from file into the array representation of the puzzle
	public static void setUp(File file) throws FileNotFoundException{
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
	}

	//search all eight directions off of the initial letter
	//returns true if the word is found in one direction
	//returns false if the word is not found in any direction
	public static Direction searchAllDir(int x, int y, char[] wordToSearch){
		for(Direction dir : Direction.values()){
			if(searchOneDir(dir.fromPos(x,y), dir, wordToSearch))
				return dir;
		}

		return null; //no match found
		
	}

	//searches one of the directions until either a mismatch is found of the entire word is found
	//returns true if all letters are matched 
	//returns false if a mismatch is found
	public static boolean searchOneDir(Point p, Direction d, char[] wordToSearch){
		int i = 1; 				//skip first char in word as we have already found a match 
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
		return p.x >= 0 && p.x < puzzle.length && p.y >= 0 && p.y < puzzle[0].length;
	}

	public enum Direction {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;

	//Generates new point in accordance to what direction you are going
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
