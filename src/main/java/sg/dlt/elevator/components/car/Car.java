package main.java.sg.dlt.elevator.components.car;

import main.java.sg.dlt.elevator.Elevator;
import main.java.sg.dlt.elevator.components.Display;
import main.java.sg.dlt.elevator.components.Processor;
import main.java.sg.dlt.elevator.request.InternalRequest;

import java.util.PriorityQueue;
import java.util.Random;

public class Car {
    private int currentFloor = 0;


    public int getCurrentFloor() {
        return currentFloor;
    }

    private static Car car = null;

    private Processor processor;

    private PriorityQueue<InternalRequest> internalRequests = new PriorityQueue<>();


    private Display display;


}
