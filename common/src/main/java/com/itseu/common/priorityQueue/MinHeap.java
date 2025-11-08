package com.itseu.common.priorityQueue;

import com.itseu.common.base.ListNode;

/*简单最小堆实现：基于数组*/
public class MinHeap {

    ListNode[] elements;
    int size;

    public MinHeap(int capacity) {
        elements = new ListNode[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    /*上浮*/
    public boolean offer(ListNode node) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && node.value < elements[parent].value) {
            elements[child] = elements[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        elements[child] = node;
        return true;
    }

    /*下沉*/
    public ListNode poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        ListNode removed = elements[--size];
        elements[size] = null;
        int parent = 0;
        while(true){
            int leftChild = 2*parent + 1;
            int rightChild = 2*parent + 2;
            int min = parent;
            if(leftChild < size && elements[leftChild].value < elements[min].value) {
                min = leftChild;
            }
            if(rightChild < size && elements[rightChild].value < elements[min].value) {
                min = rightChild;
            }
            if(min != parent){
                swap(min, parent);
                parent = min;
            } else {
                break;
            }
        }
        return removed;

    }

    private void swap(int i, int j) {
        ListNode temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }


}
