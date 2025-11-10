package com.itseu.common.blockQueue.impl;

import com.itseu.common.blockQueue.BlockingQueue;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*阻塞队列：双锁实现，头尾各一把锁*/
public class BlockingQueue2<E> implements BlockingQueue<E> {
    private final E[] elements;
    private AtomicInteger size;
    private int head;
    private int tail;

    private ReentrantLock headLock = new ReentrantLock();
    private ReentrantLock tailLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();
    private Condition tailWaits = tailLock.newCondition();

    public BlockingQueue2(int capacity) {
        elements = (E[]) new Object[capacity];
        size = new AtomicInteger(0);
    }


    @Override
    public void offer(E o) throws InterruptedException {
        // 1.正常加锁添加数据
        int c;
        tailLock.lockInterruptibly();
        try {
            while (isFull()) {
                tailWaits.await();
            }
            elements[tail++] = o;
            if (tail == elements.length) {
                tail = 0;
            }
            c = size.getAndIncrement();
            if (c + 1 < elements.length) {
                tailWaits.signal();
            }
        } finally {
            tailLock.unlock();
        }
        // 2.平级加锁唤醒以避免死锁，注意这里只有0-1的添加才进行唤醒，其余的
        // 交给级联唤醒
        if (c == 0) {
            headLock.lockInterruptibly();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E o, long timeout) throws InterruptedException {
        int c;
        tailLock.lockInterruptibly();
        try {
            long time = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()) {
                if (time <= 0) {
                    return false;
                }
                time = tailWaits.awaitNanos(time);
            }
            elements[tail++] = o;
            if (tail == elements.length) {
                tail = 0;
            }
            c = size.getAndIncrement();
            if (c + 1 < elements.length) {
                tailWaits.signal();
            }
        } finally {
            tailLock.unlock();
        }
        // 平级编写以避免死锁
        if (c == 0) {
            headLock.lockInterruptibly();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        E removed;
        int c;
        headLock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            removed = elements[head++];
            elements[head] = null;
            if (head == elements.length) {
                head = 0;
            }
            c = size.getAndDecrement();
            if (c > 1) {
                headWaits.signal();
            }
        } finally {
            headLock.unlock();
        }
        // 平级书写避免死锁,只有从full状态移除，才进行唤醒offer唤醒
        if (c == elements.length) {
            tailLock.lockInterruptibly();
            try {
                tailWaits.signal();
            } finally {
                tailLock.unlock();
            }
        }
        return removed;

    }


    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    private boolean isFull() {
        return size.get() == elements.length;
    }

    private boolean isEmpty() {
        return size.get() == 0;
    }
}
