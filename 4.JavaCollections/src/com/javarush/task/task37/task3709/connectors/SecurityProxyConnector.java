package com.javarush.task.task37.task3709.connectors;

import com.javarush.task.task37.task3709.security.SecurityChecker;
import com.javarush.task.task37.task3709.security.SecurityCheckerImpl;

public class SecurityProxyConnector implements Connector{
    private SecurityChecker checker = new SecurityCheckerImpl();
    private SimpleConnector connector;

    public SecurityProxyConnector(String string) {
        this.connector = new SimpleConnector(string);
    }

    @Override
    public void connect() {
        if(checker.performSecurityCheck()) connector.connect();
    }
}
