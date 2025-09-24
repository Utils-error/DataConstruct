package com.itseu.common.impl;


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
    private final int capacity = 10;
    // 实际存储数据的数组
    private int[] array;

    public DynamicArray() {
        array = new int[capacity];
    }

    /*在最后插入数据*/
    public void addLast(Integer element) {
        add(size, element);
    }

    /*在指定位置处插入数据*/
    public void add(int index, Integer element) {
        if (size >= capacity) {
            log.info("动态数组已达最大容量");
            return;
        }
        if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
    }
    /*foreach循环遍历*/
    public void foreach(Consumer<Integer> action) {
        for(int i = 0; i < size; i++) {
            action.accept(array[i]);
        }
    }
    @Override
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
    
    public IntStream stream() {
        return IntStream.of(Arrays.copyOf(array, size));
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
