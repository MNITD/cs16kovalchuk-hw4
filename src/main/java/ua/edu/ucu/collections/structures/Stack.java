package main.java.ua.edu.ucu.collections.structures;

import main.java.ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList immutableLinkedList;

    public Stack() {
        immutableLinkedList = new ImmutableLinkedList();
    }

    public Object peek() {
        return immutableLinkedList.getLast();
    }

    public Object pop() {
        Object obj = immutableLinkedList.getLast();
        immutableLinkedList = immutableLinkedList.removeLast();
        return obj;
    }

    public void push(Object e) {
        immutableLinkedList = immutableLinkedList.addLast(e);
    }

    public int getSize(){
        return immutableLinkedList.size();
    }
}
