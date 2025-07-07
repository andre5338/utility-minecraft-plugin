package dev.utility.commands;

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

    public GodCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("utility.god")) {
            p.sendMessage("§2System | §cYou don’t have permission to use this command.");
            return true;
        }
        UUID uuid = p.getUniqueId();
        if (godmodePlayers.contains(uuid)) {
            godmodePlayers.remove(uuid);
            p.sendMessage("§2System | §cGod mode disabled");
        } else {
            godmodePlayers.add(uuid);
            p.sendMessage("§2System | §aGod mode enabled");
        }
        return true;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player p && godmodePlayers.contains(p.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}