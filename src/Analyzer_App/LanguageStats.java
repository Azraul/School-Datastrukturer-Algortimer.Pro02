package Analyzer_App;

import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;

public class LanguageStats {
    public static HashMap<String, Language> languages = new HashMap<>();

    public static void addLanguage(Language language) {
        languages.put(language.getLabel(), language);
    }

    public static String guessLanguage(Language inputLanguage) {
        HashMap<String, Double> inputDistribution = inputLanguage.getCharDistribution();
        //HashMap<String, Double> currentLanguage = new HashMap<>();
        double current = 0, bestGuess = 0;
        Language likelyLanguage = null;
        for (Language currentLanguage : languages.values()) {
            System.out.println("Now checking "+currentLanguage.getLabel());
            for (Map.Entry<String, Double> entry : inputDistribution.entrySet()) {
                if (currentLanguage.getCharDistribution().containsKey(entry.getKey())) {
                    current += (entry.getValue() * entry.getValue()) - ((double) currentLanguage.getCharDistribution().get(entry.getKey()) * (double) currentLanguage.getCharDistribution().get(entry.getKey()));
                } else {
                    //Om bokstaven inte finns i databasen är det ju tydligt att språket inte tillhör
                    System.out.println(currentLanguage.getLabel()+ " did not have the letter "+entry.getKey());
                    current += 1;
                }
            }
            if (bestGuess > current || bestGuess == 0) {
                bestGuess = current;
                likelyLanguage = currentLanguage;
            }
            System.out.println(currentLanguage.getLabel()+ " got a score of " + current);
            current = 0;
            //for each language in languages
            //for each key on input find matching key in currentLanguage language
            //calculate values of keys and add to current
            //if current is better than best sum put new language as best
            //next language
            //Until we have done all languages, then return label of best language
        }
        System.out.println("We got to the end, the language is probably");
        System.out.println(likelyLanguage.getLabel());
        return likelyLanguage.getLabel();
    }
}
