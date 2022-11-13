package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании.


Требования:
1. В классе Solution должен существовать метод detectAllWords.
2. В классе Solution должен существовать статический класс Word.
3. Класс Solution не должен содержать статические поля.
4. Метод detectAllWords должен быть статическим.
5. Метод detectAllWords должен быть публичным.
6. Метод detectAllWords должен возвращать список всех слов в кроссворде (согласно условию задачи).
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "home", "same" ,"ful", "jjee");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        int[][] matrix = new int[crossword.length + 2][crossword[0].length + 2];
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if(i == 0 || j ==0 || i == matrix.length -1 || j == matrix[i].length -1) matrix[i][j] = 0;
                else matrix[i][j] = crossword[i-1][j-1];
            }
        }

        List<Word> result = new ArrayList<Word>();
        for(String word: words){
            Word newWord = new Word(word);
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;
            char[] contain = word.toCharArray();

            for(int i = 1; i <= crossword.length; i++){
                for (int j = 1; j <= crossword[0].length; j++){
                    if( contain[0] == matrix[i][j]){
                            if (contain[1] == matrix[i][j - 1] && isWord(contain, crossword, i - 1, j - 1, 0, -1)) {
                                startX = j - 1;
                                startY = i - 1;
                                endX = j - contain.length;
                                endY = i - 1;
                            }
                            if (contain[1] == matrix[i - 1][j - 1] && isWord(contain, crossword, i - 1, j - 1, -1, -1)) {
                                startX = j - 1;
                                startY = i - 1;
                                endX = j - contain.length;
                                endY = i - contain.length;
                            }
                            if (contain[1] == matrix[i - 1][j] && isWord(contain, crossword, i - 1, j - 1, -1, 0)) {
                                startX = j - 1;
                                startY = i - 1;
                                endX = j - 1;
                                endY = i - contain.length;
                            }
                            if (contain[1] == matrix[i - 1][j + 1] && isWord(contain, crossword, i - 1, j - 1, -1, 1)) {
                                startX = j - 1;
                                startY = i - 1;
                                endX = j + contain.length - 2;
                                endY = i - contain.length;
                            }
                            if (contain[1] == matrix[i][j + 1] && isWord(contain, crossword, i - 1, j - 1, 0, 1)) {
                                startX = j - 1;
                                startY = i - 1;
                                endX = j + contain.length - 2;
                                endY = i - 1;
                            }
                            if (contain[1] == matrix[i + 1][j + 1] && isWord(contain, crossword, i - 1, j - 1, 1, 1)) {
                                startX = j - 1;
                                startY = i - 1;
                                endX = j + contain.length - 2;
                                endY = i + contain.length - 2;
                            }
                            if(contain[1] == matrix[i + 1][j] && isWord(contain,crossword,i - 1,j - 1,1,0)){
                                startX = j - 1;
                                startY = i - 1;
                                endX = j - 1;
                                endY = i + contain.length - 2;
                            }
                            if(contain[1] == matrix[i + 1][j - 1] && isWord(contain,crossword,i - 1,j - 1,1,-1)){
                                startX = j - 1;
                                startY = i - 1;
                                endX = j - contain.length;
                                endY = i + contain.length - 2;
                            }
                    }
                }
            }
            newWord.setStartPoint(startX,startY);
            newWord.setEndPoint(endX,endY);
            result.add(newWord);

        }
        return result;
    }

    public static boolean isWord(char[] contain, int[][] crossword, int i, int j, int stepI, int stepJ){

        for (int x = 0; x < contain.length; x++){
        try{
            if(contain[x] != crossword[i][j]) return false;
            else{
            i += stepI;
            j += stepJ;
            }
            }catch (Exception e){
                return false;

            }
        }
        return true;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
