package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    public void testStorage(Shortener shortener){
        String string1 = "SameString";
        String string2 = "AnotherString";
        String string3 = "SameString";
        Long id1 = shortener.getId(string1);
        Long id2 = shortener.getId(string2);
        Long id3 = shortener.getId(string3);
        Assert.assertNotEquals(id2, id3);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);
        String backString1 = shortener.getString(id1);
        String backString2 = shortener.getString(id2);
        String backString3 = shortener.getString(id3);
        Assert.assertEquals(string1, backString1);
        Assert.assertEquals(string2, backString2);
        Assert.assertEquals(string3, backString3);
    }
    @Test
    public void testHashMapStorageStrategy(){
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy(){
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy(){
        FileStorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy(){
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy(){
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy(){
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

}
