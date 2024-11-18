package algorithm.design;

import java.util.*;

public class WordDictionary {

    private Set<String> dictionary;

    public WordDictionary() {
        dictionary = new HashSet<>();
    }

    public void addWord(String word) {
        dictionary.add(word);
    }

    public boolean search(String word) {
        boolean found = false;
        return found;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("mad");
        wordDictionary.addWord("dad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }
}
