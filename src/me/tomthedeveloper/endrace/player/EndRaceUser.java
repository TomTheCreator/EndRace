package me.tomthedeveloper.endrace.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by TomVerschueren on 22/08/2017.
 */
public class EndRaceUser {

    private Player player = null;


    public void EndRaceUser(Player player){
        this.player = player;
    }

    public EndRaceUser(UUID uuid){
        player = Bukkit.getPlayer(uuid);
    }

    public Player getPlayer(){
        return player;
    }
}
