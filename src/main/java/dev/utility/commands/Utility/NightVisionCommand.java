package dev.utility.commands.Utility;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.*;

public class NightVisionCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("utility.nightvision")) {
            p.sendMessage("§2System | §cYou don’t have permission to use this command.");
            return true;
        }
        if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            p.sendMessage("§2System | §cNight Vision disabled.");
        } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1, false, false));
            p.sendMessage("§2System | §aNight Vision enabled.");
        }
        return true;
    }
}