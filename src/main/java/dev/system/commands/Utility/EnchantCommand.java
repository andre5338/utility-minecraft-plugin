package dev.system.commands.Utility;

import org.bukkit.command.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantCommand implements CommandExecutor, TabCompleter {

    private static final String PREFIX = "§2System | §c";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(PREFIX + "Only players can use this command.");
            return true;
        }

        if (!p.hasPermission("system.enchant")) {
            p.sendMessage(PREFIX + "You do not have permission to use this command.");
            return true;
        }

        if (args.length != 2) {
            p.sendMessage(PREFIX + "Usage: /enchant <Enchantment> <Level>");
            return true;
        }

        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(PREFIX + "You must hold an item in your main hand.");
            return true;
        }

        Enchantment enchantment = Enchantment.getByName(args[0].toUpperCase());
        if (enchantment == null) {
            p.sendMessage(PREFIX + "Invalid enchantment name.");
            return true;
        }

        int level;
        try {
            level = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            p.sendMessage(PREFIX + "Level must be a number.");
            return true;
        }

        item.addUnsafeEnchantment(enchantment, level);
        p.sendMessage("§2System | §aEnchanted " + enchantment.getKey().getKey() + " " + level + " to your item.");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>();
            for (Enchantment ench : Enchantment.values()) {
                list.add(ench.getKey().getKey().toLowerCase());
            }
            return list;
        }
        if (args.length == 2) {
            return List.of("1", "10", "100", "255");
        }
        return List.of();
    }
}