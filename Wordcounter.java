import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

public class Wordcounter {

    private static String[] splitTextIntoWords(String text) {

        String[] words = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        return words;
    }

    private static void countWords(String text) {
        String[] words = splitTextIntoWords(text);
        int totalWords = words.length;

        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        for (String word : words) {

            if (word.length() >= 2) {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        int uniqueWords = wordFrequencyMap.size();

        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + uniqueWords);

        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        System.out.println("Enter text or provide a file path:");
        String input = scanner.nextLine().trim();

        try {
            File file = new File(input);
            if (file.exists()) {

                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    text += fileScanner.nextLine() + "\n";
                }
                fileScanner.close();
            } else {

                text = input;
            }

            countWords(text);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        scanner.close();
    }
}
