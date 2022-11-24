package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
//15.1
public class SpeedTest {
    //15.2
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date startId = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date stopId = new Date();
        return stopId.getTime() - startId.getTime();
    }
    //15.3
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date startString = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date stopString = new Date();
        return stopString.getTime() - startString.getTime();
    }

    //15.4
    @Test
    public void testHashMapStorage(){
        //15.4.1
        HashMapStorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        HashBiMapStorageStrategy hashBiMapStorageStrategy = new HashBiMapStorageStrategy();
        Shortener shortener1 = new Shortener(hashMapStorageStrategy);
        Shortener shortener2 = new Shortener(hashBiMapStorageStrategy);
        //15.4.2
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 1000; i++){
            origStrings.add(Helper.generateRandomString());
        }
        //15.4.3
        Set<Long> origIds = new HashSet<>();
        long idTime1 = getTimeToGetIds(shortener1, origStrings, origIds);
        long idTime2 = getTimeToGetIds(shortener2, origStrings, origIds);
        //15.4.4
        Assert.assertTrue(idTime1 > idTime2);
        //15.4.5
        long stringTime1 = getTimeToGetStrings(shortener1, origIds, origStrings);
        long stringTime2 = getTimeToGetStrings(shortener2, origIds, origStrings);
        //15.4.6
        Assert.assertEquals(stringTime1, stringTime2, 30);
    }
}
