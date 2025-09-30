package com.itseu.common.array;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/*
 * 手搓动态数组
 * */
@Slf4j
@Data
public class DynamicArray implements Iterable<Integer>{
    // 实际大小
    private int size = 0;
    // 最大容量
    private int capacity = 10;
    // 实际存储数据的数组
    private int[] array ={};

    /*在最后插入数据*/
    public void addLast(int element) {
        add(size, element);
    }

    /*在指定位置处插入数据*/
    public void add(int index, int element) {
        enSureAndGrow();
        if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
    }

    /*检查扩容*/
    public void enSureAndGrow() {
        if(size==0){
            array = new int[capacity];
        } else if (size == capacity) {
            log.info("动态数组已达最大容量,准备扩容，扩容前最大容量为={}", capacity);
            // 扩容
            capacity += capacity >> 1;
            log.info("已经完成扩容，扩容后最大容量为={}", capacity);
            int[] newArray = new int[capacity];
            System.arraycopy(array,0,newArray,0,size);
            array = newArray;
        }
    }

    /*foreach循环遍历*/
    public void foreach(Consumer<Integer> action) {
        for(int i = 0; i < size; i++) {
            action.accept(array[i]);
        }
    }
    /*迭代器遍历*/
    public Iterator<Integer> iterator(){
        Iterator iterator = new Iterator<Integer>() {
            int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Integer next() {
                return array[index++];
            }
        };
        return iterator;
    }
    /*stream流遍历*/
    public IntStream stream() {
        return IntStream.of(Arrays.copyOf(array, size));
    }

    /*删除指定索引数据*/
    public int remove(int index) {
        int removed = array[index];
        if(index < size - 1) {
            System.arraycopy(array,index+1,array,index,size - index-1);
        }
        size--;
        return removed;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
