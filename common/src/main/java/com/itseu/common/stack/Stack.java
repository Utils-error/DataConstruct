package com.itseu.common.stack;

public interface Stack<E> {

    /**
     * 向栈顶压入元素
     * @param element
     * @return
     */
    boolean push(E element);

    /**从栈顶弹出元素
     * @return
     */
    E pop();

    /**
     * 返回栈顶元素
     * @return
     */
    E peek();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     *  判断栈是否已满
     * @return
     */
    boolean isFull();
}
