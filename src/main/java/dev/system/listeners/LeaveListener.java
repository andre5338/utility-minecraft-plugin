package dev.system.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        String playerName = event.getPlayer().getName();
        int onlineCount = Bukkit.getOnlinePlayers().size() - 1;

        String message = ChatColor.DARK_GREEN + "System | " + ChatColor.RED + playerName +
                ChatColor.RED + " left the server. Now " +
                ChatColor.GREEN + onlineCount + ChatColor.RED + " members online.";

        Bukkit.broadcastMessage(message);
    }
}