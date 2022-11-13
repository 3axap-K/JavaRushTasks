package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DecimalFormat;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DirectorTablet {

    private final StatisticManager statisticManager = StatisticManager.getInstance();

    private StatisticAdvertisementManager statisticAdvertisementManager = StatisticAdvertisementManager.getInstance();
    public void printAdvertisementProfit(){
        TreeMap<String, Long> listDailyIncome = statisticManager.getListDailyIncome();
        double total = 0;

        while(!listDailyIncome.isEmpty()){
            Map.Entry<String, Long> entry = listDailyIncome.pollLastEntry();
            ConsoleHelper.writeMessage(entry.getKey() + " - " +  new DecimalFormat("#0.00").format((double)entry.getValue()/100.00));
            total += (double)entry.getValue()/100.00;
        }
        ConsoleHelper.writeMessage("Total - " + new DecimalFormat("#0.00").format(total));
    }
    public void printCookWorkloading(){
        TreeMap<String, List<String>> listCookWorkLoading = statisticManager.getListCookWorkLoading();
        while(!listCookWorkLoading.isEmpty()){
            Map.Entry<String, List<String>> entry = listCookWorkLoading.pollLastEntry();
            ConsoleHelper.writeMessage(entry.getKey());
            for (String cookWorkTime: entry.getValue()){
                ConsoleHelper.writeMessage(cookWorkTime);
            }
        }
    }
    public void printActiveVideoSet(){

        for(Advertisement advertisement : statisticAdvertisementManager.getActivVideos()){
    //        String toPrint = String.format("%s - %s",advertisement.getName(),advertisement.getHits());
            ConsoleHelper.writeMessage(advertisement.getName() + " - " + advertisement.getHits());
        }
    }
    public void printArchivedVideoSet(){
        for(Advertisement advertisement : statisticAdvertisementManager.getArchivedVideos()){

            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
