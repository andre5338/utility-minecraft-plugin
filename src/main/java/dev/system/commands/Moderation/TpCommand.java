package dev.system.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "§cOnly players can use this command.");
            return true;
        }
        if (!p.isOp() && !p.hasPermission("system.tp")) {
            p.sendMessage(PREFIX + "§cYou do not have permission to use this command.");
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                p.sendMessage(PREFIX + "§cPlayer not found.");
                return true;
            }
            p.teleport(target.getLocation());
            p.sendMessage(PREFIX + "§aTeleported to " + target.getName());
            return true;
        }

        if (args.length == 3) {
            try {
                double x = Double.parseDouble(args[0]);
                double y = Double.parseDouble(args[1]);
                double z = Double.parseDouble(args[2]);
                Location loc = new Location(p.getWorld(), x, y, z);
                p.teleport(loc);
                p.sendMessage(PREFIX + "§aTeleported to coordinates: " + x + ", " + y + ", " + z);
            } catch (NumberFormatException e) {
                p.sendMessage(PREFIX + "§cCoordinates must be valid numbers.");
            }
            return true;
        }

        p.sendMessage(PREFIX + "§cUsage: /tp <player> OR /tp <x> <y> <z>");
        return true;
    }
}