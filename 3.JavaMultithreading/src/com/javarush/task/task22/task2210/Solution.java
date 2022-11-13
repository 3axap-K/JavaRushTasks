package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
StringTokenizer
StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.

Пример
getTokens("level22.lesson13.task01", ".")

возвращает массив строк
{"level22", "lesson13", "task01"}


Требования:
1. Метод getTokens должен использовать StringTokenizer.
2. Метод getTokens должен быть публичным.
3. Метод getTokens должен принимать два параметра типа String.
4. Массив типа String возвращенный методом getTokens должен быть заполнен правильно(согласно условию задачи).
*/

public class Solution {
    public static void main(String[] args) {
        String[] result = getTokens("level22.lesson13.task01", ".");
 //       System.out.println(result[0]);

    }

    public static String[] getTokens(String query, String delimiter) {
        if(query == null || delimiter == null) return null;
        StringTokenizer buffer = new StringTokenizer(query,delimiter);
        List<String> result = new ArrayList<>();
        while(buffer.hasMoreTokens()){
            result.add(buffer.nextToken());
        }
        return result.toArray(new String[0]);
    }
}
