package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {


    public static void main(String[] args) {
 //       OrderManager orderManager = new OrderManager();
        Cook cook = new Cook("Vasya");
        cook.setQueue(ORDER_QUEUE);
        Cook cook1 = new Cook("Oleh");
        cook1.setQueue(ORDER_QUEUE);
        Waiter waiter = new Waiter();
 //       StatisticManager.getInstance().register(cook);
   //     StatisticManager.getInstance().register(cook1);
        cook.addObserver(waiter);
        cook1.addObserver(waiter);

        Thread thread = new Thread(cook);
        Thread thread1 = new Thread(cook1);
        thread.start();
        thread1.start();

        List<Tablet> tablets = new ArrayList<>();
        for(int i = 0; i<5; i++){
            Tablet tablet = new Tablet(i);
//            tablet.addObserver(orderManager);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask generatorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);

        Thread testThread = new Thread(generatorTask);
        testThread.start();
        try {
            Thread.sleep(1000);
            thread.stop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();

/*



*/
    }

    private static final LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    private static final int ORDER_CREATING_INTERVAL = 100;


}
