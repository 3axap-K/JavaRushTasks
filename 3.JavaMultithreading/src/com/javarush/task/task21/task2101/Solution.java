package com.javarush.task.task21.task2101;

/*
Определяем адрес сети
1. Даны IP-адрес и маска подсети, необходимо вычислить адрес сети - реализуй метод getNetAddress.
Используйте операцию поразрядной конъюнкции (логическое И).

Пример:
IP-адрес: 11000000 10101000 00000001 00000010 (192.168.1.2)
Маска подсети: 11111111 11111111 11111110 00000000 (255.255.254.0)
Адрес сети: 11000000 10101000 00000000 00000000 (192.168.0.0)

2. Реализовать метод print, который выведет в консоль данные в двоичном коде. Для IP-адреса(192.168.1.2)
должна быть выведена строка "11000000 10101000 00000001 00000010"
3. Метод main не участвует в тестировании


Требования:
1. Метод getNetAddress должен вычислять и возвращать адрес сети согласно переданным параметрам(IP-адрес и маска подсети).
2. Метод getNetAddress должен быть статическим и публичным.
3. Метод print должен быть статическим и публичным.
4. Метод print должен преобразовывать переданный ему IP адрес в двоичный код и выводить на экран(как в условии).
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] net = new byte[4];
        for(int i = 0; i < net.length; i++){
            net[i] = (byte) (ip[i] & mask[i]);
        }
        return net;
    }

    public static void print(byte[] bytes) {
        String byt;
        for(byte b: bytes){
            byt = "0000000000" + Integer.toBinaryString(b);
            byt = byt.substring(byt.length() - 8);
        System.out.print(byt + " ");


        }
        System.out.println();
    }
}
