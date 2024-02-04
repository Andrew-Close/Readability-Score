// You can experiment here, it won’t be checked

import java.util.regex.Pattern;

public class Task {
  public static void main(String[] args) {
    // put your code here
    String regex = "there\b";
    String text = "#there";
    System.out.println(Pattern.matches(regex, text));
    System.out.println(text.matches(regex));
    System.out.println();
    System.out.println(Pattern.matches("\bword", "word is here"));
    System.out.println(Pattern.matches("word\b", "Here is word"));
  }
}