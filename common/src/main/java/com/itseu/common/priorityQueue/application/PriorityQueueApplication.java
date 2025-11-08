package com.itseu.common.priorityQueue.application;

import com.itseu.common.base.ListNode;
import com.itseu.common.priorityQueue.MinHeap;

import java.util.List;

/*优先队列具体应用*/
public class PriorityQueueApplication {

    /*实现K个有序链表合并
    * 1.创建最小堆，容量为链表数
    * 2.将各链表首节点加入堆中
    * 3.不断移出堆顶（最小元素）加入结果链表中
    * 4.每移除一个链表，就将该链表的next节点加入，直到堆位空
    * */
    public static ListNode kMerge(ListNode[] list){
        MinHeap minHeap = new MinHeap(list.length);
        for(ListNode node : list){
            if(node != null){
                minHeap.offer(node);
            }
        }
        ListNode dummy = new ListNode(0,null);
        ListNode curr = dummy;
        while(!minHeap.isEmpty()){
            ListNode min = minHeap.poll();
            curr.next = min;
            curr = min;
            if(min.next != null){
                minHeap.offer(min.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] list = {
                ListNode.of(1,4,25),
                ListNode.of(2,6,14),
                ListNode.of(4,8,15),
        };
        System.out.println(kMerge(list));
    }
}
