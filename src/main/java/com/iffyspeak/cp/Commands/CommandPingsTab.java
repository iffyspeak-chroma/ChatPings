package com.iffyspeak.cp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class CommandPingsTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {

        final List<String> toReturn = Arrays.asList("");

        if (args.length == 1)
        {
            if (commandSender.hasPermission("iffyspeak.chatpings.version"))
            {
                toReturn.add("version");
            }
            if (commandSender.hasPermission("iffyspeak.chatpings.toggle"))
            {
                toReturn.add("toggle");
            }
        }
        return toReturn;

        /*

        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage(ChatColor.RED + "You cannot use this section of the command as console.");
        }

         ------------------------------------------------

                /chatpings : Will relay the version if they have permission. If not just say what the plugin is.
                /chatpings version : Relays just the version if they have permission.
                /chatpings toggle : Changes pingable status if they have permission.

         */
    }
}
