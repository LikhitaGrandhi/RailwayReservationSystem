class Passenger {
private static int counter = 1;
private int id;
private String name;
private int age;


public Passenger(String name, int age) {
this.id = counter++;
this.name = name;
this.age = age;
}


public int getId() { return id; }
public String getName() { return name; }
}