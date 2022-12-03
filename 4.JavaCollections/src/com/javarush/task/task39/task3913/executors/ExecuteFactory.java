package com.javarush.task.task39.task3913.executors;

import com.javarush.task.task39.task3913.Argument;
import com.javarush.task.task39.task3913.LogParser;

import java.util.Date;

public class ExecuteFactory {
    public static AbstractExecute createExecuteType(AbstractExecute.DataType type, LogParser logParser, Argument argument){
        switch (type){
            case IP:
                return new IPExecute(logParser, argument);
            case USER:
                return new UserExecute(logParser, argument);
            case DATE:
                return new DateExecute(logParser, argument);
            case EVENT:
                return new EventExecute(logParser, argument);
            case STATUS:
                return new StatusExecute(logParser, argument);
        }
        return null;
    }
}
