package dev.system.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class VanishCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | ";
    private final JavaPlugin plugin;

    public VanishCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("system.vanish")) {
            sender.sendMessage(PREFIX + "§cYou do not have permission to use this command.");
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(PREFIX + "§cOnly players can use this command without arguments.");
                return true;
            }
            toggleVanish(p);
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendMessage(PREFIX + "§cPlayer not found.");
                return true;
            }
            toggleVanish(target);
            sender.sendMessage(PREFIX + "§aToggled vanish for " + target.getName() + ".");
            return true;
        }

        sender.sendMessage(PREFIX + "§cUsage: /vanish [player]");
        return true;
    }

    private void toggleVanish(Player p) {
        boolean vanished = p.hasMetadata("vanished");

        if (!vanished) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.equals(p)) online.hidePlayer(plugin, p);
            }
            p.setMetadata("vanished", new FixedMetadataValue(plugin, true));
            p.sendMessage(PREFIX + "§aYou are now vanished.");
        } else {
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, p);
            }
            p.removeMetadata("vanished", plugin);
            p.sendMessage(PREFIX + "§aYou are no longer vanished.");
        }
    }
}