import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.*;

/**
   The Board class handes the visual layout of each player's board, in terms of where
   ships are located, and whether or not they have been hit or sunk.
*/
public class Board
{
   // variables
   private ArrayList<ArrayList<CellStatus>> layout = new ArrayList<ArrayList<CellStatus>>(SIZE);  
   private Fleet fleet;
   public static final int SIZE = 10;
   
   // constructor 
   /**
      The constructor takes in a string containing a file name, and uses that file to 
      build the board.
      @param filename the file name of the file containing the board's layout
   */
   public Board(String filename) throws IOException
   {
      // set every cell to CellStatus.NOTHING
      for (int i = 0; i < SIZE; i++)   // loop through first array
      {
         ArrayList<CellStatus> column = new ArrayList<CellStatus>(SIZE);
         
         for (int j = 0; j < SIZE; j++)   // loop through second array, setting everything to nothing
            column.add(j,CellStatus.NOTHING);
         
         layout.add(i, column);
      }
      
      //initialize fleet
      fleet = new Fleet();
      
      //load from file, add ships to layout
      boolean complete = false;
      
      while (complete == false)
      {
         try
         {
            loadFromFile(filename);
            complete = true;
         }
         catch(FileNotFoundException e)
         {
            System.out.println("Could not locate file.");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Could not locate file, please try again.");
            alert.showAndWait();
         }
      } 
   }
   
   //methods
   /**
      The loadFromFile function places the ships in the correct place on the board, based on the
      information given in the inputted file.
      @param f the name of the file to be opened
   */
   private void loadFromFile(String f) throws IOException
   {
      // create scanner to go through file
      Scanner infile = new Scanner(new File(f));
      // loop through each line in the file
      while (infile.hasNext())
      {
         // read in the line, and determine which ship it is and where it starts and ends
         String line = infile.nextLine();
         char shipID = line.charAt(0);
         char startRow = line.charAt(2);
         char startCol = line.charAt(3);
         char endRow = line.charAt(5);
         if (Character.isWhitespace(endRow))
            endRow = line.charAt(6);
         
         // aircraft carrier
         if (shipID == 65)
         {
            // if horizontal, then set CellStatus.AIRCRAFT_CARRIER to five spaces across, starting
            // at the start column
            if (startRow == endRow)
            {
               for (int i = 0; i < 5; i++)
                  layout.get(startRow - 65).set(startCol - 49 + i, CellStatus.AIRCRAFT_CARRIER);
            }
            // otherwise, set CellStatus.AIRCRAFT_CARRIER to five spaces down, starting at the start row
            else
            {
               for (int i = 0; i < 5; i++)
                  layout.get(startRow - 65 + i).set(startCol - 49, CellStatus.AIRCRAFT_CARRIER);
            }
         }
         
         // battleship
         else if (shipID == 66)
         {
            // if horizontal, then set CellStatus.BATTLESHIP to four spaces across, starting
            // at the start column
            if (startRow == endRow)
            {
               for (int i = 0; i < 4; i++)
               layout.get(startRow - 65).set(startCol - 49 + i, CellStatus.BATTLESHIP);
            }
            // otherwise, set CellStatus.BATTLESHIP to four spaces down, starting at the start row
            else
            {
               for (int i = 0; i < 4; i++)
               layout.get(startRow - 65 + i).set(startCol - 49, CellStatus.BATTLESHIP);
            }
         }
         
         // cruiser
         else if (shipID == 67)
         {
            // if horizontal, then set CellStatus.CRUISER to three spaces across, starting
            // at the start column
            if (startRow == endRow)
            {
               for (int i = 0; i < 3; i++)
               layout.get(startRow - 65).set(startCol - 49 + i, CellStatus.CRUISER);
            }
            // otherwise, set CellStatus.CRUISER to three spaces down, starting at the start row
            else
            {
               for (int i = 0; i < 3; i++)
               layout.get(startRow - 65 + i).set(startCol - 49, CellStatus.CRUISER);
            }
         } 
         
         // destroyer
         else if (shipID == 68)
         {
            // if horizontal, then set CellStatus.DESTROYER to two spaces across, starting
            // at the start column
            if (startRow == endRow)
            {
               for (int i = 0; i < 2; i++)
               layout.get(startRow - 65).set(startCol - 49 + i, CellStatus.DESTROYER);
            }
            // otherwise, set CellStatus.DESTROYER to two spaces down, starting at the start row
            else
            {
               for (int i = 0; i < 2; i++)
               layout.get(startRow - 65 + i).set(startCol - 49, CellStatus.DESTROYER);
            }
         } 
         
         // submarine
         else
         {
            // if horizontal, then set CellStatus.SUB to three spaces across, starting
            // at the start column
            if (startRow == endRow)
            {
               for (int i = 0; i < 3; i++)
               layout.get(startRow - 65).set(startCol - 49 + i, CellStatus.SUB);
            }
            // otherwise, set CellStatus.SUB to three spaces down, starting at the start row
            else
            {
               for (int i = 0; i < 3; i++)
               layout.get(startRow - 65 + i).set(startCol - 49, CellStatus.SUB);
            }
         } 
      }
   }
   
   /**
      The applyMoveToLayout method finds the spot on the board that corresponds to the inputted move,
      and changes that space to be HIT or SUNK, returning the appropriate CellStatus.
      @param m the move inputted to the method
      @return the CellStatus value for that space on the board
   */
   public CellStatus applyMoveToLayout(Move m)
   {
      ArrayList<CellStatus> column;
      column = getLayout().get(m.row());
      
      // if the space contained nothing, mark it as NOTHING_HIT and return that value
      if (column.get(m.col()-1).toString().equals("oo"))
      {
         column.set(m.col()-1, CellStatus.NOTHING_HIT);
         return CellStatus.NOTHING_HIT;
      }
      // if aircraft carrier, mark space as AIRCRAFT_CARRIER_HIT   
      else if (column.get(m.col()-1).toString().equals("oA"))
      {
         column.set(m.col()-1, CellStatus.AIRCRAFT_CARRIER_HIT);
         // if this move sank the ship, mark all spaces of this type as being SUNK, and return SUNK status
         if (fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER))
         {  
            ArrayList<CellStatus> c;
            for (int i = 0; i < 10; i++)
            {
               c = layout.get(i);
               for (int j = 0; j < 10; j++)
               {
                  if (c.get(j).equals(CellStatus.AIRCRAFT_CARRIER_HIT))
                     c.set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
               }
            }
            return CellStatus.AIRCRAFT_CARRIER_SUNK;
         }
         // otherwise return hit status
         return CellStatus.AIRCRAFT_CARRIER_HIT;
      }
      // if battleship, mark space as BATTLESHIP_HIT
      else if (column.get(m.col()-1).toString().equals("oB"))
      {
         column.set(m.col()-1, CellStatus.BATTLESHIP_HIT);
         // if this move sank the ship, mark all spaces of this type as being SUNK, and return SUNK status
         if (fleet.updateFleet(ShipType.ST_BATTLESHIP))
         {
            ArrayList<CellStatus> c;
            for (int i = 0; i < 10; i++)
            {
               c = layout.get(i);
               for (int j = 0; j < 10; j++)
               {
                  if (c.get(j).equals(CellStatus.BATTLESHIP_HIT))
                     c.set(j, CellStatus.BATTLESHIP_SUNK);
               }
            }
            return CellStatus.BATTLESHIP_SUNK;
         }
         // otherwise return HIT status
         return CellStatus.BATTLESHIP_HIT;
      }
      // if cruiser, mark space as CRUISER_HIT
      else if (column.get(m.col()-1).toString().equals("oC"))
      {
         column.set(m.col()-1, CellStatus.CRUISER_HIT);
         // if this move sank the cruiser, mark all cruiser spaces as SUNK and return SUNK status
         if (fleet.updateFleet(ShipType.ST_CRUISER))
         {
            ArrayList<CellStatus> c;
            for (int i = 0; i < 10; i++)
            {
               c = layout.get(i);
               for (int j = 0; j < 10; j++)
               {
                  if (c.get(j).equals(CellStatus.CRUISER_HIT))
                     c.set(j, CellStatus.CRUISER_SUNK);
               }
            }
            return CellStatus.CRUISER_SUNK;
         }
         // otherwise return HIT
         return CellStatus.CRUISER_HIT;
      }
      // if destroyer, mark space as DESTROYER_HIT
      else if (column.get(m.col()-1).toString().equals("oD"))
      {
         column.set(m.col()-1, CellStatus.DESTROYER_HIT);
         // if this move sank the destroyer, mark all destroyer spaces as SUNK and return SUNK status
         if (fleet.updateFleet(ShipType.ST_DESTROYER))
         {
            ArrayList<CellStatus> c;
            for (int i = 0; i < 10; i++)
            {
               c = layout.get(i);
               for (int j = 0; j < 10; j++)
               {
                  if (c.get(j).equals(CellStatus.DESTROYER_HIT))
                     c.set(j, CellStatus.DESTROYER_SUNK);
               }
            }
            return CellStatus.DESTROYER_SUNK;  
         }
         // otherwise return HIT status
         return CellStatus.DESTROYER_HIT;
      }
      // if sub, mark space as SUB_HIT
      else if (column.get(m.col()-1).toString().equals("oS"))
      {
         column.set(m.col()-1, CellStatus.SUB_HIT);
         // if this move sank the sub, mark all sub spaces as SUNK and return SUNK status
         if (fleet.updateFleet(ShipType.ST_SUB))
         {
            ArrayList<CellStatus> c;
            for (int i = 0; i < 10; i++)
            {
               c = layout.get(i);
               for (int j = 0; j < 10; j++)
               {
                  if (c.get(j).equals(CellStatus.SUB_HIT))
                     c.set(j, CellStatus.SUB_SUNK);
               }
            }
            return CellStatus.SUB_SUNK;
         }
         // otherwise return HIT status
         return CellStatus.SUB_HIT;
      }
      return CellStatus.NOTHING;    // extra return statement to ensure it compiles
   }
   
   /**
      The moveTaken method determines whether or not this is a valid move (whether or not it
      has already been done)
      @param m the move to be checked
      @return true if this move is valid, false otherwise
   */
   public boolean moveTaken(Move m)
   {
      int r = m.row();
      int c = m.col() - 1;
      // return true if CellStatus toString for given move does not contain "x" or "X" (indicating a hit)
      String s = layout.get(r).get(c).toString();
      if (s.charAt(0) != 120 && s.charAt(1) != 88)
         return true;
         
      return false;
   }
   
   /**
      The getLayout method returns the ArrayList containing the layout of the board.
      @return the layout of the board
   */
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      return layout;
   }
   
   /**
      The getFleet method returns the fleet, which contains the five ships for each player.
      @return the player's fleet of five ships
   */
   public Fleet getFleet()
   {
      return fleet;
   }
   
   /**
      The gameOver method returns whether or not the game has ended, which occurs when all ships in one
      of the player's fleets have all been sunk.
      @return whether or not all of the ships have sunk in one of the player's fleets.
   */
   public boolean gameOver()
   {
      return fleet.gameOver();
   }
   
   /**
      The getStatus method returns the CellStatus for a single space on the board.
      @return the CellStatus for the given board space.
   */
   public CellStatus getStatus(int row, int col)
   {
      return layout.get(row).get(col);
   }   
}