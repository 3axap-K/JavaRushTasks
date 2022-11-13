package com.javarush.task.task23.task2305;

/* 
Inner
Inner
Реализовать метод getTwoSolutions, который должен возвращать !!!!массив из 2-х!!! экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.


Requirements:
+ 1. В классе Solution должен быть реализован метод getTwoSolutions.
+ 2. Метод getTwoSolutions должен быть статическим.
+ 3. Метод getTwoSolutions должен быть публичным.
4. Метод getTwoSolutions должен возвращать массив типа Solution заполненный согласно заданию
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        int s = 2;
        Solution[] twoSolutions = new Solution[s];

        int i = 2;
        for (int x = 0; x < s; x++){
            twoSolutions[x] = new Solution();
            twoSolutions[x].innerClasses = new InnerClass[i];
            for (int y = 0; y < i; y++) {
                twoSolutions[x].innerClasses[y] = twoSolutions[x].new InnerClass();
            }

        }

        return twoSolutions;
    }

    public static void main(String[] args) {

    }
}
