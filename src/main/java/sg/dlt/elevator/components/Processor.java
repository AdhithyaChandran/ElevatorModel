package main.java.sg.dlt.elevator.components;

import main.java.sg.dlt.elevator.request.InternalRequest;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Processor implements Comparator<InternalRequest> {

    private PriorityQueue<InternalRequest> internalRequests = new PriorityQueue<>();


    private static Processor processor = null;

    private Thread requestProcessorThread;

    public void setRequestProcessorThread(Thread requestProcessorThread) {
        this.requestProcessorThread = requestProcessorThread;
    }

    public Thread getRequestProcessorThread() {
        return requestProcessorThread;
    }

    private int currentFloor = 0;


    public int getCurrentFloor() {
        return currentFloor;
    }

    public PriorityQueue getRequests(){
        return internalRequests;
    }


    public static Processor getInstance() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }


    public synchronized void addNewRequest(int floor) {
        InternalRequest internalRequest = new InternalRequest(currentFloor,floor,(int) System.nanoTime());
        internalRequests.add(internalRequest);
        if(getRequestProcessorThread().getState() == Thread.State.WAITING){
            notify();
        }else{
            getRequestProcessorThread().interrupt();
        }

    }

    public synchronized int nextFloor() {
        Integer floor = null;
        floor = (internalRequests.size()>0) ? internalRequests.remove().getCurrentfloor(): null;
        if (floor == null) {
            try {
                System.out.println("Waiting at Floor :" + getCurrentFloor());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (floor == null) ? -1 : floor;
    }

    public void setCurrentFloor(int currentFloor) throws InterruptedException {
        this.currentFloor = currentFloor;

        Display.getInstance().dispalyFloor(currentFloor);

        Thread.sleep(3000);
    }

    public boolean isValidFloorNumber(int floor) {
        return (floor < 100);
    }

    public void move(int destinationFloor, int currentFloor) throws InterruptedException {
        if (destinationFloor >= 0) {
            if (currentFloor > destinationFloor) {
                while (currentFloor > destinationFloor) {
                    setCurrentFloor(--currentFloor);
                }
            } else {
                while (currentFloor < destinationFloor) {
                   setCurrentFloor(++currentFloor);
                }
            }
            Display.getInstance().displayWelcome(Processor.getInstance().getCurrentFloor());
        }
    }

    @Override

    public int compare(InternalRequest a, InternalRequest b) {
        if ((a.getDestinationFloor() - a.getCurrentfloor()) > (b.getDestinationFloor() - a.getCurrentfloor()))
            return 1;
        else if ((a.getDestinationFloor() - a.getCurrentfloor()) < (b.getDestinationFloor() - a.getCurrentfloor()))
            return -1;
        return 0;
    }
}
