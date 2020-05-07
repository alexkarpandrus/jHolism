package com.jholism.core;

import com.jholism.communication.messages.GCInfo;
import com.jholism.communication.messages.GCStrategy;
import com.jholism.communication.messages.LeaderElected;

/**
 * Observes the messages exchanged between nodes.
 * All updates are guaranteed to come from a single thread, since everything is executed from the single executor.
 */
public interface INodeMessageListener {

    /**
     * Invoked when new {@link GCInfo} is received
     *
     * @param gcInfo the new GC data
     */
    void onNewGCInfo(GCInfo gcInfo);

    /**
     * Invoked when new {@link GCStrategy} is received
     *
     * @param gcStrategy the new GC strategy to process
     */
    void onNewGSStrategy(GCStrategy gcStrategy);

    /**
     * Invoked when new leader is elected
     *
     * @param leaderElected the information about a new leader elected
     */
    void onLeaderElected(LeaderElected leaderElected);

}
