package me.tomthedeveloper.endrace.game;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class ArenaManager {


    private List<Arena> arenas = new ArrayList<Arena>();

    public List<Arena> getArenas() {
        return arenas;
    }

    public ArenaManager() {

    }



    public boolean isInArena(Player p){
        boolean b = false;
        for(Arena gameInstance: arenas){
            if(gameInstance.getPlayers().contains(p)){
                b = true;
                break;
            }
        }
        return b;
    }

    public Arena getArena(Player p){
        Arena returnArena = null;
        if(p == null)
            return  null;
        if(!p.isOnline())
            return  null;

        for(Arena arena:arenas){
            for(Player player: arena.getPlayers()){
                if(player.getUniqueId() == p.getUniqueId()) {
                    returnArena = arena;
                    break;
                }
            }



        }


        return returnArena;
    }

    public void registerArena(Arena arena){
        arenas.add(arena);
    }

    public Arena getArena(String ID) {
        Arena GameInst = null;
        for (Arena arena : arenas) {
            if (arena.getID() == (ID)) {
                GameInst = arena;
                break;
            }
        }


        return GameInst;

    }

    public Arena getArenaFromSignLocation(Location location){
        for(Arena arena:arenas){
            for(Location loc:arena.getLocations().getSignLocations())
                if(loc.getX() == location.getX() && loc.getY() == location.getY() && loc.getZ() == location.getZ())
                    return arena;
        }
        return null;
    }
}
