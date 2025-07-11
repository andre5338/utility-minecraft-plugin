package dev.system.commands.Utility;

import dev.system.utils.TeleportRequestManager;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TpahereCommand implements CommandExecutor, TabCompleter {

    private static final String PREFIX = "§2System | §c";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "Only players can use this command.");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage(PREFIX + "Usage: /tpahere <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null || target == p) {
            p.sendMessage(PREFIX + "Player not found or invalid.");
            return true;
        }

        TeleportRequestManager.sendRequest(p, target, true);
        p.sendMessage("§aTeleport request sent to §e" + target.getName());
        target.sendMessage("§e" + p.getName() + "§a wants you to teleport to them. Use §6/tpaccept§a or §6/tpdeny");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.equals(sender)) completions.add(online.getName());
            }
        }
        return completions;
    }
}