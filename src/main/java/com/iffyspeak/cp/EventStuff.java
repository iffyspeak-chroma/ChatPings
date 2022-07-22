package com.iffyspeak.cp;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.*;

import static com.iffyspeak.cp.Main.saveUserPrefs;

public class EventStuff implements Listener {

    FileConfiguration _up = Main.getUserPrefs();

    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent _e)
    {
        // First lets just determine sender and recipient of ping.
        Player sender =  _e.getPlayer();
        Set<Player> messageRecipients = _e.getRecipients(); // This is everyone who will see the message
        ArrayList<Player> pingRecipients = new ArrayList<Player>();; // This is everyone who will get pinged in the message.


        String _c = _e.getMessage();
        String _cfinal = "";
        int pingCount = 0;

        for (Player p : Bukkit.getOnlinePlayers())
        {
            String uuidAsString = p.getPlayer().getUniqueId().toString();
            if (_c.contains(ConfigurationStuff.ping_prefix + p.getName()) && _up.getBoolean("user-prefs." + uuidAsString + ".enable_pings"))
            {
                pingRecipients.add(p);
                pingCount++;
            }
        }

        if (pingCount > 0)
        {
            //_e.setCancelled(true);
            // Now that we have the entire list of recipients that are willing to receive pings, lets modify the message
            // that'll be sent to those players

            for (Player p : pingRecipients)
            {
                String replacement = ChatColor.LIGHT_PURPLE + ConfigurationStuff.ping_prefix + p.getName() + ChatColor.RESET;
                _cfinal = _c.replace(ConfigurationStuff.ping_prefix + p.getName(), replacement);
            }

        /* I TOTALLY FORGOT I WANTED TO RESPECT PLAYERS THAT MAY NOT WANT TO SEE THIS MESSAGE. OOPS
        for (Player p : Bukkit.getOnlinePlayers())
        {
            if (pingRecipients.contains(p))
                p.sendMessage(_cfinal);
            else
                p.sendMessage(_c);
        }
         */
            /*

            THIS IS TO TEST AND SEE IF IT WORKS THROUGHOUT VARIOUS CHAT EDITING THINGS.

            for (Player p : messageRecipients)
            {
                if (pingRecipients.contains(p))
                    p.sendMessage(_cfinal);
                else
                    p.sendMessage(_c);
            }
            if (!pingRecipients.contains(_e.getPlayer()))
                sender.sendMessage(_cfinal);


            */

            _e.setMessage(_cfinal);
        }

    }

    @EventHandler
    public void onPlayerLogin(final PlayerLoginEvent _e)
    {
        String uuidAsString = _e.getPlayer().getUniqueId().toString();

        // Ensure the player isn't banned or anything beforehand
        if (_e.getResult() != PlayerLoginEvent.Result.ALLOWED)
            return;

        if(!_e.getPlayer().hasPlayedBefore() || !_up.contains("user-prefs." + uuidAsString))
        {
            // Add to this player to userprefs
            _up.set("user-prefs." + uuidAsString + ".name", _e.getPlayer().getName());
            _up.set("user-prefs." + uuidAsString + ".enable_pings", true);
            saveUserPrefs();
        }

    }

}
