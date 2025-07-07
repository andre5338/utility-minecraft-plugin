package dev.utility;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import dev.utility.commands.Beta.GiveMegaBookCommand;
import dev.utility.commands.Moderation.FlyCommand;
import dev.utility.commands.Moderation.GamemodeCommand;
import dev.utility.commands.Moderation.GodCommand;
import dev.utility.commands.Moderation.PermsGiveCommand;
import dev.utility.commands.Moderation.AnnouncementCommand;
import dev.utility.commands.Moderation.TphereCommand;
import dev.utility.commands.Moderation.TpCommand;
import dev.utility.commands.Moderation.ClearChatCommand;
import dev.utility.commands.Utility.FeedCommand;
import dev.utility.commands.Utility.HealCommand;
import dev.utility.commands.Utility.HelpCommand;
import dev.utility.commands.Utility.NightVisionCommand;
import dev.utility.listeners.*;

import java.lang.reflect.Field;
import java.util.Map;

public class Utility extends JavaPlugin {

    private static Utility instance;

    @Override
    public void onEnable() {
        instance = this;
        unregisterDefaultHelpCommand();
        registerCommands();
        registerListeners();

        Bukkit.getConsoleSender().sendMessage("§a[Utility] Plugin loaded successfully.");
        Bukkit.getConsoleSender().sendMessage("§a[Utility] Registered commands:");
        for (String cmd : getDescription().getCommands().keySet()) {
            Bukkit.getConsoleSender().sendMessage(" - " + cmd);
        }
        Bukkit.getConsoleSender().sendMessage("§a[Utility] Registered listeners:");
        Bukkit.getConsoleSender().sendMessage(" - Loaded 5 Listeners");
    }

    private void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("nightvision").setExecutor(new NightVisionCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("god").setExecutor(new GodCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("permsgive").setExecutor(new PermsGiveCommand(this));
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("givemegabook").setExecutor(new GiveMegaBookCommand());
        getCommand("announce").setExecutor(new AnnouncementCommand());
        getCommand("tp").setExecutor(new TpCommand());
        getCommand("tphere").setExecutor(new TphereCommand());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new TreeMinerListener(), this);
        pm.registerEvents(new SaplingGrowListener(this), this);
        pm.registerEvents(new MegaPickaxeListener(), this);
        pm.registerEvents(new PhantomBlockerListener(), this);
        pm.registerEvents(new DamageIndicatorListener(this), this);
    }

    private void unregisterDefaultHelpCommand() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

            Field knownCommandsField = commandMap.getClass().getDeclaredField("knownCommands");
            knownCommandsField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, Command> knownCommands = (Map<String, Command>) knownCommandsField.get(commandMap);

            knownCommands.remove("help");
            knownCommands.remove("bukkit:help");
            knownCommands.remove("minecraft:help");
        } catch (Exception e) {}
    }

    public static Utility getInstance() {
        return instance;
    }
}