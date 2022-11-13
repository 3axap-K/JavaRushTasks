package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {

        String result = findExpression(0, number);
        System.out.format("%s =%s\n", number, result);

        //напишите тут ваш код
    }

    private String findExpression(int step, int number){

        if(number < 3){
            switch (number){
                case 0:
                    return "";
                case 1:
                    return String.format(" + %s", (int)Math.pow(3,step));
                case 2:
                    return String.format(" - %s + %s", (int)Math.pow(3,step), (int)Math.pow(3, ++step));
            }
        }
        int num = number/3;
        int arg = number%3;

        switch (arg){
            case 0:
                return findExpression(++step, num);
            case 1:
                return String.format(" + %s", (int)Math.pow(3,step)) + findExpression(++step, num);
            case 2:
                return String.format(" - %s", (int)Math.pow(3,step)) + findExpression(++step, num+1);
        }
        return null;
    }
}