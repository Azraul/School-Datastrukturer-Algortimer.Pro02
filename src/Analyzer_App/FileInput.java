package Analyzer_App;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInput {
    private static String readFile(String URL){
        String text = new String();

        try{
            //Öppna ne fil med en specifik URL
            FileInputStream f = new FileInputStream(URL);

            //hit spara vi ASCII-koden för ett enskilt tecken
            //vi läser ur filen
            int tempChar;

            //Här lämnar vi in texten från filen TECKEN FÖR TECKEN
            //f.read() returnerar ASCII-koden för det tecken
            //den läser från frilen. f.read() returner -1
            while ( (tempChar = f.read() ) !=-1){
                //Till strängen text sätter vi tell det nya tecknet
                //vi nuss läste bort från textfilen
                text += String.valueOf((char)tempChar);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("File was not found: "+ ex.getMessage());
        }
        catch (IOException ex){
            System.out.println("IOException occured: "+ ex.getMessage());
        }
        //returnerar den text vi läste in från filen

        return text;
    }
    public static void readAvailableLanguages() {
        File folder = new File("./src/Languages");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                String URL = "./src/Languages/" + name;
                new Language(readFile(URL), name);
            }
        }
    }
}
