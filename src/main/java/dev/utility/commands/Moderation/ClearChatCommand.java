package dev.utility.commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }

        if (!p.isOp() && !p.hasPermission("utility.clearchat")) {
            p.sendMessage("§2System | §cYou don't have permission to use this command.");
            return true;
        }

        for (int i = 0; i < 100; i++) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(""));
        }

        Bukkit.getOnlinePlayers().forEach(player ->
                player.sendMessage("§2System | §aThe chat was cleared by §2" + p.getName() + "§a.")
        );

        return true;
    }
}