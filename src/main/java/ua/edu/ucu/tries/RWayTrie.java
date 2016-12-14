package ua.edu.ucu.tries;

import ua.edu.ucu.collections.structures.Queue;
import ua.edu.ucu.collections.structures.Stack;

import java.util.Iterator;

public class RWayTrie implements Trie {
    private final int R = 257;
    private Node root;

    //
    //----------------------------- private nested class Node  ----------------------------
    // contains data, instances(pointers) of(to) next nodes, node-adding methods
    //
    private class Node {
        private char c;
        private Object value;
        private int childNumber;
        private Node[] next;

        Node(char c, int R){
            this.c = c;
            next = new Node[R];
        }
        Node(int R) {
            next = new Node[R];
        }

        public Node getNext(char i){
            return next[i];
        }

        public void setNext(char c, Node node){
            next[c] = node;
            childNumber++;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
        @Override
        public String toString(){return ""+c;}

        public void decreaseChildNumber() {
            childNumber--;
        }

        public int getChildNumber() {
            return childNumber;
        }
    }
    //
    //----------------------------- End of Node  -----------------------------
    //

    public RWayTrie(){
        root = new Node(R);
    }

    @Override
    public void add(Tuple t) {
        Node node = root;

//        if (node == null) {
//            node = new Node(t.term.charAt(0), R);
//        }
        for (int i = 0; i < t.term.length(); i++) {
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
        int i = 0;
        Node node = root;
        while (i < word.length() && node.getNext(word.charAt(i)) != null) {
            node = node.getNext(word.charAt(i));
            i++;
        }
        return  i == word.length() && node.getValue() != null;
    }

    @Override
    public boolean delete(String word) {
        int i = 0;
        Node node = root;
        Stack stack = new Stack();
        stack.push(root);
        while (i < word.length() && node.getNext(word.charAt(i)) != null) {
            node = node.getNext(word.charAt(i));
            stack.push(node);
            i++;
        }
        node.setValue(null);
        if (i == word.length() && node.getChildNumber() == 0) {
            do{
                node = (Node)stack.pop();
                node.setNext(word.charAt(i-1), null);
                node.decreaseChildNumber();
                i--;
            }while (i > -1 && node.getChildNumber() == 0);
            return  true;
        }


//        boolean r = deleteHelper(root.getNext(word.charAt(0)), word, 0);
//        if (r) {
//            root.getNext(word.charAt(0)).decreaseChildNumber();
//            if (root.getNext(word.charAt(0)).getChildNumber() == 0) {
//                root.setNext(word.charAt(0), null);
//                return true;
//            }
//        }
        return false;
    }

//    private boolean deleteHelper(Node node, String s, int i) {
//        if (i < s.length() && node == null) {
//            return false;
//        } else if (i == s.length() - 1) {
//            if (node.getChildNumber() == 0) {
//                return true;
//            }
//            return false;
//        } else {
//            boolean t = deleteHelper(node.getNext(s.charAt(i + 1)), s, i + 1);
//
//            if (t) {
//                node.getNext(s.charAt(i + 1)).decreaseChildNumber();
//                if (node.getNext(s.charAt(i + 1)).getChildNumber() == 0) {
//                    node.setNext(s.charAt(i + 1), null);
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
    private Queue getWordsQueue(Node baseNode, String baseWord){
        Queue nodes = new Queue();
        Queue words = new Queue();
        Queue result = new Queue();

        nodes.enqueue(baseNode);
        words.enqueue(baseWord);

        while(nodes.getSize() != 0){
            int i = 0;
            int c = 0;
            Node node = (Node)nodes.peek();
            while(i < R && c < node.getChildNumber()){
                if(node.getNext((char)i) != null){
                    nodes.enqueue(node.getNext((char)i));
                    words.enqueue((String)words.peek() + (char)i);
                    c++;
                }
                i++;
            }
            if(node.getValue() != null){
                result.enqueue(words.peek());
            }
            words.dequeue();

            nodes.dequeue();
        }

        return result;
    }
    @Override
    public Iterable<String> words() {
        return new Iterable<String>() {
            Queue q = getWordsQueue(root, "");
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        return q.getSize()>0;
                    }

                    @Override
                    public String next() {
                        return (String) q.dequeue();
                    }
                };
            }
        };
    }

    private Node getBaseNode(String s){
        Node node = root;
        for(char i : s.toCharArray()){
            node = node.getNext(i);
        }
        return node;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        return new Iterable<String>() {
            Queue q = getWordsQueue(getBaseNode(s), s);
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        return q.getSize()>0;
                    }

                    @Override
                    public String next() {
                        return (String) q.dequeue();
                    }
                };
            }
        };
    }

    @Override
    public int size() {
        return getWordsQueue(root, "").getSize();
    }

}
