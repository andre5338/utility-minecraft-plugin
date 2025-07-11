package dev.system.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cOnly players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            if (!p.hasPermission("system.fly")) {
                p.sendMessage(PREFIX + "§cYou don’t have permission to use this command.");
                return true;
            }

            toggleFly(p, p);
            return true;
        }

        if (!p.hasPermission("system.fly")) {
            p.sendMessage(PREFIX + "§cYou don’t have permission to change flight for others.");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            p.sendMessage(PREFIX + "§cPlayer not found.");
            return true;
        }

        toggleFly(p, target);
        return true;
    }

    private void toggleFly(Player sender, Player target) {
        boolean enabled = !target.getAllowFlight();
        target.setAllowFlight(enabled);
        target.sendMessage(PREFIX + "§7Fly mode: " + (enabled ? "§aenabled" : "§cdisabled"));
        if (!sender.equals(target)) {
            sender.sendMessage(PREFIX + "§7Fly mode for " + target.getName() + ": " + (enabled ? "§aenabled" : "§cdisabled"));
        }
    }
}