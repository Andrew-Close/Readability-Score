// You can experiment here, it won’t be checked

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
  public static void main(String[] args) {
    // put your code here
    String regex = "\\b\\w+\\.";
    String text = "this is a sentence. here is another sentence.";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);

    System.out.println(matcher.find());
    System.out.println(matcher.start());

    String[] sentence = text.substring(0, matcher.end()).split(" ");
    System.out.println(Arrays.toString(sentence));
  }
}
