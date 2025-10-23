package com.itseu.common.deque;

public interface Deque<E>  {

    /**
     * 向头部加入元素
     * @param e
     * @return
     */
    boolean offerFirst(E e);

    /**
     * 向尾部加入元素
     * @param e
     * @return
     */
    boolean offerLast(E e);

    /**
     * 从头部移除元素
     * @return
     */
    E pollFirst();

    /**
     * 从尾部移除元素
     * @return
     */
    E pollLast();

    /**
     * 从头部获取元素
     * @return
     */
    E peekFirst();

    /**
     * 从尾部获取元素
     * @return
     */
    E peekLast();

    /**
     * 双端队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 双端队列是否已满
     * @return
     */
    boolean isFull();
}
