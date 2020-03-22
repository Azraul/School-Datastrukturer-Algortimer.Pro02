package Analyzer_App;

import java.util.Scanner;

public class Analyzer {
    {
        //reads Language folder for text files
        FileInput.readAvailableLanguages();
    }

    public Analyzer() {
        analyzerInterface();
    }

    private void analyzerInterface() {
        //Scanner for the menu
        Scanner keyboard = new Scanner(System.in);
        Menu menu = new Menu();
        //Choice for the menu
        int choice = 0;
        menu.Initialize();
        menu.Case(choice);
        while (choice != 9) {
            menu.main();
            choice = keyboard.nextInt();
            switch (choice) {
                case 1:
                    menu.Case(choice);
                    //new scanner for user generated text
                    Scanner language = new Scanner(System.in);
                    String s = language.nextLine();
                    Language user = new Language(s, null);
                    Scanner option = new Scanner(System.in);
                    menu.guessOptions();
                    int guessOption = option.nextInt();
                    menu.guessAnswer(LanguageStats.guessLanguage(user, guessOption));
                    break;
                case 2:
                    menu.Case(choice);
                    //Prints all languages read
                    LanguageStats.languages.entrySet().forEach(entry->{
                        System.out.println("\t# "+entry.getKey());
                    });
                    break;
                default:
                    menu.Case(choice);
                    break;
            }
        }
        System.exit(0);
    }
}
