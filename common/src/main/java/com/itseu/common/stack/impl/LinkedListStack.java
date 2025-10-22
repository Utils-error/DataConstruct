package com.itseu.common.stack.impl;

import com.itseu.common.stack.Stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/*基于链表实现栈*/
public class LinkedListStack<E> implements Stack<E>, Iterable<E> {

    private int size;
    private int capacity;
    private Node<E> head;

    static class Node<E> {
        Node<E> next;
        E val;

        public Node(E val) {
            this.val = val;
        }

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }

    public LinkedListStack(int capacity) {
        this.capacity = capacity;
        size = 0;
        head = new Node<E>(null, null);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            System.out.println("Stack is empty");
            return null;
        }
        return head.next.val;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        Node<E> node = head.next;
        head.next = head.next.next;
        size--;
        return node.val;
    }

    @Override
    public boolean push(E element) {
        if (isFull()) {
            System.out.println("Stack is full");
            return false;
        }
        head.next = new Node<>(element,  head.next);
        size++;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head.next;
            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public E next() {
                E val = current.val;
                current = current.next;
                return val;
            }
        };
    }
}
