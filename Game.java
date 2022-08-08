/**
   The Game class serves as a simpler way to navigate the Battleship game. It initializes each player's
   board and can be used to make moves and display the two boards after each turn.
*/

import java.io.*;

public class Game
{
   // variables
   private ComputerBoard computer;
   private UserBoard player;
   
   // constructor
   /**
      The Game constructor calls the constructor for both the ComputerBoard and the UserBoard
   */
   public Game() throws IOException
   {
      computer = new ComputerBoard("compFleet.txt");
      player = new UserBoard("userFleet.txt");
   }
   
   // methods
   /**
      The makeComputerMove method calls the method within UserBoard to make a move against the
      user's board.
      @return an array of two strings, the first containing the move made against the player, and the
      second containing either a message about a sunken ship, or null if no ships were sunk
   */
   public String[] makeComputerMove()
   {
      return player.makeComputerMove();
   }
   
   
   public String[] makeComputerMove(String[] movesList, int length)
   {
      return player.makeComputerMove(movesList, length);
   }
   
   /**
      The makePlayerMove method takes in a string and sends it to the makePlayerMove method within
      ComputerBoard, which uses the string to make a move against the computer's board.
      @param s the player's inputted move, in the form of a string.
      @return a string containing a message about the sinking of a ship, or null if no ship was sunk
   */
   public String makePlayerMove(String s)
   {
      Move m = new Move(s);
      return computer.makePlayerMove(m);
   }
   
   /**
      The computerDefeated method checks to see whether all of the ships on the computer's board have
      been sunk.
      @return true if all of the computer's ships have been sunk, false otherwise
   */
   public boolean computerDefeated()
   {
      return computer.gameOver();
   }
   /**
      The userDefeated method checks to see whether all of the ships on the user's board have
      been sunk.
      @return true if all of the user's ships have been sunk, false otherwise
   */
   public boolean userDefeated()
   {
      return player.gameOver();
   }
   
   @Override
   /**
      Returns each board (the computer's and the user's), showing the results of the move that
      was just made against one of them.
      @return a String containing the toString for each board, with a newline in between.
   */
   public String toString()
   {
      String returnStatement;
      returnStatement = computer.toString() + "\n" + player.toString();
      return returnStatement;
   }
   
   /**
      getComputerStatus gets the CellStatus for the given space on the computer's board.
      @return the CellStatus of the given space on the computer's board
   */
   public CellStatus getComputerStatus(int row, int col)
   {
      return computer.getStatus(row,col);
   }
   
   /**
      getUserStatus gets the CellStatus for the given space on the user's board.
      @return the CellStatus of the given space on the user's board.
   */
   public CellStatus getUserStatus(int row, int col)
   {
      return player.getStatus(row,col);
   }
}