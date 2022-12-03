package com.javarush.task.task39.task3913;

import java.util.Date;
import com.javarush.task.task39.task3913.Event;
import com.javarush.task.task39.task3913.executors.AbstractExecute;

public class Argument<T> {
    private T arg;
    private Date after;
    private Date before;

    private AbstractExecute.DataType argType;

    public Argument(T arg, AbstractExecute.DataType argType, Date after, Date before) {
        this.arg = arg;
        this.after = after;
        this.before = before;
        this.argType = argType;
    }

    public T getArg() {
        return arg;
    }

    public Date getAfter() {
        return after;
    }

    public Date getBefore() {
        return before;
    }

    public AbstractExecute.DataType getArgType() {
        return argType;
    }
}
