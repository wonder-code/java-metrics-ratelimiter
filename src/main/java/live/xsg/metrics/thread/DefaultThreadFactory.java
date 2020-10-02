package live.xsg.metrics.thread;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程创建策略
 * Created by xsg on 2020/10/2.
 */
public class DefaultThreadFactory implements ThreadFactory {

    //线程名称
    private String name;
    //是否是守护线程
    private boolean daemon;

    public DefaultThreadFactory(String name) {
        this(name, false);
    }

    public DefaultThreadFactory(String name, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, this.name);
        thread.setDaemon(this.daemon);
        return thread;
    }
}
