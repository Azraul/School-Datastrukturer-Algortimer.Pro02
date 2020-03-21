package Analyzer_App;

import java.util.HashMap;
import java.util.Map;

public class LanguageStats {
    /*
    The database for languages, labeled languages gets added to the database from the Languages constructor.
    Unlabeled languages gets guessed with the guessLanguage method.
    Guessed languages are not added to existing languages, however:
    topEstimation.getCharDistribution().putAll(inputDistribution);
     */
    public static HashMap<String, Language> languages = new HashMap<>();

    public static void addLanguage(Language language) {
        languages.put(language.getLabel(), language);
    }
    public static void guessLanguage(Language inputLanguage) {
        HashMap<String, Double> inDistro = inputLanguage.getCharDistribution();
        double currentScore = 0, topScore = -1;
        Language topEstimation = null;
        /*
        For each Language in languages
            Compare Language distribution with input Language distribution
                Apply score, if better previous append to topEstimation variable
                Score is based on (input Language(0 if no match) - current Language)^2 
                    Print the topEstimation with Menu class
         */
        for (Language currentLanguage : languages.values()) {
            HashMap<String, Double> currentLan = currentLanguage.getCharDistribution();
            for (Map.Entry<String, Double> entry : currentLan.entrySet()) {
                if (inDistro.containsKey(entry.getKey())) {
                    double inValue = inDistro.get(entry.getKey());
                    currentScore+= (inValue-entry.getValue())*(inValue-entry.getValue());
                } else {
                    currentScore += (0-entry.getValue())*(0-entry.getValue());
                }
            }
            if (topScore > currentScore || topScore == -1) {
                topScore = currentScore;
                topEstimation = currentLanguage;
            }
            currentScore = 0;
        }
        Menu menu = new Menu();
        menu.feedback(topEstimation.getLabel());
    }
}
