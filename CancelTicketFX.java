import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class CancelTicketFX {

    public static void show() {

        Stage stage = new Stage();

        Label label = new Label("Enter Passenger ID:");
        TextField idField = new TextField();

        Button cancelBtn = new Button("Cancel Ticket");

        cancelBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                RailwayFX.system.cancelTicket(id);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Ticket cancelled successfully");
                alert.showAndWait();

                stage.close();

            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Passenger ID");
                alert.show();
            }
        });

        VBox root = new VBox(10, label, idField, cancelBtn);
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Cancel Ticket");
        stage.show();
    }
}