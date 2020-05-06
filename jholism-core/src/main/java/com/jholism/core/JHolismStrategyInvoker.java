package com.jholism.core;

import com.google.common.flogger.FluentLogger;
import com.jholism.communication.IJHolismCommunicator;
import com.jholism.communication.messages.GCStrategy;

public class JHolismStrategyInvoker implements IJHolismStrategyInvoker {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();


    private final int nodeId;
    private final IJHolismCommunicator communicator;

    JHolismStrategyInvoker(final int nodeId, final IJHolismCommunicator communicator) {
        this.nodeId = nodeId;
        this.communicator = communicator;
    }


    @Override
    public void invokeStrategy(final GCStrategy gcStrategy) {
        if (gcStrategy.getTargetNode() == nodeId) {
            logger.atFiner().log("GC strategy [%s] will be invoked locally on the node", gcStrategy);
            invokeLocally(gcStrategy);
        } else {
            logger.atFiner().log("GC strategy [%s] will be send to the remote node", gcStrategy);
            communicator.sendCommand(gcStrategy.getTargetNode(), gcStrategy);
        }
    }

    private void invokeLocally(GCStrategy gcStrategy) {
        // TODO
    }
}
