import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

public class CellPane extends HBox
{
   // variables
   private int row;
   private int col;
   private CellStatus cs;
   private Text cellLabel = new Text();
   private boolean isUser;
   
   // constructor
   public CellPane(CellStatus cs, int r, int c, boolean u)
   {
      this.cs = cs;
      row = r;
      col = c;
      isUser = u;
      
      this.setAlignment(Pos.CENTER);
      this.setPrefSize(30,30);
      this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: blue;");
                     
      cellLabel.setFont(Font.font("Courier",12));
      this.setPadding(new Insets(10,10,10,10));
      
      if (cs.equals(CellStatus.AIRCRAFT_CARRIER) || cs.equals(CellStatus.AIRCRAFT_CARRIER_HIT) ||
            cs.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
         setCellAircraftCarrier();
                     
//       if (cs.equals(CellStatus.AIRCRAFT_CARRIER) && isUser == true)
//       {
//          cellLabel.setText("A");
//       }
//       
//       else if (cs.equals(CellStatus.BATTLESHIP) && isUser == true)
//       {
//          cellLabel.setText("B");
//       }
//       
//       else if (cs.equals(CellStatus.CRUISER) && isUser == true)
//       {
//          cellLabel.setText("C");
//       }
//       
//       else if (cs.equals(CellStatus.DESTROYER) && isUser == true)
//       {
//          cellLabel.setText("D");
//       }
//       
//       else if (cs.equals(CellStatus.SUB) && isUser == true)
//       {
//          cellLabel.setText("S");
//       }
//       
//       else
//          cellLabel.setText("");
//       
      this.getChildren().add(cellLabel);
         
   }
   
   private void setCellAircraftCarrier()
   {
      if (isUser == true)
      {
         cellLabel.setText("A");
         if (cs.equals(CellStatus.AIRCRAFT_CARRIER_HIT))
            this.setStyle("-fx-background-color: red;");
         else if (cs.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
            this.setStyle("-fx-background-color: yellow;"); 
      }     
         
   }
 
   
}