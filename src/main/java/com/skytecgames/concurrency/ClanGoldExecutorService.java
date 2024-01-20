package com.skytecgames.concurrency;

import com.skytecgames.service.util.LogService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Alexey Kaptur on 20.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
public class ClanGoldExecutorService {

    private final int THREADS_NUMBER = 10;
    private final ExecutorService executorService;
    private final static ClanGoldExecutorService INSTANCE = new ClanGoldExecutorService();

    private ClanGoldExecutorService() {
        executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
    }

    public static ClanGoldExecutorService getInstance() {
        return INSTANCE;
    }

    public void execute(Runnable command) {
        executorService.execute(command);
        Thread.setDefaultUncaughtExceptionHandler((e, t) -> LogService.createErrorLogMessageFromString(
                "Error occurred in thread " + Thread.currentThread().getName() + " with message " + t.getMessage() + "."));
    }

    public void shutdown() {
        executorService.shutdown();
    }


}
