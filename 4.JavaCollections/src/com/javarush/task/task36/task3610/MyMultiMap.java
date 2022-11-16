package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<V> list : map.values()){
            size += list.size();
        }
        return size;
        //напишите тут ваш код
    }

    @Override
    public V put(K key, V value) {
        if (!map.containsKey(key)){
            map.put(key, new ArrayList<V>());
            map.get(key).add(value);
            return null;
        }else {
            V last = map.get(key).get(map.get(key).size() - 1);
            if (map.get(key).size() == repeatCount){
                map.get(key).add(value);
                map.get(key).remove(0);
            }else{
                map.get(key).add(value);
            }
            return last;

            //напишите тут ваш код
        }
    }

    @Override
    public V remove(Object key) {
        if(!map.containsKey(key)){
            return null;
        }
        V last = map.get(key).get(0);
        map.get(key).remove(0);
        if(map.get(key).size() == 0) map.remove(key);
        return last;
        //напишите тут ваш код
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
        //напишите тут ваш код
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (List<V> value: map.values()){
            values.addAll(value);
        }
        return values;
        //напишите тут ваш код
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);//напишите тут ваш код
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> vList: map.values()){
            for (V v : vList){
                if (v.equals(value)) return true;
            }
        }
        return false;
        //напишите тут ваш код
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}