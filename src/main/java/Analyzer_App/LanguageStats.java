package Analyzer_App;

import java.util.*;

public class LanguageStats {
    /*
    The database for languages, labeled languages gets added to the database from the Languages constructor.
    Unlabeled languages gets guessed with the guessLanguage method.
    Guessed languages are not added to existing languages, however:
    topEstimation.getCharDistribution().putAll(inputDistribution);
     */
    public static HashMap<String, Language> languages = new HashMap<String, Language>();


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
        Map<String, Double> scoreTable = new HashMap<String, Double>();

        Language topEstimation = null;
        Menu menu = new Menu();
        menu.guessOptions();
        double score = 0, topScore = 1;
        double totalScore = score;
        if (option == 4){
            Map<String, Double> oneCharScore = new HashMap<String, Double>();
            Map<String, Double> threeCharScore = new HashMap<String, Double>();
            Map<String, Double> firstCharScore = new HashMap<String, Double>();
            for (Map.Entry<String, Language> languageEntry : languages.entrySet()) {
                String lang = languageEntry.getKey();
                Language langDistro = languageEntry.getValue();
                double combinedScore = 0;
                score = compareHashes(
                        languageToGuess.getCharDistribution(),
                        langDistro.getCharDistribution()
                );
                oneCharScore.put(lang, score);
                combinedScore += score;
                score = compareHashes(
                        languageToGuess.getThreeCharDistribution(),
                        langDistro.getThreeCharDistribution()
                );
                threeCharScore.put(lang, score);
                combinedScore += score;
                score = compareHashes(
                        languageToGuess.getFirstLetterDistribution(),
                        langDistro.getFirstLetterDistribution()
                );
                firstCharScore.put(lang, score);
                combinedScore += score;
                score = combinedScore;
                scoreTable.put(lang, score);
                if (topScore > score) {
                    topScore = score;
                    topEstimation = langDistro;
                }
        }

            menu.results(scoreTable, oneCharScore, threeCharScore, firstCharScore);
        }
        else{
                for (Map.Entry<String, Language> languageEntry : languages.entrySet()) {
                    String lang = languageEntry.getKey();
                    Language langDistro = languageEntry.getValue();
                    switch (option) {
                        case 1:
                            score = compareHashes(
                                    languageToGuess.getCharDistribution(),
                                    langDistro.getCharDistribution()
                            );
                            break;
                        case 2:
                            score = compareHashes(
                                    languageToGuess.getThreeCharDistribution(),
                                    langDistro.getThreeCharDistribution()
                            );
                            break;
                        case 3:
                            score = compareHashes(
                                    languageToGuess.getFirstLetterDistribution(),
                                    langDistro.getFirstLetterDistribution()
                            );
                            break;
                        default:
                            menu.invalid();
                            //unknown
                            return languageToGuess;
                    }
                    scoreTable.put(lang, score);
                    if (topScore > score) {
                        topScore = score;
                        topEstimation = langDistro;
                    }
                }
                    menu.results(scoreTable);
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
}
