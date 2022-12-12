package main.java.sg.dlt.elevator.request;

import main.java.sg.dlt.elevator.Elevator;
import main.java.sg.dlt.elevator.components.Processor;
import main.java.sg.dlt.elevator.components.car.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RequestListener  implements Runnable {
    @Override
    public void run() {

        while (true) {
            String floorNumberStr = null;
            try {
                // Read input from console
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                floorNumberStr = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (isValidFloorNumber(floorNumberStr)) {
                System.out.println("User Pressed : " + floorNumberStr);
                Processor processor = Processor.getInstance();
                processor.addFloor(Integer.parseInt(floorNumberStr),Elevator.requestProcessorThread);
            } else {
                System.out.println("Floor Request Invalid : " + floorNumberStr);
            }
        }
    }

        private boolean isValidFloorNumber(String s) {
            return (s != null) && s.matches("\\d{1,2}");
        }
}
