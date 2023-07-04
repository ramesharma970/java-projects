import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        Map<String, Integer> wordCountMap = countWords(sentence);

        System.out.println("Word Count:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Remove leading and trailing whitespaces, and convert to lowercase
        sentence = sentence.trim().toLowerCase();

        // Split the sentence into words
        String[] words = sentence.split("\\s+");

        // Count the occurrences of each word
        for (String word : words) {
            if (wordCountMap.containsKey(word)) {
                wordCountMap.put(word, wordCountMap.get(word) + 1);
            } else {
                wordCountMap.put(word, 1);
            }
        }

        return wordCountMap;
    }
}
