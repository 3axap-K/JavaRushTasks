package com.javarush.task.task30.task3010;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {


        int test = 0;
        for(int i = 2; i < 37; i++) {
            try {
                BigInteger bigInteger = new BigInteger(args[0], i);
                System.out.println(i);
                test = i;
                return;
            } catch (Exception e) {
            }

        }
        if(test == 0) System.out.println("incorrect");
        //напишите тут ваш код
    }
}