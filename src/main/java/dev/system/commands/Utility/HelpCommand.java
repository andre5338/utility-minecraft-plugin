package dev.system.commands.Utility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class HelpCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public HelpCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }

        Map<String, Map<String, Object>> commands = plugin.getDescription().getCommands();

        p.sendMessage("§8§m----------------------------------");
        p.sendMessage("§2System | Commands:");

        for (Map.Entry<String, Map<String, Object>> entry : commands.entrySet()) {
            String cmdName = entry.getKey();
            Map<String, Object> info = entry.getValue();

            String description = info.getOrDefault("description", "").toString();
            String usage = info.getOrDefault("usage", "/" + cmdName).toString();

            String permission = info.getOrDefault("permission", "").toString();
            if (!permission.isEmpty() && !p.hasPermission(permission)) continue;

            p.sendMessage("§a" + usage + " §7- " + description);
        }

        p.sendMessage("§8§m----------------------------------");
        p.sendMessage("§2System | §eSupport: §bhttps://discord.gg/zQRYbaXgNa");

        return true;
    }
}