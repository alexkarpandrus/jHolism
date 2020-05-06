package com.jholism.core.messages;

public class LeaderElected extends AbstractBaseMessage {

    private final int leaderNode;

    protected LeaderElected(final int targetNodeId, final int leaderNode) {
        super(targetNodeId);
        this.leaderNode = leaderNode;
    }

    public int getNodeId() {
        return leaderNode;
    }
}
