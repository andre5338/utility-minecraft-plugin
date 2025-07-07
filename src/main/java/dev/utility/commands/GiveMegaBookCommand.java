package dev.utility.commands;

import dev.utility.listeners.MegaPickaxeListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveMegaBookCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("§2System | §cOnly players can use this command.");
            return true;
        }
        if (!p.isOp()) {
            p.sendMessage("§2System | §cOnly OP players can use this command.");
            return true;
        }

        ItemStack megaBook = MegaPickaxeListener.createMegaPickaxeBook();
        p.getInventory().addItem(megaBook);
        p.sendMessage("§2System | §aYou received the MegaPickaxe book!");
        return true;
    }
}