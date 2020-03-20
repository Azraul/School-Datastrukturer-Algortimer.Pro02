package Analyzer_App;

import java.util.HashMap;
import java.util.Map;

public class LanguageStats {
    public static HashMap<String, Language> languages = new HashMap<>();

    public static void addLanguage(Language language){
    languages.put(language.getLabel(), language);
    }
    public static String guessLanguage(Language inputLanguage){
        HashMap<String, Double> inputDistribution = inputLanguage.getCharDistribution();
        HashMap<String, Double> currentLanguage = new HashMap();
        double current, bestGuess = 0;
        Language likelyLanguage = null;
        for (int i = 0; i<languages.size();i++){
            currentLanguage = languages.get(i).getCharDistribution();
            current = 0;
            for (Map.Entry<String, Double> key : inputDistribution.entrySet()){
                if(currentLanguage.containsKey(key.getKey()))
                    current += (key.getValue()*key.getValue())-(currentLanguage.get(key.getKey())*currentLanguage.get(key.getKey()));
                    System.out.println("Current "+current);
            }
            if (current<bestGuess || bestGuess == 0){
                bestGuess = current;
                likelyLanguage = languages.get(i);
            } else {
                System.out.println(likelyLanguage.getLabel()+" is still the closest language");
            }
        }
        //for each language in languages
            //for each key on input find matching key in currentLanguage language
                //calculate values of keys and add to current
        //if current is better than best sum put new language as best
        //next language
        //Until we have done all languages, then return label of best language
        return likelyLanguage.getLabel();
    }
}
