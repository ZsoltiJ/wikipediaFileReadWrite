package com.codecool.wikipediatest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {

    public String[] readCredential() {
        String[] result = new String[2];
        try {
            File myUser = new File("Peti.txt");
            Scanner scanner = new Scanner(myUser);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] temp = data.split(" = ");
                if (temp[0].equals("username")) {
                    result[0] = temp[1];
                } else {
                    result[1] = temp[1];
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String writeToFile(String title, String paragraph) {
        try {
            FileWriter textFile = new FileWriter("searchResults.txt");

                textFile.append(title + " : \n" + paragraph + "\n");

            textFile.close();
            return null;
        } catch (IOException e) {
            return e.getMessage();
        }

    }
}
