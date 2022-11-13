package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/

public class Solution {

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    public static AtomicInteger factoryCount = new AtomicInteger(1);

    public static class AmigoThreadFactory implements ThreadFactory{
        private int factoryNumber;
        protected AtomicInteger threadCount = new AtomicInteger(1);
        public AmigoThreadFactory(){
            factoryNumber = factoryCount.getAndIncrement();
        }


        @Override
        public Thread newThread(Runnable r) {
            String groupName = Thread.currentThread().getThreadGroup().getName();
            String threadName = String.format("%s-pool-%s-thread-%s", groupName , factoryNumber, threadCount.getAndIncrement());
            Thread thread =  new Thread(Thread.currentThread().getThreadGroup(), threadName){
                @Override
                public void run() {
                    r.run();
                }
            };
            thread.setDaemon(false);
            thread.setPriority(5);


            return thread;
        }
    }
    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
