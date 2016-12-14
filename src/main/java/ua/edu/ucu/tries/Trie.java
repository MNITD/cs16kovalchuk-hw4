package ua.edu.ucu.tries;

import ua.edu.ucu.collections.structures.Queue;

public interface Trie {

    public void add(Tuple word);

    public boolean contains(String word);

    public boolean delete(String word);

    public Iterable<String> words();

    public Iterable<String> wordsWithPrefix(String pref);
    
    public int size();
}
