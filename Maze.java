// Name:Yangping Zheng
// USC loginid:yangpinz
// CS 455 PA3
// Fall 2015


import java.util.HashSet;
import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).

   Assumptions about structure of the mazeData (parameter to constructor), and the
   path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate (START_SEARCH_ROW,
        START_SEARCH_COL) (constants defined below)
     -- exit loc is maze coordinate (numRows()-1, numCols()-1) 
           (methods defined below)
     -- mazeData input 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final int START_SEARCH_COL = 0;
   public static final int START_SEARCH_ROW = 0;
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
   private static final int ONE_UNIT = 1;
   private static final int NUM_OF_DIR = 4;
   private static final int EAST_DIR = 0;
   private static final int SOUTH_DIR = 1;
   private static final int WEST_DIR = 2;
//   private static final int NORTH_DIR = 3;
   

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.

    */
   private static boolean[][] mazeData = null;
   private static boolean[][] visited = null;
   private LinkedList<MazeCoord> path = null;
   
   public Maze(boolean[][] mazeData)
   {
      this.mazeData = mazeData;
      visited =new boolean[mazeData.length][mazeData[0].length];
      path = new LinkedList<MazeCoord>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return mazeData.length;  
   }

   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return mazeData[0].length;   
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
	   if(loc.getCol()<0||loc.getCol()>=numCols()||loc.getRow()<0||loc.getRow()>=numRows()) {
		   return true;
	   }
      return mazeData[loc.getRow()][loc.getCol()];  
   }

   
   /**
      Returns path through the maze. First element is starting location, and
      last element is exit location.  If there was not a path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

      return path;   // DUMMY CODE TO GET IT TO COMPILE

   }


   /**
      Find a path through the maze if there is one.
      @return whether path was found.
    */
   public boolean search()  { 
	  if(path.size() != 0) {  //determine whether we have already have a path in hand
		 return true;
	  }
	   
      return  mazeSearch(START_SEARCH_ROW,START_SEARCH_COL);   //helper method     
   }


   private boolean mazeSearch(int row, int col) {
	   if(row==numRows()-ONE_UNIT && col==numCols()-ONE_UNIT) {  //base case: whether we have reached the end point

		   System.out.println("DEBUG: get to "+new MazeCoord(row,col));
		   return true;
	   }
	   
	   if(hasWallAt(new MazeCoord(START_SEARCH_ROW,START_SEARCH_COL))) {//if there is a wall, we cannot go through
		   return false;
	   }

	   
	   visited[row][col] = true;  //set the coordinate as visited
	   
//	   System.out.println("DEBUG: get to "+new MazeCoord(row,col));
	   
	   
	   for(int i = 0; i<NUM_OF_DIR; i++) {
		   MazeCoord next = nextStep(new MazeCoord(row,col),i);
		   if(hasWallAt(next)==false && visited[next.getRow()][next.getCol()]==false) { //if we can go for the direction, then we go
			   if(mazeSearch(next.getRow(),next.getCol()) == true) {//we can reach the end point from the point
				   path.add(next);//the point like this must belongs to a path
				   return true;
			   }
		   }
	   }
	   return false;
   }
   /*
    * Returns the next point, which can be the point on the right or left or upside or downside
    * */
   private MazeCoord nextStep(MazeCoord current, int dir) {
	   if(dir == EAST_DIR) {
		   return new MazeCoord(current.getRow(),current.getCol()+ONE_UNIT);
	   }else if(dir == SOUTH_DIR) {
		   return new MazeCoord(current.getRow()+ONE_UNIT,current.getCol());
	   }else if(dir == WEST_DIR) {
		   return new MazeCoord(current.getRow(),current.getCol()-ONE_UNIT);
	   }else
		   return new MazeCoord(current.getRow()-ONE_UNIT,current.getCol());	   
   }


}
