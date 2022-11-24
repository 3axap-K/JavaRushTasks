package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size = 0;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k){
        int h;
        return (k == 0) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length){
        for (int i = 0; i < length; i++){
            if(hash(table[i].key) == hash) return i;
        }
        return length;
    }

    public Entry getEntry(Long key){
        for (Entry entry : table){
            if(entry != null && entry.getKey() == key){
                return entry;
            }
        }
        return null;
    }

    public void resize(int newCapacity){
        Entry[] oldTable = table;
        int oldCapacity = (oldTable == null) ? 0 : oldTable.length;
        int oldThreshold = threshold;
        int newThreshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = newThreshold;
    }

    public void transfer(Entry[] newTable){
        for (int i = 0; i < table.length; i++){
            newTable[i] = table[i];
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        if(bucketIndex >= size) resize(size + DEFAULT_INITIAL_CAPACITY);
        table[bucketIndex] = new Entry(hash, key, value,null);
        if (bucketIndex > 0) table[bucketIndex-1].next = table[bucketIndex];
        if (bucketIndex == size) size++;
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex){}

    @Override
    public boolean containsKey(Long key) {
        if (getEntry(key) != null) return true;
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (Entry entry : table){
            if (entry != null && entry.getValue().equals(value)){return true;}
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int bucketIndex = indexFor(hash, size);
        addEntry(hash, key, value, bucketIndex);
    }

    @Override
    public Long getKey(String value) {
        for (Entry entry : table){
            if (entry != null && entry.getValue().equals(value)){return entry.getKey();}
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        for (Entry entry : table){
            if (entry != null && entry.getKey().equals(key)){return entry.getValue();}
        }
        return null;
    }
}
