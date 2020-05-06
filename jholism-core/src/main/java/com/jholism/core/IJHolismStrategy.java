package com.jholism.core;

import com.jholism.core.messages.GCInfo;
import com.jholism.core.messages.GCStrategy;

import java.util.List;

/**
 * Represents the GC strategy of jHolism.
 * Can represent arbitrary strategy and shall be the main entity controlling the strategies over the connected cluster of nodes.
 */
public interface IJHolismStrategy {

    /**
     * Returns the name of the strategy.
     *
     * @return the name of the strategy
     */
    String getName();

    /**
     * Updates the strategy implementation with the new GC info of some node and generates the list of strategies to invoke.
     *
     * @param info the information about the GC statistics of some node
     * @return the list of strategies to invoke, {@code null} indicates no strategies
     */
    List<GCStrategy> updateState(GCInfo info);

}
