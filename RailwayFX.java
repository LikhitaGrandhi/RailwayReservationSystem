import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RailwayFX extends Application {

    public static ReservationSystem system =
            new ReservationSystem(2, 1, 1);

    @Override
    public void start(Stage stage) {

        Button bookBtn = new Button("Book Ticket");
        Button viewBtn = new Button("View Bookings");
 Button cancelBtn=new Button("Cancel Ticket");
        Button exitBtn = new Button("Exit");

        bookBtn.setOnAction(e -> BookTicketFX.show());
        viewBtn.setOnAction(e -> ViewBookingsFX.show());
        cancelBtn.setOnAction(e ->CancelTicketFX.show());
        exitBtn.setOnAction(e -> stage.close());
        

        VBox root = new VBox(15, bookBtn, viewBtn, cancelBtn,exitBtn);
        root.setStyle("-fx-padding:30; -fx-alignment:center;");

        stage.setTitle("Railway Reservation System");
        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}