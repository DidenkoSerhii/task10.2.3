package third;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordCounter {
    public static String wordCount(){
        String[] words = readToList().split("\\s+");
        Map<String, Integer> wordsMap = new TreeMap<>();

        for (String s : words){
            if (wordsMap.containsKey(s)){
                wordsMap.put(s,wordsMap.get(s) + 1);
            }
            else {
                wordsMap.put(s, 1);
            }
        }

        Set<Map.Entry<String, Integer>> wordsSet= addToSetAndSort(wordsMap);
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Integer> entry : wordsSet){
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return result.toString().trim();
    }

    private static String readToList(){
        StringBuilder text = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader("files/words.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text.toString().trim();
    }

    private static Set<Map.Entry<String, Integer>> addToSetAndSort(Map<String, Integer> wordsMap){
        Set<Map.Entry<String, Integer>> wordsSet = new TreeSet<>((entry1, entry2) -> {
            if (entry1.getValue() > entry2.getValue()){
                return - 1;
            }
            else if (entry1.getValue() < entry2.getValue()){
                return 1;
            }
            else {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
        wordsSet.addAll(wordsMap.entrySet());
        return wordsSet;
    }


}