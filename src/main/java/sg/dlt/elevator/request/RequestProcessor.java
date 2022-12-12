package main.java.sg.dlt.elevator.request;

import main.java.sg.dlt.elevator.components.Display;
import main.java.sg.dlt.elevator.components.Processor;

public class RequestProcessor implements Runnable {
    @Override
    public void run() {
        while (true) {
            Processor processor = Processor.getInstance();
            int floor = processor.nextFloor();
            int currentFloor = processor.getCurrentFloor();
            try{
                if (floor >= 0) {
                    if (currentFloor > floor) {
                        while (currentFloor > floor) {
                            processor.setCurrentFloor(--currentFloor);
                        }
                    } else {
                        while (currentFloor < floor) {
                            processor.setCurrentFloor(++currentFloor);
                        }
                    }
                    Display display = Display.getInstance();
                    display.displayWelcome(processor.getCurrentFloor());
                }

            }catch(InterruptedException e){
                // If a new request has interrupted a current request processing then check -
                // -if the current request is already processed
                // -otherwise add it back in request Set
                if(processor.getCurrentFloor() != floor){
                    processor.getRequestSet().add(floor);
                }
            }
        }
    }
}
