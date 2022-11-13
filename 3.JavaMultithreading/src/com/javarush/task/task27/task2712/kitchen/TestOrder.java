package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order{
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        this.dishes = new ArrayList<>();
        int orderSize = (int) (Math.random()*10);
        for(int i = 0; i < orderSize; i++){
            dishes.add(Dish.values()[(int)(Math.random()*Dish.values().length)]);
        }

    }
}
