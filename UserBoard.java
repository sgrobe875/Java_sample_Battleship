import java.io.*;
import java.util.ArrayList;
import java.util.Random;
/**
   The UserBoard class extends the Board class and creates what is essentially a 10 x 10 grid
   of the user's layout that the computer is able to make moves against.
*/
public class UserBoard extends Board
{
   private int movesLeft = 0;
   private ArrayList<Move> moves = new ArrayList<Move>(movesLeft);
   private Random rand = new Random();
   
   //constructor
   /**
      The constructor takes in a file name for the file containing the layout of the ships as they
      will be placed on the board, and sends it to the board constructor. It also calls the initializeMoves
      method, which creates the array of all possible moves for the computer to use.
      @param filename the name of the file containing the layout of the user's board
   */
   public UserBoard(String filename) throws IOException
   {
      super(filename);
      initializeMoves();
   }
   
   // methods
   /**
      The initializeMoves method loops through the numbers 0 through 9 twice, creating all 100 possible
      ordered pairs. These ordered pairs represent all possible moves that can be made by the computer.
   */
   private void initializeMoves()
   {
      Move m;
      for (int i = 0; i < 10; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            m = new Move(i,j);
            moves.add(movesLeft, m);   // add move to the ArrayList
            movesLeft ++;     // increase count of possible moves by 1
         }
      }
   }
   
   /**
      The makeComputerMove method randomly selects a move from the ArrayList of all possible
      moves and applies it to the user's board. This move is then removed from the list so the
      computer does not attempt to play it again. The move and, if necessary, a message containing
      information about a sunken ship are returned in an Array.
      @return an array of two strings, the first containing the move made against the player, and the
      second containing either a message about a sunken ship, or null if no ships were sunk
   */
   public String [] makeComputerMove()
   {
      Move move;
      String [] returnStatement;
      returnStatement = new String[2];
      boolean again;
      do
      {
         int index = rand.nextInt(movesLeft);   // randomly select an index
         move = moves.get(index);   // assign that move to the variable m
         moves.remove(index);    // remove that move from the list
         movesLeft = movesLeft - 1;    // decrease moves left by 1
         
         if (this.moveTaken(move))
            again = false;
         else
            again = true; 
      } while(again == true);  // remove a move if it was already taken from intelligence, try again until
                               // a random move is selected that hasn't already been done
         

      CellStatus c = applyMoveToLayout(move);
      
      returnStatement[0] = move.toString();  // add move to the return array 
      
      // assign second string to sinking message if ship was sunk, null otherwise
      if (c.equals(CellStatus.BATTLESHIP_SUNK))
         returnStatement[1] = "The computer sunk your battleship!";
      else if (c.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
         returnStatement[1] = "The computer sunk your aircraft carrier!";
      else if (c.equals(CellStatus.CRUISER_SUNK))  
         returnStatement[1] = "The computer sunk your cruiser!";
      else if (c.equals(CellStatus.DESTROYER_SUNK)) 
         returnStatement[1] = "The computer sunk your destroyer!";
      else if (c.equals(CellStatus.SUB_SUNK)) 
         returnStatement[1] = "The computer sunk your sub!";
      else
         returnStatement[1] = null;
      
      // return the array of strings   
      return returnStatement;
   }
   
   /**
      The second makeComputerMove method takes in two parameters, and works specifically with the computer
      "intelligence." This performs the same basic functions as the original makeComputerMove method, but
      also takes in a specific list of moves which are related to the previous move, allowing the computer 
      to select a move that is either above, below, right, or left of the last hit.
      @param movesList the list of "intelligent" moves
      @param length the length of the list of moves
      @return an array of two strings, the first containing the move made against the player, and the
      second containing either a message about a sunken ship, or null if no ships were sunk
   */
   public String [] makeComputerMove(String [] movesList, int length)
   {  
      Move m1, m2;
      boolean again;
      String [] returnStatement;
      String [] return2;
      returnStatement = new String[2];
      return2 = new String[2];
      boolean moveOn = false;
      // loop through the list of intelligent moves
      for (int i = 0; i < length; i++)
      {
         m1 = new Move(movesList[i]);
         if(moveTaken(m1) == true)
            moveOn = true;
      }
      // only continue on if at least one valid move in the list
      if (moveOn)
      {
         do
         {
            int index = rand.nextInt(length);    // randomly select an index
            m2 = new Move(movesList[index]);     // assign that move to the variable m
            if (this.moveTaken(m2))
               again = false;
            else
               again = true;
         } while (again == true);
         
         returnStatement[0] = m2.toString();  // add move to the return array
         CellStatus c = applyMoveToLayout(m2); 
         
         // assign second string to sinking message if ship was sunk, null otherwise
         if (c.equals(CellStatus.BATTLESHIP_SUNK))
            returnStatement[1] = "The computer sunk your battleship!";
         else if (c.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
            returnStatement[1] = "The computer sunk your aircraft carrier!";
         else if (c.equals(CellStatus.CRUISER_SUNK))  
            returnStatement[1] = "The computer sunk your cruiser!";
         else if (c.equals(CellStatus.DESTROYER_SUNK)) 
            returnStatement[1] = "The computer sunk your destroyer!";
         else if (c.equals(CellStatus.SUB_SUNK)) 
            returnStatement[1] = "The computer sunk your sub!";
         else
            returnStatement[1] = null;
            
         return returnStatement;
      }
      // if no valid moves in the list, make a random move
      else
      {
         return2 = this.makeComputerMove();
         
         return return2;
      }

      
//       // assign second string to sinking message if ship was sunk, null otherwise
//       if (c.equals(CellStatus.BATTLESHIP_SUNK))
//          returnStatement[1] = "The computer sunk your battleship!";
//       else if (c.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
//          returnStatement[1] = "The computer sunk your aircraft carrier!";
//       else if (c.equals(CellStatus.CRUISER_SUNK))  
//          returnStatement[1] = "The computer sunk your cruiser!";
//       else if (c.equals(CellStatus.DESTROYER_SUNK)) 
//          returnStatement[1] = "The computer sunk your destroyer!";
//       else if (c.equals(CellStatus.SUB_SUNK)) 
//          returnStatement[1] = "The computer sunk your sub!";
//       else
//          returnStatement[1] = null;
      
      // return the array of strings   
//       return returnStatement;
     
   }
   
   
   
   @Override
   /**
      The toString method returns the layout of the user's board in the 
      appearance of a 10 x 10 grid
      @return the 10 x 10 grid as a string
   */
   public String toString()
   {
      String s = "\nUSER BOARD:\n  1 2 3 4 5 6 7 8 9 10\n";
      char c;
      ArrayList<CellStatus> column;
      String [] letters = {"A","B","C","D","E","F","G","H","I","J"};
      // loop through each element in each array 
      for (int i = 0; i < 10; i++)
      {
         s = s + letters[i] + " ";
         column = getLayout().get(i);
         for (int j = 0; j < 10; j++)
         {
            c = column.get(j).toString().charAt(1);
            s = s + c + " ";  // add each element to s with a space between
         }

         s = s + "\n";  // add a newline every 10 elements
      }
      return s;
   }
}  
