package main.java.sg.dlt.elevator.request;


import main.java.sg.dlt.elevator.components.Display;
import main.java.sg.dlt.elevator.components.Processor;

public class RequestProcessor implements Runnable {


    @Override
    public void run() {
        int floor = Processor.getInstance().nextFloor();
        int currentFloor = Processor.getInstance().getCurrentFloor();
        while (true) {
            try{
                if (floor >= 0) {
                    if (currentFloor > floor) {
                        while (currentFloor > floor) {
                            Processor.getInstance().setCurrentFloor(--currentFloor);
                        }
                    } else {
                        while (currentFloor < floor) {
                            Processor.getInstance().setCurrentFloor(++currentFloor);
                        }
                    }
                    Display.getInstance().displayWelcome(Processor.getInstance().getCurrentFloor());
                }

            }catch(InterruptedException e){
                // If a new request has interrupted a current request processing then check -
                // -if the current request is already processed
                // -otherwise add it back in request Set
                if(Processor.getInstance().getCurrentFloor() != floor){
                    Processor.getInstance().getRequests().add(floor);
                }
            }
        }
    }
}
