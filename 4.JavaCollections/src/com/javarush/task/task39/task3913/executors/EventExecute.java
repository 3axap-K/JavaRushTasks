package com.javarush.task.task39.task3913.executors;

import com.javarush.task.task39.task3913.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EventExecute extends AbstractExecute<Event>{
    public EventExecute(LogParser logParser, Argument<Event> argument) {
        super(logParser, argument);
    }

    @Override
    public Set<Event> getUnique() {
        Set <Event> events = new HashSet<>();
        for (Log log : this.logs){
            events.add(log.getEvent());
        }
        return events;
    }

    @Override
    public Set<Event> getForIP() {
        return logParser.getEventsForIP((String) this.argument.getArg(), this.argument.getAfter(), this.argument.getBefore());
    }

    @Override
    public Set<Event> getForEvent() {
        return null;
    }

    @Override
    public Set<Event> getForStatus() {
        Set <Event> events = new HashSet<>();
        for (Log log : logs){
            if(this.argument.getArg().equals(log.getStatus())) {
                events.add(log.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getForUser() {
        return logParser.getEventsForUser((String) this.argument.getArg(), this.argument.getAfter(), this.argument.getBefore());
    }

    @Override
    public Set<Event> getForDate() {
        Set <Event> events = new HashSet<>();
        for (Log log : this.logs){
            if(this.argument.getArg().equals(log.getDate())){
                events.add(log.getEvent());
            }
        }
        return events;
    }
}
