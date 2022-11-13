package com.javarush.task.task24.task2411.task3608.view;

import com.javarush.task.task24.task2411.task3608.controller.Controller;
import com.javarush.task.task24.task2411.task3608.model.ModelData;
import com.javarush.task.task24.task2411.task3608.bean.User;

public class EditUserView implements View{
    private Controller controller;
    @Override
    public void refresh(ModelData modelData) {
        System.out.println( "User to be edited:");
        User user = modelData.getActiveUser();

            System.out.println("\t" + user.toString());

        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void fireEventUserDeleted(long id) {
        controller.onUserDelete(id);
    }

    public void fireEventUserChanged(String name, long id, int level) {
        controller.onUserChange(name, id, level);
    }
}
