package com.itseu.common.blockQueue.impl;

import com.itseu.common.blockQueue.BlockingQueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;

/*阻塞队列：单锁实现，尾进头出*/
public class BlockingQueue1<E> implements BlockingQueue<E> {

    private final E[] elements;
    private int head;
    private int tail;
    private int size;

    private Lock lock = new ReentrantLock();
    // 可以理解为一个集合
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    public BlockingQueue1(int capacity) {
        elements = (E[]) new Object[capacity];
    }

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                // 如果已满，则阻塞
                tailWaits.await();
            }
            elements[tail++] = e;
            if (tail == elements.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);

            while (isFull()) {
                if (t <= 0) {
                    return false;
                }
                // 如果已满，则阻塞
                // 返回值的含义就是剩余等待时间
                t = tailWaits.awaitNanos(t);
            }
            elements[tail++] = e;
            if (tail == elements.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
            return true;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            E removed = elements[head];
            elements[head] = null;
            head++;
            if (head == elements.length) {
                head = 0;
            }
            size--;
            tailWaits.signal();
            return removed;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
