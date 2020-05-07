package com.jholism.communication;

import com.jholism.communication.messages.IJHolismMessage;
import com.jholism.core.INodeMessageListener;

/**
 * Communicates with other nodes in the network
 */
public interface IJHolismCommunicator {

    /**
     * Processes the given command
     *
     * @param targetId the target id of the node
     * @param message  the message to send
     */
    void sendCommand(int targetId, IJHolismMessage message);

    /**
     * Registers the listener for the events
     *
     * @param listener the listener
     */
    void registerMessagesListener(INodeMessageListener listener);

}
