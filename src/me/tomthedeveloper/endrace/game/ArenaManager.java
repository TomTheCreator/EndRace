package me.tomthedeveloper.endrace.game;

import me.tomthedeveloper.endrace.EndRace;
import me.tomthedeveloper.endrace.utils.ConfigurationManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class ArenaManager {


    private EndRace plugin;
    private List<Arena> arenas = new ArrayList<Arena>();

    public List<Arena> getArenas() {
        return arenas;
    }

    public ArenaManager(EndRace plugin) {
        this.plugin = plugin;
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

    public void loadArenasFromConfig(){
        FileConfiguration config = ConfigurationManager.getConfig("arenas.yml");
        if(config == null){
            System.out.print("Cannot load arenas.yml. There must be a format mistake in the file! Please recheck the file.");
            System.out.print("Aborting plugin.");
            return;
        }
        for(String key:config.getKeys(false)){
            Arena arena = new Arena(plugin);
            ArenaPreferences arenaPreferences = new ArenaPreferences();
            arenaPreferences.setName(key);
            arenaPreferences.setMinPlayers(config.getInt(key + ".min-players"));
            arenaPreferences.setMaxPlayers(config.getInt(key + ".max-players"));
            arena.setArenaPreferences(arenaPreferences);
            for(String locKey:config.getConfigurationSection(key + ".teleport-locations").getKeys(false)){
                Location location = ConfigurationManager.getLocation(key + ".teleport-locations." + locKey, config);
                arena.getLocations().addTeleportLocation(locKey,location);
            }
        }

    }
}
