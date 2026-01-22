import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class BookTicketFX {

    public static void show() {

        Stage stage = new Stage();

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        Label result = new Label();
        Button bookBtn = new Button("Confirm Booking");

      bookBtn.setOnAction(e -> {
    try {
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());

        Passenger p = new Passenger(name, age);
        RailwayFX.system.bookTicket(p);

        String status = RailwayFX.system.getLastStatus();
result.setText("Ticket booked for " + name + "\nStatus: " + status);
    } catch (Exception ex) {
        result.setText("Invalid input");
    }
});

        VBox root = new VBox(10, nameField, ageField, bookBtn, result);
        root.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Book Ticket");
        stage.show();

    }
private static void showAlert(String title, String msg) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}
}