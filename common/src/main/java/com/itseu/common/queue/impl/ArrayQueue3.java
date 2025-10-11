package com.itseu.common.queue.impl;

import com.itseu.common.queue.Queue;

import java.util.Iterator;

/*基于环形数组实现的队列:head/tail不代表真实索引，需要进一步计算*/
public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {

    private int head;
    private int tail;
    private E[] array;

    public ArrayQueue3(int capacity) {
        head = 0;
        tail = 0;
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail - head) == array.length;
    }

    @Override
    public boolean offer(E val) {
        if (isFull()) {
            return false;
        }
        array[tail % array.length] = val;
        tail++;

        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E val = array[head % array.length];
        head++;

        return val;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head % array.length];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public E next() {
                E val = array[p % array.length];
                p++;
                return val;
            }

            @Override
            public boolean hasNext() {
                return p != tail;
            }
        };
    }
}
