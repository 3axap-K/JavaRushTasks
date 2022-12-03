package com.javarush.task.task39.task3913.executors;

import com.javarush.task.task39.task3913.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class StatusExecute extends AbstractExecute<Status>{
    public StatusExecute(LogParser logParser, Argument<Status> argument) {
        super(logParser, argument);
    }

    @Override
    public Set<Status> getUnique() {
        Set <Status> statuses = new HashSet<>();
        for (Log log : logs){
            statuses.add(log.getStatus());
        }
        return statuses;
    }

    @Override
    public Set<Status> getForIP() {
        Set <Status> statuses = new HashSet<>();
        for (Log log : this.logs){
            if(this.argument.getArg().equals(log.getIp())) {
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }

    @Override
    public Set<Status> getForEvent() {
        Set <Status> statuses = new HashSet<>();
        for (Log log : this.logs){
            if(this.argument.getArg().equals(log.getEvent())) {
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }

    @Override
    public Set<Status> getForStatus() {
        return null;
    }

    @Override
    public Set<Status> getForUser() {
        Set <Status> statuses = new HashSet<>();
        for (Log log : this.logs){
            if(this.argument.getArg().equals(log.getUser())) {
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }

    @Override
    public Set<Status> getForDate() {
        Set <Status> statuses = new HashSet<>();
        for (Log log : this.logs){
            if(this.argument.getArg().equals(log.getDate())){
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }
}
