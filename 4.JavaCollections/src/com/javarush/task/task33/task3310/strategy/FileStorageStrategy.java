package com.javarush.task.task33.task3310.strategy;

import java.util.Stack;

public class FileStorageStrategy implements StorageStrategy{

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size = 0;


    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize = 10*DEFAULT_BUCKET_SIZE_LIMIT;

    public int hash(Long k){
        int h;
        return (k == 0) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length){
        for (int i = 0; i < length; i++){
            if(table[i] == null){ return i;}

            Entry entry = table[i].getEntry();

            do{
                if(hash(entry.getKey()) == hash) return i;
            }while((entry = entry.next) != null);
            if(table[i].getFileSize() < bucketSizeLimit) return i;
        }
        return length - 1;
    }

    public Entry getEntry(Long key){
        for (int i = 0; i < table.length; i++){
            if(table[i] == null){ return null;}

            Entry entry = table[i].getEntry();
            do{
                if(entry.getKey() == key) return entry;
            }while((entry = entry.next) != null);
        }
        return null;
    }

    public void resize(int newCapacity){
        FileBucket[] oldTable = table;
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
        for (int i = 0; i < oldTable.length; i++){
            if(oldTable[i] != null) oldTable[i].remove();
        }
    }

    public void transfer(FileBucket[] newTable){
        Stack<Entry> lastEntry = new Stack<>();
        for (int i = 0; i < table.length; i++){
            newTable[i] = new FileBucket();
            Entry newEntry;
            if (table[i] == null && !lastEntry.isEmpty()) {
                newEntry = lastEntry.pop();
                Entry entry = newEntry;
                while(!lastEntry.isEmpty()){
                    entry.next = lastEntry.pop();
                    entry = entry.next;
                }
                newTable[i].putEntry(newEntry);

            }else if (table[i] != null) {

                while (table[i].getFileSize() >= getBucketSizeLimit()) {
                    lastEntry.push(deleteLastEntry(table[i]));
                }

                newEntry = table[i].getEntry();

                newTable[i].putEntry(newEntry);
            }else {
                newTable[i] = null;
            }

        }
    }

    private Entry deleteLastEntry(FileBucket bucket){
        Entry lastEntry = null;
        Entry buckedEntry = bucket.getEntry();
        Entry entry = buckedEntry;
        do{
            if(entry.next == null) lastEntry = entry;
        }while((entry = entry.next) != null);
        entry = buckedEntry;
        do{
            if(entry.next == lastEntry) entry.next = null;
        }while((entry = entry.next) != null);
        bucket.putEntry(buckedEntry);
        return lastEntry;
    }
    public void addEntry(int hash, Long key, String value, int bucketIndex){

        if (containsKey(key)){
            Entry entry = table[bucketIndex].getEntry();
            Entry newEntry = entry;
            boolean stop = false;
            do{
                if (entry.getKey() == key){

                    entry.value = value;
                    stop = true;
                }
                entry = entry.next;
            }while(!stop);
            table[bucketIndex].putEntry(newEntry);
        }else{
            createEntry(hash, key, value, bucketIndex);
        }

        if (table[bucketIndex].getFileSize() >= bucketSizeLimit) resize(table.length*2);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry newEntry = new Entry(hash, key, value, null);
        if(table[bucketIndex] == null){
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(newEntry);
        }else {
            Entry entry = table[bucketIndex].getEntry();
            Entry putEntry = entry;
            do{
                if(entry.next == null){
                    entry.next = newEntry;
                    entry = entry.next;
                }
            }while ((entry = entry.next) != null);
            table[bucketIndex].putEntry(putEntry);
        }
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        for (int i = 0; i < table.length; i++){
            if(table[i] == null){ return false;}

            Entry entry = table[i].getEntry();

            do{
                if(hash(entry.getKey()) == hash(key)) return true;
            }while((entry = entry.next) != null);

        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++){
            if(table[i] == null){ return false;}

            Entry entry = table[i].getEntry();

            do{
                if(entry.getValue().equals(value)) return true;
            }while((entry = entry.next) != null);

        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, table.length);
        addEntry(hash, key, value, bucketIndex);
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < table.length; i++){
            if(table[i] == null){ return Long.valueOf(0);}

            Entry entry = table[i].getEntry();

            do{
                if(entry.getValue().equals(value)) return entry.getKey();
            }while((entry = entry.next) != null);

        }
        return Long.valueOf(0);
    }

    @Override
    public String getValue(Long key) {
        for (int i = 0; i < table.length; i++){
            if(table[i] == null){ return null;}

            Entry entry = table[i].getEntry();

            do{
                if(hash(entry.getKey()) == hash(key)) return entry.getValue();
            }while((entry = entry.next) != null);

        }
        return null;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

}
