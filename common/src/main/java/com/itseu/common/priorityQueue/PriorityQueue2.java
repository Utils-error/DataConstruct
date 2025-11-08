package com.itseu.common.priorityQueue;

import com.itseu.common.queue.Queue;

import java.util.PriorityQueue;

/*基于有序数组实现，这里的有序主要指的是Priority的值,假定数组末尾的元素是优先级最高的*/
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    private Priority[] elements;
    private int size;

    public PriorityQueue2(int capacity) {
        elements = (E[]) new Priority[capacity];
        size = 0;
    }
    /**
     * 检查队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size==0;
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
        if(isFull()){
            System.out.println("Queue is full");
            return false;
        }
        int index = find(val.priority());
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = val;
        size++;
        return true;
    }

    /**
     * 从队列头获取元素，不移除
     *
     * @return
     */
    @Override
    public E peek() {
        if(isEmpty()){
            System.out.println("Queue is empty");
            return null;
        }
        return (E)elements[size-1];
    }

    /**
     * 从队列头获取元素并移除
     *  由于是有序数组，因此可以从数组尾移出
     * @return
     */
    @Override
    public E poll() {
        if(isEmpty()){
            System.out.println("Queue is empty");
            return null;
        }
        E val = (E)elements[size-1];
        elements[--size] = null;
        return val;
    }

    /*依据优先级找到插入位置*/
    private int find(int v){
        for(int i = 0;i<size;i++){
            if(v < elements[i].priority()){
                return i;
            }
        }
        return size;

    }


}
