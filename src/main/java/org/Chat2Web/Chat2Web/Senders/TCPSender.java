package org.Chat2Web.Chat2Web.Senders;
import org.Chat2Web.Chat2Web.ChatMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPSender implements ISender {

    @Override
    public void Send(String address, ChatMessage msg) {
        String[] addr = address.split(":");
        String serialized = "{\"Username\":\""+msg.Username+"\", \"date\":\""+msg.date.toString()+"\", \"Message\":\""+msg.Message+"\"}";

        try {
            Socket sock = new Socket(addr[0], Integer.parseInt(addr[1]));
            OutputStream outputStream = sock.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.write(serialized);
        } catch (Exception e) {
            System.out.println("O-oh! Something went wrong with tcp connection...");
        }

    }
}
