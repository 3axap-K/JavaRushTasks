package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return new FactoryException().getClass();
    }

    public static void main(String[] args) {
        System.out.println(FactoryException.creatorException(ApplicationExceptionMessage.SOCKET_IS_CLOSED));
        System.out.println(FactoryException.creatorException(DatabaseExceptionMessage.NOT_ENOUGH_CONNECTIONS));
        System.out.println(FactoryException.creatorException(UserExceptionMessage.USER_DOES_NOT_HAVE_PERMISSIONS));
        System.out.println(FactoryException.creatorException(null));
    }
}