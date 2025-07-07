package dev.utility.commands;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("utility.heal")) {
            p.sendMessage("§2System | §cYou don’t have permission to use this command.");
            return true;
        }
        p.setHealth(p.getMaxHealth());
        p.sendMessage("§2System | §aHealed.");
        return true;
    }
}