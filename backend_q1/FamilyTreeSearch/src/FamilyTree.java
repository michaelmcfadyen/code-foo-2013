import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FamilyTree {
	//could order family members in terms of generation
	//start with members of generation 0 and increase
	//could use binary search as a result 
	//use merge sort already implemented in q5 to order by generation
	//this allows me to use a binary search
	//name search is sequential search as the array is order by generation
	//name search is O(n) in both worst and average cases
	
	//you can either search for one name, two names, generation, generation and one name or generation and two names
	
	private static ArrayList<familyMember> familyTree;
	private static familyMember[] tree;
	
	public static void main(String args[]) throws FileNotFoundException{
		familyTree = new ArrayList<familyMember>();
		FamilyTree ft = new FamilyTree();

		ft.setUpTree("familytree.txt");
		
		MergeSort m = new MergeSort(); 
		tree = m.sort(familyTree);
		ArrayList<familyMember> results = ft.input();
		if(results.isEmpty()){
			System.out.printf("--------------\nNO RESULTS\n--------------\n");;
		}
		else{
			System.out.printf("--------------\nRESULTS\n--------------\n");
			for(familyMember fm : results){
				System.out.printf("%s\n",fm.toString());
			}
		}

	}
	public static ArrayList<familyMember> searchTree(String forename,String surname,int generation, familyMember[] tree){
		forename.toLowerCase();
		surname.toLowerCase();
		ArrayList<familyMember> results = new ArrayList<familyMember>();
		ArrayList<familyMember> nameResults = new ArrayList<familyMember>();
		ArrayList<familyMember> temp = searchGen(generation,tree);
		if(forename.compareTo("no") == 0 && surname.compareTo("no") != 0){
			nameResults = searchOneName(surname,tree);
		}
		else if(forename.compareTo("no") != 0 && surname.compareTo("no") == 0){
			nameResults = searchOneName(forename,tree);
		}
		else
			nameResults = searchTwoNames(forename,surname,tree);
		
		for(familyMember fm : nameResults){
			if(temp.contains(fm)){
				results.add(fm);
				}
			}
		return results;
	}
	//searches family tree for a matching forename and surname combination
	//sequential search
	public static ArrayList<familyMember> searchTwoNames(String forename, String surname, familyMember[] tree){
		forename.toLowerCase();
		surname.toLowerCase();
		ArrayList<familyMember> results = new ArrayList<familyMember>();
		for(familyMember f : tree){
			if(f.getForename().compareTo(forename) == 0 && f.getSurname().compareTo(surname) == 0){
				results.add(f);
			}
		}
		return results;
	}
	//searches family tree for a matching name. Can be either forename or surname
	//sequential search
	public static ArrayList<familyMember> searchOneName(String name, familyMember[] tree){
		name.toLowerCase();
		ArrayList<familyMember> results = new ArrayList<familyMember>();
		for(familyMember f : tree){
			if(f.getForename().compareTo(name) == 0 || f.getSurname().compareTo(name) == 0){
				results.add(f);
			}
		}
		return results;
	}
	//searches family tree for all persons of a certain generation
	//binary search
	private static ArrayList<familyMember> searchGen(int gen, familyMember[] tree){
		int split = tree.length / 2;
		boolean finished = true;
		ArrayList<familyMember> results = new ArrayList<familyMember>();
		while(finished){
			if(tree[split].generation() < gen){
				split += (tree.length - split) /2;
			}
			else if(tree[split].generation() > gen){
				split = split /2;
			}
			else{
				finished = false;
			}
		}
		for(int i = split; i >= 0 && tree[i].generation() == gen; i--){
			results.add(tree[i]);
		}
		for(int i = split+1; i < tree.length && tree[i].generation() == gen; i++){
			results.add(tree[i]);
		}
		return results;
	}
	
	public ArrayList<familyMember> input(){
		ArrayList<familyMember> results = new ArrayList<familyMember>();
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to:\n1.Search by name\n2.Search by generation\n3.Search by both name and genration\n4.Display family tree.\n");
		int selection = input.nextInt();
		if(selection == 1){
			String fore = userForename(input);
			String sur = userSurname(input);
			if(fore.compareTo("no") == 0)
				results = searchOneName(sur,tree);
			else if(sur.compareTo("no") == 0)
				results = searchOneName(fore,tree);
			else if(sur.compareTo("no") == 0 && fore.compareTo("no") == 0){
				System.out.println("No names entered");
				System.exit(1);
			}
			else
				results = searchTwoNames(fore,sur,tree);
		}
		else if(selection == 2){
			int generation = userGeneration(input);
			results = searchGen(generation,tree);
		}
		else if(selection == 3){
			String fore = userForename(input);
			String sur = userSurname(input);
			int gen = userGeneration(input);
			results = searchTree(fore,sur,gen,tree);
		}
		else if(selection == 4){
			displayTree();
		}
		else{
			System.out.println("Entered an invalid number.");
			System.exit(1);
		}
		return results;
	}
	private static void displayTree(){
		ArrayList<familyMember> temp = new ArrayList<familyMember>();
		for(familyMember m : tree){
			temp.add(m);
		}
		
		ArrayList<familyMember> kids = new ArrayList<familyMember>();
		
		int gen = -1;
		for(familyMember fm : temp){
			kids.add(fm);
			if(fm.getSpouse() != null)
				System.out.println(fm.nameToString() +" & "+fm.getSpouse().nameToString());
			else
				System.out.println(fm.nameToString());
			while(!kids.isEmpty()){
				kids.remove(0);
				temp.remove(fm);		
				for(familyMember child : fm.getChildren()){
					if(child.getSpouse() != null)
						System.out.printf("\t%s & %s",child.nameToString(),child.getSpouse().nameToString());
					else
						System.out.printf("\t%s ",child.nameToString());
				}
				System.out.println();
				kids.addAll(fm.getChildren());
				if(!kids.isEmpty())
					fm = kids.get(0);
			}
//			if(fm.generation() != gen){
//				gen = fm.generation();
//				System.out.printf("\ngen "+gen);
//			}
//			System.out.printf(" %s\t",fm.nameToString());
		}
	}
	private static String userForename(Scanner input){
		System.out.println("Enter Forename(no to skip):");
		String fore = "";
		if(input.hasNext());
			fore = input.next();
		return fore;
	}
	private static String userSurname(Scanner input){
		System.out.println("Enter Surname(no to skip):");
		String sur = "";
		if(input.hasNext());
			sur = input.next();
		return sur;
	}
	private static int userGeneration(Scanner input){
		System.out.println("Enter Generation:");
		int gen = -1;
		while(!input.hasNextInt()){
			System.out.println("Enter generation(int):");
			input.next();
		}
		gen = input.nextInt();
		return gen;
	}
	
	
	//file format either
	//forname surname sex
	//or
	//forname surname sex 'parents' mother's name father's name
	//or
	//forename surname sex 'children' child1 child2 ...
	//all family members reference must all ready be in the family tree
	//eg. can not reference child1 before child1 is created
	public void setUpTree(String filename) throws FileNotFoundException{
		File file = new File(System.getProperty("user.dir")+"/"+filename);
		Scanner fileScanner = new Scanner(file);
		while(fileScanner.hasNextLine()){
			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			String forename = lineScanner.next().toLowerCase();
			String surname = lineScanner.next().toLowerCase();
			String sex = lineScanner.next().toLowerCase();
			SEX s;
			if(sex.compareTo(SEX.FEMALE.toString().toLowerCase()) == 0)
				s = SEX.FEMALE;
			else
				s = SEX.MALE;
			if(!lineScanner.hasNext()){
				familyTree.add(new familyMember(forename,surname,s));
			}
			else{
				String section = lineScanner.next().toLowerCase();
				familyMember mother = null;
				familyMember father = null;
				ArrayList<familyMember> children = new ArrayList<familyMember>();
				familyMember spouse = null;
				while(lineScanner.hasNext()){
					if(section.compareTo("parents") == 0 ){
						String motherfore = lineScanner.next().toLowerCase();
						String mothersur = lineScanner.next().toLowerCase();
						String fatherfore = lineScanner.next().toLowerCase();
						String fathersur = lineScanner.next().toLowerCase();
						for(familyMember m : familyTree){
							if(m.getForename().compareTo(motherfore) == 0 && m.getSurname().compareTo(mothersur) == 0){
								mother = m;
							}
							else if(m.getForename().compareTo(fatherfore) == 0 && m.getSurname().compareTo(fathersur) == 0){
								father = m;
							}
						}
					}
					else if(section.compareTo("children") == 0){
						String childfore = lineScanner.next().toLowerCase();
						String childsur = lineScanner.next().toLowerCase();
						for(familyMember m : familyTree){
							if(m.getForename().compareTo(childfore) == 0 && m.getSurname().compareTo(childsur) == 0){
								children.add(m);
							}
						}
					}
					else if(section.compareTo("spouse") == 0){
						String fore = lineScanner.next().toLowerCase();
						String sur = lineScanner.next().toLowerCase();
						for(familyMember fm : familyTree){
							if(fm.getForename().compareTo(fore) == 0 && fm.getSurname().compareTo(sur) == 0){
								spouse = fm;
								break;
							}
						}
						if(spouse == null)
							System.out.println("Spouse not in family tree");
					}
					else{
						System.out.printf("%s is invalid\n'parent|children|spouse'\n", section);
						System.exit(0);
					}
				}
				familyMember curr = null;
				if(spouse != null && familyTree.contains(new familyMember(forename,surname,s))){
					familyTree.get(familyTree.indexOf(new familyMember(forename,surname,s))).setSpouse(spouse);
				}
				else if(spouse != null && !familyTree.contains(new familyMember(forename,surname,s))){
					curr = new familyMember(forename, surname, spouse, s);
				}
				else if(spouse == null && (mother == null || father == null)){
					curr = new familyMember(forename,surname,s);
				}
				else{
					curr = new familyMember(mother,father,forename,surname,s);
				}
				for(familyMember child: children){
					curr.addChild(child);
				}
				familyTree.add(curr);
			}
		}
	}
}

