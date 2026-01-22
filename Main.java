import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create system (same as before)
        ReservationSystem reservationSystem =
                new ReservationSystem(2, 1, 1);

        while (true) {

            System.out.println("\n===== Railway Ticket Reservation System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Passenger Name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();

                    System.out.print("Enter Passenger Age: ");
                    int age = sc.nextInt();

                    Passenger p = new Passenger(name, age);
                    reservationSystem.bookTicket(p);
                    break;

                case 2:
                    System.out.print("Enter Passenger ID to cancel: ");
                    int id = sc.nextInt();
                    reservationSystem.cancelTicket(id);
                    break;

                case 3:
                    reservationSystem.viewBookings();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}