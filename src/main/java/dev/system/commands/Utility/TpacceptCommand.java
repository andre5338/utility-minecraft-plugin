package dev.system.commands.Utility;

import dev.system.utils.TeleportRequestManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class TpacceptCommand implements CommandExecutor {

    private static final String PREFIX = "§2System | §c";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "Only players can use this command.");
            return true;
        }

        if (!TeleportRequestManager.hasRequest(p)) {
            p.sendMessage(PREFIX + "You have no pending teleport requests.");
            return true;
        }

        TeleportRequestManager.accept(p);
        return true;
    }
}