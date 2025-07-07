package dev.utility.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class MegaPickaxeListener implements Listener {

    private static final String ENCHANT_LORE = "3x3x3 Miner";

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasLore()) return;

        if (!item.getItemMeta().getLore().contains(ENCHANT_LORE)) return;

        Block origin = event.getBlock();

        int radius = 1;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block block = origin.getRelative(x, y, z);

                    if (block.getType() == Material.AIR) continue;
                    if (block.equals(origin)) continue;

                    block.breakNaturally(item);
                }
            }
        }
    }

    public static ItemStack createMegaPickaxeBook() {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName("MegaPickaxe Book");
        meta.setLore(Collections.singletonList(ENCHANT_LORE));
        book.setItemMeta(meta);
        return book;
    }
}