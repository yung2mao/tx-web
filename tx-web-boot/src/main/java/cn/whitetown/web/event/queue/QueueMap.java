package cn.whitetown.web.event.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 阻塞队列列表
 * @Author: taixian
 * @Date: created in 2020/12/08
 */
@Component
public class QueueMap extends HashMap<String, LocalBlockingQueue>{

    @Autowired
    private ApplicationContext context;

    public QueueMap(int initialCapacity) {
        super(initialCapacity);
    }

    public QueueMap() {
    }
}
