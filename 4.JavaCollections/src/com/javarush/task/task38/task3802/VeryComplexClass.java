package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        this.clone();
        //напишите тут ваш код
    }

    public static void main(String[] args) throws Exception {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        veryComplexClass.veryComplexMethod();
    }
}
