public class RecursionLab
{
   public static void main(String [] args)
   {
      System.out.println(recMult(15,10)); // should display 150
      System.out.println(recPower(5,3)); // should return 125
      for (int i = 1; i < 11; i++)
      {
         System.out.printf("m(%d) = %.2f\n", i, function(i));
      }
   }
   
   public static int recMult(int num, int times)
   {
      int total = 0;
      // base case
      if (times == 0)
         return total;
      
      total = total + num + recMult(num, times-1);
      return total;
   }
   
   public static int recPower(int num, int power)
   {
      int total = 1;
      //base case
      if (power == 0)
         return 1;
      total = total * num * recPower(num, power-1);
      return total;
   }
   
   public static double function(double i)
   {
      double total = 0;
      //base case
      if (i == 0)
         return 0;
         
      total = total + i/(2*i+1) + function(i-1);
      return total;  
   }
}