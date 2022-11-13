package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Смена кодировки
Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.


Требования:
1. Программа НЕ должна считывать данные с клавиатуры.
2. Программа НЕ должна выводить данные на экран.
3. Программа должна записывать данные в файл.
4. Содержимое второго файла должно соответствовать содержимому первого файла за исключением кодировки(UTF-8).
*/

public class Solution {
    public static void main(String[] args) throws IOException {


        try(BufferedInputStream file1 = new BufferedInputStream(new FileInputStream(args[0]));
            BufferedOutputStream file2 = new BufferedOutputStream(new FileOutputStream(args[1]))){
            Charset utf8 = StandardCharsets.UTF_8;
            Charset windows1251 = Charset.forName("Windows-1251");

            while(file1.available() > 0){
                byte [] buffer = new byte[1000];
                file1.read(buffer);
                String in = new String(buffer, windows1251);
                byte[] out = in.getBytes(utf8);
                file2.write(out);
            }








   //         byte[] buffer = file1.readAllBytes();

     //           String in = new String(buffer,windows1251);
       //         byte[] bufferOut = in.getBytes(utf8);
         //       file2.write(bufferOut);

        }
    }
}
