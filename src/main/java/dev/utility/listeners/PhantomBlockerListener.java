package dev.utility.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PhantomBlockerListener implements Listener {

    @EventHandler
    public void onPhantomSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.PHANTOM) {
            event.setCancelled(true);
        }
    }
}