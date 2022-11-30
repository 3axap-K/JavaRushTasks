package com.javarush.task.task39.task3913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("/Users/zakharkuropiatnyk/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++){
            try {
                for (Object o : logParser.execute(reader.readLine())){
                    System.out.println(o.toString());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}