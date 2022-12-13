package main.java.sg.dlt.elevator;

import main.java.sg.dlt.elevator.components.car.Car;
import main.java.sg.dlt.elevator.request.RequestListener;
import main.java.sg.dlt.elevator.request.RequestProcessor;


public class Elevator {
    private static Elevator elevator = null;

    Thread requestProcessorThread;


    private Elevator() {};

    public void setRequestProcessorThread(Thread requestProcessorThread) {
        this.requestProcessorThread = requestProcessorThread;
    }

    public Thread getRequestProcessorThread() {
        return requestProcessorThread;
    }

    public static Elevator getInstance() {
        if (elevator == null) {
            elevator = new Elevator();
        }
        return elevator;
    }

   // public static Thread requestProcessorThread;

    public static void main(String[] args) {
        System.out.println("<< WELCOME >>");

        Car car = new Car();
        car.loadCheck(car.generateRandomLoad());

        Thread ListenerThread = new Thread(new RequestListener(), "ListenerThread");

        Thread ProcessorThread = new Thread(new RequestProcessor(), "ProcessorThread");

        Elevator.getInstance().setRequestProcessorThread(ProcessorThread);
        ListenerThread.start();
        ProcessorThread.start();
    }

}
