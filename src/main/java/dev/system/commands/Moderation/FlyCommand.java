package dev.system.commands.Moderation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("system.fly")) {
            p.sendMessage("§2System | §cYou don’t have permission to use this command.");
            return true;
        }
        boolean enabled = !p.getAllowFlight();
        p.setAllowFlight(enabled);
        p.sendMessage("§2System | §7Fly mode: " + (enabled ? "§aenabled" : "§cdisabled"));
        return true;
    }
}