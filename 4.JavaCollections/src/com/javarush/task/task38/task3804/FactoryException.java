package com.javarush.task.task38.task3804;

import java.util.Locale;

public class FactoryException {
    public static Throwable creatorException(Enum type){
        if(type == null) return new IllegalArgumentException();
        String message = type.name();
        char first = message.charAt(0);
        message = message.replaceAll("_", " ").toLowerCase(Locale.ROOT);
        message = message.replaceFirst(String.valueOf(message.charAt(0)), String.valueOf(first));
        switch (type.getClass().getSimpleName()){
            case "ApplicationExceptionMessage":
                return new Exception(message);
            case "DatabaseExceptionMessage":
                return new RuntimeException(message);
            case "UserExceptionMessage":
                return new Error(message);
            default: return new IllegalArgumentException();
        }
    }
}
