package com.javarush.task.task26.task2601;

import java.util.*;

/* 
Почитать в инете про медиану выборки
*/

public class Solution {

    public static void main(String[] args) {
/*       Integer[] test1 = new Integer[]{13, 8, 15, 5, 17};
        Integer[] test2 = new Integer[]{10, 5, 10, 5, 10, 5};
        System.out.print("test1 before:");
        for(Integer test: test1){
            System.out.print("\t" + test);
        }
        System.out.print("\ntest1 after:");
        for(Integer test: sort(test1)){
            System.out.print("\t" + test);
        }
        System.out.print("\ntest2 before:");
        for(Integer test: test2){
            System.out.print("\t" + test);
        }
        System.out.print("\ntest2 after:");
        for(Integer test: sort(test2)){
            System.out.print("\t" + test);
        }
*/    }

    public static Integer[] sort(Integer[] array) {
        if(array.length == 0) return array;
        ArrayList<Integer> list = new ArrayList<>();

        for(Integer integer: array){
            list.add(integer);
        }

        Integer median = findMedian(array);
        list.sort(new CompareByMedian(median));

        //test
 //       System.out.println(median);

        //
        array = list.toArray(new Integer[0]);
        //implement logic here
        return array;
    }

    public static class CompareByMedian<Integer> implements Comparator<Integer> {

        private final int median;

        public CompareByMedian(Integer median) {
            this.median =(java.lang.Integer) median;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            //find distance between median & arguments
            int distance1 = (median - (java.lang.Integer)o1);
            if(distance1 < 0) distance1 = distance1 * -1;
            int distance2 = (median - (java.lang.Integer)o2);
            if(distance2 < 0) distance2 = distance2 * -1;
            // Start comparing
            int result = (distance1 - distance2);
            if(result == 0) return (java.lang.Integer)o1 -(java.lang.Integer)o2;
            else return result;
        }
    };
    public static Integer findMedian(Integer[] array){
        // Check if array is Empty or have only one element
        if(array.length == 0) return 0;
        if(array.length == 1) return array[0];
        // Fing median
        ArrayList<Integer> list = new ArrayList<>();

        for(Integer integer: array){
            list.add(integer);
        }


        Collections.sort(list);
        int middle = (java.lang.Integer) (list.size()/2);
///        System.out.println("Middle: " + middle + "Median? " + list.get(middle));
        if(list.size()%2 == 0){
            return (java.lang.Integer) ((list.get(middle -1) + list.get(middle))/2);
        }else{ return list.get(middle);}

    }
}
