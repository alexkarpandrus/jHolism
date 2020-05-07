package com.jholism.core;

import com.google.common.flogger.FluentLogger;
import com.jholism.communication.IJHolismCommunicator;
import com.jholism.communication.messages.GCStrategy;

public class JHolismStrategyInvoker {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();


    private final int nodeId;
    private final IJHolismCommunicator communicator;
    private final IJHolismGCInvoker localInvoker;

    JHolismStrategyInvoker(final int nodeId, final IJHolismCommunicator communicator, final IJHolismGCInvoker localInvoker) {
        this.nodeId = nodeId;
        this.communicator = communicator;
        this.localInvoker = localInvoker;
    }


    public void invokeStrategy(final GCStrategy gcStrategy) {
        if (gcStrategy.getTargetNode() == nodeId) {
            logger.atFiner().log("GC strategy [%s] will be invoked locally on the node", gcStrategy);
            localInvoker.invokeGCStrategy(gcStrategy);
        } else {
            logger.atFiner().log("GC strategy [%s] will be send to the remote node", gcStrategy);
            communicator.sendCommand(gcStrategy.getTargetNode(), gcStrategy);
        }
    }
}
