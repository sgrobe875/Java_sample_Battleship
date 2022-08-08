import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
public class EventDemo extends Application {
   private Button b, b2;
   private FlowPane pane;
   private Scene scene;
   @Override
   public void start(Stage primaryStage) {
       primaryStage.setTitle("Jackie's window");
       pane = new FlowPane();
       pane.setAlignment(Pos.CENTER);

       pane.setStyle("-fx-background-color: white");


       Button b = new Button("RED");
       pane.getChildren().add(b);
       RedHandler h = new RedHandler();
       b.setOnAction(this::handle);
       
       
// // //        b.setOnAction(event ->
// // //        {
// // //          pane.setStyle("-fx-background-color: red;");
// // //        });
       
       
// //        b.setOnAction(new EventHandler<ActionEvent>()
// //        {
// //          public void handle(ActionEvent e)
// //          {
// //             pane.setStyle("-fx-background-color: red;");  
// //          }
// //       });
       
//        b.setOnAction(h);
//        
//        Button b2 = new Button("BLUE");
//        pane.getChildren().add(b2);
//        BlueHandler h2 = new BlueHandler();
//        b2.setOnAction(h2);
       
       
       scene = new Scene(pane);
       primaryStage.setScene(scene);
       primaryStage.show();
   } 
   
   public void handle(ActionEvent e)
   {
      pane.setStyle("-fx-background-color: red;");
   }
   
   
   public class RedHandler implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         pane.setStyle("-fx-background-color: red;");
      }
   }
   
   public class BlueHandler implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         pane.setStyle("-fx-background-color: blue;");
      }
   }

   public static void main(String [] args) {
      launch(args);
   }
   
}
