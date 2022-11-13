package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                        Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        File directory = null;
        try {
            directory = new File(URLDecoder.decode(pathToAnimals, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        for (File file: directory.listFiles()){
            if(file.isFile()) {

    //        System.out.println(file.getAbsolutePath());

                Loader loader = new Loader();
                Class<?> clazz = null;
                try {
                    clazz = loader.findClass(file.getAbsolutePath());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if(Animal.class.isAssignableFrom(clazz)){
                    try {

                        Constructor<? extends Animal> constructor = (Constructor<? extends Animal>) clazz.getConstructor();

                        set.add((Animal) constructor.newInstance());
                    } catch (NoSuchMethodException e) {

                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return set;
    }



    private static class Loader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] buffer = Files.readAllBytes(Paths.get(name));
                return defineClass(null, buffer, 0, buffer.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
