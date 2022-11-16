package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Locale;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader(args[0]);
        char[] chars = new char[1000];
        reader.read(chars);
        TreeSet<String> set = new TreeSet<>();
        for (char c: chars){
            if(Character.isAlphabetic(c)){
                set.add(String.valueOf(c).toLowerCase(Locale.ROOT));
            }
        }
        int steps = 5;
        if (5 > set.size()) steps = set.size();
        for(int i = 0; i < steps; i++) {

                System.out.print(set.first());
                set.remove(set.first());

        }
    }
}
