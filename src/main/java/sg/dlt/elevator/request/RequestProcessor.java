package main.java.sg.dlt.elevator.request;

import main.java.sg.dlt.elevator.components.Processor;
import main.java.sg.dlt.elevator.components.Car;

public class RequestProcessor implements Runnable {
    @Override
    public void run() {
        while (true) {
            int destinationFloor = Processor.getInstance().nextFloor();
            int currentFloor = Processor.getInstance().getCurrentFloor();
            try{
                Car.getInstance().moveTo(destinationFloor,currentFloor);
            }catch(InterruptedException e){
                if(Processor.getInstance().getCurrentFloor() != destinationFloor){
                    Processor.getInstance().getRequests().add(destinationFloor);
                }
            }
        }
    }
}
