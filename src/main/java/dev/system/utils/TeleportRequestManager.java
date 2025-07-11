package dev.system.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.system.commands.Utility.TptoggleCommand;

import java.util.HashMap;
import java.util.UUID;

public class TeleportRequestManager {

    private static final HashMap<UUID, TeleportRequest> requests = new HashMap<>();

    public static void sendRequest(Player requester, Player target, boolean teleportToRequester) {
    if (TptoggleCommand.isToggledOff(target)) {
        requester.sendMessage("§c" + target.getName() + " has disabled teleport requests.");
        return;
    }
    requests.put(target.getUniqueId(), new TeleportRequest(requester.getUniqueId(), teleportToRequester));
    }

    public static boolean hasRequest(Player target) {
        return requests.containsKey(target.getUniqueId());
    }

    public static void accept(Player target) {
        TeleportRequest request = requests.remove(target.getUniqueId());
        if (request != null) {
            Player requester = Bukkit.getPlayer(request.getRequester());
            if (requester != null && requester.isOnline()) {
                if (request.teleportToRequester()) {
                    target.teleport(requester.getLocation());
                    target.sendMessage("§aYou were teleported to " + requester.getName());
                    requester.sendMessage("§a" + target.getName() + " accepted your teleport request.");
                } else {
                    requester.teleport(target.getLocation());
                    target.sendMessage("§a" + requester.getName() + " was teleported to you.");
                    requester.sendMessage("§a" + target.getName() + " accepted your teleport request.");
                }
            }
        }
    }

    public static void deny(Player target) {
        TeleportRequest request = requests.remove(target.getUniqueId());
        if (request != null) {
            Player requester = Bukkit.getPlayer(request.getRequester());
            if (requester != null && requester.isOnline()) {
                target.sendMessage("§cYou denied the teleport request.");
                requester.sendMessage("§c" + target.getName() + " denied your teleport request.");
            }
        }
    }

    private static class TeleportRequest {
        private final UUID requester;
        private final boolean teleportToRequester;

        public TeleportRequest(UUID requester, boolean teleportToRequester) {
            this.requester = requester;
            this.teleportToRequester = teleportToRequester;
        }

        public UUID getRequester() {
            return requester;
        }

        public boolean teleportToRequester() {
            return teleportToRequester;
        }
    }
}