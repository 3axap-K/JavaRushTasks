package com.javarush.task.task27.task2712.ad;

public class Advertisement {
    private Object content;
    private  String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    private long amountPerOneSecondOfDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if(hits > 0){
            this.amountPerOneDisplaying = initialAmount/hits;
            this.amountPerOneSecondOfDisplaying = 1000 * amountPerOneDisplaying/duration;
        }
    }

    public void revalidate(){
        if(hits <= 0) throw new NoVideoAvailableException();
        hits--;
    }

    @Override
    public String toString() {
        return getName() + " is displaying... " +
                getAmountPerOneDisplaying() + ", " +
                getAmountPerOneSecondOfDisplaying();
    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getAmountPerOneSecondOfDisplaying() {
        return amountPerOneSecondOfDisplaying;
    }
}
