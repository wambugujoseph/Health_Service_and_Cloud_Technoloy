package com.cloudHealth.desktopapp.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Tuesday
 * Date: 2/4/2020
 * Time: 9:15 AM
 * Project: desktop-app
 */

public class FileUtil {

    public static File renameFile(File file, String newFileName){
        try {
            String extension = getFileExtension(file);
            file.renameTo(new File("tempFile/"+newFileName+"--renamed."+extension));
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return new File("/");
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
