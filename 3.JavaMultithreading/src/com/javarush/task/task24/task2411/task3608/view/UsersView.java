package com.javarush.task.task24.task2411.task3608.view;

import com.javarush.task.task24.task2411.task3608.bean.User;
import com.javarush.task.task24.task2411.task3608.controller.Controller;
import com.javarush.task.task24.task2411.task3608.model.ModelData;

import java.util.List;

public class UsersView implements View{
    private Controller controller;



    @Override
    public void refresh(ModelData modelData) {
        if(!modelData.isDisplayDeletedUserList()){System.out.println("All users:");}
        else {System.out.println("All deleted users:");}
        List<User> users = modelData.getUsers();
        for(User user: users){
            System.out.println("\t" + user.toString());
        }
        System.out.println("===================================================");
    }
    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }
    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }



}
