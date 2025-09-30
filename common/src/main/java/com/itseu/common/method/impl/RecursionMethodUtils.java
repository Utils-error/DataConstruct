package com.itseu.common.method.impl;

import com.itseu.common.base.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*递归方法应用*/
public class RecursionMethodUtils {

    /*反向打印字符串*/
    public static void reversePrint(String str, int index) {
        if (index == str.length()) {
            return;
        }
        reversePrint(str, index + 1);
        System.out.print(str.charAt(index));
    }

    /*实现二分查找*/
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return binarySearch(arr, left, mid - 1, target);
        } else {
            return binarySearch(arr, mid + 1, right, target);
        }
    }

    /*冒泡排序：将数组划分成两个部分，左为未排序，右为已排序，未排序区间相邻元素进行
     * 比较，如果非升序则交换*/
    public static void bubbleSort(int[] arr, int right) {
        if (right == 0) {
            return;
        }
        int count = 0;
        for (int i = 0; i < right; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                count = i;
            }
        }
        bubbleSort(arr, count);
    }

    /*实现插入排序：左右两部分，左边为有序，遍历右侧数组将数放在左侧合适位置
     * */
    public static void insertionSort(int[] arr, int left) {
        insertionSort(arr, 0, arr.length, left);
    }

    /*指定区间做插入排序,左闭右开*/
    public static void insertionSort(int[] arr, int left, int right, int curr) {
        if (curr >= right) {
            return;
        }
        int added = arr[curr];
        // 1 4 2
        for (int i = left; i < curr; i++) {
            if (arr[i] > added) {
                System.arraycopy(arr, i, arr, i + 1, curr - i);
                arr[i] = added;
                break;
            }
        }
        insertionSort(arr, left, right, curr + 1);
    }

    /*斐波那契数列递归实现*/
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /*递归求阶乘*/
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 汉诺塔
     * * 最小子问题：有2个圆盘，第一步：将[1]上方圆盘1移动到[2]上，
     * * 第二步：将[1]上圆盘2动到到[3]上
     * * 第三步：将[2]上圆盘1移动到[3]上
     * *
     * * 后续多个盘子的问题解决可以分为三步走：
     * * 第一步将[1]上n-1个盘子移动到[2]
     * * 第二步将[1]上1个盘子移动到[3]
     * * 第三步重复上述步骤，将[2]上n-2个盘子移动到[1],1个盘子移动到[3]
     * *
     *
     * @param count  待移动的盘子数
     * @param from   from
     * @param helper helper
     * @param to     to
     */
    public static void hanoiTower(int count, List<Integer> from, List<Integer> helper, List<Integer> to) {
        if (count == 0) {
            return;
        }
        hanoiTower(count - 1, from, to, helper);
        to.addLast(from.removeLast());
        // System.out.print("\t"+from+"\t");
        // System.out.print("\t"+helper+"\t");
        // System.out.println("\t"+to+"\t");
        // System.out.println("-------------------------");
        hanoiTower(count - 1, helper, from, to);
    }

    /*杨辉三角*/
    public static int triElement(int i, int j) {
        if (i == j || j == 0) {
            return 1;
        }
        return triElement(i - 1, j) + triElement(i - 1, j - 1);
    }

    private static void printSpace(int i, int n) {
        for (int j = 0; j < (n - i - 1) * 2; j++) {
            System.out.print(" ");
        }
    }

    public static void printTri(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(i, n);
            for (int j = 0; j <= i; j++) {
                System.out.print(String.format("%4d", triElement(i, j)));
            }
            System.out.println();
        }
    }

    /*反转链表：递归实现*/
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        ListNode reverse = reverseList(head.next);
        node.next = head;
        head.next = null;
        return reverse;
    }

    /*反转链表：通过新旧两个指针，在原地操作，不断将旧的指针移动到新指针上*/
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oldHead = head, newHead = head;
        while (oldHead.next != null) {
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
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oldHead = head, newHead = null;
        while (oldHead != null) {
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

    /*根据值删除链表节点*/
    public static ListNode removeByValue(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        // 如果相等，不要当前值，直接返回下一个递归值
        if (head.value == val) {
            return removeByValue(head.next, val);
        } else {
            // 如果不等，则保留当前值，但其下个节点应该下一个递归值而后返回
            head.next = removeByValue(head.next, val);
            return head;
        }
    }

    /*【法一】删除链表的倒数第n个节点:O(n)遍历*/
    public static ListNode removeLastN(ListNode head, int n) {
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;

        }
        if (count - n < 0 || n < 0) {
            throw new NoSuchElementException("参数非法");
        }
        ListNode dummy = new ListNode(0, head);
        int index = 0;
        ListNode prev = dummy;
        curr = head;
        while (curr != null) {
            if (index == (count - n)) {
                prev.next = curr.next;
            }
            prev = curr;
            curr = curr.next;
            index++;
        }
        return dummy.next;
    }

    /*【法二】删除链表的倒数第n个节点:递归*/
    public static ListNode removeLastN2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        recursionLastN2(dummy, n);
        return dummy.next;
    }

    /*删除链表的倒数第n个节点:递归具体实现*/
    private static int recursionLastN2(ListNode head, int n) {
        if (head == null) {
            return 0;
        }

        int res = recursionLastN2(head.next, n);
        if (res == n) {
            head.next = head.next.next;
        }
        return res + 1;
    }

    /*【法三】删除链表的倒数第n个节点:快慢指针*/
    public static ListNode removeLastN3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /*删除有序链表的重复节点:遍历*/
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            ListNode next = curr.next;
            if (curr.value == prev.value) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = next;
        }
        return dummy.next;
    }

    /*删除有序链表的重复节点:递归*/
    public static ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        recursionDeleteDplicates3(dummy);
        return dummy.next;
    }

    /*递归具体实现1*/
    private static int recursionDeleteuDplicates2(ListNode head) {
        if (head == null) {
            return Integer.MAX_VALUE;
        }
        // 下一个节点的值
        int res = recursionDeleteuDplicates2(head.next);
        if (res == head.value) {
            head.next = head.next.next;
        }
        return head.value;
    }

    /*递归具体实现2*/
    private static ListNode recursionDeleteDplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 下一个节点的值
        if (head.value == head.next.value) {
            return recursionDeleteDplicates3(head.next);
        } else {
            head.next = recursionDeleteDplicates3(head.next);
            return head;
        }
    }

    /*删除有序链表的重复节点(不保留)：遍历实现*/
    public static ListNode removeAllDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = dummy.next;
        while (curr != null) {
            ListNode next = curr.next;
            if (next != null && curr.value == next.value) {
                int val = curr.value;
                next = curr.next;
                while (next != null && val == next.value) {
                    next = next.next;
                }
                prev.next = next;
            } else {
                prev = curr;
            }
            curr = next;
        }
        return dummy.next;
    }

    /*删除有序链表的重复节点(不保留)：递归方法*/
    public static ListNode removeAllDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.value == head.next.value) {
            ListNode node = head;
            int val = head.value;
            while (node != null && val == node.value) {
                node = node.next;
            }
            return removeAllDuplicates2(node);
        } else {
            head.next = removeAllDuplicates2(head.next);
            return head;
        }
    }

    /*合并两个有序链表：模拟*/
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode dummy = new ListNode(0, null);
        ListNode curr = dummy;
        while (p1 != null && p2 != null) {
            if (p1.value < p2.value) {
                curr.next = p1;
                p1 = p1.next;
            } else {
                curr.next = p2;
                p2 = p2.next;
            }
            curr = curr.next;
        }
        if (p1 == null) {
            curr.next = p2;
        } else {
            curr.next = p1;
        }
        return dummy.next;
    }

    /*合并两个有序链表：递归*/
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.value > l2.value) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }
    }

    /*合并多个有序链表：递归*/
    public static ListNode mergeKList(ListNode[] lists) {
        return recursionMergeKList(lists, 0, lists.length - 1);
    }

    /*合并多个有序链表：递归具体实现*/
    private static ListNode recursionMergeKList(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = (start + end) >> 1;
        ListNode node1 = recursionMergeKList(lists, start, mid);
        ListNode node2 = recursionMergeKList(lists, mid + 1, end);
        ListNode head = mergeTwoLists2(node1, node2);
        return head;
    }

    /*查找链表中间节点*/
    public static int findMid(ListNode head) {
        if (head.next == null) {
            return head.value;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    /*判断回文链表:遍历*/
    public static boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /*判断回文链表:折中+反转*/
    public static boolean isPalindrome2(ListNode head) {
        if (head.next == null) {
            return true;
        }
        // 找到中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow为中点
        ListNode node = reverseList(slow);
        while (node != null) {
            if (node.value != head.value) {
                return false;
            }
            node = node.next;
            head = head.next;
        }
        return true;

    }

    /*判断回文链表:折中+反转优化版本*/
    public static boolean isPalindrome3(ListNode head) {
        if (head.next == null) {
            return true;
        }
        // 快慢指针遍历的同时，完成前半链表的反转
        ListNode slow = head;
        ListNode fast = head;
        ListNode oldHead = head;
        ListNode newHead = null;
        while (fast != null && fast.next != null) {
            // 快慢指针遍历
            slow = slow.next;
            fast = fast.next.next;
            // 反转前半部分指针
            oldHead.next = newHead;
            newHead = oldHead;
            oldHead = slow;

        }
        if (fast != null) {
            slow = slow.next;
        }
        // slow为中点
        System.out.println(slow);
        System.out.println(newHead);
        while (newHead != null) {
            if (newHead.value != slow.value) {
                return false;
            }
            newHead = newHead.next;
            slow = slow.next;
        }
        return true;
    }

    /*判断环形链表,有则检验入口：龟兔赛跑*/
    public static ListNode isCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                slow = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /*合并两个有序数组,特别说明，是将b合并到a中，其中a已经在末尾预留了位置*/
    public static void mergeTwoArrays(int[] a, int[] b) {
        int bIndex = 0,aIndex = 0;
        int m = a.length;
        int n = b.length;
       while (bIndex < n){
           while (aIndex < m){

           }
       }
    }
}
