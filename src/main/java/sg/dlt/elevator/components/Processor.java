package main.java.sg.dlt.elevator.components;

import main.java.sg.dlt.elevator.Elevator;
import main.java.sg.dlt.elevator.components.car.Direction;
import main.java.sg.dlt.elevator.request.InternalRequest;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Processor {

    private TreeSet requestSet = new TreeSet();


    private static Processor processor = null;


    private Direction direction = Direction.UP;

    private int currentFloor = 0;


    public int getCurrentFloor() {
        return currentFloor;
    }

    public TreeSet getRequestSet() {
        return requestSet;
    }

    public void setRequestSet(TreeSet requestSet) {
        this.requestSet = requestSet;
    }
    public static Processor getInstance() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public synchronized void addFloor(int floor, Thread requestProcessorThread) {
        requestSet.add(floor);

        if(requestProcessorThread.getState() == Thread.State.WAITING){
            // Notify processor thread that a new request has come if it is waiting
            notify();
        }else{
            // Interrupt Processor thread to check if new request should be processed before current request or not.
            requestProcessorThread.interrupt();
        }

    }

    public synchronized int nextFloor() {

        Integer floor = null;

        if (direction == Direction.UP) {
            if (requestSet.ceiling(currentFloor) != null) {
                floor = (Integer) requestSet.ceiling(currentFloor);
            } else {
                floor = (Integer) requestSet.floor(currentFloor);
            }
        } else {
            if (requestSet.floor(currentFloor) != null) {
                floor = (Integer) requestSet.floor(currentFloor);
            } else {
                floor = (Integer) requestSet.ceiling(currentFloor);
            }
        }
        if (floor == null) {
            try {
                System.out.println("Waiting at Floor :" + getCurrentFloor());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // Remove the request from Set as it is the request in Progress.
            requestSet.remove(floor);
        }
        return (floor == null) ? -1 : floor;
    }

    public void setCurrentFloor(int currentFloor) throws InterruptedException {
        if (this.currentFloor > currentFloor) {
            setDirection(Direction.DOWN);
        } else {
            setDirection(Direction.UP);
        }
        this.currentFloor = currentFloor;

        Display display = Display.getInstance();
        display.dispalyFloor(currentFloor);

        Thread.sleep(3000);
    }

}
