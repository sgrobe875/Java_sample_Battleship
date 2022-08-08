import java.util.Scanner;
import java.io.*;

public class Exceptions
{
   public static void main(String [] args)
   {  
      try
      {
         Scanner s = new Scanner(new File("somefile.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not found.");
      }
      try
      {   
         int [] nums = {5,7,3,9};
         System.out.println(nums[11]);
      }
      catch ( ArrayIndexOutOfBoundsException e)
      {
         
      }
   }
}