// Name:Yangping Zheng
// USC loginid:yangpinz
// CS 455 PA3
// Fall 2015

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;


/**
   MazeComponent class
   
   A component that displays the maze and a path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   private LinkedList<MazeCoord> path;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   
   private static final int ONE_UNIT = 1;
   private static final double HALF_UNIT = 0.5;
   

   /**
      Constructs the component.
      @param maze   the maze to display
   */
   
   public MazeComponent(Maze maze) 
   {      
	   this.maze = maze;
	   this.path = maze.getPath();
   }

   
   /**
     Draws the current state of maze including a path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   Graphics2D g2 = (Graphics2D)g;
	   g2.setColor(Color.black);
	   //draw the utter wall the maze
	   Rectangle2D.Double outterWall = new Rectangle2D.Double((double)START_Y,(double)START_X,(double)
			   ((maze.numCols())*BOX_HEIGHT),(double)((maze.numRows())*BOX_WIDTH));
	   g2.draw(outterWall);
	   
	   //draw the boxes in the maze
	   for(int i = 0;i<maze.numRows();i++) {
		   for(int j = 0;j<maze.numCols();j++) {
			   if(maze.hasWallAt(new MazeCoord(i,j))) {
				   Rectangle2D.Double rect = new Rectangle2D.Double((double)((j)*BOX_HEIGHT+START_Y),
						   (double)((i)*BOX_WIDTH+START_X),(double)BOX_HEIGHT,(double)BOX_WIDTH);
				   g2.fill(rect);
			   }
		   }
	   }
	   
	   //draw the the exit point
	   g2.setColor(Color.green);
	   Rectangle2D.Double exit = new Rectangle2D.Double((double)((maze.numCols()-ONE_UNIT)*BOX_HEIGHT+START_Y),
			   (double)((maze.numRows()-ONE_UNIT)*BOX_WIDTH+START_X),(double)BOX_HEIGHT,(double)BOX_WIDTH);
	   g2.fill(exit);
	   
//	   System.out.println("DEBUG: PathSize " + path.size());
	   //draw the path
	   if(path.size()>0){
		   path.addLast(new MazeCoord(Maze.START_SEARCH_ROW,Maze.START_SEARCH_COL));
		   ListIterator<MazeCoord> iter = path.listIterator();
		   MazeCoord toCoord = iter.next();
		   MazeCoord fromCoord;
		   while(iter.hasNext()) {
			   fromCoord = toCoord;
//			   System.out.println("DEBUG: MazeCoord "+toCoord);
			   Point2D fromLoc = new Point2D.Double((fromCoord.getCol()+HALF_UNIT)*BOX_HEIGHT+START_Y,(fromCoord.getRow()+0.5)*BOX_WIDTH+START_X);
			   toCoord = iter.next();
//			   System.out.println("DEBUG: MazeCoord "+toCoord);
			   Point2D toLoc = new Point2D.Double((toCoord.getCol()+HALF_UNIT)*BOX_HEIGHT+START_Y,(toCoord.getRow()+0.5)*BOX_WIDTH+START_X);
			   Line2D.Double line = new Line2D.Double(fromLoc,toLoc);
			   g2.setColor(Color.blue);
			   g2.draw(line);
			   
		   }
	   }
   }
   
}



