import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

 public class CircleGUI extends Application
 {
    public void start(Stage primaryStage) 
      {
         BorderPane pane = new BorderPane();
         GridPane squares = new GridPane();
         HBox textArea = new HBox();
         textArea.setAlignment(Pos.CENTER);
         /* add text to bottom pane */
         HBox circles = new CirclePane(Color.RED,1);
         circles.setOnMouseClicked(this::moreCircles);
//          circles.setPrefSize(100,100);
//          circles.getChildren().add(new Circle(10,Color.RED));
//          circles.setAlignment(Pos.CENTER);
//          circles.setStyle("-fx-border-color: blue;" + "-fx-border-width: 5;");
         squares.add(circles,0,0);
         
         HBox circles2 = new CirclePane(Color.BLUE,2);
         circles2.setOnMouseClicked(this::moreCircles);
//          circles2.setPrefSize(100,100);
//          circles2.getChildren().add(new Circle(10,Color.BLUE));
//          circles2.setAlignment(Pos.CENTER);
//          circles2.setStyle("-fx-border-color: yellow;" + "-fx-border-width: 5;");
         squares.add(circles2,1,0);
         
         HBox circles3 = new CirclePane(Color.YELLOW,3);
         circles3.setOnMouseClicked(this::moreCircles);
//          circles3.setPrefSize(100,100);
//          circles3.getChildren().add(new Circle(10,Color.YELLOW));
//          circles3.setAlignment(Pos.CENTER);
//          circles3.setStyle("-fx-border-color: magenta;" + "-fx-border-width: 5;");
         squares.add(circles3,0,1);
         
         HBox circles4 = new CirclePane(Color.MAGENTA,4);
         circles4.setOnMouseClicked(this::moreCircles);
//          circles4.setPrefSize(100,100);
//          circles4.getChildren().add(new Circle(10,Color.MAGENTA));
//          circles4.setAlignment(Pos.CENTER);
//          circles4.setStyle("-fx-border-color: red;" + "-fx-border-width: 5;");
         squares.add(circles4,1,1);
         
         
//          Image i = new Image("imagename.jpg");

         
         
         
         /* add stuff to grid */



         pane.setCenter(squares);
         pane.setBottom(textArea);
   
        // center grid pane inside center of borderpane
         squares.setAlignment(Pos.CENTER); 
         Scene scene = new Scene(pane);
         primaryStage.setScene(scene);
         primaryStage.show();
    }
    
    public void moreCircles(MouseEvent event)
    {
      CirclePane cp = (CirclePane)event.getSource()    // event.getSource() returns an object
      int n = cp.getNumCircles();
      cp.setNumCircles(n%4+1);
    }

   public static void main(String [] args)
   {
   
      launch(args);
   
   }
}

   /** code for event handling **/
   // creating a named inner class.
//        Handler h = new Handler();
//        b.setOnAction(h);

//    }  // inner class
//    class Handler implements EventHandler<ActionEvent> {
//       public void handle(ActionEvent e)
//       {
//         pane.setStyle("-fx-background-color: red");
//       }
//    }

   // an anonymous inner class.
//        b = new Button("Push me");
//        pane.getChildren().add(b);
//        b.setOnAction( new EventHandler<ActionEvent>() {
//          public void handle(ActionEvent e)
//          {
//             pane.setStyle("-fx-background-color: red");
//          }
//        });

   // a lamda expression
//        b.setOnAction(event ->
//        {
//          pane.setStyle("-fx-background-color: red");
//        });

   // a method reference
//        b.setOnAction(this::handle);
//        
//    public void handle(ActionEvent e)
//    {
//       pane.setStyle("-fx-background-color: red");
//    }
