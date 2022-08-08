import java.io.*;
import java.util.ArrayList;

/**
   The ComputerBoard class extends the Board class to build a class representing the
   Computer's board, which the player makes moves against.
*/
public class ComputerBoard extends Board
{
   //constructor
   /**
      The constructor takes in a filename and sends it to Board constructor to initialize the class.
      @param filename the name of the file containing the layout for the board
   */
   public ComputerBoard(String filename) throws IOException
   {
      super(filename);
   }
   
   // methods
   /**
      The makePlayerMove method takes in a Move from the player, applies it to the layout using the
      appropriate function, and returns a string if the move resulting in the sinking of a ship,
      or null otherwise.
      @param m the move being made against the computer (by the player)
      @return a string containing a message about the sinking of a ship, or null if no ship was sunk
   */ 
   public String makePlayerMove(Move m)
   {

      CellStatus c = applyMoveToLayout(m);
      
      // display appropriate message based on CellStatus
      if (c.equals(CellStatus.BATTLESHIP_SUNK))
         return "You sunk my battleship!";
      else if (c.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
         return "You sunk my aircraft carrier!";
      else if (c.equals(CellStatus.CRUISER_SUNK))  
         return "You sunk my cruiser!";
      else if (c.equals(CellStatus.DESTROYER_SUNK)) 
         return "You sunk my destroyer!";
      else if (c.equals(CellStatus.SUB_SUNK)) 
         return "You sunk my sub!";
           
      return null;
   }
   @Override
   /**
      the toString method displays the full layout of the computer board in the
      appearance of a 10 x 10 grid
      @return a 10 x 10 grid of the computer's board
   */
   public String toString()
   {
      String s = "\nCOMPUTER BOARD:\n  1 2 3 4 5 6 7 8 9 10\n";
      char c;
      ArrayList<CellStatus> column;
      String [] letters = {"A","B","C","D","E","F","G","H","I","J"};
      // loop through each element of each array in order
      for (int i = 0; i < 10; i++)
      {
         s = s + letters[i] + " ";
         column = getLayout().get(i);
         for (int j = 0; j < 10; j++)
         {
            c = column.get(j).toString().charAt(0);
            s = s + c + " ";  // add each element to s with a space in between
         }
         
         s = s + "\n";  // add a newline every 10 elements
      }
      return s;
   }
}