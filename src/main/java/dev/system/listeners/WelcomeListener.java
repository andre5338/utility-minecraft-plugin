package dev.system.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WelcomeListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        String playerName = event.getPlayer().getName();
        int onlineCount = Bukkit.getOnlinePlayers().size();

        String message = ChatColor.DARK_GREEN + "System | " + ChatColor.RED + "Welcome " + playerName +
                ChatColor.RED + " on our Server. Right now are " +
                ChatColor.GREEN + onlineCount + ChatColor.RED + " Members Online";

        Bukkit.broadcastMessage(message);
    }
}