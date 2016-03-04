//Name:Yangping Zheng
//USC loginid:yangpinz
//CS 455 PA3
//Fall 2015

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze:
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0010
 * 
 * The top left is the maze entrance, and the bottom right is the maze exit.
 * 
 */

public class MazeViewer {
   private static final int COLNUM_OF_MAZE = 0;//indicate the maze information
   private static final int ROWNUM_OF_MAZE = 1;
   
   public static void main(String[] args)  {

      String fileName = "";

      try {

         if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
         }
         else {
            fileName = args[0];

            boolean[][] mazeData = readMazeFile(fileName);
            
//            for(int i = 0;i<mazeData.length;i++) {
//            	for(int j = 0;j<mazeData[0].length;j++) {
//            		System.out.print(mazeData[i][j] + " ");
//            	}
//            	System.out.println();
//            }
//            System.out.println(mazeData.length);

            JFrame frame = new MazeFrame(mazeData);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
         }

      }
      catch (FileNotFoundException exc) {
         System.out.println("File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
      catch (NumberFormatException exc) {
		   exc.printStackTrace();
	   }
      
   }

   /**
   readMazeFile reads in and returns a maze from the file whose name is
   String given. The file format is shown in the MazeViewer class comments.
   
   @param fileName
             the name of a file to read from
   @returns 
            the array with maze contents. false at a location means there is no wall
            (0 in the file) and true means there is a wall (1 in the file).
            The first dimension is which row, and the second is which column. E.g. if the file
            started with 3 10, it would mean the array returned would have
            3 rows, and 10 columns.
   @throws FileNotFoundException
              if there's no such file (subclass of IOException)
   @throws IOException
              (hook given in case you want to do more error-checking.
               that would also involve changing main to catch other exceptions)
   */
   private static boolean[][] readMazeFile(String fileName) throws IOException {
	   String rd = "";    //to save the content of each line of maze data
	   BufferedReader in = null;    //set a new reference of a Buffer
	   boolean[][] rtn = null;		//set a 2D array to save the maze data we read from the file
	   try {
		      in = new BufferedReader(new FileReader(fileName));   //BufferReader is to read in the file and transfer to a buffer
		      rd=in.readLine();  //read a line each time
		      String[] rowComStr = rd.split(" "); //to parse the 2 numbers in the first row of maze data
		      int rowNum = Integer.parseInt(rowComStr[COLNUM_OF_MAZE]);
	    	  int colNum = Integer.parseInt(rowComStr[ROWNUM_OF_MAZE]);
	    	  rtn = new boolean[rowNum][colNum];  //define the 2D array we are going to use
	    	    
	    	  int rowCount = 0;
		      while(((rd=in.readLine())!=null)&&rowCount<rtn.length) {  //iterate over each line maze data
		    	  for(int i = 0;i<colNum;i++) {
		    		  rtn[rowCount][i]=parseBool(rd.charAt(i)-'0');   
		    	  }
		    	  rowCount++;
		      }
		      in.close();
		      
	   } catch (NumberFormatException e) {
		   System.out.println("Unexpected content in file!");  //catch the exception from parseInt()
		   throw e;
	   }
 
      return rtn; 

   }
   /*
    * parse the '1' and '0' into true and false
    * */
   
   private static boolean parseBool(int data) {  
	   return (data==0)?false:true;
   }

}

