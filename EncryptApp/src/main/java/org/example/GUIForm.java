package org.example;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class GUIForm {
    private JPanel panel1;
    private JTextField textField1;
    private JButton setFileButton;
    private JButton EncButton;
    private File selectedFile;

    private String Decode = "Decipher";
    private String Encode = "Encrypt";


    private boolean zipEncrypted = false;
    public GUIForm(){
        setFileButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.showOpenDialog(panel1);
                selectedFile = chooser.getSelectedFile();
                if (selectedFile == null){
                    textField1.setText("");
                    EncButton.setVisible(false);
                    return;
                }
                textField1.setText(selectedFile.getAbsolutePath());
                try {
                    ZipFile zipFile = new ZipFile(selectedFile);
                        zipEncrypted = zipFile.isValidZipFile() && zipFile.isEncrypted();
                        EncButton.setText(zipEncrypted ? Decode : Encode);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                EncButton.setVisible(true);
            }
        });
        EncButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile == null ){
                    return;
                }
                String password = JOptionPane.showInputDialog("Enter password");
                if (password == null || password.length() == 0){
                    setWrongPasswordError();
                    return;
                }
                if (zipEncrypted){
                    getDecode(password);
                }else{
                    getEncode(password);
                }
            }
        });
    }
    public JPanel getPanel1(){
        return panel1;
    }
    public void setButtonsEnabled(boolean enabled){
        setFileButton.setEnabled(enabled);
        EncButton.setEnabled(enabled);
    }
    private void getEncode(String password){
        EncrypterThread thread = new EncrypterThread(this);
        thread.setFile(selectedFile);
        thread.setPassword(password);
        thread.start();
    }
    private void getDecode(String password){
        DecrypterThread thread = new DecrypterThread(this);
        thread.setFile(selectedFile);
        thread.setPassword(password);
        thread.start();
    }
    public void setWrongPasswordError(){
        JOptionPane.showMessageDialog(panel1, "wrong password", "Exeption",
                JOptionPane.WARNING_MESSAGE);
    }
}
