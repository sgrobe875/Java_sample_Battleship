import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class TextInterface {

    enum Player {USER, COMPUTER}

    public static void main(String[] Args) throws IOException{

        Game game = new Game();
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        Player player;

        if (rand.nextBoolean())
            player = Player.USER;
        else
            player = Player.COMPUTER;

        System.out.print("Welcome to Battleship!\n\n");
        if (player == Player.USER)
            System.out.println("You won the coin toss and get to go first.");
        else
            System.out.println("The computer won the coin toss and gets to go first.");

        while (!game.computerDefeated() && !game.userDefeated()){
            if (player == Player.USER){
                System.out.print("Your turn: ");

                String entry = input.nextLine().toUpperCase();

                //Validate user input. The user must enter a letter A-J followed by a number 1-10. The loop repeats
                //until the user enters a valid value.
                while (entry.length() < 2 || entry.length() >3 ||
                        (entry.charAt(0) < 'A' || entry.charAt(0) > 'J') ||
                        (Integer.parseInt(entry.substring(1)) < 1 || Integer.parseInt(entry.substring(1)) > 10))
                {
                    System.out.print("Invalid move, try again: ");
                    entry = input.nextLine().toUpperCase();
                }

                String sunk = game.makePlayerMove(entry);
                if (sunk != null)
                {
                    System.out.println("The Computer says: \"" + sunk + "\"");
                }
                System.out.println(game);

                //The user has completed their turn, so we change the value of player so that the next iteration of this
                //loop will process a move by the computer.
                player = Player.COMPUTER;

            } 
            else 
            {
                System.out.println("Computer's turn. Press any key to continue.");
                input.nextLine();

                //We call a Game function which returns a computer move. This allows us to print that move for the user
                //to see.
                String[] result = game.makeComputerMove();
                System.out.println("Computer Chose : " + result[0]);
                if (result[1] != null)
                {
                    System.out.println("You spontaneously exclaim: \"" + result[1] + "\"");
                }
                System.out.println(game);

                //The computer has completed their turn, so we change the value of player so that the next iteration of
                //this loop will process a move by the computer.
                player = Player.USER;

            }
        }

        System.out.println("Game Over!");
        if (player == Player.USER)
            System.out.println("The Computer wins!");
        else
        {
            System.out.println("You defeated the Computer. That was easy.");
        }
    }

}
