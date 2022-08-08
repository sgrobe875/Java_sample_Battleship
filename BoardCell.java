import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class BoardCell extends HBox
{
   // variables
   private int row;
   private int col;
   private CellStatus cs;
   private Text cellLabel = new Text();
   private boolean isUser;
   
   // constructor
   public BoardCell(int r, int c, CellStatus cs, boolean u)
   {
      row = r;
      col = c;
      this.cs = cs;
      isUser = u;
      String moveString;
      
      this.setAlignment(Pos.CENTER);
      this.setPrefSize(50,50);
      this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: blue;");
                     
      cellLabel.setFont(Font.font("Courier",20));
      this.setPadding(new Insets(10,10,10,10));
      
      if (cs.equals(CellStatus.AIRCRAFT_CARRIER) || cs.equals(CellStatus.AIRCRAFT_CARRIER_HIT) ||
            cs.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
      {
         cellLabel.setText(" ");
         setCellAircraftCarrier();
      }

      else if (cs.equals(CellStatus.BATTLESHIP) || cs.equals(CellStatus.BATTLESHIP_HIT) ||
            cs.equals(CellStatus.BATTLESHIP_SUNK))
      {
         cellLabel.setText(" ");
         setCellBattleship();
      }
         
      else if (cs.equals(CellStatus.CRUISER) || cs.equals(CellStatus.CRUISER_HIT) ||
            cs.equals(CellStatus.CRUISER_SUNK))
      {
         cellLabel.setText(" ");
         setCellCruiser();
      }
        
      else if (cs.equals(CellStatus.DESTROYER) || cs.equals(CellStatus.DESTROYER_HIT) ||
            cs.equals(CellStatus.DESTROYER_SUNK))
      {
         cellLabel.setText(" ");
         setCellDestroyer();
      }
         
      else if (cs.equals(CellStatus.SUB) || cs.equals(CellStatus.SUB_HIT) ||
            cs.equals(CellStatus.SUB_SUNK))
      {
         cellLabel.setText(" ");
         setCellSub();
      }
      
      else if (cs.equals(CellStatus.NOTHING) || cs.equals(CellStatus.NOTHING_HIT))
      {
         cellLabel.setText(" ");
         setCellNothing();
      }
         
         
      this.getChildren().add(cellLabel);

   }
   
   // methods for setting cell appearance (by Ship Type)
   private void setCellAircraftCarrier()
   {
      if (cs.equals(CellStatus.AIRCRAFT_CARRIER_HIT))
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: red;");
      else if (cs.equals(CellStatus.AIRCRAFT_CARRIER_SUNK))
      {
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: yellow;");
         cellLabel.setText("A");
      }
      
      if (isUser == true)
         cellLabel.setText("A");
   }
   
   private void setCellBattleship()
   {
      if (cs.equals(CellStatus.BATTLESHIP_HIT))
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: red;");
      else if (cs.equals(CellStatus.BATTLESHIP_SUNK))
      {
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: yellow;");
         cellLabel.setText("B");
      }
      
      if (isUser == true)
         cellLabel.setText("B");
   }
   
   private void setCellCruiser()
   {
      if (cs.equals(CellStatus.CRUISER_HIT))
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: red;");
      else if (cs.equals(CellStatus.CRUISER_SUNK))
      {
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: yellow;");
         cellLabel.setText("C");
      }
      
      if (isUser == true)
         cellLabel.setText("C");
   }
   
   private void setCellDestroyer()
   {
      if (cs.equals(CellStatus.DESTROYER_HIT))
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: red;");
      else if (cs.equals(CellStatus.DESTROYER_SUNK))
      {
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: yellow;");
         cellLabel.setText("D");
      }
      
      if (isUser == true)
         cellLabel.setText("D");
   }
   
   private void setCellSub()
   {
      if (cs.equals(CellStatus.SUB_HIT))
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: red;");
      else if (cs.equals(CellStatus.SUB_SUNK))
      {
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: yellow;");
         cellLabel.setText("S");
      }
      
      if (isUser == true)
         cellLabel.setText("S");
   }
   
   private void setCellNothing()
   {
      if (cs.equals(CellStatus.NOTHING_HIT))
         this.setStyle("-fx-border-width: 2;" +
                     "-fx-border-color: black;" +
                     "-fx-background-color: darkgray;");
   }
   
   // accessors
   public int row()
   {
      return row;
   }
   
   public int col()
   {
      return col;
   }
   
   public CellStatus cellStatus()
   {
      return cs;
   }
}
