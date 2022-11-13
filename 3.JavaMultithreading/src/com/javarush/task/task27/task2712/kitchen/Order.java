package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();

    }

    public int getTotalCookingTime(){
        int time = 0;
        for(Dish dish: dishes){
            time += dish.getDuration();
        }
        return time;
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    public List<Dish> getDishes(){
        return dishes;
    }
    @Override
    public String toString() {
        String order = "";
        if(this.dishes.size() == 0) return order;
        for( Dish dish: this.dishes){
            if(order.equals(""))order = dish.name();
            else order = order + ", " + dish.name();
        }
        order = String.format("Your order: [%s] of %s, cooking time %smin"
                , order, this.tablet.toString(), getTotalCookingTime() );
        return order;


    }

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
