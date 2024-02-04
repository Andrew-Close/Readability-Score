package readability;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int sentences;
    private static int polysyllables;
    private static int words;
    private static int syllables;
    private static int characters = 0;

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        final File file = new File(args[0]);
        String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        words = getNumOfWords(text);
        sentences = getNumOfSentences(text);
        characters = getNumOfCharacters(text);
        syllables = getNumOfSyllables(text);
        polysyllables = getNumOfPolySyllables(text);

        System.out.println("The text is:");
        System.out.println(text);
        System.out.println();
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = scanner.next();
        System.out.println();
        System.out.println();

        switch (choice) {
            case "ARI":
                printARIScore();
                break;
            case "FK":
                printFleschKincaidScore();
                break;
            case "SMOG":
                printSMOGScore();
                break;
            case "CL":
                printColemanLiauScore();
                break;
            case "all":
                printAllScores();
        }
    }

    public static int getNumOfSentences(String text) {
        return text.split("[.!?]").length;
    }

    public static int getNumOfPolySyllables(String text) {
        int counter = 0;
        for (String word : getWordArray(text)) {
            if (getNumOfSyllables(word) > 2) {
                ++counter;
            }
        }
        return counter;
    }

    public static int getNumOfWords(String text) {
        return text.split("\\s").length;
    }

    public static String[] getWordArray(String text) {
        StringBuilder textNoPunc = new StringBuilder(text);
        for (int i = 0; i < textNoPunc.length(); i++) {
            if (Character.toString(textNoPunc.charAt(i)).matches("[.!?]")) {
                textNoPunc.delete(i, i + 1);
            }
        }
        return textNoPunc.toString().split("\\s");
    }

    public static int getNumOfSyllables(String text) {
        String[] words = getWordArray(text);
        int textCounter = 0;
        int previousIndex;
        Pattern pattern = Pattern.compile("[aeiouyAEIOUY]");
        for (String word : words) {
            previousIndex = -2;
            Matcher matcher = pattern.matcher(word);
            int wordCounter = 0;
            while (matcher.find()) {
                if (!(matcher.start() == previousIndex + 1) && !(matcher.start() == word.length() - 1 && "e".equalsIgnoreCase(matcher.group()))) {
                    ++wordCounter;
                }
                previousIndex = matcher.start();
            }
            if (wordCounter == 0) {
                ++textCounter;
            } else {
                textCounter += wordCounter;
            }
        }
        return textCounter == 0 ? 1 : textCounter;
    }

    public static int getNumOfCharacters(String text) {
        int counter = 0;
        for (char character : text.toCharArray()) {
            if (Character.toString(character).matches("\\S")) {
                ++counter;
            }
        }
        return counter;
    }

    public static double calculateARIScore() {
        return Double.parseDouble(String.valueOf(BigDecimal.valueOf(4.71 * ((double) characters / words) + 0.5 * ((double) words / sentences) - 21.43).setScale(2, RoundingMode.HALF_EVEN)));
    }

    public static void printARIScore() {
        double score = calculateARIScore();
        System.out.println("Automated Readability Index: " + score + " (about " + getAgeLevel(score) + "-year-olds).");
    }

    public static double calculateFleschKincaidScore() {
        return Double.parseDouble(String.valueOf(BigDecimal.valueOf(0.39 * ((double) words / sentences) + 11.8 * ((double) syllables / words) - 15.59).setScale(2, RoundingMode.HALF_EVEN)));
    }

    public static void printFleschKincaidScore() {
        double score = calculateSMOGScore();
        System.out.println("Flesch–Kincaid readability tests: " + score + " (about " + getAgeLevel(score) + "-year-olds).");
    }

    public static double calculateSMOGScore() {
        return Double.parseDouble(String.valueOf(BigDecimal.valueOf(1.043 * Math.sqrt(polysyllables * ((double) 30 / sentences)) + 3.1291).setScale(2, RoundingMode.HALF_EVEN)));
    }

    public static void printSMOGScore() {
        double score = calculateSMOGScore();
        System.out.println("Simple Measure of Gobbledygook: " + score + " (about " + getAgeLevel(score) + "-year-olds).");
    }

    public static double calculateColemanLiauScore() {
        double divisor = (double) words / 100;
        double l = (double) characters / divisor;
        double s = (double) sentences / divisor;
        return Double.parseDouble(String.valueOf(BigDecimal.valueOf(0.0588 * l - 0.296 * s - 15.8).setScale(2, RoundingMode.HALF_EVEN)));
    }

    public static void printColemanLiauScore() {
        double score = calculateColemanLiauScore();
        System.out.println("Coleman–Liau index: " + score + " (about " + getAgeLevel(score) + "-year-olds).");
    }
    
    public static void printAllScores() {
        double ariScore = calculateARIScore();
        double fleshKincaidScore = calculateFleschKincaidScore();
        double smogScore = calculateSMOGScore();
        double colemanLiauScore = calculateColemanLiauScore();
        int ariAge = getAgeLevel(ariScore);
        int fleshKincaidAge = getAgeLevel(fleshKincaidScore);
        int smogAge = getAgeLevel(smogScore);
        int colemanLiauAge = getAgeLevel(colemanLiauScore);

        System.out.println("Automated Readability Index: " + ariScore + " (about " + ariAge + "-year-olds).");
        System.out.println("Flesch–Kincaid readability tests: " + fleshKincaidScore + " (about " + fleshKincaidAge + "-year-olds).");
        System.out.println("Simple Measure of Gobbledygook: " + smogScore + " (about " + smogAge + "-year-olds).");
        System.out.println("Coleman–Liau index: " + colemanLiauScore + " (about " + colemanLiauAge + "-year-olds).");
        System.out.println();
        System.out.println("This text should be understood in average by " + BigDecimal.valueOf(((double) ariAge + fleshKincaidAge + smogAge + colemanLiauAge) / 4).setScale(2, RoundingMode.HALF_EVEN) + "-year-olds.");
    }

    public static int getAgeLevel(double score) {
        int roundedScore = (int) Math.round(score);
        return switch (roundedScore) {
            case 1 -> 7;
            case 2 -> 8;
            case 3 -> 9;
            case 4 -> 10;
            case 5 -> 11;
            case 6 -> 12;
            case 7 -> 13;
            case 8 -> 14;
            case 9 -> 15;
            case 10 -> 16;
            case 11 -> 17;
            case 12 -> 18;
            default -> 22;
        };
    }
}
