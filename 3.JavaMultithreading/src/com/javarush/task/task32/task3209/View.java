package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

/// Varriables
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    private UndoManager undoManager = new UndoManager();

    private UndoListener undoListener = new UndoListener(undoManager);


/// Constructors

    public View(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){ExceptionHandler.log(e);}
    }

///// Methods
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Новый":
                this.controller.createNewDocument();
                break;
            case "Открыть":
                this.controller.openDocument();
                break;
            case "Сохранить":
                this.controller.saveDocument();
                break;
            case "Сохранить как...":
                this.controller.saveDocumentAs();
                break;
            case "Выход":
                this.controller.exit();
                break;
            case "О программе":
                this.showAbout();
                break;
        }
    }

    public void update(){
        htmlTextPane.setDocument(this.controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(getContentPane(),"About", "task program",JOptionPane.INFORMATION_MESSAGE);
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;

    }

    public void undo(){
        try{
            undoManager.undo();
        }catch (Exception e){ ExceptionHandler.log(e);}
    }

    public void redo(){
        try{undoManager.redo();}catch (Exception e){ ExceptionHandler.log(e);}
    }
    public boolean canUndo(){return undoManager.canUndo();}

    public boolean canRedo() {return undoManager.canRedo();}

    public void resetUndo(){
        undoManager.discardAllEdits();
    }
    public void init(){
        this.initGui();
        FrameListener listener = new FrameListener(this);
        addWindowListener(listener);
        setVisible(true);
    }

    public void exit(){
        this.controller.exit();
    }

    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPaneHtml = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", jScrollPaneHtml);
        JScrollPane jScrollPanePlain = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", jScrollPanePlain);
        tabbedPane.setPreferredSize(new Dimension(500,500));
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        this.initMenuBar();
        this.initEditor();
        this.pack();
    }

    public void selectedTabChanged(){
        switch(tabbedPane.getSelectedIndex()){
            case 0:
                controller.setPlainText(plainTextPane.getText());
                break;
            case 1:
                plainTextPane.setText(controller.getPlainText());
                break;
        }
        this.resetUndo();

    }

/// Getters & Setters
    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }
}
