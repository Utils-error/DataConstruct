package com.itseu.common.deque.impl;

import com.itseu.common.deque.Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {

    /*head与tail指针最的区别在，tail一直指向并不存在的元素
     * 而head则是实际存在的元素，这就意味对tail进行操作时，需要先赋值再移动，
     * 而head需要先移动再赋值,
     * 且由于仅仅使用了head和tail，那么就必须腾出一个空位不存储元素，用于区分循环数组的空/满状态*/
    private E[] elements;
    int head;
    int tail;

    public ArrayDeque1(int capacity) {
        elements = (E[]) new Object[capacity + 1];
        head = 0;
        tail = 0;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = head;

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public E next() {
                E val = elements[cursor];
                cursor = (cursor + 1) % elements.length;
                return val;
            }

            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return cursor != tail;
            }
        };
    }

    /**
     * 双端队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 双端队列是否已满
     *
     * @return
     */
    @Override
    public boolean isFull() {
        if(tail == head) return false;
        return tail > head ? tail - head == elements.length - 1 : head - tail == 1;
    }

    /**
     * 向头部加入元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            System.out.println("queue is full");
            return false;
        }
        head = (head + elements.length - 1) % elements.length;
        elements[head] = e;
        return true;
    }

    /**
     * 向尾部加入元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            System.out.println("queue is full");
            return false;
        }
        elements[tail] = e;
        tail = (tail + 1) % elements.length;
        return true;
    }

    /**
     * 从头部获取元素
     *
     * @return
     */
    @Override
    public E peekFirst() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return null;
        }
        return elements[head];
    }

    /**
     * 从尾部获取元素
     *
     * @return
     */
    @Override
    public E peekLast() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return null;
        }
        return elements[tail];
    }

    /**
     * 从头部移除元素
     *
     * @return
     */
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return null;
        }
        E val = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return val;
    }

    /**
     * 从尾部移除元素
     *
     * @return
     */
    @Override
    public E pollLast() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return null;
        }
        tail = (tail - 1) % elements.length;
        E val = elements[tail];
        elements[tail] = null;
        return val;
    }
}
