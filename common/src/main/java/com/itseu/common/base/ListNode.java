package com.itseu.common.base;

import java.util.Iterator;
import java.util.List;

/*共用节点类*/
public class ListNode implements Iterable<Integer> {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }
    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public Iterator<Integer> iterator() {
        return  new Iterator<Integer>() {
            ListNode node = ListNode.this;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Integer next() {
                int val = node.value;
                node = node.next;
                return val;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Integer i : this) {
            sb.append(i.toString()).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }
}
