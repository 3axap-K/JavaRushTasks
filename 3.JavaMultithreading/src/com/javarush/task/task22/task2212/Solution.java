package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
Проверка номера телефона
Метод checkTelNumber должен проверять, является ли аргумент telNumber валидным номером телефона.

Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')' , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру

Примеры:
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false


Требования:
1. Метод checkTelNumber должен возвращать значение типа boolean.
2. Метод checkTelNumber должен быть публичным.
3. Метод checkTelNumber должен принимать один параметр типа String.
4. Метод checkTelNumber должен корректно проверять валидность номера телефона переданного ему в качестве параметра.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber==null) return false;
        return telNumber.matches("^(\\+\\d{2})?(\\(\\d{3}\\)|\\d{3})\\d{3}\\-?\\d{2}\\-?\\d{2}$");

    }

    public static void main(String[] args) throws IOException {
        List<String> numbers = new ArrayList<>();
        try(BufferedReader buffer = new BufferedReader(new FileReader(args[1]))){
            while(buffer.ready()) {
                numbers.add(buffer.readLine());
            }
        }

        for(String number: numbers){
            System.out.println(number + " - " + checkTelNumber(number));
        }

    }
}
