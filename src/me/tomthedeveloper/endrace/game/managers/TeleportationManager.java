package me.tomthedeveloper.endrace.game.managers;

import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class TeleportationManager {

    private ArenaLocations arenaLocations;
    private Arena arena;

    public TeleportationManager(Arena arena){
       this.arena = arena;
        this.arenaLocations = arena.getLocations();
    }

    public void teleportPlayers(String location){
        Location toLoc = arenaLocations.getTeleportLocation(location);
        for(Player player:arena.getPlayers()){
            player.teleport(toLoc);
        }
    }

    public void teleportPlayer(Player player, String location){
        player.teleport(arenaLocations.getTeleportLocation(location));
    }
}
