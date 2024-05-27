package icu.dbkx.opnmb.common.thread;

import lombok.AllArgsConstructor;

import java.util.concurrent.ThreadFactory;

/**
 * 装饰器模式
 */
@AllArgsConstructor
public class MyThreadFactory implements ThreadFactory {
    private static final MyUncaughtExceptionHandler HANDLER = new MyUncaughtExceptionHandler();

    private ThreadFactory original;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = original.newThread(r);
        thread.setUncaughtExceptionHandler(HANDLER);
        return thread;
    }
}
