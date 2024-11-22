package org.Chat2Web.Chat2Web;

import org.Chat2Web.Chat2Web.Senders.EchoSender;
import org.Chat2Web.Chat2Web.Senders.ISender;
import org.Chat2Web.Chat2Web.Senders.TCPSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Date;

public final class Chat2Web extends JavaPlugin implements Listener {

    static ISender MessageSender;
    static String SenderType = "";
    static String Address = "";

    @Override
    public void onEnable() {
        saveDefaultConfig();
        System.out.println("Starting...");

        if(getConfig().getString("address")==null || getConfig().getString("sender-type")==null) {
            System.out.println("Wrong configuration! Plugin will continue work as \'echo\' plugin");
            Address = "not configured";
            SenderType = "not configured";
        }
        else {
            SenderType = getConfig().getString("sender-type");
            Address = getConfig().getString("address");
        }
        switch(SenderType) {
            case "tcp":
                MessageSender = new TCPSender();
                break;
             default:
                 System.out.println("Wrong configuration! Plugin will continue work as \'echo\' plugin");
                 MessageSender = new EchoSender();
                 break;
        }
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        System.out.println("Stopped");
    }

    @EventHandler
    public void onMessageSent(AsyncPlayerChatEvent event) {
        ChatMessage msg = new ChatMessage();
        msg.date = new Date(System.currentTimeMillis());
        msg.Message = event.getMessage();
        msg.Username = event.getPlayer().getDisplayName();

        MessageSender.Send(Address,msg);
    }
}
