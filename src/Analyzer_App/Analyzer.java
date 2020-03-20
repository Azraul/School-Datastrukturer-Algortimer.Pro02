package Analyzer_App;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Analyzer {
    {
        /*TODO move replaceAll to readfile??
        Access them by label
        have them in an arraylisy Languages?
        */
        System.out.println("Time to read");

        File folder = new File("./src/Languages");
        File[] listOfFiles = folder.listFiles();
        ArrayList<Language> Languages = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                System.out.println("Reading " + name);
                String URL = "./src/Languages/" + name;
                Languages.add(new Language(FileInput.readFile(URL).replaceAll("\\s", ""), name));
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("Print arraylist with languages");
        for (Language l : Languages
        ) {
            System.out.println(l.getLabel());
            l.printMap();
        }
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Skriv någe");
        String usertext = keyboard.next();
        Languages.add(new Language(usertext.replaceAll("\\s", ""), "unknown"));



    }
}
