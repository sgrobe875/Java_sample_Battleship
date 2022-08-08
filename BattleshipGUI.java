import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.Random;
import javafx.application.Platform;
import java.io.*; 

public class BattleshipGUI extends Application
{
   enum Player {USER, COMPUTER, GAME_OVER}
   Button buttonTurn = new Button("Computer's Turn");
   Button buttonNew = new Button("Start new game");
   Button buttonExit = new Button("Exit game");
   Player playerTurn = Player.USER;
   Alert alert = new Alert(AlertType.INFORMATION);
   Alert alert2 = new Alert(AlertType.INFORMATION);
   Alert alert3 = new Alert(AlertType.INFORMATION);
   private Text turnLabel = new Text();
   private HBox mainTitle = new HBox();
   private Text compLabel = new Text();
   private Text userLabel = new Text();
   private Text title = new Text("CS 110 Battleship");
   private HBox bottom = new HBox();
   private HBox userLabelBox = new HBox();
   private HBox compLabelBox = new HBox();
   private GridPane boardPane = new GridPane();
   private VBox rightSide = new VBox();
   private Move lastMove;
   private boolean firstMove = true;
   
   @Override
   public void start(Stage primaryStage) 
   {   
      alert2.setHeaderText("Welcome to Battleship!");
      
      
      
      // randomly determine who goes first
      Random rand = new Random();
      
      if (rand.nextBoolean())
      {
         playerTurn = Player.USER;
         turnLabel.setText("User's turn!");
         alert3.setHeaderText("You have won the coin toss and get to go first.");
      }
      else
      {
         playerTurn = Player.COMPUTER;
         turnLabel.setText("Computer's turn!");
         alert3.setHeaderText("The computer has won the coin toss and gets to go first.");
      }
      
      
      
      // main title
      title.setFont(Font.font("Comic Sans MS",48));
      mainTitle.getChildren().add(title);
      mainTitle.setPadding(new Insets(20,20,20,20));
      mainTitle.setAlignment(Pos.CENTER);
      mainTitle.setPrefSize(1200,100);
      mainTitle.setStyle("-fx-background-color: lightblue;");
     
      
      
      // board labels     
      userLabel.setText("User Board");
      userLabel.setFont(Font.font("Comic Sans MS",24));
      userLabelBox.setPadding(new Insets(20,20,0,20));
      userLabelBox.setAlignment(Pos.CENTER);
      userLabelBox.getChildren().add(userLabel);
      
      compLabel.setText("Computer Board");
      compLabel.setFont(Font.font("Comic Sans MS",24));
      compLabelBox.setPadding(new Insets(20,20,0,20));
      compLabelBox.setAlignment(Pos.CENTER);
      compLabelBox.getChildren().add(compLabel);
      
     
     
      primaryStage.setTitle("CS 110 Battleship");
      
      
      // right side (buttons)
      rightSide.setPadding(new Insets(20,20,20,20));
      rightSide.setSpacing(20);
      rightSide.getChildren().add(buttonTurn);
      rightSide.getChildren().add(buttonNew);
      rightSide.getChildren().add(buttonExit);


      // main pane
      FlowPane mainPane = new FlowPane();
      mainPane.setPrefSize(1200,800);
      mainPane.setPadding(new Insets(20,20,20,20));
      
      GridPane userBoard = new GridPane();
      GridPane compBoard = new GridPane();

      
      
      // bottom
      turnLabel.setFont(Font.font("Comic Sans MS",24));
      bottom.getChildren().add(turnLabel);
      bottom.setPrefSize(1200,50);
      bottom.setAlignment(Pos.CENTER);
      
      // boardPane
      boardPane.add(userLabelBox,0,0);
      boardPane.add(compLabelBox,1,0);
      boardPane.add(userBoard,0,1);
      boardPane.add(compBoard,1,1);
      boardPane.add(rightSide,2,1);
      
      try{
         // create game & boards
         Game game = new Game();
         
         createUserBoard(game, userBoard);
         createCompBoard(game, compBoard);
         
         // assemble the mainPane
         mainPane.getChildren().add(mainTitle);
         mainPane.getChildren().add(boardPane);
         mainPane.getChildren().add(bottom);

         primaryStage.setScene(new Scene(mainPane));
   
         primaryStage.show();
         
         // computer turn button
         buttonTurn.setOnAction(new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent e)
            {
               if (playerTurn == Player.COMPUTER)  // button only works when it's the computer's turn
               {
                  playerTurn = Player.USER;  // set it to be user's turn
                  turnLabel.setText("User's turn!");
                  String [] message = new String[2];
                  
                  ///////// extra credit computer intelligence /////////////////////////////////////////////////////////////////
                  
                  if (firstMove) // only do this for the first computer move of the game
                  {
                     message = game.makeComputerMove();
                     createUserBoard(game, userBoard);
                     firstMove = false;
                  }
                  else
                  {
                     CellStatus status = game.getUserStatus(lastMove.row(), lastMove.col()-1);
                     // only do this if the previous turn was a hit
                     if (status.equals(CellStatus.AIRCRAFT_CARRIER_HIT) || status.equals(CellStatus.BATTLESHIP_HIT) ||
                        status.equals(CellStatus.DESTROYER_HIT) || status.equals(CellStatus.CRUISER_HIT) || 
                        status.equals(CellStatus.SUB_HIT) )
                     {
                        // if in row A
                        if (lastMove.row() == 0)
                        {
                           // if in row A and column 1
                           if (lastMove.col() == 1)
                           {
                              String [] moves = {"A2","B1"};
                              int length = 2;
                              message = game.makeComputerMove(moves, length);
                           }
                           // if in row A and column 10
                           else if (lastMove.col() == 10)
                           {
                              String [] moves = {"A9","B10"};
                              int length = 2;
                              message = game.makeComputerMove(moves, length);
                           }
                           // if in row A but not on either end
                           else
                           {
                              Move m1 = new Move(0, lastMove.col()-2);
                              Move m2 = new Move(0, lastMove.col());
                              Move m3 = new Move(1, lastMove.col()-1);
                              String [] moves = {m1.toString(), m2.toString(), m3.toString()};
                              int length = 3;
                              message = game.makeComputerMove(moves, length); 
                           }
                        }
                        // if in row J
                        else if (lastMove.row() == 9)
                        {
                           // if in row J and column 1
                           if (lastMove.col() == 1)
                           {
                              String [] moves = {"J2","I1"};
                              int length = 2;
                              message = game.makeComputerMove(moves, length);
                           }
                           // if in row J and column 10
                           else if (lastMove.col() == 10)
                           {
                              String [] moves = {"J9", "I10"};
                              int length = 2;
                              message = game.makeComputerMove(moves, length);
                           }
                           // if in row J but not on the end
                           else
                           {
                              Move m1 = new Move(9, lastMove.col()-2);
                              Move m2 = new Move(9, lastMove.col());
                              Move m3 = new Move(8, lastMove.col()-1);
                              String [] moves = {m1.toString(), m2.toString(), m3.toString()};
                              int length = 3;
                              message = game.makeComputerMove(moves, length);
                           }
                        }
                        // if in column 1 (but not row A or row J)
                        else if (lastMove.col() == 1)
                        {
                           Move m1 = new Move(lastMove.row()+1, lastMove.col()-1);
                           Move m2 = new Move(lastMove.row()-1, lastMove.col()-1);
                           Move m3 = new Move(lastMove.row(), lastMove.col());
                           String [] moves = {m1.toString(), m2.toString(), m3.toString()};
                           int length = 3;
                           message = game.makeComputerMove(moves, length);
                        }
                        // if in column 10 (but not row A or row J)
                        else if(lastMove.col() == 10)
                        {
                           Move m1 = new Move(lastMove.row()+1, lastMove.col()-1);
                           Move m2 = new Move(lastMove.row()-1, lastMove.col()-1);
                           Move m3 = new Move(lastMove.row(), lastMove.col()-2);
                           String [] moves = {m1.toString(), m2.toString(), m3.toString()};
                           int length = 3;
                           message = game.makeComputerMove(moves, length);
                        }
                        // if a hit in the middle of the board (not row A/J or column 1/10)
                        else
                        {
                           Move m1 = new Move(lastMove.row()+1, lastMove.col()-1);  // above
                           Move m2 = new Move(lastMove.row()-1, lastMove.col()-1);  // below
                           Move m3 = new Move(lastMove.row(), lastMove.col()-2);    // left
                           Move m4 = new Move(lastMove.row(), lastMove.col());      // right
                           String [] moves = {m1.toString(), m2.toString(), m3.toString(), m4.toString()};
                           int length = 4;
                           message = game.makeComputerMove(moves, length);
                        }
                     }
                     // if last move was not a hit (choose random move like normal)
                     else
                     {
                        message = game.makeComputerMove();
                     }
                  }
                 
                  createUserBoard(game, userBoard);
                  lastMove = new Move(message[0]); // assign the String of tye move that was just made to the variable lastMove
                  
                  ////////////////////////////////////////////////////////////////////////////////////////////////////////////   
                  
                  try
                  {
                     if (!message[1].equals(null))
                     {
                        // if not null, then ship has sunk, so tell the user
                        alert.setHeaderText(message[1]); 
                        alert.showAndWait();
                        // since ship was sunk, check to see if game is over, if so display the message
                        if(game.userDefeated())
                        {
                           alert.setHeaderText("Game over! Computer wins!");
                           alert.showAndWait();
                           playerTurn = Player.GAME_OVER;
                           turnLabel.setText("Game over! Computer wins!");
                        }
                     }
                  }
                  catch (NullPointerException exception) // do nothing if null
                  {
                  }
               }
            }
         });
         
         ///////// extra credit new game button //////////////////////////////////////////////////////////////////////
         
         buttonNew.setOnAction(new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent e)
            {
               // clear out everything
               mainTitle.getChildren().clear();
               userLabelBox.getChildren().clear();
               compLabelBox.getChildren().clear();
               rightSide.getChildren().clear();
               bottom.getChildren().clear();
               mainPane.getChildren().clear();
               boardPane.getChildren().remove(userLabelBox);
               boardPane.getChildren().remove(compLabelBox);
               boardPane.getChildren().remove(userBoard);
               boardPane.getChildren().remove(compBoard);
               boardPane.getChildren().remove(rightSide);
               // create a new stage and relaunch 
               Stage newStage = new Stage();
               start(newStage);
            }
         });
         
         /////////////////////////////////////////////////////////////////////////////////////////////////////////////
         
         // button to exit the game
         buttonExit.setOnAction(new EventHandler<ActionEvent>()
         {
            public void handle(ActionEvent e)
            {
               Platform.exit();
            }
         });
      }
      catch(IOException exception)
      {
      }
      
      alert2.showAndWait();
      alert3.showAndWait();
   }
   
   
   
   // methods for boards
   // method for drawing the user's board
   public void createUserBoard(Game game, GridPane userBoard)
   {  
      // loop through each space, creating a BoardCell for each   
      for (int i = 0; i < 10; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            CellStatus cs = game.getUserStatus(i,j);
            BoardCell b = new BoardCell(i,j,cs, true);
            userBoard.add(b,j,i);
            userBoard.setPadding(new Insets(20,5,20,0));
         }
      }
   }
   // method for drawing the computer's board
   public void createCompBoard(Game game, GridPane compBoard)
   {
      // loop through each space, creating a BoardCell for each
      for (int i = 0; i < 10; i++)
      {
         for (int j = 0; j < 10; j++)
         {
            CellStatus cs = game.getComputerStatus(i,j);
            BoardCell b = new BoardCell(i,j,cs, false);
            compBoard.add(b,j,i);
            compBoard.setPadding(new Insets(20,20,20,5));
            
            // add mouse listener to each space on the board
            b.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
               public void handle(MouseEvent event)
               {
                  BoardCell bc = (BoardCell)event.getSource();
                  // only recognize spaces that haven't yet been hit
                  if (playerTurn == Player.USER && (bc.cellStatus() == CellStatus.NOTHING || 
                     bc.cellStatus() == CellStatus.AIRCRAFT_CARRIER || bc.cellStatus() == CellStatus.BATTLESHIP || 
                     bc.cellStatus() == CellStatus.CRUISER || bc.cellStatus() == CellStatus.DESTROYER || 
                     bc.cellStatus() == CellStatus.SUB))
                  {
                     // set to the computer's turn
                     playerTurn = Player.COMPUTER;
                     turnLabel.setText("Computer's turn!");
                     // turn the cell into a move and apply to the board
                     Move m = new Move(bc.row(), bc.col());
                     String message = game.makePlayerMove(m.toString());
                     // redraw the board
                     createCompBoard(game, compBoard); 
                     
                     try  
                     {
                        if (!message.equals(null))
                        {
                           alert.setHeaderText(message);
                           alert.showAndWait();
                           
                           if(game.computerDefeated())
                           {
                              alert.setHeaderText("Game over! User wins!");
                              alert.showAndWait();
                              playerTurn = Player.GAME_OVER;
                              turnLabel.setText("Game over! User wins!");
                           }
                        }
                     }
                     catch (NullPointerException exception) // do nothing if null
                     {  
                     }
                  }
               }
            });
         }
      }
   }
    

   public static void main(String [] args)
   {   
      launch(args);
   }
}