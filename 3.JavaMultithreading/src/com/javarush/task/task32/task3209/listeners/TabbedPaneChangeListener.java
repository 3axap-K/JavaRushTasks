package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPaneChangeListener implements ChangeListener {

   /// Variables
   private View view;

   /// Constructors
    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }


    /// Methods
    @Override
    public void stateChanged(ChangeEvent e) {
        this.view.selectedTabChanged();
    }

    /// Getters & Setters


}
