package com.itseu.test;

import com.itseu.common.impl.BinarySearchImpl;
import com.itseu.common.impl.DynamicArray;
import com.itseu.common.impl.SingleLinkedList;
import org.junit.jupiter.api.DisplayName;

import org.junit.Test;
import org.junit.Assert;


public class ApplicationTest {

    private static BinarySearchImpl binarySearchUtils = new BinarySearchImpl();

    @Test
    @DisplayName("Binary Search找到了")
    public void test1() {
        int[] arr = {1, 2, 3, 4, 4, 5, 6, 7};
        Assert.assertEquals(0, binarySearchUtils.search(arr, 1));
        Assert.assertEquals(2, binarySearchUtils.search(arr, 3));
        Assert.assertEquals(4, binarySearchUtils.searchV3(arr, 4));
        Assert.assertEquals(3, binarySearchUtils.searchLeftMost(arr, 4));
        Assert.assertEquals(4, binarySearchUtils.searchRightMost(arr, 4));

    }

    @Test
    @DisplayName("Binary Search没找到")
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Assert.assertEquals(-1, binarySearchUtils.search(arr, 10));
        Assert.assertEquals(-1, binarySearchUtils.search(arr, 30));
    }

    @Test
    @DisplayName("插入点测试")
    public void test3() {
        int[] arr = {1, 2, 4, 4, 4, 7, 7};
        Assert.assertEquals(2, binarySearchUtils.searchLeftMostV2(arr, 4));
        Assert.assertEquals(6, binarySearchUtils.searchLeftMostV2(arr, 5) + 1);
        Assert.assertEquals(2, binarySearchUtils.searchRightMostV2(arr, 2));
        Assert.assertEquals(6, binarySearchUtils.searchRightMostV2(arr, 4) + 1);
    }

    @Test
    @DisplayName("数组方法测试")
    public void test4() {

        DynamicArray array = new DynamicArray();
        System.out.println(array.toString());
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addLast(8);
        array.add(6, 10);
        array.add(2, 12);
        array.add(3, 14);

        array.foreach(ele -> System.out.print(ele + " "));
        System.out.println();
        for (Object ele : array) {
            System.out.print(ele + " ");
        }
        System.out.println();
        array.stream().forEach(ele -> System.out.print(ele + " "));
        array.remove(1);
        System.out.println();
        array.stream().forEach(ele -> System.out.print(ele + " "));

    }

    @Test
    @DisplayName("链表方法测试")
    public void test5() {
        SingleLinkedList list = new SingleLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addFirst(3);
        list.loop1(val -> System.out.print(val + " "));
        System.out.println();

        list.insert(0,4);
        list.insert(4,5);
        list.loop1(val -> System.out.print(val + " "));

        System.out.println();

        list.remove(0);
        list.loop1(val -> System.out.print(val + " "));
        System.out.println();
        list.remove(3);
        list.loop1(val -> System.out.print(val + " "));
        System.out.println();
    }
}
