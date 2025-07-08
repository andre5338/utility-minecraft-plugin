package dev.system;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import dev.system.commands.Moderation.FlyCommand;
import dev.system.commands.Moderation.GamemodeCommand;
import dev.system.commands.Moderation.GodCommand;
import dev.system.commands.Moderation.PermsGiveCommand;
import dev.system.commands.Moderation.AnnouncementCommand;
import dev.system.commands.Moderation.TphereCommand;
import dev.system.commands.Moderation.TpCommand;
import dev.system.commands.Moderation.ClearChatCommand;
import dev.system.commands.Utility.FeedCommand;
import dev.system.commands.Utility.HealCommand;
import dev.system.commands.Utility.HelpCommand;
import dev.system.commands.Utility.NightVisionCommand;
import dev.system.listeners.*;
import dev.system.tasks.AutoClearTask;

import java.lang.reflect.Field;
import java.util.Map;

public class system extends JavaPlugin {

    private static system instance;
    private int registeredListeners;
    private int registeredCommands;

    @Override
    public void onEnable() {
        instance = this;
        unregisterDefaultHelpCommand();
        registerCommands();
        registerListeners();

        new AutoClearTask(this).start();

        Bukkit.getConsoleSender().sendMessage("§a[system] - Plugin loaded successfully.");
        Bukkit.getConsoleSender().sendMessage("§a[system] - Registered " + registeredCommands + " commands:");
        Bukkit.getConsoleSender().sendMessage("§a[system] - Listeners loaded successfully.");
        Bukkit.getConsoleSender().sendMessage("§a[system] - Registered " + registeredListeners + " listeners.");
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
        getCommand("announce").setExecutor(new AnnouncementCommand());
        getCommand("tp").setExecutor(new TpCommand());
        getCommand("tphere").setExecutor(new TphereCommand());

        registeredCommands = 13;
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new TreeMinerListener(), this);
        pm.registerEvents(new SaplingGrowListener(this), this);
        pm.registerEvents(new WelcomeListener(), this);
        pm.registerEvents(new LeaveListener(), this);
        pm.registerEvents(new PhantomBlockerListener(), this);
        pm.registerEvents(new DamageIndicatorListener(this), this);
        pm.registerEvents(new VeinMinerListener(), this);
        pm.registerEvents(new BlockBreakerListener(), this);

        registeredListeners = 8;
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

    public static system getInstance() {
        return instance;
    }
}