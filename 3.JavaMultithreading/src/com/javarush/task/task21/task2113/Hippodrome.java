package com.javarush.task.task21.task2113;

/*
Ипподром(15)
Добавим определение победителя.
В классе Hippodrome сделаем два метода:
public Horse getWinner() и public void printWinner()

Метод getWinner должен возвращать лошадь пробежавшую самую большую дистанцию.
Метод printWinner выводит на экран имя победителя в виде: Winner is <name>!

Пример:
Winner is Lucky!


Требования:
1. В классе Hippodrome должен быть создан метод getWinner без параметров.
2. В классе Hippodrome должен быть создан метод printWinner без параметров.
3. Метод getWinner должен возвращать лошадь пробежавшую наибольшую дистанцию.
4. Метод printWinner должен выводить на экран имя победителя на экран в формате заданном в условии задачи.

*/


import java.util.ArrayList;
import java.util.List;

public class Hippodrome {


    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    static Hippodrome game;

    public void run() throws InterruptedException {
        for(int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move(){
        for(Horse horse: horses){
            horse.move();
        }
    }

    public void print(){
        for(Horse horse: horses){
            horse.print();
        }
        for(int i = 0; i < 10; i++){
            System.out.println();
        }

    }

    public Horse getWinner(){
        Horse winner = new Horse("Winner",0,0);
        for(Horse horse: horses){
            if (horse.getDistance() > winner.getDistance()) winner = horse;
        }
        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<Horse>();
        Horse siwka = new Horse("Siwka-Burka", 3,0);
        Horse buts = new Horse("Butsefal",3,0);
        Horse majlo = new Horse("Majlo",3,0);
        horses.add(siwka);
        horses.add(buts);
        horses.add(majlo);
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();


    }
}
