package dev.utility.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    private final String PREFIX = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[SYSTEM] " + ChatColor.RESET;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + ChatColor.RED + "Only players can use this command.");
            return true;
        }
        if (!p.isOp() && !p.hasPermission("utility.tp")) {
            p.sendMessage(PREFIX + ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }
        if (args.length != 1) {
            p.sendMessage(PREFIX + ChatColor.RED + "Usage: /tp <player>");
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            p.sendMessage(PREFIX + ChatColor.RED + "Player not found.");
            return true;
        }
        p.teleport(target.getLocation());
        p.sendMessage(PREFIX + ChatColor.GREEN + "Teleported to " + target.getName());
        return true;
    }
}