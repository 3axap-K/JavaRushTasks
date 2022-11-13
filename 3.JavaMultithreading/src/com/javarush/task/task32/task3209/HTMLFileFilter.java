package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if(f == null) return false;
        String name = f.getName().toLowerCase();
        return (f.isDirectory() || name.endsWith(".html") || name.endsWith(".htm"));
    }

    @Override
    public String getDescription() {

        return "HTML и HTM файлы";
    }
}
