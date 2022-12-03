package com.javarush.task.task39.task3913.executors;

import com.javarush.task.task39.task3913.*;
import com.javarush.task.task39.task3913.Event;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractExecute<T> {
    LogParser logParser;
    Set<Log> logs;
    Argument argument;
    public enum DataType{
        IP,
        USER,
        DATE,
        EVENT,
        STATUS,
        NULL
    }

    public AbstractExecute(LogParser logParser, Argument argument) {
        this.logParser = logParser;
        this.logs = logParser.readLogs(argument.getAfter(), argument.getBefore());
        this.argument = argument;

    }



    public Set<T> getData(){
        switch (this.argument.getArgType()){
            case IP:
                return getForIP();
            case USER:
                return getForUser();
            case DATE:
                return getForDate();
            case EVENT:
                return getForEvent();
            case STATUS:
                return getForStatus();
            default:
                return getUnique();
        }
    }



    abstract Set<T> getUnique();
    abstract Set<T> getForIP();

    abstract Set<T> getForEvent();

    abstract Set<T> getForStatus();

    abstract Set<T> getForUser();
    abstract Set<T> getForDate();
}
