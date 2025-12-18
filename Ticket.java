class Ticket {
private Passenger passenger;
private String status; // CONFIRMED / RAC / WAITING


public Ticket(Passenger passenger, String status) {
this.passenger = passenger;
this.status = status;
}


public Passenger getPassenger() { return passenger; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
}