package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/* 
Составить цепочку слов
Составить цепочку слов
В методе main считай с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставь все слова в таком порядке, чтобы последняя буква данного слова
совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Считай, что абсолютно все слова из исходного списка могут (и должны!) быть включены в результат (лишних слов нет).
Метод getLine должен возвращать любой правильный вариант при наличии нескольких таковых (см. пример).
Слова разделять пробелом.
Вывести полученную строку на экран.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
или
Вена Амстердам Мельбурн Нью-Йорк Киев
или
Мельбурн Нью-Йорк Киев Вена Амстердам
и т.п.


Требования:
1. Метод main должен считывать имя файла с клавиатуры.
2. В классе Solution не должно быть статических полей.
3. В методе getLine должен быть использован StringBuilder.
4. Метод getLine должен возвращать пустую строку (пустой StringBuilder) в случае если ему не были переданы параметры (слова).
5. Метод getLine не должен изменять переданные ему параметры (слова).
6. Все слова переданные в метод getLine должны быть включены в результирующую строку.
7. Вывод на экран должен соответствовать условию задачи.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileName = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(String.valueOf(fileName.readLine())));
        fileName.close();
        StringBuilder  buffer = new StringBuilder();
        while(fileReader.ready()){
            buffer.append(fileReader.readLine() + " ");
        }
        fileReader.close();
        StringBuilder result = getLine(buffer.toString().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        int i = 0;

        StringBuilder result;
        if(words.length == 0){return new StringBuilder();}
        while(true) {
            int count = words.length;
            result = new StringBuilder();
            result.append(words[i]);
            count--;

            for (int j = 0; j < words.length; j++) {
    //            System.out.println(result.toString());//tet
                char what = result.toString().toLowerCase().charAt(result.length() - 1);
                if (result.toString().lastIndexOf(words[j]) == -1 && what == (words[j].toLowerCase().charAt(0))) {
                              //                             System.out.println(" " + (words[j].toLowerCase().charAt(0)) + "-" + words[j]);//test
       //             System.out.println(words[j]);
                    result.append(" " + words[j]);
                    count--;
                    j = -1;


                }

            }



            if(count == 0) break;
            else i++;
        }


        return result;
    }
}
