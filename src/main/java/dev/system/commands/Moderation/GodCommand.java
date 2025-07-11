package dev.system.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GodCommand implements CommandExecutor, Listener {

    private final Set<UUID> godmodePlayers = new HashSet<>();
    private final JavaPlugin plugin;
    private static final String PREFIX = "§2System | ";

    public GodCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PREFIX + "§cOnly players can use this command.");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            if (!p.hasPermission("system")) {
                p.sendMessage(PREFIX + "§cYou don’t have permission to use this command.");
                return true;
            }

            toggleGod(p, p);
            return true;
        }

        if (!p.hasPermission("system.god")) {
            p.sendMessage(PREFIX + "§cYou don’t have permission to change god mode for others.");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            p.sendMessage(PREFIX + "§cPlayer not found.");
            return true;
        }

        toggleGod(p, target);
        return true;
    }

    private void toggleGod(Player sender, Player target) {
        UUID uuid = target.getUniqueId();
        if (godmodePlayers.contains(uuid)) {
            godmodePlayers.remove(uuid);
            target.sendMessage(PREFIX + "§cGod mode disabled.");
            if (!sender.equals(target))
                sender.sendMessage(PREFIX + "§cGod mode disabled for " + target.getName() + ".");
        } else {
            godmodePlayers.add(uuid);
            target.sendMessage(PREFIX + "§aGod mode enabled.");
            if (!sender.equals(target))
                sender.sendMessage(PREFIX + "§aGod mode enabled for " + target.getName() + ".");
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player p && godmodePlayers.contains(p.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}