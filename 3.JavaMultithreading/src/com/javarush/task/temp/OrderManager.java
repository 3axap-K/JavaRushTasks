package com.javarush.task.temp;


import java.util.Observable;
import java.util.Observer;

public class OrderManager implements Observer {
    @Override
    public void update(Observable o, Object arg) {

    }

/*
    private LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private Set<Cook> cooks= StatisticManager.getInstance().getCooks();
    public OrderManager(){

        Thread thread = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        this.sleep(10);


                                for(Cook cook : cooks){
                                    if(!cook.isBusy() && !orderQueue.isEmpty()){
                                        cook.startCookingOrder(orderQueue.poll());
                                    }
                                }


                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        orderQueue.add((Order) arg);
    }


 */
}
