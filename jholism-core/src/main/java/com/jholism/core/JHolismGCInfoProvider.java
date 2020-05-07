package com.jholism.core;

import com.google.common.flogger.FluentLogger;
import com.jholism.common.JHolisticScheduledExecutor;
import com.jholism.communication.IJHolismCommunicator;
import com.jholism.communication.messages.GCInfo;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Thread-safe due to invocation happening from the same executor.
 */
class JHolismGCInfoProvider {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private static final int INITIAL_DELAY = 0;

    private final IJHolismCommunicator communicator;
    private final IJHolismGCInfoProvider gcInfoProvider;
    private final ScheduledFuture<?> collectionTask; // TODO: probably needed for future stop functionality

    /**
     * Current leader to pass the GC info to
     */
    private int currentLeaderId;

    JHolismGCInfoProvider(
            final int updatePeriod,
            final IJHolismCommunicator communicator,
            final JHolisticScheduledExecutor executor,
            final IJHolismGCInfoProvider gcInfoProvider) {
        this.communicator = communicator;
        this.gcInfoProvider = gcInfoProvider;
        this.collectionTask = executor.scheduleAtFixedRate(INITIAL_DELAY, updatePeriod, TimeUnit.MILLISECONDS, this::collectGCStats);
    }

    public void onNewLeader(final int nodeId) {
        this.currentLeaderId = nodeId;
    }

    /**
     * Collects GC stats and sends them to the leader
     */
    private void collectGCStats() {
        final GCInfo gcInfo = gcInfoProvider.getGCInfo();
        if (gcInfo == null) {
            logger.atWarning().log("GC info provider returned null");
            return;
        }
        logger.atFinest().log("GC info: %s", gcInfo);
        communicator.sendCommand(currentLeaderId, gcInfo);
    }

}
