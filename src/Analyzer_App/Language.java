package Analyzer_App;

import java.util.HashMap;

public class Language {
    private String Label;
    private String content;
    private HashMap charDistribution;

    public Language(String string, String languageLabel) {
        Label = languageLabel;
        content = string;
        charDistribution = calculateCharDistribution(string);
        if (languageLabel == "unknown"){
            LanguageStats.guessLanguage(this);
        } else{
        LanguageStats.addLanguage(this);
        }
    }

    public HashMap calculateCharDistribution(String content) {
        content = content.toLowerCase();
        HashMap<String, Double> calculatedCharDistribution = new HashMap<>();
        for (int i = 0; i < content.length(); i++) {
            String c = String.valueOf(content.charAt(i));
            Double val = calculatedCharDistribution.get(c);
            if (val != null) {
                calculatedCharDistribution.put(c, val + (1.0/content.length()));
            } else {
                calculatedCharDistribution.put(c, 1.0/content.length());
            }
        }
        return calculatedCharDistribution;
    }

    public String getLabel() {
        return Label;
    }
    public HashMap getCharDistribution(){
        return charDistribution;
    }

    public void printMap() {
        charDistribution.forEach((k, v) -> {
            System.out.println("\t"+k + "[" + Math.round((double) v *1000.0)/1000.0 + "%]");
        });
    }
}
