package com.cloudHealth.desktopapp.util;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Wednesday
 * Date: 1/29/2020
 * Time: 5:47 PM
 * Project: desktop-app
 */

public class StringFormatter {
    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
}
