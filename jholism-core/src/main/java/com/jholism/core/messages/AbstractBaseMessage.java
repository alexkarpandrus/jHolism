package com.jholism.core.messages;

public abstract class AbstractBaseMessage implements IJHolismMessage {

    private final int targetNodeId;

    protected AbstractBaseMessage(int targetNodeId) {
        this.targetNodeId = targetNodeId;
    }


    @Override
    public int getTargetNode() {
        return targetNodeId;
    }
}
