package com.jholism.core;

import com.google.common.flogger.FluentLogger;
import com.jholism.core.messages.GCInfo;
import com.jholism.core.messages.GCStrategy;
import com.jholism.core.messages.LeaderElected;

import java.util.List;

class JHolismNode implements INodeMessageObserver {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private boolean isLeader = false;

    private final int nodeId;
    private final IJHolismStrategy strategy;
    private final IJHolismStrategyInvoker strategyInvoker;
    private final IJHolismGCInfoProvider gcInfoProvider;

    JHolismNode(final int nodeId, final IJHolismStrategy strategy, final IJHolismStrategyInvoker strategyInvoker,
                final IJHolismGCInfoProvider gcInfoProvider) {
        this.nodeId = nodeId;
        this.strategy = strategy;
        this.strategyInvoker = strategyInvoker;
        this.gcInfoProvider = gcInfoProvider;
    }


    @Override
    public void onNewGCInfo(final GCInfo gcInfo) {
        logger.atFiner().log("GCInfo is received: %s", gcInfo);
        if (!isLeader) {
            logger.atWarning().log("GCInfo received even though node isn't a leader");
            return;
        }

        final List<GCStrategy> strategies = this.strategy.updateState(gcInfo);

        if (strategies == null || strategies.isEmpty()) {
            logger.atFine().log("No GC strategy will be invoked");
            return;
        }

        for (int i = 0; i < strategies.size(); i++) {
            strategyInvoker.invokeStrategy(strategies.get(i));
        }

    }

    @Override
    public void onNewGSStrategy(final GCStrategy gcStrategy) {
        logger.atFiner().log("GCStrategy is received: %s", gcStrategy);
        strategyInvoker.invokeStrategy(gcStrategy);
    }

    @Override
    public void onLeaderElected(final LeaderElected leaderElected) {
        logger.atFiner().log("LeaderElected is received: %s", leaderElected);
        isLeader = leaderElected.getNodeId() == nodeId;
        gcInfoProvider.onNewLeader(leaderElected.getNodeId());
    }


}
