// You can experiment here, it won’t be checked

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
  public static void main(String[] args) {
    // put your code here
    int counter = 0;
    String text = "Here is a sentence. There is some punctuation! Including a question mark? But there is no punctuation at the end";
    Pattern pattern = Pattern.compile("\\w+[.!?]|\\w+$");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      System.out.println(text.substring(matcher.start(), matcher.end()));
    }
  }
}
