package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        //6.2.4
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 1000);
        OurHashMapStorageStrategy ourStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourStrategy, 1000);
        FileStorageStrategy fileStorageStrategy = new FileStorageStrategy();
        testStrategy(fileStorageStrategy, 50);
        OurHashBiMapStorageStrategy biMapStorageStrategy = new OurHashBiMapStorageStrategy();
        testStrategy(biMapStorageStrategy, 1000);
    }
//6.2.1
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> set = new HashSet<>();
        for (String string : strings){
            set.add(shortener.getId(string));
        }
        return set;
    }
    //6.2.2
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> set = new HashSet<>();
        for (Long key : keys){
            set.add(shortener.getString(key));
        }
        return set;
    }
    //6.2.3
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        //6.2.3.1
        Helper.printMessage(strategy.getClass().getSimpleName());
        //6.2.3.2
        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++){
            testSet.add(Helper.generateRandomString());
        }
        //6.2.3.3
        Shortener shortener = new Shortener(strategy);
        //6.2.3.4
        Date startId = new Date();
        Set<Long> testId = getIds(shortener, testSet);
        Date stopId = new Date();
        Helper.printMessage(String.valueOf(stopId.getTime() - startId.getTime()));
        //6.2.3.5
        Date startString = new Date();
        Set<String> testString = getStrings(shortener, testId);
        Date stopString = new Date();
        Helper.printMessage(String.valueOf(stopString.getTime() - startString.getTime()));
        //6.2.3.6
        if (testSet.equals(testString)){
            Helper.printMessage("Тест пройден.");
        }else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
