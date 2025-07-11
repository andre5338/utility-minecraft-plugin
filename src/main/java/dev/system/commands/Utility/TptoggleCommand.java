package dev.system.commands.Utility;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class TptoggleCommand implements CommandExecutor {

    private static final Set<Player> toggledOff = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        if (toggledOff.contains(p)) {
            toggledOff.remove(p);
            p.sendMessage("§aTeleport requests are now §aenabled§a.");
        } else {
            toggledOff.add(p);
            p.sendMessage("§cTeleport requests are now §cdisabled§c.");
        }
        return true;
    }

    public static boolean isToggledOff(Player player) {
        return toggledOff.contains(player);
    }
}