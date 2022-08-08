import java.io.*;
import java.util.Scanner;
public class PhaseOneTester
{
   public static void main(String [] args) throws IOException
   {
      // Test your Move class
      Move m = new Move(1,1);
      System.out.println(m);  // B2
      m = new Move("E1");
      System.out.println(m);  // E1
      
      // Test your ShipType enumerated type
      ShipType st;
      st = ShipType.ST_SUB;
      System.out.println(st);  // ST_SUB should print
      
      // Test your Fleet class
      Fleet fleet = new Fleet();

      for (int i = 0; i < 3; i++)
         fleet.updateFleet(ShipType.ST_SUB);
      System.out.println(fleet.gameOver()); // false
      for (int i = 0; i < 2; i++)
         fleet.updateFleet(ShipType.ST_DESTROYER);
      System.out.println(fleet.gameOver()); // false
      for (int i = 0; i < 5; i++)
         fleet.updateFleet(ShipType.ST_BATTLESHIP);
      System.out.println(fleet.gameOver()); // false
      for (int i = 0; i < 3; i++)
         fleet.updateFleet(ShipType.ST_CRUISER);
      System.out.println(fleet.gameOver()); // false
      for (int i = 0; i < 5; i++)
         fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
      System.out.println(fleet.gameOver()); // true
      
      // Test your board class    
      ComputerBoard computer = new ComputerBoard("compFleet.txt");
      UserBoard user = new UserBoard("userFleet.txt");
      
      Scanner keyboard = new Scanner(System.in);
      
      System.out.println(computer.getLayout().get(0).get(0));
      System.out.println(user.getLayout().get(0).get(0));
      
      System.out.println("User board at start");
      System.out.println(user);

      String[] s = user.makeComputerMove();
      System.out.println(s[0]);
      if (s[1] != null)
         System.out.println(s[1]);
      System.out.println("User board after one move");
      System.out.println(user);
      
      
      String strMove;
      String result;
      Move move;
      
      do {
         System.out.print("Move? ");
         strMove = keyboard.nextLine().toUpperCase();
         move = new Move(strMove);
         while (!computer.moveTaken(move))
         {
            System.out.print("Location not available, try again: ");
            strMove = keyboard.nextLine().toUpperCase();
            move = new Move(strMove);
         }            
         result = computer.makePlayerMove(move);
         System.out.println(computer);
      } while (result == null);
//    
   }
}