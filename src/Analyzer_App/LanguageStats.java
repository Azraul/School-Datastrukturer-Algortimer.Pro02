package Analyzer_App;

import java.util.HashMap;

public class LanguageStats {
    private HashMap languages;

    public LanguageStats(){
    languages = new HashMap<String, Language>();
    }
    private void addLanguage(Language language){
    languages.put(language.getLabel(), language);
    }
    private String guessLanguage(Language inputLanguage){
        Language best;
        double current;
        double bestMatch;
        //for each language in languages
            //for each key on input find matching key in currentLanguage language
                //calculate values of keys and add to current
        //if current is better than best sum put new language as best
        if (current<bestMatch){
            best = currentLanguage;
        }
        //next language
        //Until we have done all languages, then return label of best language
        return best.getLabel();
    }
}
