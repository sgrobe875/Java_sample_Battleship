import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.animation.Animation;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import javafx.scene.paint.Color;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.util.Duration;
import java.util.Date;

public class TimeLineTester2 extends Application
{
   private Button start, pause, restart; //buttons to control animation
   private BorderPane mainPane;  // main outer pane
   private GridPane controlPane;    // pane for buttons
   private HBox controlPaneLeft,controlPaneRight;  //sub panes for controls
   private Pane animationPane;   // pane for square to move in
   private HBox infoPane;  // pane to display information in text
   private Scene scene; 
   private Rectangle rect;
   private KeyFrame frame1;
   private Timeline timeline;
   private Text info;         // display info to user
   private ToggleGroup group;
   private RadioButton rbColor1, rbColor2, rbColor3; // radio buttons for color
   
   private final static int START_X = 0,START_Y = 50;  // where box starts
   private final static int RECT_DIM = 50;   // size of box (square)
   private final static int AREA_DIM = 200;  // preferred size of area for box
   private final static int STEP = 2;  // how far does box move?
   private final static int END_X = AREA_DIM-RECT_DIM;   // when does box need to start over?
   
   @Override
   public void start(Stage primaryStage) {
      primaryStage.setTitle("Moving Blocks");
      // setup panes
      mainPane = new BorderPane();
      controlPane = new GridPane();
      controlPaneLeft = new HBox(10);
      controlPaneLeft.setAlignment(Pos.CENTER);
      controlPaneLeft.setStyle("-fx-background-color: mediumslateblue");
      controlPaneLeft.setPadding(new Insets(20,20,20,20));
      controlPane.add(controlPaneLeft,0,0);
      
      // buttons
      controlPaneRight = new HBox(10);
      controlPaneRight.setAlignment(Pos.CENTER);
      controlPaneRight.setStyle("-fx-background-color: lightgrey");
      controlPaneLeft.setPadding(new Insets(20,20,20,20));
      controlPane.add(controlPaneRight,1,0);

      // empty space for box to move in
      animationPane = new Pane();
      animationPane.setPrefSize(AREA_DIM,AREA_DIM);
      mainPane.setCenter(animationPane);
      mainPane.setTop(controlPane);
      mainPane.setStyle("-fx-background-color: ROSYBROWN");

      // bottom pane for user info
      infoPane = new HBox(20);
      infoPane.setAlignment(Pos.CENTER);
      infoPane.setStyle("-fx-background-color: CORNSILK");
      mainPane.setBottom(infoPane);
            
      // create the box
      rect = new Rectangle(START_X,START_Y,RECT_DIM,RECT_DIM);
      rect.setFill(Color.CHOCOLATE);
      animationPane.getChildren().add(rect);
      // when mouse is clicked in region, upper left corner of box moves to mouse click
      mainPane.setOnMouseClicked(new EventHandler<MouseEvent>()
      {
         @Override
         public void handle(MouseEvent me)
         {
            rect.setX(me.getX()); 
            rect.setY(me.getY());           
         }
      });

      
      // animation -- box moves across screen horizontally
      class TimeHandler implements EventHandler<ActionEvent> 
      { 
         @Override
         public void handle(ActionEvent e)
         {
            
            if (rect.getX()+RECT_DIM >= animationPane.getWidth())
               rect.setX(START_X);
            else
               rect.setX(rect.getX() + STEP);
         }
      }
      // set up the Timeline
      frame1 = new KeyFrame(Duration.millis(100), new TimeHandler());
      timeline = new Timeline(frame1);
      timeline.setCycleCount(Animation.INDEFINITE);

      start = new Button("start");
      start.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            timeline.play();
         }
      });
      controlPaneLeft.getChildren().add(start);
      pause = new Button("pause");
      pause.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            timeline.pause();
         }
      });
      controlPaneLeft.getChildren().add(pause);

      restart = new Button("restart");
      restart.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            rect.setX(START_X);
            timeline.play();
        
         }
      });
      controlPaneLeft.getChildren().add(restart);
      
      // color radio button group setup
      group = new ToggleGroup();
      rbColor1 = new RadioButton("BROWN");
      rbColor1.setTextFill(Color.BROWN);
      rbColor1.setToggleGroup(group);
      controlPaneRight.getChildren().add(rbColor1);
      rbColor1.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            rect.setFill(Color.CHOCOLATE);         
         }
      });

      
      rbColor2 = new RadioButton("ORANGE");
      rbColor2.setTextFill(Color.ORANGE);
      rbColor2.setToggleGroup(group);
      controlPaneRight.getChildren().add(rbColor2);
      rbColor2.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            rect.setFill(Color.ORANGE);         
         }
      });

      rbColor3 = new RadioButton("GREEN");
      rbColor3.setTextFill(Color.FORESTGREEN);
      rbColor3.setToggleGroup(group);
      controlPaneRight.getChildren().add(rbColor3);
      rbColor3.setOnAction(new EventHandler<ActionEvent>() 
      {  @Override
         public void handle(ActionEvent e)
         {
            rect.setFill(Color.FORESTGREEN);         
         }
      });

      
      rbColor1.setSelected(true); // start with BROWN
     

      scene = new Scene(mainPane);
       
     
      primaryStage.setScene(scene);
      primaryStage.show();
  
   }
   public static void main(String [] args)
   {
   
      launch(args);
   
   }


}