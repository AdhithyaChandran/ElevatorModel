package main.java.sg.dlt.elevator;

import main.java.sg.dlt.elevator.components.Processor;
import main.java.sg.dlt.elevator.components.car.Car;
import main.java.sg.dlt.elevator.request.RequestListener;
import main.java.sg.dlt.elevator.request.RequestProcessor;


public class Elevator {
    private static Elevator elevator = null;

    public static Elevator getInstance() {
        if (elevator == null) {
            elevator = new Elevator();
        }
        return elevator;
    }


    public static void main(String[] args) {
        System.out.println("<< WELCOME >>");

        Thread ListenerThread = new Thread(new RequestListener(), "ListenerThread");

        Thread ProcessorThread = new Thread(new RequestProcessor(), "ProcessorThread");

        Processor.getInstance().setRequestProcessorThread(ProcessorThread);
        ListenerThread.start();
        ProcessorThread.start();
    }

}
