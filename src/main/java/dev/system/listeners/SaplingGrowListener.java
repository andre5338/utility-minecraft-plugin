package dev.system.listeners;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SaplingGrowListener implements Listener {

    private final JavaPlugin plugin;

    public SaplingGrowListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSaplingPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();

        TreeType treeType = switch (type) {
            case OAK_SAPLING -> TreeType.TREE;
            case SPRUCE_SAPLING -> TreeType.REDWOOD;
            case BIRCH_SAPLING -> TreeType.BIRCH;
            case JUNGLE_SAPLING -> TreeType.JUNGLE;
            case ACACIA_SAPLING -> TreeType.ACACIA;
            case DARK_OAK_SAPLING -> TreeType.DARK_OAK;
            default -> null;
        };

        if (treeType == null) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                Material currentType = block.getType();

                if (currentType.isAir() || !currentType.name().endsWith("LOG")) {
                    block.getWorld().generateTree(block.getLocation(), treeType);
                }
            }
        }.runTaskLater(plugin, 100L);
    }
}