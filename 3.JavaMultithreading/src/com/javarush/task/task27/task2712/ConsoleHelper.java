package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> order = new ArrayList<>();
        String menu = Dish.allDishesToString();
        writeMessage(menu);
        writeMessage("Make your order - when you finish type \"exit\"");
        String some = readString();
        while(!some.toLowerCase().equals("exit")){
            int i = order.size();
            for(Dish dish: Dish.values()){
                if (dish.name().toLowerCase().equals(some.toLowerCase())) {
                    order.add(dish);
                    break;
                }
            }
            if(i == order.size()) writeMessage("Sorry, we have no that dish");
            some = readString();
        }
        return order;
    }
}
