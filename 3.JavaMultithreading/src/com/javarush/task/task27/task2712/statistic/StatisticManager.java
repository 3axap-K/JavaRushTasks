package com.javarush.task.task27.task2712.statistic;



import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static final StatisticManager instance;

    static {
        try {
            instance = new StatisticManager();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private final StatisticStorage statisticStorage = new StatisticStorage();
//    private Set<Cook> cooks = new HashSet<>();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
    private StatisticManager() throws ParseException {}

    private class StatisticStorage {
        private final Map<EventType, List<EventDataRow>> storage;

        private StatisticStorage() throws ParseException {
            storage = new HashMap<>();
            for(EventType eventType : EventType.values()){
                storage.put(eventType, new ArrayList<>());
            }


        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

 /*   public void register(Cook cook){
        cooks.add(cook);
    }
*/
    public TreeMap<String, Long> getListDailyIncome(){
        TreeMap<String, Long> listDailyIncome = new TreeMap<>();
        for(EventDataRow eventDataRow : statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS)){
            String date = dateFormat.format(eventDataRow.getDate());
            if(!listDailyIncome.containsKey(date)) listDailyIncome.put(date, getDailyIncome(date));
        }
        return listDailyIncome;
    }


    private long getDailyIncome(String date){

        long dailyIncome = 0;
        for (EventDataRow eventDataRow : statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS)){
            VideoSelectedEventDataRow  videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            if (date.equals(dateFormat.format(videoSelectedEventDataRow.getDate()))){

                dailyIncome = videoSelectedEventDataRow.getAmount() + dailyIncome;
            }
        }
        return dailyIncome;
    }

    public TreeMap<String, List<String>> getListCookWorkLoading(){
        TreeMap<String, List<String>> listCookWorkLoading = new TreeMap<>();
        for(EventDataRow eventDataRow : statisticStorage.getStorage().get(EventType.COOKED_ORDER)){
            String date = dateFormat.format(eventDataRow.getDate());
            if(!listCookWorkLoading.containsKey(date)) listCookWorkLoading.put(date, getCookWorkLoading(date));
        }
        return listCookWorkLoading;
    }

    private List<String> getCookWorkLoading(String date){
        List<String> cookWork = new ArrayList<>();
        List<String> cooks = new ArrayList<>();
        for (EventDataRow eventDataRow : statisticStorage.getStorage().get(EventType.COOKED_ORDER)){
            CookedOrderEventDataRow dataRow = (CookedOrderEventDataRow) eventDataRow;
            if (date.equals(dateFormat.format(dataRow.getDate())) && !cooks.contains(dataRow.getCookName())){
                cooks.add(dataRow.getCookName());
                cookWork.add(cookDailyTime(date, dataRow.getCookName()));
            }
        }
        Collections.sort(cookWork);
        return cookWork;
    }

    private String cookDailyTime(String date, String name){
        String dailyTime = null;
        int time = 0;
        for (EventDataRow eventDataRow : statisticStorage.getStorage().get(EventType.COOKED_ORDER)){
            CookedOrderEventDataRow dataRow = (CookedOrderEventDataRow) eventDataRow;
            if (date.equals(dateFormat.format(dataRow.getDate())) && name.equals(dataRow.getCookName())){

                time += dataRow.getTime();
            }
        }
        dailyTime = String.format("%s - %s min",name ,(int)Math.ceil((double)time/60));
        return dailyTime;
    }


/*    public Set<Cook> getCooks() {
        return cooks;
    }
*/
    public static StatisticManager getInstance(){
        return instance;
    }


}
