package dev.system.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakerListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block center = event.getBlock();
        ItemStack tool = player.getInventory().getItemInMainHand();

        if (tool == null || !tool.getType().name().endsWith("_PICKAXE")) return;
        if (tool.getEnchantmentLevel(Enchantment.DURABILITY) != 3) return;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block target = center.getRelative(x, y, z);
                    if (!target.equals(center) && target.getType().isBlock() && target.getType().isSolid() && target.getType() != Material.BEDROCK) {
                        target.breakNaturally(tool);
                    }
                }
            }
        }
    }
}