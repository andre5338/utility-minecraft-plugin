package dev.system.commands.Utility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }

        player.sendMessage("§8§m----------------------------------");
        player.sendMessage("§2System | §aUtility Plugin Commands:");
        player.sendMessage("§a/fly §7– Toggle fly mode");
        player.sendMessage("§a/nightvision §7– Toggle night vision");
        player.sendMessage("§a/gm <0|1|2|3> §7– Change your gamemode");
        player.sendMessage("§a/heal §7– Heal yourself");
        player.sendMessage("§a/feed §7– Feed yourself");
        player.sendMessage("§a/god §7– Toggle god mode");
        player.sendMessage("§a/permsgive <player> <permission> §7– Give permission to a player");
        player.sendMessage("§8§m----------------------------------");
        player.sendMessage("§2System | §eSupport: §bhttps://discord.gg/zQRYbaXgNa");
        return true;
    }
}