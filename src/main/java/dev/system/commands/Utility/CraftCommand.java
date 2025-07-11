package dev.system.commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CraftCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | §c";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "Only players can use this command.");
            return true;
        }

        if (!p.hasPermission("system.craft")) {
            p.sendMessage(PREFIX + "You do not have permission to use this command.");
            return true;
        }

        p.openWorkbench(null, true);
        p.sendMessage(PREFIX + "Crafting table opened.");
        return true;
    }
}