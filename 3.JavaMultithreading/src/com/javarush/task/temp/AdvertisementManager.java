package com.javarush.task.temp;



import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.AdvertisementStorage;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    private List<List<Advertisement>> listPosibilities = new ArrayList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){

        if(storage.list().isEmpty()) throw new NoVideoAvailableException();

        List<Advertisement> listVideosWhichCanBeUsed = new ArrayList<>();
        for(Advertisement advertisement: storage.list()){
            if(advertisement.getHits() > 0 && advertisement.getDuration() <= timeSeconds)
                listVideosWhichCanBeUsed.add(advertisement);
        }
        if(listVideosWhichCanBeUsed.isEmpty()) return;
        Collections.sort(listVideosWhichCanBeUsed, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long amount = o2.getAmountPerOneSecondOfDisplaying() - o1.getAmountPerOneSecondOfDisplaying();
                if(amount == 0) return 0;
                return amount > 0 ? 1 : -1;
            }
        });

        showReclam(getChoosedList(listVideosWhichCanBeUsed));

    }

    private List<Advertisement> getChoosedList(List<Advertisement> listVideosWhichCanBeUsed){
        getListPosibilities(listVideosWhichCanBeUsed);
        if(listVideosWhichCanBeUsed.size() > 1) Collections.sort(listPosibilities, new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                int result;
                long amount = getPrice(o2) - getPrice(o1);
                if(amount == 0){
                    result = getShowTime(o2) - getShowTime(o1);
                    if(result == 0) return o1.size() - o2.size();
                }else result = (int) amount;
                return result;
            }
        });
        return listPosibilities.get(0);
    }

    private void getListPosibilities(List<Advertisement> checkLisk){
        int lostTime = timeSeconds;
        List<Advertisement> showList = new ArrayList<>();
        if(!checkLisk.isEmpty()) {
            for (Advertisement advertisement : checkLisk) {
                if (lostTime >= advertisement.getDuration()) {
                    showList.add(advertisement);
                    lostTime = lostTime - advertisement.getDuration();
                }
            }

            if(lostTime != timeSeconds){
                listPosibilities.add(showList);
                checkLisk.removeAll(showList);
                getListPosibilities(checkLisk);}
        }

    }

    private long getPrice(List<Advertisement> showList){
        long price = 0;
        if(!showList.isEmpty()){
            for(Advertisement advertisement : showList){
                price = advertisement.getAmountPerOneDisplaying() + price;
            }
        }
        return price;
    }

    private void showReclam(List<Advertisement> finallyShowList){
        Collections.sort(finallyShowList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long initial = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();

                if(initial == 0){
                    long amount = o1.getAmountPerOneSecondOfDisplaying() - o2.getAmountPerOneDisplaying();
                    if(amount == 0) return 0;
                    else return (int) amount;
                }else return (int) initial;
            }
        });
        for(Advertisement advertisement : finallyShowList){
            ConsoleHelper.writeMessage(advertisement.toString());
            advertisement.revalidate();
        }
    }



    private int getShowTime(List<Advertisement> showList){
        int showTime = 0;
        if(!showList.isEmpty()){
            for(Advertisement advertisement : showList){
                showTime = showTime + advertisement.getDuration();
            }
        }
        return showTime;
    }









}
