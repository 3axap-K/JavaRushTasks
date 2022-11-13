package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.*;

public class Controller {

    public static void main(String args[]){

        View view1 = new View();
        Controller controller = new Controller(view1);
        view1.setController(controller);
        view1.init();
        controller.init();
    }

    /// Varriables
    private View view;
    private HTMLDocument document;
    private File currentFile;

    ///constructors
    public Controller(View view) {
        this.view = view;
    }

    ///// Methods
    public void init(){
        this.createNewDocument();
    }

    public void resetDocument(){
        if(this.document != null){
            document.removeUndoableEditListener(this.view.getUndoListener());}
            this.document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
            this.document.addUndoableEditListener(this.view.getUndoListener());
            this.view.update();

    }
    public void exit(){
        System.exit(0);
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader reader = new StringReader(text);
        try{
            new HTMLEditorKit().read(reader, this.document, 0);
            reader.close();
        }catch(Exception e){ExceptionHandler.log(e);}
    }

    public String getPlainText(){
        StringWriter writer = new StringWriter();
        String plainText;
        try{
            new HTMLEditorKit().write(writer, this.document, 0, document.getLength() );
            writer.close();
        }catch(Exception e){ExceptionHandler.log(e);}
        return String.valueOf(writer);
    }

    public void createNewDocument() {
        this.view.selectHtmlTab();
        this.resetDocument();
        this.view.setTitle("HTML редактор");
        this.currentFile = null;
    }

    public void openDocument() {
        this.view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        if(chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION){
            this.currentFile = chooser.getSelectedFile();
            resetDocument();
            this.view.setTitle(this.currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                new HTMLEditorKit().read(fileReader, this.document, 0);
                fileReader.close();
                this.view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public void saveDocument() {
        this.view.selectHtmlTab();
        if(currentFile != null){
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, this.document, 0, this.document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }else saveDocumentAs();
    }

    public void saveDocumentAs() {
        this.view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        if(chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION){
           this.currentFile = chooser.getSelectedFile();
           this.view.setTitle(this.currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, this.document, 0, this.document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    ///Getters & Setters
    public HTMLDocument getDocument() {
        return document;
    }


}
