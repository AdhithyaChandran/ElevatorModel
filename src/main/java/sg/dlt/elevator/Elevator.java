package main.java.sg.dlt.elevator;

import main.java.sg.dlt.elevator.components.car.Direction;
import main.java.sg.dlt.elevator.request.RequestListener;
import main.java.sg.dlt.elevator.request.RequestProcessor;

import java.util.TreeSet;

public class Elevator {
    private static Elevator elevator = null;

    private TreeSet requestSet = new TreeSet();

    private int currentFloor = 0;

    private Direction direction = Direction.UP;

    private Elevator() {};

    public Thread getRequestProcessorThread() {
        return requestProcessorThread;
    }
    public void setRequestProcessorThread(Thread requestProcessorThread) {
        this.requestProcessorThread = requestProcessorThread;
    }

    static Elevator getInstance() {
        if (elevator == null) {
            elevator = new Elevator();
        }
        return elevator;
    }

    public static Thread requestProcessorThread;

    public static void main(String[] args) {
        System.out.println("Welcome to MyLift");

        // RequestListenerThread to read requested floor and add to Set
        Thread requestListenerThread = new Thread(new RequestListener(), "RequestListenerThread");

        // RequestProcessorThread to read Set and process requested floor
        Thread requestProcessorThread = new Thread(new RequestProcessor(), "RequestProcessorThread");

        Elevator.getInstance().setRequestProcessorThread(requestProcessorThread);
        requestListenerThread.start();
        requestProcessorThread.start();
    }

}
