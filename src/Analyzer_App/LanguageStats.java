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
        double current = 0, bestGuess = -1;
        Language likelyLanguage = null;
        for (Language currentLanguage : languages.values()) {
            HashMap<String, Double> currentLan = currentLanguage.getCharDistribution();
            for (Map.Entry<String, Double> entry : currentLan.entrySet()) {
                if (inputDistribution.containsKey(entry.getKey())) {
                    double inValue = inputDistribution.get(entry.getKey());
                    current+= (inValue-entry.getValue())*(inValue-entry.getValue());
                } else {
                    current += (0-entry.getValue())*(0-entry.getValue());
                }
            }
            if (bestGuess > current || bestGuess == -1) {
                bestGuess = current;
                likelyLanguage = currentLanguage;
            }
            System.out.println(currentLanguage.getLabel()+ " got a score of " + current);
            current = 0;
        }
        System.out.println("We got to the end, the language is probably");
        System.out.println(likelyLanguage.getLabel());
        return likelyLanguage.getLabel();
    }
}
