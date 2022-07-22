package com.iffyspeak.cp;

import com.iffyspeak.cp.Commands.CommandPings;
import com.iffyspeak.cp.Commands.CommandPingsTab;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    public static File configurationFile;
    public static File userPrefsFile;

    public static FileConfiguration configuration;
    public static FileConfiguration userPrefs;


    @Override
    public void onEnable() {
        Bukkit.getLogger().info("ChatPings by iffyspeak");
        Bukkit.getLogger().info("Loading configuration.");

        loadConfiguration();
        loadUserprefs();

        getServer().getPluginManager().registerEvents(new EventStuff(), this);
        this.getCommand("pings").setExecutor(new CommandPings());
        //this.getCommand("pings").setTabCompleter(new CommandPingsTab()); Stupid piece of shit. I fucking hate TabCompleters.

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Thank you for using ChatPings by iffyspeak.");
        Bukkit.getLogger().info("Disabling!");
    }

    public static FileConfiguration getConfiguration() {
        return configuration;
    }

    public static FileConfiguration getUserPrefs()
    {
        return userPrefs;
    }

    public void loadConfiguration() {
        configurationFile = new File(getDataFolder(), "config.yml");
        if (!configurationFile.exists())
        {
            configurationFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        configuration = new YamlConfiguration();
        try {
            configuration.load(configurationFile);
            Bukkit.getLogger().info("Loaded config");

            ConfigurationStuff.enable_pings = getConfiguration().getBoolean("settings.enable_pings");
            ConfigurationStuff.ping_prefix = getConfiguration().getString("settings.ping_prefix");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadUserprefs() {
        userPrefsFile = new File(getDataFolder(), "userprefs.yml");
        if (!userPrefsFile.exists())
        {
            userPrefsFile.getParentFile().mkdirs();
            saveResource("userprefs.yml", false);
        }

        userPrefs = new YamlConfiguration();
        try {
            userPrefs.load(userPrefsFile);
            Bukkit.getLogger().info("Loaded user-prefs");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void saveUserPrefs()
    {
        try {
            userPrefs.save(userPrefsFile);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
