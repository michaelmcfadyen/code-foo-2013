import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class LetterSwap {

	public int swap(String a, String b){
		char[] array = a.toCharArray();
		String[] stages = new String[4];
		stages[0] = a;
		int mismatch = 0;
		
		System.out.println("Change "+a + " to " + b);

		for(int i = 0 ; i < a.length(); i++){
			if(array[i] != b.charAt(i)){
				mismatch++;
				array[i] = b.charAt(i);
				stages[i+1] = new String(array);
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
		File file = new File(System.getProperty("user.dir")+"/three-letter-words.txt");
		Scanner lineScanner = new Scanner(file);
		String[] list = new String[1013];
		int i = 0;
		while(lineScanner.hasNextLine()){
			list[i] = lineScanner.next();
			i++;
		}
		Random r = new Random();
		for(int j = 0 ; j<5; j++){
			int result = s.swap(list[r.nextInt(list.length-1)], list[r.nextInt(list.length-1)]);
			System.out.println(result + " moves");
		}
	}
}