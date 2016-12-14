/*
 * TCSS 342 - Spring 2016
 * Assignment 5 - MazeGenerator
 */

/**
 * Main driver Class.
 * 
 * @author Samantha Ong - sjfoh and Devin Durham - d1duram
 * @version 3 May 2016
 *
 */
public class Main {

	/**
	 * Test controller.
	 * @param args for input.
	 */
	public static void main(String[] args) {
			System.out.println("5x5 maze with debug on: ");
			Maze maze = new Maze(5, 5, true);
			maze.display();
			
			System.out.println("10x10 maze with debug off: ");
			Maze maze10 = new Maze(10, 10, false);
			maze10.display();
			//testMaze();
	}
	
	/**
	 * Method to test the maze.
	 */
	private static void testMaze() {
		System.out.println("Debug is off test: ");
		Maze debugOff = new Maze(5, 5, false);
		debugOff.display();
		
		System.out.println("Debug is on test: ");
		Maze debugOn = new Maze(3, 3, true);
		debugOn.display();
		
		System.out.println("Check it's random: ");
		Maze isRandom = new Maze(3, 3, true);
		isRandom.display();
		
		System.out.print("Is it really random: ");
		Maze isReally = new Maze(3, 3, false);
		isReally.display();
		
		System.out.println("A bigger maze: ");
		Maze crazy = new Maze(12, 12, false);
		crazy.display();
		
	}
}
