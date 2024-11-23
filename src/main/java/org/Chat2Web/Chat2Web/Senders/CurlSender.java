package org.Chat2Web.Chat2Web.Senders;

import org.Chat2Web.Chat2Web.ChatMessage;

import java.util.regex.Pattern;

public class CurlSender implements ISender{
    @Override
    public void Send(String argument, ChatMessage msg) {
        String message = msg.Message.replaceAll("(\\'|\\\"|\\[|\\]|\\{|\\})", "");

        String serialized = "{\"Username\":\""+msg.Username+"\", \"date\":\""+msg.date.toString()+"\", \"Message\":\""+message+"\"}";
        String[] command = {"/bin/sh", "-c", "curl --request post --header \"Content-Type: application/json\" --data '" + serialized + "' " + argument};

        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            System.out.println("O-oh! Something went wrong with tcp connection...");
        }
    }
}
