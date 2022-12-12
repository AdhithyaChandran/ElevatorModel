package main.java.sg.dlt.elevator.components;

public class Display {
    private static Display display = null;

    public static Display getInstance() {
        if (display == null) {
            display = new Display();
        }
        return display;
    }

    public void dispalyFloor(int currentFloor) {
        System.out.println("Floor : " + currentFloor);
    }

    public void displayWelcome(int currentFloor){
        System.out.println("Welcome to Floor : " + currentFloor);
    }

}
