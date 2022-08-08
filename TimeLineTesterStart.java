import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.util.Date;
import javafx.geometry.Pos;
public class TimeLineTesterStart extends Application
{
   private HBox pane;
   private Label label;
   private Scene scene;
   @Override
   public void start(Stage primaryStage) {
      primaryStage.setTitle("Jackie's Clock");
      // setup gui
      pane = new HBox();
      pane.setAlignment(Pos.CENTER);
      
      
      
      scene = new Scene(pane);
 
      primaryStage.setScene(scene);
      primaryStage.show();
  
   }
   public static void main(String [] args)
   {
   
      launch(args);
   
   }


}