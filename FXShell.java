import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

public class FXShell extends Application {
   @Override
   public void start(Stage primaryStage) {
   
      // FlowPane pane = new FlowPane();
      Pane pane = new Pane();
      Scene scene = new Scene(pane,400,400);
      
      Circle c = new Circle();
      c.setCenterX(100);
      c.setCenterY(100);
      c.setRadius(50);
      
      c.setStroke(Color.RED);
      c.setFill(Color.BLUE);
      //c.setFill(Color.TRANSPARENT);
      c.setStrokeWidth(10);
      
      pane.getChildren().add(c);
      
      Circle c2 = new Circle(150,150,75);
      c2.setStyle("-fx-stroke: black; -fx-fill: red;");
//       c2.setStroke(Color.YELLOW);
//       c2.setFill(Color.TRANSPARENT);
//       c2.setStrokeWidth(15);
      
      pane.getChildren().add(c2);
      
      primaryStage.setScene(scene);
      
//       
//       Button b = new Button();
//       b.setText("Push me");
//       
//       // to add a Node to the Pane
//       pane.getChildren().add(b);
//       
//       Button b2 = new Button();
//       b2.setText("Second button");
//       
//       pane.getChildren().add(b2);
//       
//       Text t = new Text("Just some text");      
//       Font f1 = new Font("Arial",28);
//       t.setFont(f1);
//       
//       Text t2 = new Text("Second text");      
//       //Font f2 = new Font("Arial",28);
//       Font f2 = Font.font("Times New Roman",FontWeight.BOLD, FontPosture.ITALIC,14);
//       t2.setFont(f2);
//       
//       
//       pane.getChildren().add(t);
//       pane.getChildren().add(t2);
      
//       Pane pane = new Pane();
//       Scene s = new Scene(pane,200,300);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Jackie's window");
      primaryStage.show();
   }
   
   public static void main(String [] args) {
      launch(args);
   }
}