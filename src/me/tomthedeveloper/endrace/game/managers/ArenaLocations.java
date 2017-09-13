package me.tomthedeveloper.endrace.game.managers;

import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class ArenaLocations {

    private HashMap<String, Location> teleportLocations = new HashMap<>();

    private List<Location> signLocations = new ArrayList<>();

    public Location getTeleportLocation(String string){
        if(!teleportLocations.containsKey(string))
            return null;
        return teleportLocations.get(string);
    }

    public void addTeleportLocation(String string,Location location){
        teleportLocations.put(string,location);
    }

    public void addSignLocation(Location location){
        signLocations.add(location);
    }

    public List<Location> getSignLocations(){
        return signLocations;
    }

    public void removeSignLocation(Location location){
        signLocations.remove(location);
    }

}
