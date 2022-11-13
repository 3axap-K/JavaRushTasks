package com.javarush.task.task26.task2603;

import java.util.Comparator;

/* 
Убежденному убеждать других не трудно
*/

public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T> {

        private Comparator<T>[] comparators;
        public CustomizedComparator(Comparator<T>... comparators){
            this.comparators = comparators;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int res = 0;
            for (int i = 0; i< comparators.length; i++){
                res = comparators[i].compare((T) o1, (T) o2);
                if(res != 0) return res;
            }
            return res;
        }


    }

    public static void main(String[] args) {

    }
}
