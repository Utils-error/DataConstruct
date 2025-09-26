package com.itseu.common.impl;

import lombok.Data;

import java.util.Iterator;
import java.util.function.Consumer;

/*双向链表实现*/
public class DoublyLinkedList {
    private Node head ;
    private Node tail ;

    /*节点定义*/
    @Data
    private class Node {
        private int val;
        private Node next;
        private Node prev;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public DoublyLinkedList() {
        head = new Node(512,null,null);
        tail = new Node(706,null,null);
        head.next = tail;
        tail.prev = head;
    }

    /*根据索引找到对应节点*/
    private Node findNode(int index) {
        Node left = head;
        int i = -1;
        while (left != tail) {
            if(i == index) {
                return left;
            }
            left = left.next;
            i++;
        }
        return null;
    }

    /*尾部插入添加节点*/
    public void addLast(int val) {
        Node prevNode = tail.prev;
        Node newNode = new Node(val,tail,prevNode);
        prevNode.next = newNode;
        tail.prev = newNode;
    }

    /*头部插入节点*/
    public void addFirst(int val) {
        insert(0,val);
    }


    /*正向遍历链表*/
    public void forwardTraversal(Consumer<Integer> consumer) {
        for(Node curr = head.next; curr != tail; curr = curr.next) {
            consumer.accept(curr.val);
        }
        System.out.println();
    }

    /*逆向遍历链表*/
    public void reverseTraversal(Consumer<Integer> consumer) {
        for(Node curr = tail.prev; curr != head; curr = curr.prev) {
            consumer.accept(curr.val);
        }
        System.out.println();
    }


    /*根据索引找节点的值*/
    public int get(int index) {
        return findNode(index).val;
    }

    /*在指定索引位置插入节点*/
    public void insert(int index, int val) {
        Node prevNode = findNode(index-1);
        if(prevNode == null) {
            throw new IllegalArgumentException(String.format("Index [%d] 不合法%n", index));
        }
        Node nextNode = prevNode.next;
        Node newNode = new Node(val,nextNode,prevNode);
        prevNode.next = newNode;
        nextNode.prev = newNode;
    }

    /*删除头节点*/
    public void removeFirst() {
        remove(0);
    }
    /*删除末尾节点*/
    public void removeLast() {
        Node removed = tail.prev;
        if(removed == head) {
            throw new IllegalArgumentException(String.format("Index [%d] 不合法%n", 0));
        }
        Node prevNode = removed.prev;
        prevNode.next = tail;
        tail.prev = prevNode;
    }

    /*删除指定索引位置节点*/
    public void remove(int index){
        Node prevNode = findNode(index-1);
        if(prevNode == null || prevNode.next == tail) {
            throw new IllegalArgumentException(String.format("Index [%d] 不合法%n", index));
        }
        Node removed = prevNode.next;
        prevNode.next = removed.next;
        removed.next.prev =  prevNode;
        removed.next = null;
        removed.prev = null;


    }

}
