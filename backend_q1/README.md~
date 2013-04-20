Please find my solution to Backend Question 1 in the files FamilyTree.java, familyMember.java and SEX.java.

Algorithms
----------

To search the family tree for a certain name I used sequential search. This has an average and worst case of O(n).

To search the family tree for generation I used a binary search. To ensure the binary search operated in O(logn) the array that stored the family tree had to be sorted in terms of generation. I used the merge sort I had implemented in Backend Question 4 to do this. 

My initial thoughts were to create a binary tree however there were two obstructions that I faced. 1) No family tree contains a root, as a family must start from two people, 2) a child node would need two parent nodes.

So instead I represented the family tree as an array list ordered by generation(ascending). Each family member object holds data about their parents, children and their spouses. This allows me to easily keep track of the family tree.

Input
-----

The problem did not define what the input was or how it was to be inputted. This made the consruction of the family tree rather tricky and resulting in myself writing a rather tedious and overcomplicated input method. The file 'familytree.txt' is the input file for this program and creates the tree display in the file 'tree.txt'. Unfortunately, I did not have the time to give the user an option in the program to display the tree.

Running the Program
-------------------

To compile enter the command:
```
javac *.java
```

Please ensure that the file familytree.txt is in the same directory as the program

To run the program enter the command:
```
java FamilyTree
```

Here you will be given three options on how to search the family tree.

The program will output the names and details of the family members it finds. It will output "NO RESULTS" if no family member was found.

Thanks,

Michael McFadyen


