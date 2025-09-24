package com.itseu.common.impl;

import com.itseu.common.BinarySearch;

public class BinarySearchImpl implements BinarySearch {
    /*  二分查找（左闭右闭）
        相关疑问
        1.while循环条件：为什么是left <= right？
        A：因为需要比较左右两个端点的元素，如果仅仅使用<，则无法比较左右端点，根据具体情况使用
        2.中间索引计算，int mid = (left + right) / 2 是否存在问题？
        A: 当left和right均比较大时，可能会导致超出java所表示的int正数上限，会自动将最高位看为符号位，即负数，也就
        会最终影响二分查找结果， 因此这里推荐使用>>>1右移运算符，或者是i + (j-i)+1的形式，避免大数相加。
    * */
    public int search(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /*  二分查找 改进版本，改动之处均已经标出（左闭右开）
    change1: 相比与版本1，右指针指向了一个并不存在实际数组中的边界，也就意味着它
    并不能参与到比较当中，后续的change也是基于此去做改动。
    change2： 既然右指针不能被比较，那么自然也就不能写等于。
    change3： 每次进入循环后，都会进行mid指针的比较，因此当不匹配时，右指针无须再为mid+1，因为
    mid既然无法匹配，那么自然也就符合我们对于right右指针的定义！

    一些反例：自己可以思考一下
* */
    public int searchV2(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n;   // change1
        while (left < right) {         // change2
            int mid = (left + right) >>1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid; // change3
            } else {
                return mid;
            }
        }
        return -1;
    }

    /* (平衡算法版）
    * 在V2版本中，每一次进入循环都需要判断两次，会导致查找最左端值与最右端值的失衡，
    * 在改动1中，循环退出的边界为待查找的范围内仅剩一个元素，退出循环后，判断当前值是否
    * 为目标值，即改动3。此外，当目前值 不位于mid左侧,由于无法是否等于，因此left=mid，
    * 而不是mid+1
    * */
    public int searchV3(int[] arr, int target){
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n;
        while(right - left > 1){                //改动1
            int mid = (left + right) >>> 1;
            if(arr[mid] > target){
                right = mid;
            } else {
                left = mid;                     //改动2
            }
        }
        if(arr[left] == target){               //改动3
            return left;
        }else {
            return -1;
        }
    }

    /*
    * 当数组中重复元素时，希望返回重复元素的第一个索引。
    * 主要改动点为 当找到目前元素后，不直接返回，而是作为记录，
    * 并继续向左找，直到找到第一个索引，进行返回即可
    *
    * */
    public int searchLeftMost(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                candidate = mid;             // 改动1
                right = mid - 1;
            }
        }
        return candidate;
    }

    /*
     * 当数组中重复元素时，希望返回重复元素的最后一个索引。
     * 主要改动点为 当找到目前元素后，不直接返回，而是作为记录，
     * 并继续向右查找，直到找到第一个索引，进行返回即可
     *
     * */
    public int searchRightMost(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                candidate = mid;             // 改动1
                left = mid + 1;
            }
        }
        return candidate;
    }

    /*
     * 当数组中重复元素时，希望返回重复元素的第一个索引,如果没有命中，则返回
     * 插入点，如果是重复元素，则最左插入点。
     * 主要改动点为 当找到目前元素后，不直接返回，而是作为记录，
     * 并继续向左找，直到找到第一个索引，进行返回即可
     *
     * */
    public int searchLeftMostV2(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else{
                right = mid-1;
            }
        }
        return left;        // 改动1
    }

    /*
     * 当数组中重复元素时，希望返回重复元素的最后一个索引。如果没有命中，则返回
     * 插入点，如果是重复元素，则最右插入点。
     * 主要改动点为 当找到目前元素后，不直接返回，而是作为记录，
     * 并继续向右查找，直到找到第一个索引，进行返回即可
     *
     * */
    public int searchRightMostV2(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) {
            return arr[0] == target ? 0 : -1;
        }
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else  {
                right = mid - 1;
            }
        }
        return left;               // 改动1
    }
}

