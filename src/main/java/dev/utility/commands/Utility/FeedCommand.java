package dev.utility.commands.Utility;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("utility.feed")) {
            p.sendMessage("§2System | §cYou don’t have permission to use this command.");
            return true;
        }
        p.setFoodLevel(20);
        p.setSaturation(20f);
        p.sendMessage("§2System | §aYou have been fully fed and your saturation is maxed.");
        p.sendMessage("§2System | §7Stay healthy and enjoy your game!");
        return true;
    }
}