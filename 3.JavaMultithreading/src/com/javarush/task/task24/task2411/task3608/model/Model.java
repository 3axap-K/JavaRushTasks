package com.javarush.task.task24.task2411.task3608.model;

public interface Model {
    ModelData getModelData();

    void loadUsers();

    void loadDeletedUsers();

    void loadUserById(long userId);

    void deleteUserById(long id);


    void changeUserData(String name, long id, int level);
}
