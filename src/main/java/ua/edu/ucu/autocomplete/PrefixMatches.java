package ua.edu.ucu.autocomplete;

import ua.edu.ucu.collections.structures.Queue;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.Iterator;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int len = 0;
        for(String s: strings){
            String[] words = s.split(" ");
            for(String w: words) {
                if(w.length()>2){
                    trie.add(new Tuple(w, w.length()));
                    len++;
                }
            }
        }
        return len;
    }

    public boolean contains(String word){
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    Iterator iterator = trie.wordsWithPrefix(pref).iterator();
                    String word;
                    @Override
                    public boolean hasNext() {
                        while(iterator.hasNext()){
                            word = (String)iterator.next();
                            if(word.length()< pref.length()+k){
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public String next() {
                        return word;
                    }
                };
            }
        };
    }

    public int size() {
        return trie.size();
    }

}
