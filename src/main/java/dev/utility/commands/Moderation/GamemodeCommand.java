package dev.utility.commands.Moderation;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;

public class GamemodeCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage("§2System | §cUsage: /gm <0|1|2|3>");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("utility.gamemode")) {
            p.sendMessage("§2System | §cYou don’t have permission to use this command.");
            return true;
        }
        switch (args[0]) {
            case "0": p.setGameMode(GameMode.SURVIVAL); break;
            case "1": p.setGameMode(GameMode.CREATIVE); break;
            case "2": p.setGameMode(GameMode.ADVENTURE); break;
            case "3": p.setGameMode(GameMode.SPECTATOR); break;
            default:
                p.sendMessage("§2System | §cUsage: /gm <0|1|2|3>");
                return true;
        }
        p.sendMessage("§2System | §7Gamemode set to §a" + p.getGameMode().toString());
        return true;
    }
}