package com.jholism.communication;

import com.jholism.common.JHolisticExecutorProvider;
import com.jholism.communication.messages.IJHolismMessage;
import com.jholism.core.INodeMessageListener;

public class JHolismCommunicator implements IJHolismCommunicator {

    private final JHolisticExecutorProvider executorProvider;

    JHolismCommunicator(final JHolisticExecutorProvider executorProvider) {
        this.executorProvider = executorProvider;
    }

    // TODO
    @Override
    public void sendCommand(final int targetId, final IJHolismMessage message) {

    }

    // TODO
    @Override
    public void registerMessagesListener(final INodeMessageListener listener) {

    }
}
