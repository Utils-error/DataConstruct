package com.itseu.common.queue.impl;

import com.itseu.common.queue.Queue;

import java.util.Iterator;

/*基于环形数组实现的队列*/
public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {

    private int head;
    private int tail;
    private int capacity;
    private E[] array;

    public ArrayQueue1(int capacity) {
        head = 0;
        tail = 0;
        this.capacity = capacity ;
        array = (E[]) new Object[capacity+1];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    @Override
    public boolean offer(E val) {
        if(isFull()) {
            return false;
        }
        array[tail] = val;
        tail = (tail + 1) % capacity;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E val = array[head];
        head = (head + 1) % capacity;
        return val;
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public E next() {
                E val = array[p];
                p = (p + 1) % capacity;
                return val;
            }

            @Override
            public boolean hasNext() {
                return p != tail;
            }
        };
    }
}
