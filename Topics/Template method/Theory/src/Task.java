// You can experiment here, it won’t be checked

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
  public static void main(String[] args) {
    // put your code here
    ArrayList<String> array = new ArrayList<>();
    String text = "There are some words in this sentence";
    Pattern pattern = Pattern.compile("s");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      array.add(text.substring(matcher.start(), matcher.end()));
    }
    System.out.println(array);
  }
}
