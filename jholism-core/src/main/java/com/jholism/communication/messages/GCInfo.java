package com.jholism.communication.messages;

public class GCInfo extends AbstractBaseMessage {
    protected GCInfo(final int targetNodeId) {
        super(targetNodeId);
    }
}
