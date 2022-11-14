package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;
import com.javarush.task.task37.task3702.male.KidBoy;
import com.javarush.task.task37.task3702.male.Man;
import com.javarush.task.task37.task3702.male.TeenBoy;


public class FemaleFactory implements AbstractFactory {

    public  <T extends Human> T getPerson(int age){
        if(age < 0) return null;
        if(age <= com.javarush.task.task37.task3702.male.KidBoy.MAX_AGE){
            return (T) new KidGirl();
        } else if (age <= com.javarush.task.task37.task3702.male.TeenBoy.MAX_AGE) {
            return (T) new TeenGirl();
        } else {
            return (T) new Woman();
        }
    }
}
