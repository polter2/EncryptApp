package org.example;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import javax.swing.*;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("File Encrypter");
        jFrame.setSize(600, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        GUIForm form = new GUIForm();
        jFrame.add(form.getPanel1());
        jFrame.setVisible(true);
    }
}