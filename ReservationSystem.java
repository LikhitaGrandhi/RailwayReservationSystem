import java.util.*;
import java.io.*;

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
public List<Ticket> getConfirmed() {
    return confirmed;
}

public Queue<Ticket> getRac() {
    return rac;
}

public Queue<Ticket> getWaiting() {
    return waiting;
}

    // ===== FILE SAVE =====
    private void saveToFile(Ticket t) {
        try {
            FileWriter fw = new FileWriter("tickets.txt", true);
            fw.write(
                t.getPassenger().getId() + "," +
                t.getPassenger().getName() + "," +
                t.getStatus() + "\n"
            );
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    // ===== REWRITE FILE AFTER CANCEL =====
    private void rewriteFile() {
        try {
            FileWriter fw = new FileWriter("tickets.txt", false);

            for (Ticket t : confirmed)
                fw.write(t.getPassenger().getId() + "," +
                         t.getPassenger().getName() + "," +
                         t.getStatus() + "\n");

            for (Ticket t : rac)
                fw.write(t.getPassenger().getId() + "," +
                         t.getPassenger().getName() + "," +
                         t.getStatus() + "\n");

            for (Ticket t : waiting)
                fw.write(t.getPassenger().getId() + "," +
                         t.getPassenger().getName() + "," +
                         t.getStatus() + "\n");

            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating file");
        }
    }

    // ===== BOOK TICKET =====
    public void bookTicket(Passenger p) {

        if (confirmed.size() < maxSeats) {
            Ticket t = new Ticket(p, "CONFIRMED");
            confirmed.add(t);
            saveToFile(t);
            System.out.println("Ticket CONFIRMED for " + p.getName());

        } else if (rac.size() < maxRac) {
            Ticket t = new Ticket(p, "RAC");
            rac.add(t);
            saveToFile(t);
            System.out.println("Ticket in RAC for " + p.getName());

        } else if (waiting.size() < maxWaiting) {
            Ticket t = new Ticket(p, "WAITING");
            waiting.add(t);
            saveToFile(t);
            System.out.println("Ticket in WAITING LIST for " + p.getName());

        } else {
            System.out.println("No tickets available");
        }
    }

    // ===== CANCEL TICKET =====
    public void cancelTicket(int passengerId) {

        Iterator<Ticket> it = confirmed.iterator();

        while (it.hasNext()) {
            Ticket t = it.next();

            if (t.getPassenger().getId() == passengerId) {
                it.remove();
                System.out.println("Confirmed ticket cancelled");

                if (!rac.isEmpty()) {
                    Ticket r = rac.poll();
                    r.setStatus("CONFIRMED");
                    confirmed.add(r);

                    if (!waiting.isEmpty()) {
                        Ticket w = waiting.poll();
                        w.setStatus("RAC");
                        rac.add(w);
                    }
                }

                rewriteFile();
                return;
            }
        }
        System.out.println("Passenger ID not found");
    }

    // ===== VIEW BOOKINGS =====
    public void viewBookings() {

        System.out.println("\nConfirmed:");
        for (Ticket t : confirmed)
            System.out.println(t.getPassenger().getId() + " - " + t.getPassenger().getName());

        System.out.println("\nRAC:");
        for (Ticket t : rac)
            System.out.println(t.getPassenger().getId() + " - " + t.getPassenger().getName());

        System.out.println("\nWaiting:");
        for (Ticket t : waiting)
            System.out.println(t.getPassenger().getId() + " - " + t.getPassenger().getName());
    }
public String getLastStatus() {
    if (!confirmed.isEmpty())
        return confirmed.get(confirmed.size() - 1).getStatus();
    if (!rac.isEmpty())
        return "RAC";
    if (!waiting.isEmpty())
        return "WAITING";
    return "NONE";
}
}