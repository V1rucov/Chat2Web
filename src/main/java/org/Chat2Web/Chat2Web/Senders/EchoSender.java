package org.Chat2Web.Chat2Web.Senders;

import org.Chat2Web.Chat2Web.ChatMessage;

public class EchoSender implements ISender {

    @Override
    public void Send(String address, ChatMessage msg) {
        System.out.println(msg.Username + ": " + msg.Message);
    }
}
