package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int redex;
        int index = 0;
        if(s.charAt(0) == '-') index = 1;
        if(s.charAt(index) == '0'){
            switch (s.charAt(index+1)){
                case 'b':
                    redex = 2;
                    s = s.substring(index + 2);
                    break;
                case 'x':
                    redex = 16;
                    s = s.substring(index + 2);
                    break;
                default:
                    redex = 8;
                    s = s.substring(index + 1);
                    break;
            }

            if (index == 1) s = "-" + s;
        }else redex = 10;

        int i = Integer.parseInt(s, redex);
        s = String.valueOf(i);
        return s;
    }
}
