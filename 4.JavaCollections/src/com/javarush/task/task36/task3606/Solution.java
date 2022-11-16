package com.javarush.task.task36.task3606;



import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {

        File directory = null;
        try {
            directory = new File(URLDecoder.decode(packageName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        for (File file: directory.listFiles()){
            if(file.isFile() && file.getName().endsWith(".class")) {

                //        System.out.println(file.getAbsolutePath());

                Loader loader = new Loader();
                Class<?> clazz = null;
                try {
                    clazz = loader.findClass(file.getAbsolutePath());
                    if(HiddenClass.class.isAssignableFrom(clazz))
                    hiddenClasses.add(clazz);

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }


    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses){
            if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){

                Constructor<?> constructor = null;
                try {
                    constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (NoSuchMethodException e) {

                } catch (InvocationTargetException e) {

                } catch (InstantiationException e) {

                } catch (IllegalAccessException e) {

                }


            }
        }
        return null;
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

