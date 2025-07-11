package dev.system.commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(PREFIX + "§cOnly players can heal themselves.");
                return true;
            }
            if (!p.hasPermission("system.heal")) {
                p.sendMessage(PREFIX + "§cYou don’t have permission to heal yourself.");
                return true;
            }
            p.setHealth(p.getMaxHealth());
            p.sendMessage(PREFIX + "§aYou have been healed.");
            return true;
        } else if (args.length == 1) {
            if (!sender.hasPermission("system.heal")) {
                sender.sendMessage(PREFIX + "§cYou don’t have permission to heal others.");
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(PREFIX + "§cPlayer not found.");
                return true;
            }
            target.setHealth(target.getMaxHealth());
            target.sendMessage(PREFIX + "§aYou have been healed.");
            sender.sendMessage(PREFIX + "§aYou healed " + target.getName() + ".");
            return true;
        } else {
            sender.sendMessage(PREFIX + "§cUsage: /heal [player]");
            return true;
        }
    }
}