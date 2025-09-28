package com.itseu.common.impl;

import com.itseu.common.base.ListNode;

import java.sql.SQLOutput;
import java.util.List;

/*递归方法应用*/
public class RecursionMethodUtils {

    /*反向打印字符串*/
    public static void reversePrint(String str,int index){
        if(index == str.length()){
            return;
        }
        reversePrint(str,index+1);
        System.out.print(str.charAt(index));
    }

    /*实现二分查找*/
    public static int binarySearch(int[] arr,int left,int right,int target){
        if(left > right){
            return -1;
        }
        int mid = (left +right) >>1;
        if(arr[mid] == target){
            return mid;
        }
        if(arr[mid] > target){
            return binarySearch(arr,left,mid-1,target);
        } else {
            return binarySearch(arr,mid+1,right,target);
        }
    }

    /*冒泡排序：将数组划分成两个部分，左为未排序，右为已排序，未排序区间相邻元素进行
    * 比较，如果非升序则交换*/
    public static void bubbleSort(int[] arr,int right){
        if( right == 0){
            return;
        }
        int count =0 ;
        for(int i = 0;i<right;i++){
            if(arr[i]>arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                count= i;
            }
        }
        bubbleSort(arr,count);
    }

    /*实现插入排序：左右两部分，左边为有序，遍历右侧数组将数放在左侧合适位置
    * */
    public static void insertionSort(int[] arr,int left){
        insertionSort(arr,0,arr.length,left);
    }

    /*指定区间做插入排序,左闭右开*/
    public static void insertionSort(int[] arr,int left,int right,int curr){
        if(curr >= right){
            return;
        }
        int added = arr[curr];
        // 1 4 2
        for(int i = left;i<curr;i++){
            if(arr[i] > added){
                System.arraycopy(arr,i,arr,i+1,curr-i);
                arr[i] = added;
                break;
            }
        }
        insertionSort(arr,left,right,curr+1);
    }

    /*斐波那契数列递归实现*/
    public static int fibonacci(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    /*递归求阶乘*/
    public static int factorial(int n){
        if(n==0){
            return 1;
        }
        return n*factorial(n-1);
    }

    /**
     *     汉诺塔
     *     * 最小子问题：有2个圆盘，第一步：将[1]上方圆盘1移动到[2]上，
     *     * 第二步：将[1]上圆盘2动到到[3]上
     *     * 第三步：将[2]上圆盘1移动到[3]上
     *     *
     *     * 后续多个盘子的问题解决可以分为三步走：
     *     * 第一步将[1]上n-1个盘子移动到[2]
     *     * 第二步将[1]上1个盘子移动到[3]
     *     * 第三步重复上述步骤，将[2]上n-2个盘子移动到[1],1个盘子移动到[3]
     *     *
     * @param count 待移动的盘子数
     * @param from from
     * @param helper   helper
     * @param to to
     */
    public static void hanoiTower(int count, List<Integer> from, List<Integer> helper, List<Integer> to){
        if(count==0){
            return;
        }
        hanoiTower(count-1,from,to,helper);
        to.addLast(from.removeLast());
        // System.out.print("\t"+from+"\t");
        // System.out.print("\t"+helper+"\t");
        // System.out.println("\t"+to+"\t");
        // System.out.println("-------------------------");
        hanoiTower(count-1,helper,from,to);
    }

    /*杨辉三角*/
    public static int triElement(int i,int j){
        if( i == j || j == 0){
            return 1;
        }
        return triElement(i-1,j)+triElement(i-1,j-1);
    }
    private static void printSpace(int i,int n){
        for(int j=0;j<(n-i-1)*2;j++){
            System.out.print(" ");
        }
    }
    public static void printTri(int n){
        for(int i = 0;i<n;i++){
            printSpace(i,n);
            for(int j = 0;j<=i;j++){
                System.out.print(String.format("%4d",triElement(i,j)));
            }
            System.out.println();
        }
    }

    /*反转链表：递归实现*/
    public static ListNode reverseList(ListNode head){
        if( head == null || head.next == null){
            return head;
        }
        ListNode node = head.next;
        ListNode reverse = reverseList(head.next);
        node.next = head;
        head.next = null;
        return reverse;
    }
    /*反转链表：通过新旧两个指针，在原地操作，不断将旧的指针移动到新指针上*/
    public static ListNode reverseList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode oldHead = head,newHead = head;
        while(oldHead.next!= null){
            // 1.将旧链表第二个节点拿出来
            ListNode next = oldHead.next;
            oldHead.next = oldHead.next.next;
            // 2.将其连接到链表头部
            next.next = newHead;
            // 3.移动新的头节点指针
            newHead = next;
        }
        return newHead;
    }

    /*反转链表：通过两个指针，不断将旧指针的头部节点移动到新指针的头部节点*/
    public static ListNode reverseList3(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode oldHead = head,newHead = null;
        while(oldHead != null){
            // 1.保存旧指针下一个头结点
             ListNode temp = oldHead.next;
             // 2.从旧移动到新
             oldHead.next = newHead;
             // 3. 复位新指针
             newHead = oldHead;
             // 4.复位旧指针
             oldHead = temp;
        }
        return newHead;
    }




}
