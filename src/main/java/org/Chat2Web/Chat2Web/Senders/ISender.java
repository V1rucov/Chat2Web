package org.Chat2Web.Chat2Web.Senders;

import org.Chat2Web.Chat2Web.ChatMessage;

public interface ISender {
    public void Send(String address, ChatMessage msg);
}
