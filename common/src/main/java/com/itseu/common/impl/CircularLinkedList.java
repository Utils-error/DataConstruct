package com.itseu.common.impl;

import lombok.Data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*环形链表实现*/
public class CircularLinkedList implements Iterable<Integer> {

    private Node sentinel = new Node(10);

    /*节点定义*/
    @Data
    private static class Node {
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

    public CircularLinkedList() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /*添加头结点*/
    public void addFisrt(int val) {
        Node next = sentinel.next;
        Node added = new Node(val,next,sentinel);
        sentinel.next = added;
        next.prev = added;
    }

    /*添加尾结点*/
    public void addLast(int val) {
        Node last = sentinel.prev;
        Node added = new Node(val,sentinel,last);
        sentinel.prev = added;
        last.next = added;
    }

    /*删除头节点*/
    public void removeFisrt() {
        Node removed = sentinel.next;
        if(removed == sentinel) {
            throw new NoSuchElementException("链表为空，无法删除");
        };
        Node next = removed.next;
        sentinel.next = next;
        next.prev = sentinel;
    }

    /*删除尾结点*/
    public void removeLast() {
        Node removed = sentinel.prev;
        if(removed == sentinel) {
            throw new NoSuchElementException("链表为空，无法删除");
        }
        Node prev = removed.prev;
        sentinel.prev = prev;
        prev.next = sentinel;
    }

    /*依据值删除*/
    public void remove(int val) {
        Node removed = findNode(val);
        if(removed == null) {
            throw new NoSuchElementException(String.format("Node = [%d] not found%n",val));
        }
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;

    }
    /*依据值查找节点*/
    private Node findNode(int val){
        Node node = sentinel.next;
        while (node != sentinel){
            if(node.val == val){
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /*迭代器遍历*/
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node node = sentinel.next;
            @Override
            public boolean hasNext() {
                return node != sentinel;
            }

            @Override
            public Integer next() {
                int val = node.val;
                node = node.next;
                return val;
            }
        };
    }

    /*递归遍历*/
    public void print() {
        recursion(sentinel.next);
    }

    private void recursion(Node node) {
        if(node== sentinel) {
            return;
        }
        System.out.println(node.val);
        recursion(node.next);
    }
}
