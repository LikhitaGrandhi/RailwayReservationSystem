import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ViewBookingsFX {

    public static void show() {

        Stage stage = new Stage();
        TextArea area = new TextArea();
        area.setEditable(false);

        StringBuilder sb = new StringBuilder();

        sb.append("CONFIRMED:\n");
        for (Ticket t : RailwayFX.system.getConfirmed()) {
            sb.append(t.getPassenger().getId())
              .append(" - ")
              .append(t.getPassenger().getName())
              .append("\n");
        }

        sb.append("\nRAC:\n");
        for (Ticket t : RailwayFX.system.getRac()) {
            sb.append(t.getPassenger().getName()).append("\n");
        }

        sb.append("\nWAITING:\n");
        for (Ticket t : RailwayFX.system.getWaiting()) {
            sb.append(t.getPassenger().getName()).append("\n");
        }

        area.setText(sb.toString());

        stage.setScene(new Scene(new VBox(area), 350, 300));
        stage.setTitle("View Bookings");
        stage.show();
    }
}