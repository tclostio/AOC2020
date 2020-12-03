package day3;

import java.util.ArrayList;

public class Slope {
	
	private static int positionRow, positionCol;
	
	public static void printMaze(char[][] maze) {	//Prints maze
		for (int i = 0; i < 323; i++) {
			for (int j = 0; j < 31; j++)
				System.out.print(maze[i][j]);
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static int counter(char[][] maze) {		//counts how many trees hit in maze
		int total = 0;
		for(int i = 0; i < 323; i++) {
			for (int j = 0; j < 31; j++) {
				if (maze[i][j] == 'X') {
					++total;
				}
			}
		}
		return total;
	}
	
	public static void solve(char[][] maze, int posRow, int posCol, int right, int down) {
		if (maze[posRow][posCol] == '#') {			// '#' represents trees hit
			maze[posRow][posCol] = 'X';
		}
		else if (maze[posRow][posCol] == '.') {		// '.' represents open space
			maze[posRow][posCol] = 'O';
		}

		if (posCol <= (30-right) && posRow <= (322-down)) {	//following direction normally
			solve(maze, posRow+down, posCol+right, right, down);	//recursion :)											
		}
		else if (posCol > (30-right) && posRow <= (322-down)) {	//following direction once edge is met
			solve(maze, posRow+down, (right-1)-(30-posCol), right, down);												
		}

		
	}
	
	public static void main(String args[]) {
		ArrayList<Character> input = new ArrayList<Character>();
		
		try {										//opens file and puts it in array list
	        new FileOpenReadWrite("input_day3.txt", input);
		}
	    catch(Exception e) {						//if file doesn't exist
	        System.err.println(e);
	    }
		
		char[][] maze = new char[323][31];			//input file was 323 lines long with 31 characters each
		char[][] maze1 = new char[323][31];			//there's 5 mazes because of part 2
		char[][] maze2 = new char[323][31];
		char[][] maze3 = new char[323][31];
		char[][] maze4 = new char[323][31];
		int index = 0;
		
		for(int i = 0; i < 323; i++) {
			for (int j = 0; j < 31; j++) {			//puts each character into respective columns/rows
				maze[i][j] = input.get(index);
				maze1[i][j] = input.get(index);
				maze2[i][j] = input.get(index);
				maze3[i][j] = input.get(index);
				maze4[i][j] = input.get(index++);
				}
		}
		solve(maze, 1,3,3,1);
		System.out.println(counter(maze));
		solve(maze1, 1,1,1,1);
		System.out.println(counter(maze1));
		solve(maze2, 1,5,5,1);
		System.out.println(counter(maze2));
		solve(maze3, 1,7,7,1);
		System.out.println(counter(maze3));
		solve(maze4, 2,1,1,2);
		System.out.println(counter(maze4));
		
		long total = (counter(maze)*counter(maze1)*counter(maze2)*counter(maze3));
		System.out.println(total*counter(maze4));	//really large number, too big for Java


	}
		
}
