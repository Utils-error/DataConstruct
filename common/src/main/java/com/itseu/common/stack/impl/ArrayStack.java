package com.itseu.common.stack.impl;

import com.itseu.common.stack.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*基于数组实现栈*/
public class ArrayStack<E> implements Stack<E>,Iterable<E> {

    private int head;
    private E[] array;

    /**
     * 判断栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return head == 0;
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return head==array.length;
    }

    /**
     * 返回栈顶元素
     *
     * @return
     */
    @Override
    public E peek() {
        if(isEmpty()){
            return null;
        }
        return array[head-1];
    }

    /**
     * 从栈顶弹出元素
     *
     * @return
     */
    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        E val = array[head-1];
        head--;
        return val;
    }

    /**
     * 向栈顶压入元素
     *
     * @param element
     * @return
     */
    @Override
    public boolean push(E element) {
        if(isFull()){
            return false;
        }
        array[head++] = element;
        return false;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int index = head;
            /**
             * Returns {@code true} if the iteration has more elements.
             * (In other words, returns {@code true} if {@link #next} would
             * return an element rather than throwing an exception.)
             *
             * @return {@code true} if the iteration has more elements
             */
            @Override
            public boolean hasNext() {
                return index != 0;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more elements
             */
            @Override
            public E next() {
                return array[--index];
            }
        };
    }

    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
        head = 0;
    }


}
