package com.javarush.task.task25.task2512;

import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Живем своим умом
*/

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        List <Throwable> throwables = new ArrayList<>();
        throwables.add(e);
        Throwable tx = e;
        while(true){
            tx = tx.getCause();
            if(tx == null) break;
            throwables.add(tx);
        }
        for(int i = throwables.size()-1; i >= 0; i--){
            System.out.println(throwables.get(i).getClass().toString().replace("class ", "") + ": " + throwables.get(i).getMessage());
        }




    }

    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        };
        thread.setUncaughtExceptionHandler(new Solution());
        thread.start();
    }
}
