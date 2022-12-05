package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest test = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for(String name : test.fullyQualifiedNames()){
                System.out.println(name);
            }
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        if(c.isAnnotationPresent(PrepareMyTest.class)){
            PrepareMyTest test = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            for(Class clazz : test.value()){
                System.out.println(clazz.getSimpleName());
            }
            return true;
        }
        return false;
    }
}
