package com.itseu.common.blockQueue;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*线程不安全测试
 * 保证线程安全的单锁实现：
 * 1.synchronized 关键字，底层实现，功能相对单一
 * 2.ReentrantLock 工具类(java实现的框架），功能相对丰富
 * */
public class TestThreadUnsafe {

    private final String[] arr = new String[10];
    private int tail = 0;
    private int size = 0;
    ReentrantLock lock = new ReentrantLock();  // 创建锁对象
    Condition condition = lock.newCondition();  // 条件变量用于控制线程

    public void offer(String e) throws InterruptedException {

        lock.lockInterruptibly();   // 手动加锁
        try {
            while (isFull()) {
                System.out.println("FULL");
                // 当存满时，需要将线程阻塞
                condition.await();
            }
            arr[tail] = e;
            if (++tail == arr.length) {
                tail = 0;
            }
            size++;
        } finally {
            lock.unlock(); // 手动释放锁
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }


    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadUnsafe tt = new TestThreadUnsafe();
        for (int i = 0; i < 10; i++) {
            tt.offer("a" + i);
        }
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"Before");
                tt.offer("a11");
                System.out.println(Thread.currentThread().getName()+"After");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1").start();
        new Thread(() -> {
            System.out.println("开始唤醒");
            try {
                tt.lock.lockInterruptibly();
                tt.condition.signal();
            } catch (Exception e){

            } finally {
                tt.lock.unlock();
            }
        },"t2").start();
    }
}

