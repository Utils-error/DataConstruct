package com.itseu.test;



import com.itseu.common.array.DynamicArray;
import com.itseu.common.base.ListNode;
import com.itseu.common.blockQueue.impl.BlockingQueue1;
import com.itseu.common.blockQueue.impl.BlockingQueue2;
import com.itseu.common.deque.impl.ArrayDeque1;
import com.itseu.common.deque.impl.LinkedListDeque;
import com.itseu.common.linkedlist.CircularLinkedList;
import com.itseu.common.linkedlist.DoublyLinkedList;
import com.itseu.common.linkedlist.SingleLinkedList;
import com.itseu.common.method.impl.BinarySearchImpl;
import com.itseu.common.method.impl.RecursionMethodUtils;
import com.itseu.common.method.impl.SortMethodUtils;
import com.itseu.common.priorityQueue.PriorityQueue1;
import com.itseu.common.priorityQueue.PriorityQueue2;
import com.itseu.common.priorityQueue.PriorityQueue3;
import com.itseu.common.priorityQueue.Student;
import com.itseu.common.queue.impl.ArrayQueue3;
import com.itseu.common.queue.impl.LinkedListQueue;
import com.itseu.common.queue.impl.TwoStackQueue;
import com.itseu.common.stack.impl.ArrayStack;
import com.itseu.common.stack.impl.LinkedListStack;
import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;


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
        list.addLast(3);
        list.addLast(3);
        list.addFirst(3);
        list.addLast(3);
        list.addLast(30);
        list.loop1(val -> System.out.print(val+" "));
        System.out.println();
        list.removeByValue(3);
        list.loop1(val -> System.out.print(val+" "));
        System.out.println();

    }

    @Test
    @DisplayName("双向链表方法测试")
    public void test6() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addLast(1);
        list.forwardTraversal(val -> System.out.print(val + " "));
        list.reverseTraversal(val -> System.out.print(val + " "));
        list.addFirst(2);
        list.forwardTraversal(val -> System.out.print(val + " "));
        list.reverseTraversal(val -> System.out.print(val + " "));
        list.insert(0,3);
        list.forwardTraversal(val -> System.out.print(val + " "));
        list.reverseTraversal(val -> System.out.print(val + " "));

        list.forwardTraversal(val -> System.out.print(val + " "));
        list.reverseTraversal(val -> System.out.print(val + " "));


    }

    @Test
    @DisplayName("循环链表方法测试")
    public void test7() {
        CircularLinkedList list = new CircularLinkedList();
        list.addFisrt(1);
        list.addFisrt(2);
        list.addFisrt(4);
        for(int val : list){
            System.out.print(val + " ");
        }
        System.out.println();

        System.out.println();
        list.print();

    }

    @Test
    @DisplayName("递归方法测试")
    public void test8(){
        // RecursionMethodUtils.reversePrint("abc",0);
        // System.out.println();
        // System.out.println(RecursionMethodUtils.binarySearch(new int[]{1,2,3},0,2,1));
        // int[] arr = {200,100,30,6,34,23,56};
        // RecursionMethodUtils.bubbleSort(arr,arr.length-1);
        // RecursionMethodUtils.insertionSort(arr,1);
        // RecursionMethodUtils.insertionSort(arr,1,arr.length-1,1);
        // System.out.println(Arrays.toString(arr));
        // System.out.println(RecursionMethodUtils.fibonacci(4));
        // System.out.println(RecursionMethodUtils.factorial(4));
        // List<Integer> a =new LinkedList<>();
        // for(int i = 1;i<=10;i++){
        //     a.addFirst(i);
        // }
        // List<Integer> b =new LinkedList<>();
        // List<Integer> c =new LinkedList<>();
        // System.out.print(a + "\t");
        // System.out.print(b+"\t");
        // System.out.println(c+"\t");
        // System.out.println("-------------------------");
        // long start = System.currentTimeMillis();
        // RecursionMethodUtils.hanoiTower(10,a,b,c);
        // long end = System.currentTimeMillis();
        // System.out.println(end-start);
        // RecursionMethodUtils.printTri(10);
        ListNode tail = new ListNode(60,null);
        ListNode node1 = new ListNode(50,tail);
        ListNode node2 = new ListNode(40,tail);
        ListNode node3 = new ListNode(30,node2);
        ListNode node4 = new ListNode(20,node3);
        ListNode head = new ListNode(10,node4);

        ListNode tail1 = new ListNode(44,null);
        ListNode node11 = new ListNode(37,tail1);
        ListNode node21 = new ListNode(35,node11);
        ListNode node31 = new ListNode(34,node21);
        ListNode node41 = new ListNode(25,node31);
        ListNode head1 = new ListNode(12,node41);

        ListNode tail11 = new ListNode(25,null);
        ListNode node111 = new ListNode(18,tail11);
        ListNode node211 = new ListNode(15,node111);
        ListNode node311 = new ListNode(55,node211);
        ListNode node411 = new ListNode(18,node311);
        ListNode head111 = new ListNode(25,node411);

        tail11.next = head111;

        // System.out.println(head111);
        // System.out.println(RecursionMethodUtils.removeLastN3(head,0).toString());
        // System.out.println(RecursionMethodUtils.removeAllDuplicates2(head).toString());
        // System.out.println(RecursionMethodUtils.mergeTwoLists2(head1,head));
        // System.out.println(RecursionMethodUtils.mergeKList(new ListNode[]{head1,head111,head}));
        // System.out.println(RecursionMethodUtils.findMid(head));
        // System.out.println(RecursionMethodUtils.isPalindrome3(head111));
        System.out.println(RecursionMethodUtils.isCycle(head111).value);
    }

    @Test
    @DisplayName("递归合并：合并两个有序(升序）数组")
    public void test9() {
        int[] from = {1,19,22,4,5,21};
        int[] to = new int[from.length];
        SortMethodUtils.merge(from,0,2,3,5,to,0);
        System.out.println(Arrays.toString(to));
        System.out.println(Arrays.toString(to));
    }

    @Test
    @DisplayName("基于链表实现的队列")
    public void test10() {
        LinkedListQueue<Integer> queue = new LinkedListQueue();
        System.out.println(queue.isEmpty());
        for(Integer i  : queue){
            System.out.print(i + " ");
        };
        queue.offer(10);
        queue.offer(2);

        for(Integer i  : queue){
            System.out.print(i + " ");
        }
        System.out.println(queue.poll());
        System.out.println();
        for(Integer i  : queue){
            System.out.print(i + " ");
        }

    }

    @Test
    @DisplayName("基于环形数组实现的队列")
    public void test11() {
        ArrayQueue3<Integer> queue = new ArrayQueue3(3);
        queue.offer(10);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        for(Integer i  : queue){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println();
        for(Integer i  : queue){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Test
    @DisplayName("基于链表实现栈")
    public void test12(){
        LinkedListStack<Integer> stack = new LinkedListStack(3);
        stack.push(10);
        stack.push(4);
        stack.push(3);
        for(int i : stack){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }

    @Test
    @DisplayName("基于链表实现栈")
    public void test13(){
        ArrayStack<Integer> stack = new ArrayStack(3);
        stack.push(10);
        stack.push(4);
        stack.push(3);
        for(int i : stack){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }

    @Test
    @DisplayName("双栈实现队列")
    public void test14(){
        TwoStackQueue queue = new TwoStackQueue(30);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue.peek());
    }

    @Test
    @DisplayName("基于链表的双端队列")
    public void test15(){
        LinkedListDeque<Integer> deque = new LinkedListDeque(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);

        for(int i : deque){
            System.out.print(i + " ");
        }
        // System.out.println();
        // deque.pollFirst();
        // deque.pollLast();
        // System.out.println(deque.peekFirst());
        // System.out.println(deque.peekLast());
        // deque.pollFirst();
        // deque.pollLast();
        // System.out.println(deque.peekFirst());
    }

    @Test
    @DisplayName("基于循环数组的双端队列")
    public void test16(){
        ArrayDeque1<Integer> deque = new ArrayDeque1(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);

        for(int i : deque){
            System.out.print(i + " ");
        }
        System.out.println();
        deque.pollFirst();
        deque.pollLast();
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        deque.pollFirst();
        deque.pollLast();
        System.out.println(deque.peekFirst());
    }

    @Test
    @DisplayName("基于无序数组的优先队列")
    public void test17(){
        PriorityQueue1<Student> priorityQueue1 = new PriorityQueue1<>(10);
        priorityQueue1.offer(new Student("a"));
        priorityQueue1.offer(new Student("b"));
        priorityQueue1.offer(new Student("c"));
        System.out.println(priorityQueue1.poll());
        System.out.println(priorityQueue1.peek());

    }

    @Test
    @DisplayName("基于有序数组的优先队列")
    public void test18(){
        PriorityQueue2<Student> priorityQueue2 = new PriorityQueue2<>(2);
        priorityQueue2.offer(new Student("e"));
        priorityQueue2.offer(new Student("a"));
        priorityQueue2.offer(new Student("d"));

        System.out.println(priorityQueue2.poll());
        System.out.println(priorityQueue2.peek());

    }

    @Test
    @DisplayName("基于数组实现的最大堆")
    public void test19(){
        PriorityQueue3<Student> priorityQueue3 = new PriorityQueue3<>(10);
        priorityQueue3.offer(new Student("a"));
        priorityQueue3.offer(new Student("b"));
        priorityQueue3.offer(new Student("c"));
        priorityQueue3.offer(new Student("d"));
        priorityQueue3.offer(new Student("e"));
        priorityQueue3.print();
        priorityQueue3.poll();
        // System.out.println(priorityQueue3.peek());
        priorityQueue3.print();

    }

    @Test
    @DisplayName("阻塞队列：单锁实现，尾进头出")
    public void test29() throws InterruptedException {
        BlockingQueue1<String> blockingQueue1 = new BlockingQueue1<>(3);

        new Thread(() ->{
            try {
                System.out.println(System.currentTimeMillis()+" begin");
                blockingQueue1.offer("task1");
                System.out.println(blockingQueue1);
                blockingQueue1.offer("task2");
                System.out.println(blockingQueue1);
                blockingQueue1.offer("task3");
                System.out.println(blockingQueue1);
                blockingQueue1.offer("task4",5000L);
                System.out.println(blockingQueue1);
                System.out.println(System.currentTimeMillis()+" end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"producer").start();
        Thread.sleep(2000);
        new Thread(() -> {
            try {
                blockingQueue1.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"consumer").start();
    }


    @Test
    @DisplayName("阻塞队列：双锁实现，头尾各一把锁")
    public void test30() throws InterruptedException {
        // BlockingQueue1<String> queue = new BlockingQueue1<>(3);
        BlockingQueue2<String> queue = new BlockingQueue2<>(3);
        queue.offer("task1");
        new Thread(() ->{
            try {
                queue.offer("task2");
                System.out.println(queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"producer").start();

        new Thread(() -> {
            try {
                queue.poll();
                System.out.println(queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"consumer").start();

        while (true){

        }


    }

}
