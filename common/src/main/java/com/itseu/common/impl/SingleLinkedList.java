package com.itseu.common.impl;

import lombok.Data;
import lombok.val;

import java.util.Iterator;
import java.util.function.Consumer;

/*单向链表实现*/
@Data
public class SingleLinkedList implements Iterable<Integer> {

    private Node head = new Node(512,null);

    /*节点定义*/
    @Data
    private class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /*添加节点*/
    public void addLast(int val) {
        findLast().next = new Node(val);

    }

    /*头插*/
    public void addFirst(int val) {
        insert(0, val);
    }

    /*循环遍历链表*/
    public void loop1(Consumer<Integer> consumer) {
        Node curr = head.next;
        while (curr != null) {
            consumer.accept(curr.val);
            curr = curr.next;
        }
    }

    /*for循环遍历链表*/
    public void loop2(Consumer<Integer> consumer) {
        for (Node curr = head.next; curr != null; curr = curr.next) {
            consumer.accept(curr.val);
        }
    }

    /*迭代器遍历链表*/
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node curr = head.next;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Integer next() {
                int val = curr.val;
                curr = curr.next;
                return val;
            }
        };
    }

    /*找到末尾tail*/
    private Node findLast() {
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        return curr;
    }

    /*根据索引找到对应节点*/
    private Node findNode(int index) {
        int in = -1;
        for (Node curr = head; curr != null; curr = curr.next) {
            if (in == index) {
                return curr;
            }
            in++;
        }
        return null;

    }

    /*根据索引找节点的值*/
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw new IllegalArgumentException(String.format("Index [%d] 不合法%n", index));
        }
        return node.val;
    }

    /*在指定索引位置插入节点*/
    public void insert(int index, int val) {
        Node prev = findNode(index - 1);
        if(prev == null){
            throw new IllegalArgumentException(String.format("Index [%d] 不合法%n", index));
        }
        prev.next = new Node(val, prev.next);
    }

    /*删除头节点*/
    public void removeFirst() {
        remove(0);
    }

    /*删除指定索引位置节点*/
    public void remove(int index){
        Node prev = findNode(index - 1);
        if(prev == null || prev.next == null){
            throw new IllegalArgumentException(String.format("Index [%d] 不合法%n", index));
        }
        prev.next = prev.next.next;
    }
}
