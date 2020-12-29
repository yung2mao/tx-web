package cn.whitetown.web.event.queue;

import cn.whitetown.web.exception.WhEventException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * @Author: taixian
 * @Date: created in 2020/12/08
 */
public class LocalBlockingQueue<E>{

    private static int size;

    private Logger logger = LoggerFactory.getLogger(LocalBlockingQueue.class);

    private String topic;

    private ArrayBlockingQueue<EventMessage<E>> blockingQueue;

    public LocalBlockingQueue(String topic, int capacity) {
        blockingQueue = new ArrayBlockingQueue<>(capacity);
        destroy();
    }

    public LocalBlockingQueue(int capacity) {
        synchronized (this) {
            topic = "local_queue_" + size++;
        }
        blockingQueue = new ArrayBlockingQueue<>(capacity);
        destroy();
    }

    /**
     * destroy
     */
    private void destroy() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::clear));
    }

    /**
     * topic
     * @return -
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 队列满阻塞
     * @param event 元素
     */
    public void putOrWait(EventMessage event) {
        try {
            blockingQueue.put(event);
        }catch (InterruptedException ex) {
            throw new WhEventException(ex.getMessage());
        }

    }

    /**
     * 添加元素 - 等待指定时长
     * @param event 元素
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     * @return 指定时间内是否成功添加
     */
    public boolean putOrWaitTime(EventMessage event, long timeout, TimeUnit timeUnit) {
        try {
            return blockingQueue.offer(event, timeout, timeUnit);
        }catch (InterruptedException ex) {
            throw new WhEventException(ex.getMessage());
        }
    }

    /**
     * 立即取出一个元素 - 没有则为null
     * @return
     */
    public EventMessage getNow() {
        return blockingQueue.poll();
    }

    /**
     * 取出一个元素或等待取出一个元素
     * @return
     */
    public EventMessage<E> getAndWait() {
        try {
            return blockingQueue.take();
        }catch (InterruptedException ex) {
            throw new WhEventException(ex.getMessage());
        }
    }

    /**
     * 取出一个元素 - 等待指定时长
     * @param timeout
     * @param unit
     * @return
     */
    public EventMessage<E> getAndWaitTime(long timeout, TimeUnit unit) {
        try {
            return blockingQueue.poll(timeout,unit);
        }catch (InterruptedException ex) {
            throw new WhEventException(ex.getMessage());
        }
    }

    public void clear() {
        logger.info("the queue is destroyed, topic is {}", this.topic);
        blockingQueue.clear();
    }

}
