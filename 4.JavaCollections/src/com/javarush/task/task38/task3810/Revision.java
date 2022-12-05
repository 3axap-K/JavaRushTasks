package com.javarush.task.task38.task3810;

public @interface Revision {
    Date date();
    int revision();
    String comment() default "";
    Author[] authors() default {};
    //напиши свой код
}
