package com.iffyspeak.cp;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigLoaders {

    /*
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
     */

    public static void loadConfiguration(File _configuration) {
        if (!_configuration.exists())
        {
            _configuration.getParentFile().mkdirs();
            Bukkit.getPluginManager().getPlugin("ChatPings").saveResource("config.yml", false);
        }

        Main.configuration = new YamlConfiguration();
        try {
            Main.configuration.load(_configuration);
            Bukkit.getLogger().info("Loaded config");

            ConfigurationStuff.enable_pings = Main.getConfiguration().getBoolean("settings.enable_pings");
            ConfigurationStuff.ping_prefix = Main.getConfiguration().getString("settings.ping_prefix");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void loadUserPrefs(File _userprefs) {
        if (!_userprefs.exists())
        {
            _userprefs.getParentFile().mkdirs();
            Bukkit.getPluginManager().getPlugin("ChatPings").saveResource("userprefs.yml", false);
        }

        Main.userPrefs = new YamlConfiguration();
        try {
            Main.userPrefs.load(_userprefs);
            Bukkit.getLogger().info("Loaded user-prefs");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
