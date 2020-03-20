package Analyzer_App;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Analyzer {
    {
        /*TODO move replaceAll to readfile??
        */
        System.out.println("Time to read");

        File folder = new File("./src/Languages");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                System.out.println("Reading " + name);
                String URL = "./src/Languages/" + name;
                new Language(FileInput.readFile(URL).replaceAll("\\s", ""), name);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("Printa languages hashmap");
        LanguageStats.languages.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            entry.getValue().printMap();
        });

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Skriv någe");
        String usertext = keyboard.next();
        new Language(usertext.replaceAll("\\s", ""), "unknown");



    }
}
