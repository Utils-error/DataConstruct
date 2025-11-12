package com.itseu.common.heap.application;

import com.itseu.common.heap.MaxHeap;
import com.itseu.common.heap.MinHeap;

public class HeapApplication {

    public static void main(String[] args) {
        int[] arr = new int[]{2,43,5,74,8};
        // System.out.println(Arrays.toString(heapSort(arr)));
        System.out.println(kMax(arr,2));
    }

    /*堆排序：
    * 1.为待排序数组建立一个堆
    * 2.不断将堆顶元素与末尾进行交换并进行调整，每交换一次缩小一次堆
    * 3.直到堆中剩下最后一个元素*/
    public static int[] heapSort(int[] arr){
        if(arr == null || arr.length == 0 || arr.length == 1){
            return arr;
        }
        MaxHeap maxHeap = new MaxHeap(arr);
        while(maxHeap.getSize()>1){
            maxHeap.swap(0,maxHeap.getSize()-1);
            maxHeap.setSize(maxHeap.getSize()-1);
            maxHeap.down(0);
        }
        return maxHeap.getArray();
    }

    public static int kMax(int[] arr, int k){
        /*MaxHeap maxHeap = new MaxHeap(arr);
        for(int i = 0;i<k-1;i++){
            maxHeap.poll();
        }
        return maxHeap.peek();*/

        MinHeap minHeap = new MinHeap(k);
        for(int i=0; i<arr.length; i++){
            if(i<k){
                minHeap.offer(arr[i]);
            }else {
                if(arr[i]>minHeap.peek()){
                    minHeap.replace(arr[i]);
                }
            }
        }
        return minHeap.peek();

    }
}
