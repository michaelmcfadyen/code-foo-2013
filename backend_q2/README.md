My solution to Backend Question 2 is located in both MergSort.java and HighScore.java.

Why MergeSort?
-------------

The HighScore class holds a float(score) and a String(player's name) and includes the basic accessor, mutator, constructor and toString methods.

I chose to implement a merge sort algorithm as although merge sort is slower than quicksort in the best case, merge sort guarantees 0(nlogn) performance and the worst case unlike quicksort that operates at 0(nsquared) at worst case.

Also, as the size of data to sort is unknown, I had to choose a algorithm that would perform well with any data size. Although, insertion sort would perform better than merge sort with a small data set, it would be out performed by mergesort on any other data set.

MergeSort scales very well to large data sets. 

Run Solution
------------

To compile use the command:
```
javac MergeSort.java HighScore.java
```

To run the program use the command:
```
java MergeSort [user|file]
```

User argument: This allows you to input the scores and names of the players by hand

File argument: This allows you to input the name of a .txt file in the current directory that has the highscores you want to sort.(eg. scores.txt)

The file must fit the format:
score name
score name
...

The program outputs the highscore table with highest score at the top,

Thanks,

Michael McFadyen

