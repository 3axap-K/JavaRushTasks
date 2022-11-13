package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;
    static public final int MAX_TRUCK_SPEED = 80, MAX_SEDAN_SPEED = 120, MAX_CABRIOLET_SPEED = 90;
    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) throws Exception{
        if( numberOfLiters <0) throw new Exception();
        else fuel += numberOfLiters;

    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {

        if (!isSummer(date, SummerStart, SummerEnd)) {
            return getWinterConsumption(length);
        } else {
            return getSummerConsumption(length);
        }

    }

    public int getNumberOfPassengersCanBeTransferred() {
        if(canPassengersBeTransferred())
        return numberOfPassengers;
        else return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (getNumberOfPassengersCanBeTransferred() > 0) fastenPassengersBelts();
        fastenDriverBelt();

    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed()/*{        if (type == TRUCK)
            return 80;
        if (type == SEDAN)
            return 120;
        return 90;
}*/;



    public static Car create(int type, int numberOfPassengers){
        if(type == 0) return new Truck(numberOfPassengers);
        else if(type == 1) return new Sedan(numberOfPassengers);
        else if (type == 2) return new Cabriolet(numberOfPassengers);
        else return null;
    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
        return date.after(summerStart) && date.before(summerEnd);
    }
    public double getWinterConsumption(int length){
        return length * winterFuelConsumption + winterWarmingUp;
    }
    public double getSummerConsumption(int length){
        return length * summerFuelConsumption;
    }

    private boolean canPassengersBeTransferred(){
        return isDriverAvailable() && fuel > 0;
    }
}