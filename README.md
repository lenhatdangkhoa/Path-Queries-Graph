# Path Queries in a Graph
By: Khoa Le and Owen Na.
## Overview
Our goal for this project is to build a directed acyclic graph using the primitive  data structures. Our graph does not support cycles, and there are multiple queries you can perform on our graph.
## Compilation Using Makefile
To compile
```
make
```
To run (running a sample file in the Makefile)
```
make run
```
To clean up directory
```
make clean
```
## Execution
To run the program, the user has to provide a text file that contains vertices and edges separated by spaces. The format should be "vertex edge vertex". Finally to run the program, run the following command:
```
javac -d bin p4/src/Graph.java
javac -d bin -cp bin p4/src/Driver.java
java -cp bin p4.src.Driver <input.txt>
```
Change the `<input.txt>` to your file name.
## Queries
1. Find all directed paths between A and B
- The user provides the program with a starting node A and an ending node B. Then the program will output all the possible paths from A to B.
2. Find directed paths of a given length (edge count) between A and B
- The user provides the program with a starting node A, an ending node B, and the length of the path. Then the program will output all the possible paths from A to B with that given length.
3. Find shortest path(s) with minimum number of edges
- The user provides the program with a starting node A and an ending node B. Then the program will output the shortest path(s) from A to B.
4. Find paths that match a pattern between A and B
- The user provides the program with a starting node A, an ending node B, and a pattern. Then the program will output all the possible paths that matches that pattern.
- For example, let's say the pattern is `a.b`. This will match `abc` or `ac` or `adc` etc.The user has to enter vertices instead of letters. For `a.b` is the same as `(vertex1).(vertex2)`. Another example is `a(bc)*`. The user has to enter `(vertex1)((vertex2)(vertex))*`.