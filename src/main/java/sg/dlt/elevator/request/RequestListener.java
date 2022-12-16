package main.java.sg.dlt.elevator.request;

import main.java.sg.dlt.elevator.components.Processor;


import java.util.Scanner;


public class RequestListener  implements Runnable {
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int floorNumber ;
            floorNumber = sc.nextInt();
            if (Processor.getInstance().isValidFloorNumber(floorNumber)) {
                System.out.println("User Pressed : " + floorNumber);
                Processor.getInstance().addNewRequest(floorNumber);
            } else {
                System.out.println("Floor Request Invalid : " + floorNumber);
            }
        }
    }

        
}
