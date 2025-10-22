package com.itseu.common.queue.impl;

import com.itseu.common.queue.Queue;
import com.itseu.common.stack.impl.ArrayStack;
import lombok.val;

import java.util.Iterator;

/*使用两个栈模拟队列：存数据时，先放Stack1，取数据时先都放到stack2中，再取数据
* */
public class TwoStackQueue<E> implements Queue<E>,Iterable<E> {

    ArrayStack<E> stack1;
    ArrayStack<E> stack2;

    public TwoStackQueue(int capacity) {
        stack1 = new ArrayStack<>(capacity);
        stack2 = new ArrayStack<>(capacity);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        move();
        return stack2.iterator();
    }

    /**
     * 向队列尾插入元素
     *
     * @param val
     * @return
     */
    @Override
    public boolean offer(E val) {
        stack1.push(val);
        return true;
    }

    private void move(){
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
    }
    /**
     * 从队列头获取元素，不移除
     *
     * @return
     */
    @Override
    public E peek() {
        if(stack2.isEmpty()){
            move();
        }
        return stack2.peek();
    }

    /**
     * 从队列头获取元素并移除
     *
     * @return
     */
    @Override
    public E poll() {
        if(stack2.isEmpty()){
            move();
        }
        return stack2.pop();
    }

    /**
     * 检查队列是否存满
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return stack1.isFull() || stack2.isFull();
    }

    /**
     * 检查队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
