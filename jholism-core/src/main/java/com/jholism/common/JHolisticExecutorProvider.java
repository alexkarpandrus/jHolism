package com.jholism.common;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JHolisticExecutorProvider {

    private final Executor executor;

    JHolisticExecutorProvider() {
        // TODO: introduce custom executors for better control and performance
        this.executor = Executors.newSingleThreadExecutor();
    }

    public Executor getExecutor() {
        return executor;
    }


}
