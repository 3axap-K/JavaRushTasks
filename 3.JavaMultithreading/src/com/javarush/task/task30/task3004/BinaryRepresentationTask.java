package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;

    public BinaryRepresentationTask(int x) {
        this.x = x;
    }

    @Override
    protected String compute() {
        if(this.x/2 > 0) {
            BinaryRepresentationTask forkTask = new BinaryRepresentationTask(this.x / 2);
            forkTask.fork();
            return forkTask.join() + String.valueOf(this.x%2);
        }

        return String.valueOf(this.x%2);
    }
}
