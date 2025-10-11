package com.itseu.common.queue.impl;

import com.itseu.common.queue.Queue;
import lombok.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* 基于单向环形带哨兵 链表的方式实现
 * */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {

    private Node<E> head = new Node<>(null, null);
    private Node<E> tail = head;
    private int capacity;
    private int size;

    public LinkedListQueue() {
        tail.next = head;
        size = 0;
        capacity = 3;
    }

    @Data
    private static class Node<E> {
        private E val;
        private Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public boolean offer(E val) {
        if(size == capacity) {
            return false;
        }
        Node<E> added = new Node<>(val, head);
        tail.next = added;
        tail = added;
        size++;
        return true;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.next.val;
    }

    @Override
    public E poll() {
        if (isEmpty()) throw new NoSuchElementException();
        Node<E> removed = head.next;
        head.next = removed.next;
        if (removed == tail) {
            tail = head;
        }
        size--;
        return removed.val;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head.next;

            @Override
            public E next() {
                E ret = current.val;
                current = current.next;
                return ret;
            }

            @Override
            public boolean hasNext() {
                return current != head;
            }
        };
    }
}
