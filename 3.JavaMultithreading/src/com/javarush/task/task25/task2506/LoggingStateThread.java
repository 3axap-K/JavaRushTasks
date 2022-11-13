package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{

    private Thread target;
    public LoggingStateThread(Thread target) {
        this.target = target;
    }

    @Override
    public synchronized void start() {
        this.setDaemon(true);
        super.start();
    }

    @Override
    public void run() {

        super.run();
        State state = target.getState();
        System.out.println(state);
        while(true) {

            if(state != target.getState()){
                System.out.println(target.getState());

                state = target.getState();
                if(state == State.TERMINATED) return;
            }
        }
    }
}
