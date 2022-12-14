package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
/*        videos.add( new Advertisement(someContent, "First Video", 5000, 100, 4 * 60));
        videos.add( new Advertisement(someContent, "second Video", 100, 10, 15 * 60));
        videos.add( new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        videos.add( new Advertisement(someContent, "Четвертое Video", 5000, 100, 4 * 60));
        videos.add( new Advertisement(someContent, "пятое Video", 100, 10, 15 * 60));
        videos.add( new Advertisement(someContent, "6 Video", 400, 2, 10 * 60));
        videos.add( new Advertisement(someContent, "First Video", 5000, 0, 4 * 60));
        videos.add( new Advertisement(someContent, "второе Video", 100, 0, 15 * 60));
        videos.add( new Advertisement(someContent, "3 Video", 400, 0, 10 * 60));
*/add(new Advertisement(someContent, "Video 1", 500, 10, 5 * 60));
        add(new Advertisement(someContent, "Video 2", 700, 10, 7 * 60));

    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }

    public static AdvertisementStorage getInstance(){
        return instance;
    }
}
