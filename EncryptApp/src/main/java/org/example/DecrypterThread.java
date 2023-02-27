package org.example;

import net.lingala.zip4j.core.ZipFile;

import java.io.File;

public class DecrypterThread extends Thread{
    private GUIForm form;
    private File file;
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public DecrypterThread(GUIForm form){
        this.form = form;
    }
    @Override
    public void run() {
        String outPath = getOutPath();
        try {
            ZipFile zipFile = new ZipFile(file);
            zipFile.setPassword(password);
            zipFile.extractAll(outPath);
        } catch (Exception ex) {
            form.setWrongPasswordError();
        }
    }
    private String getOutPath(){
        String path = file.getAbsolutePath().replaceAll("\\.enc$", "");
        for (int i = 1; ; i++){
            String number = i > 1 ? Integer.toString(i) : "";
            String outPath = path + number;
            if (!new File(outPath).exists()){
                return outPath;
            }
        }
    }
}
