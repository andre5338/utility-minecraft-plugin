package dev.system.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TphereCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "§cOnly players can use this command.");
            return true;
        }
        if (!p.isOp() && !p.hasPermission("system.tphere")) {
            p.sendMessage(PREFIX + "§cYou do not have permission to use this command.");
            return true;
        }
        if (args.length != 1) {
            p.sendMessage(PREFIX + "§cUsage: /tphere <player>");
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            p.sendMessage(PREFIX + "§cPlayer not found.");
            return true;
        }
        target.teleport(p.getLocation());
        p.sendMessage(PREFIX + "§aTeleported " + target.getName() + " to you.");
        return true;
    }
}