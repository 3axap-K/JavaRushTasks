package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Использовать StringBuilder.
Кодировка файла - UTF-8.

Пример содержимого файла
рот тор торт о
о тот тот тот

Вывод:
рот тор
о о
тот тот


Требования:
1. Метод main должен считывать имя файла с клавиатуры.
2. В методе main должен быть использован StringBuilder.
3. В классе Solution должен содержаться вложенный класс Pair с методами equals, hashCode и toString. Удалять или изменять эти методы нельзя.
4. В классе Pair должен быть объявлен конструктор без параметров (или конструктор по умолчанию).
5. Список result должен быть заполнен корректными парами согласно условию задачи.
*/

public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        BufferedReader readFile = new BufferedReader(new FileReader(fileName));
        List <String> buffer = new ArrayList<>();
        while(readFile.ready()){
            buffer.addAll(Arrays.asList(readFile.readLine().split(" ")));
        }
        for(int i = 0; i < buffer.size()-1; i++){
            StringBuilder test = new StringBuilder(buffer.get(i));
            test.reverse();
  //          System.out.print(test + " "); //test
            for(int j = i+1; j < buffer.size(); j++){
                if(test.toString().equals(buffer.get(j))) {
                    Pair pair = new Pair();
                    pair.first = buffer.get(i);
                    pair.second = buffer.get(j);
                    result.add(pair);
                    buffer.remove(j);
                    j--;
                    buffer.remove(i);
                    i--;
                    break;
               //     result.add(pair); //test
                }
            }
        }
        for (Pair pair: result){
            System.out.println(pair.toString());
        }

  //      System.out.println(result.size() + "\t" + buffer.size());
   //     for(String b: buffer){
     //       System.out.print(b+ " ");
       // }




    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
