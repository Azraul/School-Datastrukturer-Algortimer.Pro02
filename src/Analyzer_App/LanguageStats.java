package Analyzer_App;

import java.util.*;

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

    /*
    For each Language in languages
        Compare Language distribution with input Language distribution
            Apply score, if better previous append to topEstimation variable
                Print the topEstimation with Menu class
     */
    public static Language guessLanguage(Language languageToGuess, int option) {
        Map<String, Double> score = new HashMap<>();
        Language topEstimation = null;
        Menu menu = new Menu();
        //System.out.println(languageToGuess.keySet());
        double currentScore = 0, topScore = 1;
        double totalScore = currentScore;
        for (Map.Entry<String, Language> languageEntry : languages.entrySet()) {
            String lang = languageEntry.getKey();
            Language langDistro = languageEntry.getValue();
            switch (option) {
                case 1:
                    currentScore = compareHashes(languageToGuess.getCharDistribution(), langDistro.getCharDistribution());
                    break;
                case 2:
                    currentScore = compareHashes(languageToGuess.getThreeCharDistribution(), langDistro.getThreeCharDistribution());
                    break;
                case 3:
                    currentScore = compareHashes(languageToGuess.getFirstLetterDistribution(), langDistro.getFirstLetterDistribution());
                    break;
                case 4:
                    currentScore = compareAllHashes(languageToGuess, langDistro);
                    break;
                default:
                    menu.invalid();
                    //unknown
                    return languageToGuess;
            }
            score.put(lang, currentScore);
            if (topScore > currentScore) {
                topScore = currentScore;
                topEstimation = langDistro;
            }
        }
        //https://howtodoinjava.com/sort/java-sort-map-by-values/
        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        score.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            //https://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html
            System.out.format("%16s%16s", key, Math.round(value * 1000.0) / 1000.0+"\n");
        }
        return topEstimation;
    }

    private static double compareHashes(HashMap<String, Double> in, HashMap<String, Double> db) {
        double weight, currentScore = 0;
        for (Map.Entry<String, Double> inEntry : in.entrySet()) {
            String key = inEntry.getKey();
            Double value = inEntry.getValue();
            if (db.get(key) != null) {
                weight = value - (double) db.get(key);
                //System.out.println(weight);
            } else {
                weight = value;
            }
            //System.out.println(key+" - "+weight);
            currentScore += weight * weight;
        }
        return currentScore;
    }

    private static Double compareAllHashes(Language languageToGuess, Language langDistro){
        //HashMap<String, Double> totalScore = new HashMap<>();
        Double currentScore, totalScore;
        currentScore = compareHashes(languageToGuess.getCharDistribution(), langDistro.getCharDistribution());
        currentScore += compareHashes(languageToGuess.getThreeCharDistribution(), langDistro.getThreeCharDistribution());
        currentScore += compareHashes(languageToGuess.getFirstLetterDistribution(), langDistro.getFirstLetterDistribution());
        totalScore = currentScore;
        return totalScore;
    }
}
