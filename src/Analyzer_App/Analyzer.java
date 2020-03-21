package Analyzer_App;

import java.util.Scanner;

public class Analyzer {
    {
        FileInput.readAvailableLanguages();
        Scanner keyboard = new Scanner(System.in);
        String userText = keyboard.nextLine();
        new Language(userText, "unknown");
    }

}
