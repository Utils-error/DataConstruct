package com.itseu.common.priorityQueue;

import com.itseu.common.queue.Queue;
import com.sun.nio.file.ExtendedWatchEventModifier;

import java.util.PriorityQueue;

/*基于数组实现的最大堆*/
public class PriorityQueue3<E extends Priority> implements Queue<E> {

    private Priority[] elements;
    private int size;

    public PriorityQueue3(int capacity) {
        elements = new Priority[capacity];
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
        // 插入元素时，可以暂时先不放入数组中，因为一旦
        // 发生父节点下移，当前值也会被覆盖
        int child = size++;
        int parent = (child - 1) / 2;
        // 开始比较父子节点，如果父优先级小则需要下沉，此外，子节点得
        // 得保证大于0，否则索引不合法
        while (child>0 && val.priority() > elements[parent].priority()) {
            elements[child] = elements[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        elements[child] = val;
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
        // 交换root与当前最后一个元素,并移除元素
        swap(0, size - 1);
        E val = (E) elements[--size];
        elements[size] = null;
        // 与插入的新元素上浮的情况，此时应该当交换后的元素下沉
        int parent = 0;
        while(true){
            int leftChild = 2*parent + 1;
            int rightChild = 2*parent + 2;
            int max = parent;
            if(leftChild < size && elements[leftChild].priority() > elements[max].priority()) {
                max = leftChild;
            }
            if(rightChild < size && elements[rightChild].priority() > elements[max].priority()) {
                max = rightChild;
            }
            if(max != parent){
                swap(max, parent);
                parent = max;
            } else {
                break;
            }
        }
        return val;
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
        return (E) elements[0];
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

    /*交换数据*/
    private void swap(int i, int j) {
        Priority temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /*打印数组*/
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i] + " ");
        }
        System.out.println();
    }
}
