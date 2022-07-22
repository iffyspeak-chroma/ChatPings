package com.iffyspeak.cp.Commands;

import com.iffyspeak.cp.ConfigLoaders;
import com.iffyspeak.cp.ConfigurationStuff;
import com.iffyspeak.cp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

import static com.iffyspeak.cp.Main.configuration;
import static com.iffyspeak.cp.Main.saveUserPrefs;

public class CommandPings implements CommandExecutor {

    FileConfiguration _up = Main.getUserPrefs();
    boolean toggleLock = false;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        String uuidAsString = Bukkit.getOfflinePlayer(commandSender.getName()).getUniqueId().toString();

        /*
        if (args.length == 0)
        {
            commandSender.sendMessage(ChatColor.GREEN + "ChatPings written by iffyspeak");
            if (commandSender.hasPermission("iffyspeak.chatpings.version"))
            {
                commandSender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.YELLOW + ConfigurationStuff.version);
            }
            commandSender.sendMessage(
                    ChatColor.GREEN + "----------------- \n" +
                            "/pings version - States the version of the plugin\n" +
                            "/pings toggle - Toggles whether you can be pinged or not.");
        }*/
        /*
        if (args.length >= 1)
            argument = args[0];

        if (argument != null)
        {
            if (argument == "version")
            {
                commandSender.sendMessage(ChatColor.GREEN + "ChatPings written by iffyspeak");
                if (commandSender.hasPermission("iffyspeak.chatpings.version"))
                {
                    commandSender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.YELLOW + ConfigurationStuff.version);
                }
            } else

                if (argument == "toggle")
                {
                    if (!(commandSender instanceof Player))
                    {
                        commandSender.sendMessage(ChatColor.RED + "You cannot use this section of the command as console.");
                    }

                    if (!commandSender.hasPermission("iffyspeak.chatpings.toggle"))
                    {
                        commandSender.sendMessage(ChatColor.RED + "You do not have permission to change that.");
                    }

                    Player _p = (Player) commandSender;
                    String uuidAsString = _p.getPlayer().getUniqueId().toString();

                    if (_up.getBoolean("user-prefs." + uuidAsString + ".enable_pings"))
                    {
                        _up.set("user-prefs." + uuidAsString + ".enable_pings", false);
                        commandSender.sendMessage(
                                ChatColor.GREEN + "Successfully turned your pings" + ChatColor.RED + ChatColor.BOLD  + " OFF" + ChatColor.RESET + ChatColor.GREEN + "."
                        );
                        saveUserPrefs();
                    }
                    else
                    {
                        _up.set("user-prefs." + uuidAsString + ".enable_pings", true);
                        commandSender.sendMessage(
                                ChatColor.GREEN + "Successfully turned your pings" + ChatColor.DARK_GREEN + ChatColor.BOLD  + " ON" + ChatColor.RESET + ChatColor.GREEN + "."
                        );
                        saveUserPrefs();
                }
        }


        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(ChatColor.RED + "You cannot use this section of the command as console.");
        }

         ------------------------------------------------

                /chatpings : Will relay the version if they have permission. If not just say what the plugin is.
                /chatpings version : Relays just the version if they have permission.
                /chatpings toggle : Changes pingable status if they have permission.


    }
    */

            if (args.length == 0)
            {
                commandSender.sendMessage(ChatColor.GREEN + "ChatPings written by iffyspeak");
                if (commandSender.hasPermission("iffyspeak.chatpings.version"))
                {
                    commandSender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.YELLOW + ConfigurationStuff.version);
                }
            } else
            {
                if (args[0].equals("version"))
                {
                    commandSender.sendMessage(ChatColor.GREEN + "ChatPings written by iffyspeak");
                    if (commandSender.hasPermission("iffyspeak.chatpings.version"))
                    {
                        commandSender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.YELLOW + ConfigurationStuff.version);
                    }
                }

                if (args[0].equals("toggle"))
                {
                    if (commandSender instanceof Player)
                    {
                        if (commandSender.hasPermission("iffyspeak.chatpings.toggle"))
                        {
                            if (_up.getBoolean("user-prefs." + uuidAsString + ".enable_pings"))
                            {
                                // Turn pings off
                                _up.set("user-prefs." + uuidAsString + ".enable_pings", false);
                                saveUserPrefs();
                                commandSender.sendMessage(ChatColor.GREEN + "Turned chat pings " + ChatColor.RED + "OFF" + ChatColor.GREEN + ".");
                            }
                            else
                            {
                                if (!_up.getBoolean("user-prefs." + uuidAsString + ".enable_pings"))
                                {
                                    // Turn pings on
                                    _up.set("user-prefs." + uuidAsString + ".enable_pings", true);
                                    saveUserPrefs();
                                    commandSender.sendMessage(ChatColor.GREEN + "Turned chat pings " + ChatColor.RED + "ON" + ChatColor.GREEN + ".");
                                }
                            }
                        } else
                        {
                            commandSender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                        }
                    } else
                    {
                        commandSender.sendMessage(ChatColor.RED + "You cannot use this command in the console.");
                    }
                }

                if (args[0].equals("reload"))
                {
                    if (commandSender.hasPermission("iffyspeak.chatpings.reload"))
                    {
                        commandSender.sendMessage(ChatColor.YELLOW + "Attempting to reload ChatPings.");

                        /*
                        Method configMethod = null;
                        Method userPrefMethod = null;
                        Object t = null;
                        
                        try {
                            t = Class.forName("com.iffyspeak.cp.Main");
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        

                        try {
                            configMethod = Main.class.getMethod("loadConfiguration");
                            userPrefMethod = Main.class.getMethod("loadUserprefs");
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        
                        try {
                            configMethod.setAccessible(true);
                            userPrefMethod.setAccessible(true);
                            configMethod.invoke(t);
                            userPrefMethod.invoke(t);
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                         */

                        ConfigLoaders.loadConfiguration(Main.configurationFile);
                        ConfigLoaders.loadUserPrefs(Main.userPrefsFile);
                        
                        commandSender.sendMessage(ChatColor.GREEN + "Successfully reloaded ChatPings.");
                    } else
                    {
                        commandSender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    }
                }
            }

        return false;
    }
}
