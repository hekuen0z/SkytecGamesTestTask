
package com.skytecgames.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Executor service for managing clan gold operations.
 * <p>
 * Created by Alexey Kaptur on 20.01.2024
 * <p>
 * Contact: kaptur.swdev@gmail.com
 */
@Slf4j
public class ClanGoldExecutorService {

    private final int THREADS_NUMBER = 16;
    private final ExecutorService executorService;
    private final static ClanGoldExecutorService INSTANCE = new ClanGoldExecutorService();

    private ClanGoldExecutorService() {
        executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        Thread.setDefaultUncaughtExceptionHandler((e, t) -> log.error(
                "Error occurred in thread " + Thread.currentThread().getName() + " with message " + t.getMessage() + "."));
    }

    /**
     * Executes the given lambda command.
     *
     * @param command the command to execute
     */
    public void execute(Runnable command) {
        if (!executorService.isShutdown()) {
            executorService.execute(command);
        } else {
            log.warn("ExecutorService is already shutdown. Command cannot be executed.");
        }
    }

    /**
     * Submits a task for execution and returns a Future representing the pending result of the task.
     *
     * @param command the task to submit
     * @return a Future representing the pending result of the task, or null if the ExecutorService is already shutdown
     */
    public Future<Integer> submit(Callable<Integer> command) {
        if (!executorService.isShutdown()) {
            return executorService.submit(command);
        } else {
            log.warn("ExecutorService is already shutdown. Command cannot be executed.");
            return null;
        }
    }


    /**
     * Initiates an orderly shutdown of the ExecutorService.
     * <p>
     * After shutdown is called, the ExecutorService will no longer accept new tasks,
     * and will attempt to gracefully shut down by allowing previously submitted tasks to complete.
     * <p>
     * Note that this method does not wait for the tasks to complete. To wait for the tasks to complete,
     * use the awaitTermination method after calling shutdown.
     */
    public void shutdown() {
        executorService.shutdown();
    }

    /**
     * Blocks until all tasks have completed execution after a shutdown request,
     * or the timeout occurs, or the current thread is interrupted, whichever happens first.
     *
     * @param i        the maximum time to wait
     * @param timeUnit the time unit of the timeout argument
     * @return true if this executor terminated and false if the timeout elapsed before termination
     * @throws InterruptedException if interrupted while waiting
     */
    public boolean awaitTermination(int i, TimeUnit timeUnit) throws InterruptedException {
        return executorService.awaitTermination(i, timeUnit);
    }

    /**
     * Returns the singleton instance of ClanGoldExecutorService.
     *
     * @return the singleton instance of ClanGoldExecutorService
     */
    public static ClanGoldExecutorService getInstance() {
        return INSTANCE;
    }
}
