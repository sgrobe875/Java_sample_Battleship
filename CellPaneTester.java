import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;

public class CellPaneTester extends Application {
    @Override
    public void start(Stage primaryStage) {
    primaryStage.setTitle("Lab Tester Window");
    GridPane mainPane = new GridPane();
    CellPane cp1 = new CellPane(CellStatus.AIRCRAFT_CARRIER,
    0, 0, true);
    mainPane.add(cp1,0,0);
    CellPane cp2 = new CellPane(CellStatus.CRUISER,
    0, 1, true);
    mainPane.add(cp2,1,0);
    CellPane cp3 = new CellPane(CellStatus.BATTLESHIP,
    1, 0, true);
    mainPane.add(cp3,0,1);
    CellPane cp4 = new CellPane(CellStatus.AIRCRAFT_CARRIER,
    1, 1, true);
    mainPane.add(cp4,1,1);
    Scene scene = new Scene(mainPane);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    public static void main(String [] args) {
    launch(args);
    }
}