package com.javarush.task.task24.task2411.task3608;

import com.javarush.task.task24.task2411.task3608.controller.Controller;
import com.javarush.task.task24.task2411.task3608.view.EditUserView;
import com.javarush.task.task24.task2411.task3608.view.UsersView;
import com.javarush.task.task24.task2411.task3608.model.MainModel;
import com.javarush.task.task24.task2411.task3608.model.Model;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();
        Controller controller = new Controller();

        usersView.setController(controller);
        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);
        editUserView.setController(controller);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126);
        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged("NIKO", 126, 3);
        usersView.fireEventShowDeletedUsers();
    }
}