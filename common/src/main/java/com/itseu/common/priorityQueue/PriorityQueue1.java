package com.itseu.common.priorityQueue;

import com.itseu.common.queue.Queue;
import lombok.val;

import java.util.PriorityQueue;


/*基于无序数组实现*/
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] elements;
    int size;

    public PriorityQueue1(int capacity) {
        elements = new Priority[capacity];
        size = 0;
    }

    /**
     * 检查队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 检查队列是否存满
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return size == elements.length;
    }

    /**
     * 向队列尾插入元素
     *
     * @param val
     * @return
     */
    @Override
    public boolean offer(E val) {
        if (isFull()) {
            System.out.println("Queue is full");
            return false;
        }
        elements[size++] = val;
        return true;
    }

    /**
     * 从队列头获取元素并移除
     *
     * @return
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        int max = selecetMax();
        Priority val = (E) elements[max];
        remove(max);
        return (E) val;
    }

    /**
     * 从队列头获取元素，不移除
     *
     * @return
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return (E) elements[selecetMax()];
    }

    /*寻找最优先值*/
    private int selecetMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (elements[i].priority() > elements[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    /*动态删除数组元素*/
    private void remove(int index) {
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        elements[--size] = null;

    }
}
