
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/*
 * TCSS 342 - Spring 2016
 * Assignment 5 - MazeGenerator
 */

/**
 * Maze Class.
 * 
 * @author Samantha Ong - sjfoh and Devin Durham -
 * @version 5 May 2016
 *
 */
public class Maze {
	/**
	 * Represents the width.
	 */
	public int myWidth;
	/**
	 * Reoresents the depth.
	 */
	public int myDepth;
	/**
	 * Represents if debug is on.
	 */
	public boolean myDebug;
	
	/*
	 * Represents the maze.
	 */
	public Block[][] myMaze;
	
	/*
	 * Represents visited blocks.
	 */
	public Stack<int[]> vBlocks;
	
	/*
	 * Represents shortest path.
	 */
	public Stack<int[]> shortest;
	
	/*
	 * Represents if we found the target vertex.
	 */
	public boolean targetFound;
	
	
	/**
	 * Creates a 2d maze of size n by m.
	 * @param width the width of the maze.
	 * @param depth the depth of the maze.
	 * @param debug a boolean if set to true,
	 * will show the steps of maze creation.
	 */
	public Maze (int width, int depth, boolean debug) {
			myWidth = (width * 2) + 1;
			myDepth = (depth * 2) + 1;
			myDebug = debug;
			targetFound = false;
			/*fence post*/
			myMaze = new Block[myDepth][myWidth];
			vBlocks = new Stack<int[]>();
			shortest = new Stack<int[]>();
			makeMaze();
			createPath(1, 1);
			solutionDisplay();

	}
	
	/**
	 * Initial
	 */
	private void makeMaze() {
		for(int i = 0; i < myDepth; i++) {
			for (int j = 0; j < myWidth; j++) {
				
				if (i % 2 == 0 || j % 2 == 0) {
					myMaze[i][j] = new Block(i, j, "X ");
				} else {
					myMaze[i][j] = new Block(i, j, "  ");
				}
			}
		}
		myMaze[0][1] = new Block(0, 1, "  ");
		myMaze[myDepth-1][myWidth-2] = new Block(myDepth-1, myWidth-2, "  ");
	}
	
	/**
	 * Displays the contents of the maze. 
	 */
	public void display() {
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[i].length; j++) {
				System.out.print(myMaze[i][j].myString);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	private void solutionDisplay() {
		while(shortest.isEmpty()==false){		
			int[] cordn = shortest.pop();
			myMaze[cordn[0]][cordn[1]].myString = "* ";
		}
		
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[i].length; j++) {
				if(myMaze[i][j].myString.equals("V ")) {
					myMaze[i][j].myString = "  ";
				}
			}
		}
	}
		
	/**
	 * Recursively creates paths on the maze.
	 * Pseudo from wiki/Depth-first_search
	 * procedure DFS(G,v):
2    *   label v as discovered
3    *   for all edges from v to w in G.adjacentEdges(v) do
4    *       if vertex w is not labeled as discovered then
5    *           recursively call DFS(G,w)
	 */
	public void createPath(int row, int col) {
		
		
		myMaze[row][col].isVisited = true;
		
		myMaze[row][col].myString = "V ";
		int [] visitedBlock =  {row, col};
		vBlocks.push(visitedBlock); //keeping track of visited places.
		
		if(myDebug) {
			display();
		}
		
		if(!targetFound) {
			shortest.push(visitedBlock);
		}


		Random rand = new Random();
		
		ArrayList<Integer> tracker = new ArrayList<Integer>();
		
		Integer chosenOne = new Integer(rand.nextInt(4) + 1);
		
		while (tracker.size() < 4) {
			
			while (tracker.contains(chosenOne)) {
				chosenOne = rand.nextInt(4) + 1;
			}
			tracker.add(chosenOne);
			
			
			if(row == myDepth-2 && col == myWidth-2) {
				targetFound = true;
			}		
			
			if(chosenOne == 1 && row - 2 > 0 && myMaze[row - 2][col].myString.equals("  ")) {
				myMaze[row-1][col] = new Block(row-1, col, "  ");
				createPath((row - 2), col);
				
			}
			
			if(chosenOne == 2 && row + 2 < myDepth && myMaze[row + 2][col].myString.equals("  ")) {
				myMaze[row+1][col] = new Block(row+1, col, "  ");
				createPath((row + 2), col);
				
			}
			
			if(chosenOne == 3 && col + 2 < myWidth && myMaze[row][col + 2].myString.equals("  ")){
				myMaze[row][col+1] = new Block(row, col+1, "  ");
				createPath(row, (col + 2));
				
			}
			
			if(chosenOne == 4 && col - 2 > 0 && myMaze[row][col - 2].myString.equals("  ")) {
				myMaze[row][col - 1] = new Block(row,col - 1, "  ");
				createPath(row, (col - 2));
			}
			
			
		}
		
		/*
		 * If target hasn't been found,
		 * means by now its not the shortest path.
		 */
		if(!targetFound){
			shortest.pop();
		}
		
		
	}
	
	
	
	class Block {
		int myRow;
		int myCol;
		boolean isVisited;
		String myString;
		
		public Block(int row, int col, String leChar) {
			myRow = row;
			myCol = col;
			isVisited = false;
			myString = leChar;
		}
		
	}
	

}
