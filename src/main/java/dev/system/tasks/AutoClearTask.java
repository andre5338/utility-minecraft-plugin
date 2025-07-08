package dev.system.tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoClearTask extends BukkitRunnable {

    private final JavaPlugin plugin;

    public AutoClearTask(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        int removed = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                    removed++;
                }
            }
        }
        Bukkit.broadcastMessage("§2System | §aCleared §e" + removed + " §aitems from the ground.");
    }

    public void start() {
        this.runTaskTimer(plugin, 0L, 6000L);
    }
}