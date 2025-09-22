package com.itseu.test;

import com.itseu.common.binary.impl.BinarySearchUtils;
import org.junit.jupiter.api.DisplayName;

import org.junit.Test;
import org.junit.Assert;
public class ApplicationTest {

    private static BinarySearchUtils binarySearchUtils = new BinarySearchUtils();

    @Test
    @DisplayName("Binary Search找到了")
    public void test1(){
        int[] arr={1,2,3,4,4,5,6,7};
        Assert.assertEquals(0, binarySearchUtils.search(arr,1));
        Assert.assertEquals(2, binarySearchUtils.search(arr,3));
        Assert.assertEquals(4, binarySearchUtils.searchV3(arr,4));
        Assert.assertEquals(3, binarySearchUtils.searchLeftMost(arr,4));
        Assert.assertEquals(4, binarySearchUtils.searchRightMost(arr,4));

    }
    @Test
    @DisplayName("Binary Search没找到")
    public void test2(){
        int[] arr={1,2,3,4,5,6,7};
        Assert.assertEquals(-1, binarySearchUtils.search(arr,10));
        Assert.assertEquals(-1, binarySearchUtils.search(arr,30));
    }
}
