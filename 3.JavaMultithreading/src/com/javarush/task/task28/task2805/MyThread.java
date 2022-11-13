package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread{

    public static AtomicInteger priority = new AtomicInteger(MIN_PRIORITY);
{
    int prior = priority.get() < MAX_PRIORITY ?  priority.getAndIncrement() : priority.getAndSet(MIN_PRIORITY);
///    prior = Math.min(prior, this.getThreadGroup().getMaxPriority());
    this.setPriority(prior);
}
    private void myPriority(){

    }
    public MyThread() {
        myPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        myPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        myPriority();
    }

    public MyThread(String name) {
        super(name);
        myPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        myPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        myPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        myPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        myPriority();
    }


}
