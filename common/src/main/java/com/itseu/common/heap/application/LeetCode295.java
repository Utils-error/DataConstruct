package com.itseu.common.heap.application;

import com.itseu.common.heap.MaxHeap;
import com.itseu.common.heap.MinHeap;

import java.util.Arrays;

/*编写类以实现数据流中位数的获取*/
public class LeetCode295 {

    /*基本思路：
     * 1.维护两个堆，一个最大堆，一个最小堆，最大堆存储前半数据的，最小堆存储着后半数据
     * 2. 每当有新数据来时，遵循以下规则：
     *   如果两个堆数据大小相同，则加入到最大堆
     *   如果最大堆数据更多，则加入到最小堆，
     * 当然，这里加入数据依然要保持前半和后半的特性，
     * 最大堆增加的数据来源应该是最小堆的堆顶，
     * 最小堆的数据来源应该是最大堆的堆顶
     * */

    MaxHeap maxHeap = new MaxHeap(10);
    MinHeap minHeap = new MinHeap(10);

    public void offer(int val) {
      if(maxHeap.getSize()==minHeap.getSize()){
          minHeap.offer(val);
          maxHeap.offer(minHeap.poll());
      } else {
          maxHeap.offer(val);
          minHeap.offer(maxHeap.poll());
      }
    }

    public int getMedian() {
        if (maxHeap.getSize() == minHeap.getSize()) {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     * @apiNote In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * The string output is not necessarily stable over time or across
     * JVM invocations.
     * @implSpec The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     */
    @Override
    public String toString() {
        return maxHeap.toString()+"<->"+minHeap.toString();
    }


    public static void main(String[] args) {
        LeetCode295 leetCode295 = new LeetCode295();
        for(int i = 1; i <= 10; i++){
            leetCode295.offer(i);
            System.out.println(leetCode295);
        }
    }
}
