package cn.whitetown.web.base.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * 线程池配置
 * @author taixian
 * @date 2020/07/31
 **/
public class WhiteThreadPoolFactory {

    /**
     * 创建一个线程池
     * @return -
     */
    public static ExecutorService executorService(int corePoolSize,
                                                  int maxPoolSize,
                                                  int keepAliveTime,
                                                  int queueSize,
                                                  String threadPoolName) {

        CustomizableThreadFactory threadFactory = new CustomizableThreadFactory();
        threadFactory.setThreadGroupName(threadPoolName);
        BlockingQueue<Runnable> theadQueue = new ArrayBlockingQueue<>(queueSize);
        return new ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                theadQueue,
                threadFactory);
    }
}
