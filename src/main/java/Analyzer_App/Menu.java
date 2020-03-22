package Analyzer_App;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    //https://howtodoinjava.com/sort/java-sort-map-by-values/
    private static Map sortMap(Map<String, Double> mapToSort) {
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        mapToSort.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        return sortedMap;
    }

    public void Initialize() {
        System.out.println("Welcome to language analyzer application");
    }

    public void main() {
        System.out.println("Choose by typing the relevant number and pressing return:");
    }

    public void Case(int i) {
        //Switch in a switch so you can switch while you switch - Easy to hide options in main "switch" like case 5 & 9
        switch (i) {
            case 1:
                System.out.println("You choose 1 - Insert custom text for analyzing");
                System.out.println("Please start typing to provide a text and hit return when ready:");
                break;
            case 2:
                System.out.println("You choose 2 - See supported languages");
                System.out.println("Currently the following languages are provided for estimation of your generated text:");
                break;
            case 5:
                System.out.println("You choose 5 - application information");
                System.out.println("This application was made by Kuvaja as a school project at Arcada, for the the course Datastrukturer och Algoritmer, 2020");
                System.out.println("The application reads predefined language files and generates values based on character and string pattern occurrences in the texts.");
                System.out.println("These values are then stored in a Hashmap that gets compared with user generated texts. By comparing we can estimate what language the users text originates form.");
                System.out.println("Estimations are loosely based. For commercial use larger texts along with more sophisticated algorithms would be needed.");
                break;
            case 9:
                System.out.println("Thank you for using my application");
                System.out.println("See you soon again!");
                break;
            default:
                System.out.println("\t MENU OPTIONS");
                System.out.println("##########################################");
                System.out.println("We provide the following options:");
                System.out.println("1: Insert custom text for analyzing");
                System.out.println("2: See supported languages");
                System.out.println("5: See application information");
                System.out.println("9: Quit the program");
                break;
        }
    }

    public void invalid() {
        System.out.println("No valid option was chosen, please try again.");
    }

    public void guessOptions() {
        System.out.println("Select how we should analyse your text by providing the relevant number:");
        System.out.println("1: By letters");
        System.out.println("2: By groups of 3 letters");
        System.out.println("3: By the first letter in each word");
        System.out.println("4: By all the above options");
    }

    public void guessAnswer(Language estimated) {
        System.out.println("By our current database we estimate that the text you provided originates in " + estimated.getLabel());
        System.out.println("Thank you for using our program");
    }

    //https://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html
    //For single analysis
    public void results(Map scoreTable) {
        LinkedHashMap<String, Double> sortedMap = (LinkedHashMap<String, Double>) sortMap(scoreTable);
        System.out.format("%45s", "Scores\n\n");
        System.out.format("%16s%16s", "Language", "Analysis\n");
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.format("%16s%16s", key, Math.round(value * 1000.0) / 1000.0 + "\n");
        }
    }

    //For combined analysis
    public void results(Map<String, Double> scoreTable, Map<String, Double> oneChrScore, Map<String, Double> threeCharScore, Map<String, Double> firstCharScore) {
        LinkedHashMap<String, Double> sortedMap = (LinkedHashMap<String, Double>) sortMap(scoreTable);
        System.out.format("%45s", "Scores\n\n");
        System.out.format("%16s%16s%16s%16s%16s", "Language", "Method 1", "Method 2", "Method 3", "Total" + "\n");
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            System.out.format("%16s%16s%16s%16s%16s",
                    key,
                    Math.round(oneChrScore.get(key) * 1000.0) / 1000.0,
                    Math.round(threeCharScore.get(key) * 1000.0) / 1000.0,
                    Math.round(firstCharScore.get(key) * 1000.0) / 1000.0,
                    Math.round(value * 1000.0) / 1000.0 +
                            "\n");
        }
    }
}
