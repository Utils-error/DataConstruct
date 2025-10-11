package com.itseu.common.queue;

public interface Queue<E> {

    /**
     * 向队列尾插入元素
     * @param val
     * @return
     */
    boolean offer(E val);

    /**
     * 从队列头获取元素并移除
     * @return
     */
    E poll();

    /**
     * 从队列头获取元素，不移除
     * @return
     */
    E peek();

    /**
     * 检查队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 检查队列是否存满
     * @return
     */
    boolean isFull();

}
