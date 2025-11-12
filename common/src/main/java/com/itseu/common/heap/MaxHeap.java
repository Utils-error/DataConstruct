package com.itseu.common.heap;

import lombok.Data;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/*正经堆：底层为数组*/
@Data
public class MaxHeap {
    private int[] array;
    private int size;

    public MaxHeap(int capacity) {
        array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        size = array.length;
        heapify();
    }

    public boolean offer(int val){
        if(isFull()){
            throw new NoSuchElementException();
        }
        up(val);
        size++;
        return true;
    }

    /*返回堆顶元素*/
    public int peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return array[0];
    }

    /*移除堆顶元素*/
    public int poll(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int removed = array[0];
        swap(0, size-1);
        size--;
        down(0);
        return removed;

    }

    /*移除指定位置元素*/
    public int poll(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException("Index is out of bounds: " + index);
        }
        int removed = array[index];
        swap(index, size-1);
        size--;
        // 这里需要注意，除了下沉之外，仍需要判断一下，是否需要上浮。
        if(index!=size){
            down(index);
            int parentIndex = (index-1)/2;
            if(array[parentIndex]< array[index]){
                up(index);
            }
        }
        return removed;
    }

    /*替换堆顶元素*/
    public void replace(int replaced){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        array[0] = replaced;
        down(0);
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }



    /*堆化（Floyid算法）
     * 1、找到最后一个非叶节点
     * 2、从后向前遍历所有的非叶子节点，
     * 3、对每一个非叶子结点进行下沉，直到其抵达最后一层*/
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    /*下沉*/
    public void down(int parent) {
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int max = parent;
        if (leftChild < size && array[max] < array[leftChild]) {
            max = leftChild;
        }
        if (rightChild < size && array[max] < array[rightChild]) {
            max = rightChild;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void up(int offered){
        int child = size;
        while(child > 0){
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                (a,b) -> b - a
        );
        pq.add(1);
        pq.add(2);
        System.out.println(pq.toString());
    }
}
