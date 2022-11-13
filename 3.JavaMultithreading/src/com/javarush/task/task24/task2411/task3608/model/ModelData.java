package com.javarush.task.task24.task2411.task3608.model;

import com.javarush.task.task24.task2411.task3608.bean.User;

import java.util.List;

public class ModelData {
    private User activeUser;
    private List<User> users;
    private boolean displayDeletedUserList;

    private boolean displayEditedUser;

    public void setDisplayEditedUser(boolean displayEditedUser) {
        this.displayEditedUser = displayEditedUser;
    }

    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
