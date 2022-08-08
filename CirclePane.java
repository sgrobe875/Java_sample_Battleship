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
import javafx.scene.paint.Paint;

public class CirclePane extends HBox
{
   private int numCircles;
   private Paint fill;
   private static final int DEFAULT_NUM = 1;
   
   public CirclePane(Paint c, int n)
   {
//       super();
      this.fill = c;
      this.numCircles = n;
      this.setPrefSize(100,100);
//       this.getChildren().add(new Circle(10,c));
      this.setAlignment(Pos.CENTER);
      this.setStyle("-fx-border-color: blue;" + "-fx-border-width: 5;");
      
      for (int i = 1; i<= n; i++)
         this.getChildren().add(new Circle(10,c));
   }
   
   public CirclePane(Paint c)
   {
      this(c,DEFAULT_NUM);
   }
   
   public int getNumCircles()
   {
      return numCircles;
   }
   
   public void setNumCircles(int n)
   {
      numCircles = n;
      this.getChildren().clear();
      for(int i = 1; i<=n; i++)
      {
         this.getChildren().add(new Circle(10,fill));
      }
   }
   
   public Color getFill()
   {
      return fill;
   }
   
   public int getNumCircles()
   {
      return numCircles;
   }
   
//    public void setNumCircles(int n)
//    {
//       numCircles = n;
//       this.getChildren().clear();
//       for (int i = 0; i<numCircles; i++)
//       {
//          
//       }
//    }
}