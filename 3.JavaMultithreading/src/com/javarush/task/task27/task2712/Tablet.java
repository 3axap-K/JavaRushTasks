package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {

    private final int number;
    public static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;
    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            constantOrder(order);
            return order;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
            return null;
        }
    }

    private void constantOrder(Order order) {
        if(!order.isEmpty()){
//            setChanged();
  //          notifyObservers(order);
            queue.add(order);
            AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);


            try {
                manager.processVideos();
            }catch (NoVideoAvailableException e){
                logger.log(Level.INFO, "No video is available for the order " + order);
            }

        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{number=" + number + "}";
    }



    public void createTestOrder() throws IOException {
        TestOrder order = new TestOrder(this);
        ConsoleHelper.writeMessage(order.toString());
        constantOrder(order);
    }

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }
}
