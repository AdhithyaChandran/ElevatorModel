package main.java.sg.dlt.elevator.request;

public class InternalRequest {
    private int currentfloor;
    private int destinationFloor;
    private int timestamp;

    public int getCurrentfloor() {
        return currentfloor;
    }

    public void setCurrentfloor(int currentfloor) {
        this.currentfloor = currentfloor;
    }

    public InternalRequest(int currentfloor, int destinationFloor, int timestamp) {
        this.currentfloor = currentfloor;
        this.destinationFloor = destinationFloor;
        this.timestamp = timestamp;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
