package com.jholism.core;

import com.google.common.flogger.FluentLogger;
import com.jholism.communication.IJHolismCommunicator;
import com.jholism.communication.messages.GCInfo;

/**
 * Thread-safe due to invocation happening from the same executor.
 */
class JHolismGCInfoProvider {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private final IJHolismCommunicator communicator;

    /**
     * Current leader to pass the GC info to
     */
    private int currentLeaderId;

    JHolismGCInfoProvider(final IJHolismCommunicator communicator) {
        this.communicator = communicator;
        // todo: schedule the scan

    }

    public void onNewLeader(final int nodeId) {
        this.currentLeaderId = nodeId;
    }

    /**
     * Collects GC stats and sends them to the leader
     */
    private void collectGCStats() {
        final GCInfo gcInfo = getGCInfo();
        logger.atFinest().log("GC info: %s", gcInfo);
        communicator.sendCommand(currentLeaderId, gcInfo);
    }

    // TODO
    private GCInfo getGCInfo() {
        return null;
    }
}
