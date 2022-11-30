package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int[]    numbers = {900,    1000,   400,    500,     90 ,   100,     40 ,   50 ,      9 ,   10 ,    4,    5 ,    1 };
        String[] letters = {"CM",   "M" ,   "CD",   "D",    "XC",   "C",    "XL",   "L",    "IX",   "X",   "IV", "V",   "I"};
        int arab = 0;
        for (int i = 0; i < numbers.length; i++){
            while (s.contains(letters[i])){
                arab += numbers[i];
                s = s.replaceFirst(letters[i],"" );
            }
        }
        if (s.length() != 0) throw  new NumberFormatException();
        return arab;
    }
}
