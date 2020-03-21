package Analyzer_App;

/**
 *
 * @author kuvajaan
 */
public class Menu {
    public void Initialize() {
        System.out.println("Welcome to language analyzer application");
    }
    public void main(){
        System.out.println("Choose by typing the relevant number and pressing return:");
    }
    public void Case(int i){
        //Switch in a switch so you can switch while you switch - Easy to hide options in main "switch" like case 5 & 9
     switch (i){
         case 1:
             System.out.println("You choose 1 - Insert custom text for analyzing");
             System.out.println("Please start typing to provide a text and hit return when ready:");
             break;
         case 2:
             System.out.println("You choose 2 - See supported languages");
             System.out.println("Currently the following languages are provided for estimation of your generated text:");
             break;
         case 5:
             System.out.println("You choose 5 - application information");
             System.out.println("This application was made by Kuvaja as a school project at Arcada, for the the course Datastrukturer och Algoritmer, 2020");
             System.out.println("The application reads predefined language files and generates values based on character and string pattern occurrences in the texts.");
             System.out.println("These values are then stored in a Hashmap that gets compared with user generated texts. By comparing we can estimate what language the users text originates form.");
             System.out.println("Estimations are loosely based. For commercial use larger texts along with more sophisticated algorithms would be needed.");
             break;
         case 9:
             System.out.println("Thank you for using my application");
             System.out.println("See you soon again!");
             break;
         default:
             System.out.println("\t MENU OPTIONS");
             System.out.println("##########################################");
             System.out.println("We provide the following options:");
             System.out.println("1: Insert custom text for analyzing");
             System.out.println("2: See supported languages");
             System.out.println("5: See application information");
             System.out.println("9: Quit the program");
             break;
     }
    }
    public void feedback(String s){
        System.out.println("The most likely language, according to our estimations, for your text is "+s);
        System.out.println("If you are satisfied with the answer, remember to provide feedback to your nearest Admin");
    }
}
