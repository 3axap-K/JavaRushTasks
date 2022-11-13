package com.javarush.task.task24.task2411.task3608.controller;

import com.javarush.task.task24.task2411.task3608.view.UsersView;
import com.javarush.task.task24.task2411.task3608.model.Model;
import com.javarush.task.task24.task2411.task3608.view.EditUserView;

public class Controller {

    private EditUserView editUserView;



    private Model model;

    private UsersView usersView;

    public void setModel(Model model) {
        this.model = model;
    }

    public void onShowAllUsers(){

        model.loadUsers();
        usersView.refresh(model.getModelData());
    }

    public void setUsersView(UsersView usersView) {
        this.usersView = usersView;
    }

    public void onShowAllDeletedUsers() {
       model.loadDeletedUsers();

        usersView.refresh(model.getModelData());
    }

    public void onOpenUserEditForm(long userId) {
        model.loadUserById(userId);
        editUserView.refresh(model.getModelData());
    }

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }

    public void onUserDelete(long id) {
        model.deleteUserById(id);
        usersView.refresh(model.getModelData());
    }

    public void onUserChange(String name, long id, int level) {
        model.changeUserData(name, id, level);
        usersView.refresh(model.getModelData());
    }
}
