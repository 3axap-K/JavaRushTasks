package com.javarush.task.task27.task2712.ad;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private final StatisticManager statisticManager = StatisticManager.getInstance();
    private int timeSeconds;

    private long bestPrice;

    private int bestTime;

    private int bestSize;
    private List<Advertisement> bestList = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){

        if(storage.list().isEmpty()){
            statisticManager.register(new NoAvailableVideoEventDataRow(bestTime));
            throw new NoVideoAvailableException();}

        List<Advertisement> listVideosWhichCanBeUsed = new ArrayList<>();
        for(Advertisement advertisement: storage.list()){
            if(advertisement.getHits() > 0 && advertisement.getDuration() <= timeSeconds)
                listVideosWhichCanBeUsed.add(advertisement);
        }
        if(listVideosWhichCanBeUsed.isEmpty()) return;
Collections.reverse(listVideosWhichCanBeUsed);
        int totalListTime = getTotalListTime(listVideosWhichCanBeUsed);

        if(totalListTime <= timeSeconds){
            bestList = listVideosWhichCanBeUsed;
            bestPrice = getTotalListPrice(listVideosWhichCanBeUsed);
            bestTime = getTotalListTime(listVideosWhichCanBeUsed);
            bestSize = listVideosWhichCanBeUsed.size();
        }else findBestList(listVideosWhichCanBeUsed);

        Collections.sort(bestList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                if(o2.getAmountPerOneDisplaying() > o1.getAmountPerOneDisplaying()) return 1;
                if (o2.getAmountPerOneDisplaying() == o1.getAmountPerOneDisplaying())
                    return (int) (o1.getAmountPerOneSecondOfDisplaying() - o2.getAmountPerOneSecondOfDisplaying());


                return -1;
            }
        });

        statisticManager.register(new VideoSelectedEventDataRow(bestList, bestPrice, bestTime));

        for(Advertisement advertisement : bestList){
            ConsoleHelper.writeMessage(advertisement.toString());
            advertisement.revalidate();
        }





    }




    private void findBestList(List<Advertisement> listVideosWhichCanBeUsed){
        if(listVideosWhichCanBeUsed.size() > 0 &&
                getTotalListTime(listVideosWhichCanBeUsed) <= timeSeconds)
            compareList(listVideosWhichCanBeUsed);

        for(int i = 0; i < listVideosWhichCanBeUsed.size(); i++){
            List<Advertisement> nextListVideosWhichCanBeUsed = new ArrayList<>(listVideosWhichCanBeUsed);
            nextListVideosWhichCanBeUsed.remove(i);
            findBestList(nextListVideosWhichCanBeUsed);
        }
    }

    private void compareList(List<Advertisement> listForComparing){
        long price = getTotalListPrice(listForComparing);
        int time = getTotalListTime(listForComparing);
        int size = listForComparing.size();

        if(bestList.size() == 0 && getTotalListTime(listForComparing) < timeSeconds){
            bestList = listForComparing;
            bestPrice = getTotalListPrice(listForComparing);
            bestTime = getTotalListTime(listForComparing);
            bestSize = listForComparing.size();
        }else
            if(bestPrice < price){
                bestList = listForComparing;
                bestPrice = getTotalListPrice(listForComparing);
                bestTime = getTotalListTime(listForComparing);
                bestSize = listForComparing.size();
            }else
                if(bestPrice == price && bestTime < time){
                    bestList = listForComparing;
                    bestPrice = getTotalListPrice(listForComparing);
                    bestTime = getTotalListTime(listForComparing);
                    bestSize = listForComparing.size();
                }else
                    if(bestPrice == price && bestTime == time && bestSize > size ){
                        bestList = listForComparing;
                        bestPrice = getTotalListPrice(listForComparing);
                        bestTime = getTotalListTime(listForComparing);
                        bestSize = listForComparing.size();
                    }




    }



    private int getTotalListTime(List<Advertisement> checkList){
        int time = 0;
            for(Advertisement advertisement : checkList){
                time += advertisement.getDuration();
            }
        return time;
    }

    private long getTotalListPrice(List<Advertisement> checkList){
        long price = 0;
        for(Advertisement advertisement : checkList){
            price += advertisement.getAmountPerOneDisplaying();
        }
        return price;
    }




}
