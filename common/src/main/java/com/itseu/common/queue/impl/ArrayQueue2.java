package com.itseu.common.queue.impl;

import com.itseu.common.queue.Queue;
import lombok.val;

import java.util.Iterator;

/*基于环形数组实现的队列:加入size计算当前大小*/
public class ArrayQueue2<E> implements Queue<E>, Iterable<E> {
    private int head;
    private int tail;
    private int capacity;
    private int size;
    private E[] array;

    public ArrayQueue2(int capacity) {
        head = 0;
        tail = 0;
        size = 0;
        this.capacity = capacity;
        array = (E[]) new Object[capacity+1];
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
    public boolean offer(E val) {
        if(isFull()) {
            return false;
        }
        array[tail] = val;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E val = array[head];
        head = (head + 1) % capacity;
        size--;
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
            int count = 0;
            @Override
            public E next() {
                E val = array[p];
                p = (p + 1) % capacity;
                count++;
                return val;
            }

            @Override
            public boolean hasNext() {
                return count < size;
            }
        };
    }
}
