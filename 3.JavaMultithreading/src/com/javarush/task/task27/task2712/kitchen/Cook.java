package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.io.ObjectStreamException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{

    private final String name;
    private boolean busy;



    private LinkedBlockingQueue<Order> queue;


    private StatisticManager statisticManager = StatisticManager.getInstance();

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    public void startCookingOrder(Order order) throws InterruptedException {
        this.busy = true;
        ConsoleHelper.writeMessage("Start cooking - " + order);
        Thread.sleep(order.getTotalCookingTime()*10L);
        setChanged();
        notifyObservers(order);
        this.busy = false;
    }


    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {

                Thread.sleep(10);
                if(!isBusy() && !queue.isEmpty()){
                    this.startCookingOrder(queue.poll());
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
/*
    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        setChanged();
        notifyObservers(arg);
        Order order = (Order) arg;
        statisticManager.register(new CookedOrderEventDataRow(o.toString(),
                this.name, order.getTotalCookingTime()*60, order.getDishes()));

    }

 */



}
