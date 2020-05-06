package com.jholism.core;

import com.jholism.communication.messages.GCStrategy;

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
