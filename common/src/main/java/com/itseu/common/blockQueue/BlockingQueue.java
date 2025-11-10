package com.itseu.common.blockQueue;

import java.util.concurrent.TimeUnit;

/*阻塞队列通用接口*/
public interface BlockingQueue<E> {

    void offer(E e) throws InterruptedException;

    boolean offer(E e, long timeout) throws InterruptedException;

    E poll() throws InterruptedException;
}
