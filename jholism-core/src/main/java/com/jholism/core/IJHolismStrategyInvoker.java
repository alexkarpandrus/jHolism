package com.jholism.core;

import com.jholism.core.messages.GCStrategy;

/**
 * Invoker of GC strategies
 */
public interface IJHolismStrategyInvoker {

    /**
     * Invokes given GC strategy
     *
     * @param gcStrategy the strategy to invoke
     */
    void invokeStrategy(GCStrategy gcStrategy);
}
