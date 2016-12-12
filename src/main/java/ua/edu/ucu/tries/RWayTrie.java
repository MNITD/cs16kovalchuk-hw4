package main.java.ua.edu.ucu.tries;

public class RWayTrie<T> implements Trie {
    private final int R;
    private Node root;

    public RWayTrie(int rway) {
        R = rway;
        root = new Node(rway);
    }

    @Override
    public void add(Tuple t) {
        Node node = root;

        if (node == null) {
            node = new Node(t.term.charAt(0), R);
        }
        for (int i = 1; i < t.term.length(); i++) {
            Node temp = node.getNext(t.term.charAt(i));
            if (temp == null) {
                temp = new Node(t.term.charAt(i), R);
                node.setNext(t.term.charAt(i), temp);
            }
            node = temp;
        }
        node.setValue(t.weight);
//        root.setNext(t.term.charAt(0), addHelper(root.getNext(t.term.charAt(0)), t.term, 0, t.weight));
    }

//    private Node addHelper(Node node, String str, int i, Object value) {
//        if (node == null) {
//            node = new Node(str.charAt(i), R);
//        }
//
//        if (i + 1 == str.length()) {
//            node.setValue(value);
//            return node;
//        }
//        node.setNext(str.charAt(i + 1), addHelper(node.getNext(str.charAt(i + 1)), str, i + 1, value));
//        return node;
//    }

    @Override
    public boolean contains(String word) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(String word) {
        boolean r = deleteHelper(root.getNext(word.charAt(0)), word, 0);
        if(r){
            root.getNext(word.charAt(0)).decreaseChildNumber();
            if(root.getNext(word.charAt(0)).getChildNumber() == 0){
                root.setNext(word.charAt(0), null);
                return true;
            }
        }
        return false;
    }

    private boolean deleteHelper(Node node, String s, int i) {
        if (i < s.length() && node == null) {
            return false;
        } else if (i == s.length() - 1) {
            if (node.getChildNumber() == 0) {
                return true;
            }
            return false;
        } else {
            boolean t = deleteHelper(node.getNext(s.charAt(i + 1)), s, i + 1);

            if(t){
                node.getNext(s.charAt(i + 1)).decreaseChildNumber();
                if(node.getNext(s.charAt(i + 1)).getChildNumber() == 0){
                    node.setNext(s.charAt(i + 1), null);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public Iterable<String> words() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
