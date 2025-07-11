package dev.system.commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(PREFIX + "§cOnly players can feed themselves.");
                return true;
            }
            if (!p.hasPermission("system.feed")) {
                p.sendMessage(PREFIX + "§cYou don’t have permission to feed yourself.");
                return true;
            }
            p.setFoodLevel(20);
            p.setSaturation(20f);
            p.sendMessage(PREFIX + "§aYou have been fully fed and your saturation is maxed.");
            p.sendMessage(PREFIX + "§7Stay healthy and enjoy your game!");
            return true;
        } else if (args.length == 1) {
            if (!sender.hasPermission("system.feed")) {
                sender.sendMessage(PREFIX + "§cYou don’t have permission to feed others.");
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(PREFIX + "§cPlayer not found.");
                return true;
            }
            target.setFoodLevel(20);
            target.setSaturation(20f);
            target.sendMessage(PREFIX + "§aYou have been fully fed and your saturation is maxed.");
            target.sendMessage(PREFIX + "§7Stay healthy and enjoy your game!");
            sender.sendMessage(PREFIX + "§aYou fed " + target.getName() + ".");
            return true;
        } else {
            sender.sendMessage(PREFIX + "§cUsage: /feed [player]");
            return true;
        }
    }
}