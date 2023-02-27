package org.example;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;

public class EncrypterThread extends Thread {
    private GUIForm form;
    private File file;
    private ZipParameters parameters;

    public EncrypterThread(GUIForm form) {
        this.form = form;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setPassword(String password) {
        parameters = Parameters.getParameters();
        parameters.setPassword(password);
    }

    @Override
    public void run() {
        onStart();
        String archiveName = getArchiveName();
        try {
            ZipFile zipFile = new ZipFile(archiveName);
            if (file.isDirectory()) {
                zipFile.addFolder(file, parameters);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        onFinish();
    }
    private void onStart(){
        form.setButtonsEnabled(false);
    }

    private void onFinish() {
        form.setButtonsEnabled(true);
        parameters.setPassword("");
    }

    private String getArchiveName() {

        for (int i = 1; ; i++) {
            String number = i > 0 ? Integer.toString(i) : "";
            String archiveName = file.getAbsolutePath() + number + ".enc";
            if (!new File(archiveName).exists()) {
                return archiveName;
            }
        }
    }
}
