package com.jholism.common;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JHolisticScheduledExecutor {

    private final ScheduledThreadPoolExecutor schedulingService;
    private final Executor executor;

    JHolisticScheduledExecutor(JHolisticExecutorProvider executorProvider) {
        // TODO: introduce custom thread factory for better control
        this.schedulingService = new ScheduledThreadPoolExecutor(1);
        this.executor = executorProvider.getExecutor();
    }

    public ScheduledFuture<?> scheduleAtFixedRate(int delay, int period, TimeUnit timeUnit, Runnable task) {
        return schedulingService.scheduleAtFixedRate(() -> executor.execute(task), delay, period, timeUnit);
    }
}
