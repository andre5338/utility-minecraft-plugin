package dev.system;

import java.lang.reflect.Field;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.system.commands.Moderation.*;
import dev.system.commands.Utility.*;
import dev.system.listeners.*;
import dev.system.tasks.AutoClearTask;

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

        Bukkit.getConsoleSender().sendMessage("§a[System] - Plugin loaded successfully.");
        Bukkit.getConsoleSender().sendMessage("§a[System] - Registered " + registeredCommands + " commands:");
        Bukkit.getConsoleSender().sendMessage("§a[System] - Listeners loaded successfully.");
        Bukkit.getConsoleSender().sendMessage("§a[System] - Registered " + registeredListeners + " listeners.");
    }

    private void registerCommands() {
        registeredCommands = 0;

        register("fly", new FlyCommand());
        register("nightvision", new NightVisionCommand());
        register("gm", new GamemodeCommand());
        register("heal", new HealCommand());
        register("feed", new FeedCommand());
        register("god", new GodCommand(this));
        register("clearchat", new ClearChatCommand());
        register("permsgive", new PermsGiveCommand(this));
        register("help", new HelpCommand(this));
        register("announce", new AnnouncementCommand());
        register("tp", new TpCommand());
        register("tphere", new TphereCommand());
        register("ec", new EnderChestCommand());
        register("craft", new CraftCommand());
        register("vanish", new VanishCommand(this));
        register("enchant", new EnchantCommand());
        register("tpa", new TpaCommand());
        register("tpahere", new TpahereCommand());
        register("tpaccept", new TpacceptCommand());
        register("tpdeny", new TpdenyCommand());
        register("tptoggle", new TptoggleCommand());
    }

    private void register(String name, Object executor) {
        PluginCommand cmd = getCommand(name);
        if (cmd != null) {
            if (executor instanceof org.bukkit.command.CommandExecutor ce)
                cmd.setExecutor(ce);
            if (executor instanceof TabCompleter completer)
                cmd.setTabCompleter(completer);
            registeredCommands++;
        } else {
            Bukkit.getLogger().warning("Command /" + name + " not found in plugin.yml!");
        }
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
        } catch (Exception ignored) {}
    }

    public static system getInstance() {
        return instance;
    }
}