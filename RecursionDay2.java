public class RecursionDay2
{
   public static void main(String [] args)
   {
//       System.out.println(mod(41,8));
//       System.out.println(isAlpha("oneFatFox")); // true
//       System.out.println(isAlpha("oneFatF0x")); // false
//       System.out.println(strLength("oneFatF0x"));
      writeNums(5);
   }
   public static void writeNums(int n)
   {
      if (n == 1)
         System.out.print(1);
      else
      {
         writeNums(n-1);
         System.out.print(n);
      }
   }  
   // strLength(String)
   public static int strLength(String s)
   {
      // base case
      if (s.equals(""))
         return 0;
      return 1 + strLength(s.substring(1));
   }
   // isAlpha(String)
   public static boolean isAlpha(String s)
   {
      // base case
      if (s.length() == 0)
         return true;
      
      if (isAlphaChar(s.charAt(0)))
         return isAlpha(s.substring(1));
      return false;
   }
   public static boolean isAlphaChar(char ch)
   {
      if ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z')
         return true;
      return false;
   }
   //val % divisor
   public static int mod(int val, int divisor)
   {
      // base case
      if (val < divisor)
         return val;
      
      return mod(val-divisor, divisor);
   }
}