package com.javarush.task.task39.task3913.executors;

import com.javarush.task.task39.task3913.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateExecute extends AbstractExecute<Date>{
    public DateExecute(LogParser logParser, Argument<Date> argument) {
        super(logParser, argument);
    }

    @Override
    public Set<Date> getUnique() {
        Set <Date> dates = new HashSet<>();
        for (Log log : logs){
            dates.add(log.getDate());
        }
        return dates;
    }

    @Override
    public Set<Date> getForIP() {
        Set <Date> dates = new HashSet<>();
        for (Log log : logs){
            if(this.argument.getArg().equals(log.getIp())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getForEvent() {
        Set <Date> dates = new HashSet<>();
        for (Log log : logs){
            if(this.argument.getArg().equals(log.getEvent())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getForStatus() {
        Set <Date> dates = new HashSet<>();
        for (Log log : logs){
            if(this.argument.getArg().equals(log.getStatus())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getForUser() {
        Set <Date> dates = new HashSet<>();
        for (Log log : logs){
            if(this.argument.getArg().equals(log.getUser())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getForDate() {
        return null;
    }
}
