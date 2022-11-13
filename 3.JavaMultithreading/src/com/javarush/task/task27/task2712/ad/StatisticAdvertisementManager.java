package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();

    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private StatisticAdvertisementManager(){}


    public List<Advertisement> getActivVideos(){
        List<Advertisement> activVideos = new ArrayList<>();
        for(Advertisement advertisement : storage.list()){
            if(advertisement.getHits() > 0) activVideos.add(advertisement);
        }
        activVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {

                return o1.getName().toLowerCase(Locale.ROOT).compareTo(o2.getName().toLowerCase());
            }
        });
        return activVideos;
    }

    public List<Advertisement> getArchivedVideos(){
        List<Advertisement> archivedVideos = new ArrayList<>();
        for(Advertisement advertisement : storage.list()){
            if(advertisement.getHits() <= 0) archivedVideos.add(advertisement);
        }
        archivedVideos.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {

                return o1.getName().toLowerCase(Locale.ROOT).compareTo(o2.getName().toLowerCase());
            }
        });
        return archivedVideos;
    }

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }
}
