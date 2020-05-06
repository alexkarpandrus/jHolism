package com.jholism.communication;

import com.jholism.core.messages.IJHolismMessage;

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

}
