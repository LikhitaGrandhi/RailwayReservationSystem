import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

class ReservationSystem {

    private int maxSeats;
    private int maxRac;
    private int maxWaiting;

    private List<Ticket> confirmed = new ArrayList<>();
    private Queue<Ticket> rac = new LinkedList<>();
    private Queue<Ticket> waiting = new LinkedList<>();

    public ReservationSystem(int seats, int rac, int waiting) {
        this.maxSeats = seats;
        this.maxRac = rac;
        this.maxWaiting = waiting;
    }

    public void bookTicket(Passenger p) {
        if (confirmed.size() < maxSeats) {
            confirmed.add(new Ticket(p, "CONFIRMED"));
            System.out.println("Ticket CONFIRMED for " + p.getName());

        } else if (rac.size() < maxRac) {
            rac.add(new Ticket(p, "RAC"));
            System.out.println("Ticket in RAC for " + p.getName());

        } else if (waiting.size() < maxWaiting) {
            waiting.add(new Ticket(p, "WAITING"));
            System.out.println("Ticket in WAITING LIST for " + p.getName());

        } else {
            System.out.println("No tickets available");
        }
    }

    public void cancelTicket(int passengerId) {
        Iterator<Ticket> it = confirmed.iterator();

        while (it.hasNext()) {
            Ticket t = it.next();

            if (t.getPassenger().getId() == passengerId) {
                it.remove();
                System.out.println("Confirmed ticket cancelled");

                if (!rac.isEmpty()) {
                    Ticket racTicket = rac.poll();
                    racTicket.setStatus("CONFIRMED");
                    confirmed.add(racTicket);

                    if (!waiting.isEmpty()) {
                        Ticket w = waiting.poll();
                        w.setStatus("RAC");
                        rac.add(w);
                    }
                }
                return;
            }
        }
        System.out.println("Passenger ID not found in confirmed list");
    }

    public void viewBookings() {

        System.out.println("\nConfirmed Tickets:");
        for (Ticket t : confirmed)
            System.out.println(t.getPassenger().getId() + " - " + t.getPassenger().getName());

        System.out.println("RAC Tickets:");
        for (Ticket t : rac)
            System.out.println(t.getPassenger().getId() + " - " + t.getPassenger().getName());

        System.out.println("Waiting List:");
        for (Ticket t : waiting)
            System.out.println(t.getPassenger().getId() + " - " + t.getPassenger().getName());
    }
}
