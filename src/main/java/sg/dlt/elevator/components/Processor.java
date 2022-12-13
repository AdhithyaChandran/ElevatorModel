package main.java.sg.dlt.elevator.components;

import main.java.sg.dlt.elevator.Elevator;
import main.java.sg.dlt.elevator.components.car.Direction;

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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public synchronized void addNewRequest(int floor) {
        requestSet.add(floor);
        Elevator elevator = Elevator.getInstance();
        if(elevator.getRequestProcessorThread().getState() == Thread.State.WAITING){
            notify();
        }else{
            elevator.getRequestProcessorThread().interrupt();
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

    public boolean isValidFloorNumber(int floor) {
        return (floor < 100);
    }

}
