package main.java.sg.dlt.elevator.request;

import main.java.sg.dlt.elevator.Elevator;
import main.java.sg.dlt.elevator.components.Processor;
import main.java.sg.dlt.elevator.components.car.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class RequestListener  implements Runnable {
    Processor processor = Processor.getInstance();
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int floorNumberStr ;
            floorNumberStr = sc.nextInt();
            if (processor.isValidFloorNumber(floorNumberStr)) {
                System.out.println("User Pressed : " + floorNumberStr);
                processor.addNewRequest(floorNumberStr);
            } else {
                System.out.println("Floor Request Invalid : " + floorNumberStr);
            }
        }
    }

        
}
