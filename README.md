# Random Maze Generator Solver
Randomly generates a maze (or import from file) and solves the maze using BFS and DFS

Generates random maze using disjoint sets. 
<ol>
	<li>Start out with N = nxn isolated rooms. Represented by an Nx4 array of arrays, where each length 4 array corresponds to the room’s 4 wall (0 for no wall, 1 for wall).</li>
	<li>Randomly pick a room and randomly pick a wall to remove. Removing a wall corresponds to the union operation joining two rooms. Continue this until the starting room and the goal room is in the same set. This means there’s a path from start to goal.</li>
	<li>Solve this maze using BFS and print path.</li>
	<li>Solve this maze using DFS and print path.</li>
</ol>

# Running the program
Follow these steps to compile and run the program:
<ol>
	<li>Open command line</li>
	<li>navigate to folder containing the 7 .java source code files and the .txt file of maze to be imported.</li>
	<li>type "javac *.java" to compile the prgoram.</li>
	<li>type "java Prog4_main maze.txt" to execute the program with maze.txt file included. OR, type "java Prog4_main" (without file name argument) to randomly generate maze.</li>
</ol>


