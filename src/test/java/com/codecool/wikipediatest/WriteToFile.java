package com.codecool.wikipediatest;

import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;

public class WriteToFile {


    public static void writeLog( String date,String email, String name ) {
        String newLog = (date + ";"+date + ";"+date + "\n");
        try {
            FileWriter logWriter = new FileWriter("RegLog.txt");
            logWriter.write(newLog);
            logWriter.close();
            System.out.println("Log successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String LastLog() {
        try {
            File logFile = new File("RegLog.txt");
            Scanner logReader = new Scanner(logFile);
            String logData = "";
            while (logReader.hasNextLine()) {
                logData = logReader.nextLine();
            }
            System.out.println("Last signed up datas: " + logData);
            return logData;

        } catch (IOException e) {
            System.out.println("An error occurred by reading the file RegLog.txt.");
            e.printStackTrace();
        }
        return null;
    }


}