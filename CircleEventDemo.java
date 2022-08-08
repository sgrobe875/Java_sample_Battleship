import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.Random;


import javafx.geometry.Pos;
public class CircleEventDemo extends Application {
   private Button bGrow,bShrink,bExit; 

   private HBox pane;
   private Scene scene;
   private Circle circle;
   private Random random;
   @Override
   public void start(Stage primaryStage) {
       random = new Random();
       primaryStage.setTitle("Jackie's window");
       pane = new HBox();
       pane.setAlignment(Pos.CENTER);

       pane.setStyle("-fx-background-color: white");


       Button bExit = new Button("Exit");
       bExit.setStyle("-fx-background-color: yellow");         
       

       bGrow = new Button("Grow");
       bGrow.setOnAction(new EventHandler<ActionEvent>()
       {
         public void handle(ActionEvent e)
         {
            double r = circle.getRadius();
            circle.setRadius(r * 1.1);
         }
       });
       
       bShrink = new Button("Shrink");
       bShrink.setOnAction(new EventHandler<ActionEvent>()
       {
         public void handle(ActionEvent e)
         {
            double r = circle.getRadius();
            circle.setRadius(r * 0.9);
         }
       });
       
       bExit = new Button("Exit");
       bExit.setOnAction(new EventHandler<ActionEvent>()
       {
         public void handle(ActionEvent e)
         {
            Platform.exit();
         }
       });
       
       pane.getChildren().add(bGrow);
       pane.getChildren().add(bShrink);
       pane.getChildren().add(bExit);
       circle = new Circle(200,200,20);
       
       circle.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
         public void handle(MouseEvent e)
         {
            circle.setFill(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
         }
       });
       
       pane.getChildren().add(circle);
       
       pane.setOnMouseClicked(new EventHandler<MouseEvent>()
       {
         public void handle(MouseEvent e)
         {
            pane.setStyle("-fx-background-color: blue;");
         }
       });
       
       scene = new Scene(pane,400,400);
       primaryStage.setScene(scene);
       primaryStage.show();
   } 
   public static void main(String [] args) {
      launch(args);
   }
}
