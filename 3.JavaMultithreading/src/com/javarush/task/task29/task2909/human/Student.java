package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends UniversityPerson {

    private double averageGrade;

    private Date beginningOfSession;
    private Date endOfSession;

    private int course;

    public Student(String name, int age, double averageGrade) {
        super(name,age);
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
    }



    public void live() {
        learn();
    }

    @Override
    public String getPosition() {
        return "Студент";
    }

    public void learn() {
    }

    public void incAverageGrade(double delta) {
        setAverageGrade(getAverageGrade() + delta);
    }

/*    public void setValue(String name, double value) {
        if (name.equals("averageGrade")) {
            averageGrade = value;
            return;
        }
        if (name.equals("course")) {
            course = (int) value;
            return;
        }
    }
*/
    public void setCourse(int course) {
        this.course = course;
    }

    public void setAverageGrade(double averageGrade){
        this.averageGrade = averageGrade;
    }

    public int getCourse() {
        return course;
    }

    public void setBeginningOfSession(Date date) {
        this.beginningOfSession = date;
    }

    public void setEndOfSession(Date date) {
        this.endOfSession = date;
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }
}