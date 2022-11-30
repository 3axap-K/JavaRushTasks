package com.javarush.task.task39.task3913;

import java.util.Date;

public class Log {
    private String ip;
    private String user;
    private Date date;
    private Event event;
    private String taskNumber;
    private Status status;

    public Log(String ip, String user, Date date, Event event, String taskNumber, Status status) {
        this.ip = ip;
        this.user = user;
        this.date = date;
        this.event = event;
        this.status = status;
        this.taskNumber = taskNumber;
    }

    public String getIp() {
        return ip;
    }



    public String getUser() {
        return user;
    }



    public Date getDate() {
        return date;
    }



    public Event getEvent() {
        return event;
    }



    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Status getStatus() {
        return status;
    }


}
