package dev.system.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnouncementCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender.hasPermission("system.announce") || sender.isOp())) {
            sender.sendMessage(PREFIX + "§cYou do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(PREFIX + "§cUsage: /announce <message>");
            return true;
        }

        String message = String.join(" ", args);
        String announcement = ChatColor.GREEN + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n" +
                              ChatColor.GOLD + "" + ChatColor.BOLD + "Announcement:\n" +
                              ChatColor.WHITE + message + "\n" +
                              ChatColor.GREEN + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬";

        Bukkit.broadcastMessage(announcement);
        return true;
    }
}