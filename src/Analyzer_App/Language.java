package Analyzer_App;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language {
    private String Label;
    private String content;
    private HashMap charDistribution;
    private HashMap threeSignDistribution;
    private HashMap firstLetterDistribution;

    public Language(String string, String languageLabel) {
        Label = getLabelFreeExtension(languageLabel);
        content = string;
        charDistribution = calculateCharDistribution(content.toLowerCase(), 1);
        charDistribution.putAll(calculateCharDistribution(content.toLowerCase(), 3));
        charDistribution.putAll(calculateCharDistribution(content.toUpperCase(),1));
        threeSignDistribution = calculateCharDistribution(content, 3);
        firstLetterDistribution = calculateCharDistribution(getFirstLetters(content.toUpperCase()),1);
        if (languageLabel == "unknown") {
            LanguageStats.guessLanguage(this);
        } else {
            LanguageStats.addLanguage(this);
        }
    }

    public HashMap calculateCharDistribution(String content, int charSequence) {
        content = getLettersOnly(content);
        HashMap<String, Double> calculatedCharDistribution = new HashMap<>();
        for (int i = 0; i < content.length(); i++) {
            if (content.length()-charSequence>i){
                String c = content.substring(i, i + charSequence);
                Double val = calculatedCharDistribution.get(c);
                if (val != null) {
                    calculatedCharDistribution.put(c, val + (1.0 /content.length()));
                } else {
                    calculatedCharDistribution.put(c, 1.0 /content.length());
                }
            }
        }
        return calculatedCharDistribution;
    }

    public String getLabel() {
        return Label;
    }

    public HashMap getCharDistribution() {
        return charDistribution;
    }

    private String getLabelFreeExtension(String label){
        //https://stackoverflow.com/questions/941272/how-do-i-trim-a-file-extension-from-a-string-in-java
        if(label != null && label.length() > 0) {
            while(label.contains(".")) {
                label = label.substring(0, label.lastIndexOf('.'));
            }
        }
        return label;
    }

    private String getFirstLetters(String string)
    {
        StringBuilder newString = new StringBuilder();
        Pattern myPattern = Pattern.compile("\\b\\p{IsAlphabetic}");
        Matcher m = myPattern.matcher(string);
        while (m.find()){
            newString.append(m.group());
        }
        return newString.toString();
    }
    private String getLettersOnly(String string){
        StringBuilder s = new StringBuilder();
        Pattern p = Pattern.compile("\\p{IsAlphabetic}");
        Matcher m = p.matcher(string);
        while (m.find()){
            s.append(m.group());
        }
        return s.toString();
    }

    public void printCharDistribution() {
        charDistribution.forEach((k, v) -> {
            System.out.println("\t" + k + "[" + Math.round((double) v * 1000.0) / 1000.0 + "%]");
        });
    }
    public void printSignDistribution() {
        threeSignDistribution.forEach((k, v) -> {
            System.out.println("\t" + k + "[" + Math.round((double) v * 1000.0) / 1000.0 + "%]");
        });
    }
    public void printfirstLetterDistribution() {
        firstLetterDistribution.forEach((k, v) -> {
            System.out.println("\t" + k + "[" + Math.round((double) v * 1000.0) / 1000.0 + "%]");
        });
    }
}
