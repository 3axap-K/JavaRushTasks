package com.javarush.task.task30.task3001;


import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* 
Конвертер систем счислений
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        try{
            checkException(number);
        }catch (NumberFormatException e){throw new NumberFormatException();}

        String taskNumber = number.getDigit();
        int taskNumberSystem = number.getNumberSystem().getNumberSystemIntValue();

        int newNumberSystem = expectedNumberSystem.getNumberSystemIntValue();

        BigInteger taskInteger = new BigInteger(taskNumber, taskNumberSystem);

        String newNumber = taskInteger.toString(newNumberSystem);

        return new Number(expectedNumberSystem, newNumber);

    }

    private static void checkException(Number number){

        String taskNumber = number.getDigit();

        if(taskNumber.contains(".") || taskNumber.contains("-")) throw new NumberFormatException();

        int numberSystem = number.getNumberSystem().getNumberSystemIntValue();
        String checkList = "0123456789ABCDEF";



        checkList = checkList.substring(0,numberSystem);

        for(int i = 0; i < taskNumber.length(); i++) {
            if (!checkList.contains(String.valueOf(taskNumber.toUpperCase(Locale.ROOT).charAt(i)))) throw new NumberFormatException();
        }

    }
}
