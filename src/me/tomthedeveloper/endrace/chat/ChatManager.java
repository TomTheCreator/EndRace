package me.tomthedeveloper.endrace.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by TomVerschueren on 23/08/2017.
 */
public class ChatManager {

    private static String prefix = ChatColor.GOLD + "[EndRace] ";

    public static void sendSuccesMessage(Player player, String string){
        player.sendMessage(prefix + ChatColor.GREEN + string);
    }

    public static void sendFailMessage(Player player, String string){
        player.sendMessage(prefix + ChatColor.RED + string);
    }

}
