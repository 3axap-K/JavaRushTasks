package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//1.2. Реализуй интерфейс IPQuery у класса LogParser:
public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    Path logDir;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    //1.1. Добавь в класс LogParser конструктор с параметром Path logDir, где logDir -
    // директория с логами (логов может быть несколько, все они должны иметь расширение log).
    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    private Set<Log> readLogs(Date after, Date before){
        Set<Log> logs = new HashSet<>();
        Log log;
        long lDate;
        long lAfter = after != null ? after.getTime() : 0;
        long lBefore = before != null ? before.getTime() : Long.MAX_VALUE;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(logDir)){
            for (Path path : stream){
                if (path.toString().endsWith(".log")) {
                    try (BufferedReader reader = Files.newBufferedReader(path)) {
                        while (reader.ready()) {
                            log = readLog(reader.readLine());
                            lDate = log.getDate().getTime();
                            if (lAfter <= lDate && lDate <= lBefore) {
                                logs.add(log);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return logs;
    }

    private Log readLog(String line){
        Log log = null;
        String[] eventString;
        String ip;
        String user;
        Date date;
        Event event;
        String taskNumber;
        Status status;
        String[] logString = line.split("\t");
        if (logString.length >= 5){
            ip = logString[0];
            user = logString[1];
            try {
                date = format.parse(logString[2]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            eventString = logString[3].split(" ");
            event = Event.valueOf(eventString[0]);
            if (eventString.length == 2){
                taskNumber = eventString[1];
            }else {
                taskNumber = "";
            }
            status = Status.valueOf(logString[4]);
            log = new Log(ip, user, date, event, taskNumber, status);
        }


        return log;
    }

    //1.2.1. Метод getNumberOfUniqueIPs(Date after, Date before) должен возвращать количество уникальных IP адресов за выбранный период
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {

        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set <String> IPs = new HashSet<>();
        for (Log log : readLogs(after, before)){
            IPs.add(log.getIp());
        }
        return IPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set <String> IPs = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(user.equals(log.getUser())) {
                IPs.add(log.getIp());
            }
        }
        return IPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set <String> IPs = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(event.equals(log.getEvent())) {
                IPs.add(log.getIp());
            }
        }
        return IPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {

        Set <String> IPs = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(status.equals(log.getStatus())) {
                IPs.add(log.getIp());
            }
        }
        return IPs;
    }

    

    @Override
    public Set<String> getAllUsers() {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(null, null)){
            users.add(log.getUser());
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            users.add(log.getUser());
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (user.equals(log.getUser())) {
                events.add(log.getEvent());
            }
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (ip.equals(log.getIp())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Event.LOGIN.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Event.DOWNLOAD_PLUGIN.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Event.WRITE_MESSAGE.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Event.SOLVE_TASK.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if ( String.valueOf(task).equals(log.getTaskNumber())
                    && Event.SOLVE_TASK.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Event.DONE_TASK.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if ( String.valueOf(task).equals(log.getTaskNumber())
                    && Event.DONE_TASK.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if ( user.equals(log.getUser())
                    && event.equals(log.getEvent())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if ( Status.FAILED.equals(log.getStatus())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if ( Status.ERROR.equals(log.getStatus())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date date = null;
        long firstDate = Long.MAX_VALUE;
        for (Date date1 : getDatesForUserAndEvent(user, Event.LOGIN, after, before)){
            if (firstDate > date1.getTime()) {
                date = date1;
                firstDate = date.getTime();
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date date = null;
        long firstDate = Long.MAX_VALUE;
        for (Log log : readLogs(after, before)){
            if ( user.equals(log.getUser())
                    && Event.SOLVE_TASK.equals(log.getEvent())
                    && String.valueOf(task).equals(log.getTaskNumber())) {
                if (firstDate > log.getDate().getTime()) {
                    date = log.getDate();
                    firstDate = date.getTime();
                }
            }
        }
        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = null;
        long firstDate = Long.MAX_VALUE;
        for (Log log : readLogs(after, before)){
            if ( user.equals(log.getUser())
                    && Event.DONE_TASK.equals(log.getEvent())
                    && String.valueOf(task).equals(log.getTaskNumber())) {
                if (firstDate > log.getDate().getTime()) {
                    date = log.getDate();
                    firstDate = date.getTime();
                }
            }
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        return getDatesForUserAndEvent(user, Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            events.add(log.getEvent());
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (ip.equals(log.getIp())) {
                events.add(log.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (user.equals(log.getUser())) {
                events.add(log.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Status.FAILED.equals(log.getStatus())) {
                events.add(log.getEvent());
            }
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if (Status.ERROR.equals(log.getStatus())) {
                events.add(log.getEvent());
            }
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        Map<Integer, Integer> map = getAllSolvedTasksAndTheirNumber(after, before);
        int n = map.containsKey(task) ? map.get(task) : 0;
        return n;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        Map<Integer, Integer> map = getAllDoneTasksAndTheirNumber(after, before);
        int n = map.containsKey(task) ? map.get(task) : 0;
        return n;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer> map = new HashMap<>();
        for (Log log : readLogs(after, before)){
            if(Event.SOLVE_TASK.equals(log.getEvent())) {
                int key = Integer.parseInt(log.getTaskNumber());
                if (!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    int count = map.get(key) +1;
                    map.put(key, count);
                }
            }
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer,Integer> map = new HashMap<>();
        for (Log log : readLogs(after, before)){
            if(Event.DONE_TASK.equals(log.getEvent())
                    ) {
                int key = Integer.parseInt(log.getTaskNumber());
                if (!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    int count = map.get(key) +1;
                    map.put(key, count);
                }
            }
        }
        return map;
    }

    private Set<Date> getUniqueDates(Date after, Date before){
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(after, before)){

            dates.add(log.getDate());

        }
        return dates;
    }
    private Set<Date> getDatesForIP(String ip){
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(null, null)){
            if(ip.equals(log.getIp())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    private Set<Date> getDatesForEvent(Event event){
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(null, null)){
            if(event.equals(log.getEvent())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    private Set<Date> getDatesForStatus(Status status){
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(null, null)){
            if(status.equals(log.getStatus())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }

    private Set<Date> getDatesForUser(String user){
        Set <Date> dates = new HashSet<>();
        for (Log log : readLogs(null, null)){
            if(user.equals(log.getUser())) {
                dates.add(log.getDate());
            }
        }
        return dates;
    }
    private Set<Event> getUniqueEvents(Date after, Date before){
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){

            events.add(log.getEvent());

        }
        return events;
    }

    private Set<Status> getUniqueStatus(Date after, Date before){
        Set <Status> statuses = new HashSet<>();
        for (Log log : readLogs(after, before)){

            statuses.add(log.getStatus());

        }
        return statuses;
    }
    
    private Set<String> getUsersForDate(Date parse) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(parse, parse)){
            users.add(log.getUser());
        }
        return users;
    }

    private Set<String> getUsersForEvent(Event event, Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(event.equals(log.getEvent())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    private Set<String> getUsersForStatus(Status status, Date after, Date before) {
        Set <String> users = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(status.equals(log.getStatus())) {
                users.add(log.getUser());
            }
        }
        return users;
    }

    private Set<Event> getEventsForDate(Date parse) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(parse, parse)){
            events.add(log.getEvent());
        }
        return events;
    }

    private Set<Event> getEventsForStatus(Status status, Date after, Date before) {
        Set <Event> events = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(status.equals(log.getStatus())) {
                events.add(log.getEvent());
            }
        }
        return events;
    }

    private Set<Status> getStatusesForIP(String ip){
        Set <Status> statuses = new HashSet<>();
        for (Log log : readLogs(null, null)){
            if(ip.equals(log.getIp())) {
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }

    private Set<Status> getStatusesForDate(Date parse) {
        Set <Status> statuses = new HashSet<>();
        for (Log log : readLogs(parse, parse)){
            statuses.add(log.getStatus());
        }
        return statuses;
    }

    private Set<Status> getStatusesForUser(String user, Date after, Date before){
        Set <Status> statuses = new HashSet<>();
        for (Log log : readLogs(null, null)){
            if(user.equals(log.getUser())) {
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }

    private Set<Status> getStatusesForEvent(Event event, Date after, Date before) {
        Set <Status> statuses = new HashSet<>();
        for (Log log : readLogs(after, before)){
            if(event.equals(log.getEvent())) {
                statuses.add(log.getStatus());
            }
        }
        return statuses;
    }
    @Override
    public Set<Object> execute(String query) {
        String[] queryes = query.split(" " );
        String arg = null;
        if (query.contains("=")){
            arg = query.substring(query.indexOf("\"") + 1, query.lastIndexOf("\"") );
        }
        if (queryes[0].equals("get")) {
            switch (queryes[1]) {
                case "ip":
                    if(queryes.length == 2){  return new HashSet<>(getUniqueIPs(null, null));}
                    return executeIp(queryes[3], arg);                    
                case "user":
                    if(queryes.length == 2){  return new HashSet<>(getAllUsers());}
                    return executeUser(queryes[3], arg);
                case "date":
                    if(queryes.length == 2){  return new HashSet<>(getUniqueDates(null, null));}
                    return executeDate(queryes[3], arg);
                case "event":
                    if(queryes.length == 2){  return new HashSet<>(getUniqueEvents(null, null));}
                    return executeEvent(queryes[3], arg);
                case "status":
                    if(queryes.length == 2){  return new HashSet<>(getUniqueStatus(null, null));}
                    return executeStatus(queryes[3], arg);                    
            }
        }

    
        return new HashSet<>();
    }

    private Set<Object> executeStatus(String querye, String arg) {
        switch (querye){
            case "ip":
                return new HashSet<>(getStatusesForIP(arg));
            case "date":
                try {
                    return new HashSet<>(getStatusesForDate(format.parse(arg)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            case "user":
                return new HashSet<>(getStatusesForUser(arg, null, null));
            case "event":
                return new HashSet<>(getStatusesForEvent(Event.valueOf(arg), null, null));
        }
        return new HashSet<>();
    }

    private Set<Object> executeEvent(String querye, String arg) {
        switch (querye){
            case "ip":
                return new HashSet<>(getEventsForIP(arg, null, null));
            case "date":
                try {
                    return new HashSet<>(getEventsForDate(format.parse(arg)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            case "user":
                return new HashSet<>(getEventsForUser(arg, null, null));
            case "status":
                return new HashSet<>(getEventsForStatus(Status.valueOf(arg), null, null));
        }
        return new HashSet<>();
    }

    private Set<Object> executeDate(String querye, String arg) {
        switch (querye){
            case "user":
                return new HashSet<>(getDatesForUser(arg));
            case "ip":
                return new HashSet<>(getDatesForIP(arg));
            case "event":
                return new HashSet<>(getDatesForEvent(Event.valueOf(arg)));
            case "status":
                return new HashSet<>(getDatesForStatus(Status.valueOf(arg)));
        }
        return new HashSet<>();
    }

    private Set<Object> executeUser(String querye, String arg) {
        switch (querye){
            case "ip":
                return new HashSet<>(getUsersForIP(arg, null, null));
            case "date":
                try {
                    return new HashSet<>(getUsersForDate(format.parse(arg)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            case "event":
                return new HashSet<>(getUsersForEvent(Event.valueOf(arg), null, null));
            case "status":
                return new HashSet<>(getUsersForStatus(Status.valueOf(arg), null, null));
        }
        return new HashSet<>();
    }




    private Set<Object> executeIp(String querye, String arg) {
        switch (querye){
            case "user":
                return new HashSet<>(getIPsForUser(arg, null, null));
            case "date":
                try {
                    return new HashSet<>(getUniqueIPs(format.parse(arg), format.parse(arg)));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } 
            case "event":
                return new HashSet<>(getIPsForEvent(Event.valueOf(arg), null, null)); 
            case "status":
                return new HashSet<>(getIPsForStatus(Status.valueOf(arg), null, null));
        }
        return new HashSet<>();
    }
}