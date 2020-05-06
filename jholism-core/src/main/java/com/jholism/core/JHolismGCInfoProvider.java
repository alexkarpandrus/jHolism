package com.jholism.core;

import com.jholism.communication.IJHolismCommunicator;
import com.jholism.communication.messages.GCInfo;

/**
 * Thread-safe due to invocation happening from the same executor.
 */
public class JHolismGCInfoProvider implements IJHolismGCInfoProvider {

    private final IJHolismCommunicator communicator;

    /**
     * Current leader to pass the GC info to
     */
    private int currentLeaderId;

    JHolismGCInfoProvider(final IJHolismCommunicator communicator) {
        this.communicator = communicator;
        // todo: schedule the scan

    }

    @Override
    public void onNewLeader(final int nodeId) {
        this.currentLeaderId = nodeId;
    }

    /**
     * Collects GC stats and sends them to the leader
     */
    private void collectGCStats() {
        final GCInfo gcInfo = getGCInfo();
        communicator.sendCommand(currentLeaderId, gcInfo);
    }

    // TODO
    private GCInfo getGCInfo() {
        return null;
    }
}