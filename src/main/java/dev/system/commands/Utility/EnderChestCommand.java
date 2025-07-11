package dev.system.commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class EnderChestCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | §c";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "Only players can use this command.");
            return true;
        }

        if (args.length == 0) {
            if (!p.hasPermission("system.ec")) {
                p.sendMessage(PREFIX + "You do not have permission to open your own Ender Chest.");
                return true;
            }
            p.openInventory(p.getEnderChest());
            p.sendMessage(PREFIX + "Your Ender Chest has been opened.");
            return true;
        }

        if (args.length == 1) {
            if (!p.hasPermission("system.ec.others")) {
                p.sendMessage(PREFIX + "You do not have permission to open others' Ender Chests.");
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                p.sendMessage(PREFIX + "Player not found.");
                return true;
            }
            p.openInventory(target.getEnderChest());
            p.sendMessage(PREFIX + "Opened Ender Chest of " + target.getName() + ".");
            return true;
        }

        p.sendMessage(PREFIX + "Usage: /ec [player]");
        return true;
    }
}