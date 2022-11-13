package com.javarush.task.task27.task2712;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private int interval;
    private List<Tablet> tabletList;
    public RandomOrderGeneratorTask(List<Tablet> tabletList, int interval) {
        this.interval = interval;
        this.tabletList = tabletList;
    }


    @Override
    public void run() {
        int chooseTablet = (int)(Math.random()*tabletList.size());
        synchronized (this){
            while(!Thread.interrupted()) {
                try {
                    Thread.sleep(interval);
                    tabletList.get(chooseTablet).createTestOrder();
                } catch (InterruptedException e) {

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
