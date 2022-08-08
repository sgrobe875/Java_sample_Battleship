/**
   The move class takes input, whether it's from the user or the randomly selected move for the
   computer, and turns it into row and column values so it can be applied to the layout of the board.
*/
public class Move
{
   //variables
   private int row;
   private int col;
   
   //constructors
   /**
      Constructor that takes integer input and converts it into row and column data that can be
      used to relate it to the board.
      @param r row number
      @param c column number (+1 to make it between 1 and 10, rather than 0 and 9)
   */
   public Move(int r, int c)
   {
      row = r;
      col = c + 1;
   }
   /**
      Constructor that takes string input and pases it to the rowToInt and colToInt functions
      in order to turn it into integers to relate it to the board.
      @param s inputted string of a letter and number indicating a space on the board
   */
   public Move(String s)
   {
      char c1 = s.charAt(0);  // first character = letter
      row = rowToInt(c1);
      char c2 = s.charAt(1);  // second character = number
      col = colToInt(c2, s);
   }
   
   /**
      The rowToInt function takes the character input from the string in the second constructor,
      and turns it into an integer corresponding with the row.
      @param c the character of the inputted string corresponding to the row
      @return the integer value of the row
   */
   private int rowToInt(char c)
   {
      int [] ascii = {65,66,67,68,69,70,71,72,73,74}; // ascii values for A through J
      // loop through ascii array until the character matches
      for (int i = 0; i < 10; i++)
         if (c == ascii[i])
            return i;   // return the index of the corresponding ascii value (this equals the row number)
            
      return c;   // extra return statement so that it compiles; not actually necessary
   }
   
   /**
      The colToInt function takes the character input from the string in the second constructor
      and turns it into an integer corresponding with the column.
      @param c the character of the inputted string corresponding to the column
      @return the integer value of the column
   */
   private int colToInt(char c, String s)
   {
      int [] ascii = {48,49,50,51,52,53,54,55,56,57}; // ascii values for 0-9
      // loop through ascii array until the character matches
      for (int i = 0; i < 10; i++)
      {
         if (c == 49 && s.length() == 3)
            return 10;
         else if (c == ascii[i])
            return i;   // return the index of the corresponding ascii value
      }
            
      return c;   // extra return statement so that it compiles; not actually necessary   
   }
   
   //getters
   /**
      The row function returns the row for the given move
      @return the row number of the move
   */
   public int row()
   {
      return row;
   }
   
   /**
      The col function returns the column for the given move
      @return the column number of the move
   */
   public int col()
   {
      return col;
   }
   
   @Override
   /**
      The toString method returns the move in a readable format, with a letter for the row
      and a number for the column
      @return a string containing the row letter and column number
   */
   public String toString()
   {
      String [] letters = {"A","B","C","D","E","F","G","H","I","J"};
      String [] numbers = {"1","2","3","4","5","6","7","8","9","10"};
      return letters[row] + numbers[col-1]; 
   }
}