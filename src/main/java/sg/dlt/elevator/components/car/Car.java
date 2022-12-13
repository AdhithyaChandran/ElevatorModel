package main.java.sg.dlt.elevator.components.car;

import main.java.sg.dlt.elevator.components.Display;
import main.java.sg.dlt.elevator.components.Processor;

import java.util.Random;

public class Car {

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




}
