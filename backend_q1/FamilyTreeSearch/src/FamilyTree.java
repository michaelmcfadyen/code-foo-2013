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
	
	public static void main(String args[]) throws FileNotFoundException{
		familyTree = new ArrayList<familyMember>();
		FamilyTree ft = new FamilyTree();

		ft.setUpTree("familytree.txt");
		
//		familyMember son = new familyMember("Steve","Jones",SEX.MALE);
//		familyTree.add(son);
//		familyMember Dad = new familyMember("Dave","Jones",SEX.MALE);
//		Dad.addChild(son);
//		familyTree.add(Dad);
//		familyMember Mum = new familyMember("Susan","Jones",SEX.FEMALE);
//		Mum.addChild(son);
//		familyTree.add(Mum);
		
		MergeSort m = new MergeSort();
		familyMember[] tree = m.sort(familyTree);
		int gen = -1;
		for(familyMember f : tree){
			if(f.generation() != gen){
				gen = f.generation();
				System.out.println("------------------");
				System.out.println("Generation "+ gen);
				System.out.println("------------------");
			}
			System.out.println(f.toString());
		}
		
//		for(familyMember f: ft.searchGen(0, tree)){
//			System.out.println("Found:"+f.toString());
//		}
//		System.out.println(SEX.FEMALE.toString().toLowerCase());
//		for(familyMember f: ft.searchTree("Jones", 1, tree)){
//			System.out.println("Found :"+f);
//		}
		
	}
	public ArrayList<familyMember> searchTree(String name,int generation, familyMember[] tree){
		ArrayList<familyMember> results = new ArrayList<familyMember>();

		ArrayList<familyMember> temp = searchGen(generation,tree);
		
		String[] names = name.split(" ");
		String forename = names[0];
		if(names.length > 1){
			String surname = names[1];
			for(familyMember fm : searchTwoNames(forename,surname,tree)){
				if(temp.contains(fm)){
					results.add(fm);
				}
			}
		}
		else{
			for(familyMember fm : searchOneName(forename,tree)){
				if(temp.contains(fm)){
					results.add(fm);
				}
			}
		}
		return results;
	}
	//searches family tree for a matching forename and surname combination
	//sequential search
	public ArrayList<familyMember> searchTwoNames(String forename, String surname, familyMember[] tree){
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
	public ArrayList<familyMember> searchOneName(String name, familyMember[] tree){
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
	public ArrayList<familyMember> searchGen(int gen, familyMember[] tree){
		int split = tree.length / 2;
		boolean finished = false;
		ArrayList<familyMember> results = new ArrayList<familyMember>();
		
		while(finished){
			if(tree[split].generation() < gen){
				split += (tree.length - split) /2;
			}
			else if(tree[split].generation() > gen){
				split = (tree.length - split) /2;
			}
			else
				finished = true;
		}
		for(int i = split; i >= 0 && tree[i].generation() == gen; i--){
			results.add(tree[i]);
		}
		for(int i = split+1; i < tree.length && tree[i].generation() == gen; i++){
			results.add(tree[i]);
		}
		return results;
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
					else{
						System.out.printf("%s is invalid\n'parent|children'\n", section);
						System.exit(0);
					}
				}
				familyMember curr;
				if(mother == null || father == null){
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


