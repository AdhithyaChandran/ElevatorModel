package main.java.sg.dlt.elevator.components;


import java.util.Random;

public class Car {

    private static Car car = null;

    public static Car getInstance(){
        if(car==null)
            car = new Car();
        return car;
    }

    public int generateRandomLoad() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public void loadCheck(int load) {
        if (load > 1050) {
            System.out.println("Overload...");
            loadCheck(generateRandomLoad());
        }
    }

    public void moveTo(int destinationFloor, int currentFloor) throws InterruptedException {
        Processor.getInstance().move(destinationFloor, currentFloor);
    }


}
