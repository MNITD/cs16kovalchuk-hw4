package main.java.ua.edu.ucu.tries;

/**
 * Created by Dell on 11.12.2016.
 */
public class Node {
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


//    public void increaseChildNumber() {
//        childNumber++;
//    }

    public void decreaseChildNumber() {
        childNumber--;
    }

    public int getChildNumber() {
        return childNumber;
    }
}
