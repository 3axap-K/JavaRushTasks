package com.javarush.task.task35.task3512;

public class Generator<T> {

    public Generator(Class<T> clazz) {
        this.clazz = clazz;
    }

    Class<T> clazz;
    T  newInstance() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
       T t = (T) clazz.newInstance();
        return t;
    }
}
