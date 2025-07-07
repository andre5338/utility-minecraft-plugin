package dev.utility.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnnouncementCommand implements CommandExecutor {

    private final String PREFIX = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[SYSTEM] " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (!p.isOp() && !p.hasPermission("utility.announce")) {
            p.sendMessage(PREFIX + ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage(PREFIX + ChatColor.RED + "Usage: /announce <message>");
            return true;
        }

        String message = String.join(" ", args);
        String announcement = ChatColor.GREEN + "" + ChatColor.BOLD + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n" +
                              PREFIX + ChatColor.GOLD + "" + ChatColor.BOLD + "Announcement:\n" +
                              ChatColor.WHITE + message + "\n" +
                              ChatColor.GREEN + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬";

        Bukkit.broadcastMessage(announcement);
        return true;
    }
}