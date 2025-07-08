package dev.system.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public class SaplingGrowListener implements Listener {

    private final JavaPlugin plugin;

    public SaplingGrowListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSaplingPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Material type = block.getType();

        TreeType treeType = null;

        switch (type) {
            case OAK_SAPLING:
                treeType = TreeType.TREE;
                break;
            case SPRUCE_SAPLING:
                treeType = TreeType.REDWOOD;
                break;
            case BIRCH_SAPLING:
                treeType = TreeType.BIRCH;
                break;
            case JUNGLE_SAPLING:
                treeType = TreeType.JUNGLE;
                break;
            case ACACIA_SAPLING:
                treeType = TreeType.ACACIA;
                break;
            case DARK_OAK_SAPLING:
                treeType = TreeType.DARK_OAK;
                break;
            default:
                return;
        }

        final TreeType finalTreeType = treeType;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (block.getType() == type) {
                    block.getWorld().generateTree(block.getLocation(), finalTreeType);
                }
            }
        }.runTaskLater(plugin, 100L);
    }
}