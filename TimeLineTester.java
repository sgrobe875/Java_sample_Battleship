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
public class TimeLineTester extends Application
{
   private HBox pane;
   private Label label;
   private Scene scene;
   private Date now;
   @Override
   public void start(Stage primaryStage) {
      primaryStage.setTitle("Jackie's Clock");
      // setup gui
      pane = new HBox();
      pane.setAlignment(Pos.CENTER);
      
      label = new Label();
      now = new Date();
      label.setText(now.toString());
      label.setFont(Font.font("Courier",24));
      pane.getChildren().add(label);
      class TimeHandler implements EventHandler<ActionEvent>
      {
         @Override
         public void handle(ActionEvent e)
         {
            now = new Date();
            label.setText(now.toString());
         
         }
      
      }
      KeyFrame frame1 = new KeyFrame(Duration.seconds(1),new TimeHandler());
      Timeline timeline = new Timeline(frame1);
      timeline.setCycleCount(Animation.INDEFINITE);
      timeline.play();
      
      scene = new Scene(pane);
 
      primaryStage.setScene(scene);
      primaryStage.show();
  
   }
   public static void main(String [] args)
   {
   
      launch(args);
   
   }


}