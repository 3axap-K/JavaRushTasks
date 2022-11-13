package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

    private Thread thread;


    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                System.out.println(this.thread.getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    @Override
    public void start(String threadName) {
        this.thread = new Thread(this, threadName);
        this.thread.start();
    }

    @Override
    public void stop() {
        this.thread.interrupt();
    }
}
