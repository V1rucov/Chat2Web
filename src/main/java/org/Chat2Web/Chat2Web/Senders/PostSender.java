package org.Chat2Web.Chat2Web.Senders;

import org.Chat2Web.Chat2Web.ChatMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostSender implements ISender{
    @Override
    public void Send(String argument, ChatMessage msg) {
        String message = msg.Message.replaceAll("(\\'|\\\"|\\[|\\]|\\{|\\})", "");
        String serialized = "{\"Username\":\""+msg.Username+"\", \"date\":\""+msg.date.toString()+"\", \"Message\":\""+message+"\"}";

        try {
            URL url = new URL (argument);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            byte[] input = serialized.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            br.close();

        }
        catch (Exception ex) {
            System.out.println("O-oh! Something went wrong with tcp connection...");
        }
    }
}
