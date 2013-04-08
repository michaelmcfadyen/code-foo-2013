import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LetterSwap {
	ArrayList<String> threeLetterWords;

	public int swap(String a, String b){

		File file = new File(System.getProperty("user.dir")+"/three-letter-words.txt");
		Scanner lineScanner = new Scanner(file);
		threeLetterWords = new ArrayList<String>();
		while(lineScanner.hasNextLine()){
			threeLetterWords.add(lineScanner.next());
		}

		char[] array = a.toCharArray();
		String[] stages = new String[4];
		stages[0] = a;
		int mismatch = 0;
		
		System.out.println("Change "+a + " to " + b);

wordcheck:	for(int i = 0 ; i < a.length(); i++){
loop:			for(int j = 0; j < a.length(); j++){
				if(array[i] != b.charAt(i){
					array[i] = b.charAt(i);
					if(threeLetterWords.contains(new String(array))){
						mismatch++;
						stages[i+1] = new String(array);
						break loop;
					}
					else
						array[i] = a.charAt(i);
				}
				else
					break loop;
				break wordcheck;
			}
		}
		for(String s : stages){
			if(s != null)
				System.out.printf("%s -> ", s);
		}
		return mismatch;
	}	

	public static void main(String args[]) throws FileNotFoundException{
		LetterSwap s = new LetterSwap();

		Random r = new Random();
		for(int j = 0 ; j<5; j++){
			int result = s.swap(list[r.nextInt(list.length-1)], list[r.nextInt(list.length-1)]);
			System.out.println(result + " moves");
		}
	}
}
