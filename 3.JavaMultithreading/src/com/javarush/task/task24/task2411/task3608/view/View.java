package com.javarush.task.task24.task2411.task3608.view;

import com.javarush.task.task24.task2411.task3608.controller.Controller;
import com.javarush.task.task24.task2411.task3608.model.ModelData;

public interface View {

    void refresh(ModelData modelData);
    void setController(Controller controller);
}
