package com.itseu.common.deque.impl;

import com.itseu.common.deque.Deque;
import lombok.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*基于双向环形链表实现*/
@Data
public class LinkedListDeque<E> implements Deque<E>,Iterable<E> {

    private Node<E> sentinel;
    private int capacity;
    private int size;

    static class Node<E> {
       Node<E> next;
       Node<E> prev;
        E val;

        public Node(E val) {
            this.val = val;
        }

        public Node(E val, Node<E> next, Node<E> prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel = new Node<E>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = sentinel.next;
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return node != sentinel;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public E next() {
                E val = node.val;
                node = node.next;
                return val;
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
        return size == 0;
    }

    /**
     * 双端队列是否已满
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * 向头部加入元素
     *
     * @param e
     * @return
     */
    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            System.out.println("deque is full");
            return false;
        }
        Node<E> next = sentinel.next;
        Node<E> added = new Node(e, next, sentinel);
        sentinel.next = added;
        next.prev = added;
        size++;
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
        if(isFull()){
            System.out.println("deque is full");
            return false;
        }
        Node<E> prev = sentinel.prev;
        Node<E> added = new Node(e, sentinel, prev);
        sentinel.prev = added;
        prev.next = added;
        size++;
        return true;
    }

    /**
     * 从头部获取元素
     *
     * @return
     */
    @Override
    public E peekFirst() {
        if(isEmpty()){
            System.out.println("deque is empty");
            return null;
        }
        return sentinel.next.val;
    }

    /**
     * 从尾部获取元素
     *
     * @return
     */
    @Override
    public E peekLast() {
        if(isEmpty()){
            System.out.println("deque is empty");
            return null;
        }
        return sentinel.prev.val;
    }

    /**
     * 从头部移除元素
     *
     * @return
     */
    @Override
    public E pollFirst() {
        if(isEmpty()){
            System.out.println("deque is empty");
            return null;
        }
        Node<E> removed = sentinel.next;
        Node<E> next = removed.next;
        sentinel.next = next;
        next.prev = sentinel;
        size--;
        return removed.val;
    }

    /**
     * 从尾部移除元素
     *
     * @return
     */
    @Override
    public E pollLast() {
        if(isEmpty()){
            System.out.println("deque is empty");
            return null;
        }
        Node<E> removed = sentinel.prev;
        Node<E> prev = removed.prev;
        sentinel.prev = prev;
        prev.next = sentinel;
        size--;
        return removed.val;
    }
}
