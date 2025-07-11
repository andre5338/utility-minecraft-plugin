package dev.system.commands.Utility;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.*;

public class NightVisionCommand implements CommandExecutor {
    private static final String PREFIX = "§2System | ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "§cOnly players can use this command.");
            return true;
        }
        if (!p.hasPermission("system.nightvision")) {
            p.sendMessage(PREFIX + "§cYou don’t have permission to use this command.");
            return true;
        }

        Player target;
        if (args.length == 0) {
            target = p;
        } else if (args.length == 1) {
            target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                p.sendMessage(PREFIX + "§cPlayer not found.");
                return true;
            }
        } else {
            p.sendMessage(PREFIX + "§cUsage: /nightvision [player]");
            return true;
        }

        if (target.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            target.removePotionEffect(PotionEffectType.NIGHT_VISION);
            if (target.equals(p)) {
                p.sendMessage(PREFIX + "§cNight Vision disabled.");
            } else {
                p.sendMessage(PREFIX + "§aNight Vision disabled for " + target.getName() + ".");
                target.sendMessage(PREFIX + "§cNight Vision disabled by " + p.getName() + ".");
            }
        } else {
            target.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
            if (target.equals(p)) {
                p.sendMessage(PREFIX + "§aNight Vision enabled.");
            } else {
                p.sendMessage(PREFIX + "§aNight Vision enabled for " + target.getName() + ".");
                target.sendMessage(PREFIX + "§aNight Vision enabled by " + p.getName() + ".");
            }
        }
        return true;
    }
}