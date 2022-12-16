package main.java.sg.dlt.elevator;

import main.java.sg.dlt.elevator.components.Processor;
import main.java.sg.dlt.elevator.request.RequestListener;
import main.java.sg.dlt.elevator.request.RequestProcessor;


public class Elevator {
    public void start(){
        System.out.println("<< WELCOME >>");
        Thread listenerThread = new Thread(new RequestListener(), "ListenerThread");
        Thread processorThread = new Thread(new RequestProcessor(), "ProcessorThread");

        Processor.getInstance().setRequestProcessorThread(processorThread);
        listenerThread.start();
        processorThread.start();
    }

}
