package Analyzer_App;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Language {
    private String Label;
    private String content;
    private HashMap charDistribution;
    private HashMap threeCharDistribution;
    private HashMap firstLetterDistribution;

    /*
    I decided to create 1 large Hashmap (charDistribution) for all 3 required analyses
    and values as it was sufficient for the assignment. First word letters are defined by upper case.
     */
    public Language(){

    }
    public Language(String string, String languageLabel) {
        content = string;
        charDistribution = calculateCharDistribution(content.toLowerCase(), 1);
        threeCharDistribution = calculateCharDistribution(content.toLowerCase(), 3);
        firstLetterDistribution = calculateCharDistribution(getFirstLetters(content.toUpperCase()),1);
        //Defines between read text files and user generated languages
        if (languageLabel != null){
            Label = getLabelFreeExtension(languageLabel);
            LanguageStats.addLanguage(this);
        }
    }

    public HashMap calculateCharDistribution(String content, int charSequence) {
        content = getLettersOnly(content);
        //Dividing by content.length is not exact for character sequences larger than one
        double denominator = (double) content.length()-charSequence+1.0;
        HashMap<String, Double> calculatedCharDistribution = new HashMap<>();
        for (int i = 0; i < content.length(); i++) {
            String c = null;
            if (charSequence==1){
                c = String.valueOf(content.charAt(i));
            } else if (content.length()-charSequence>i){
                c = content.substring(i, i + charSequence);
            }
            if (c !=null){
                Double val = calculatedCharDistribution.get(c);
                if (val != null) {
                    calculatedCharDistribution.put(c, val + (1.0 /denominator));
                } else {
                    calculatedCharDistribution.put(c, 1.0 /denominator);
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
    public HashMap getThreeCharDistribution(){
        return threeCharDistribution;
    }
    public HashMap getFirstLetterDistribution(){
        return firstLetterDistribution;
    }

    //Pretty label
    private String getLabelFreeExtension(String label){
        //https://stackoverflow.com/questions/941272/how-do-i-trim-a-file-extension-from-a-string-in-java
        if(label != null && label.length() > 0) {
            while(label.contains(".")) {
                label = label.substring(0, label.lastIndexOf('.'));
            }
        }
        return label;
    }

    /*After discussing with classmate I changed to a much cleaner StringBuilder
    for my regex constructed strings. StringBuilder made it a lot easier to see
    and just read what these function are supposed to do.
     */
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
}
