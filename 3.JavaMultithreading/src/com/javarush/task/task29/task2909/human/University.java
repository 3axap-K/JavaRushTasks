package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University{
    private int age;
    private String name;

    private List<Student> students = new ArrayList<>();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for(Student student: students){
            if(averageGrade == student.getAverageGrade()) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverageGrade = Double.MIN_VALUE;
        Student ourWinner = null;
        for(Student student: students){
            if(maxAverageGrade < student.getAverageGrade()){
                maxAverageGrade = student.getAverageGrade();
                ourWinner = student;
            }
        }
        return ourWinner;
    }

    public Student getStudentWithMinAverageGrade() {
        double minAverageGrade = Double.MAX_VALUE;
        Student ourLosser = null;
        for(Student student: students){
            if(minAverageGrade > student.getAverageGrade()){
                minAverageGrade = student.getAverageGrade();
                ourLosser = student;
            }
        }
        return ourLosser;
    }

    public void expel(Student student){
        students.remove(student);
    }
}