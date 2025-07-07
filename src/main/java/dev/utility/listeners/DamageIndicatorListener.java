package dev.utility.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public class DamageIndicatorListener implements Listener {

    private final JavaPlugin plugin;

    public DamageIndicatorListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof Damageable)) return;

        double damage = event.getFinalDamage();
        Location loc = entity.getLocation().add(0, 1.8, 0);

        ArmorStand indicator = (ArmorStand) entity.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        indicator.setVisible(false);
        indicator.setMarker(true);
        indicator.setGravity(false);
        indicator.setCustomName("§c-" + String.format("%.1f", damage));
        indicator.setCustomNameVisible(true);
        indicator.setSmall(true);

        new BukkitRunnable() {
            double y = 0;
            @Override
            public void run() {
                if (y > 1.2 || indicator.isDead()) {
                    indicator.remove();
                    cancel();
                    return;
                }
                Location l = indicator.getLocation().add(0, 0.05, 0);
                indicator.teleport(l);
                y += 0.05;
            }
        }.runTaskTimer(plugin, 0, 2);
    }
}