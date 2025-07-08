package dev.system.commands.Moderation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public class PermsGiveCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final Map<UUID, PermissionAttachment> attachments = new HashMap<>();

    public PermsGiveCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender.isOp() || sender.hasPermission("system.perms.give"))) {
            sender.sendMessage("§2System | §cYou don't have permission to use this command.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("§2System | §cUsage: /permsgive <player> <permission>");
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("§2System | §cPlayer not found.");
            return true;
        }

        String permission = args[1];
        PermissionAttachment attachment = attachments.computeIfAbsent(target.getUniqueId(), k -> target.addAttachment(plugin));
        attachment.setPermission(permission, true);
        target.recalculatePermissions();

        sender.sendMessage("§2System | §aPermission '" + permission + "' granted to " + target.getName() + ".");
        target.sendMessage("§2System | §aYou have been granted permission: " + permission);
        return true;
    }
}