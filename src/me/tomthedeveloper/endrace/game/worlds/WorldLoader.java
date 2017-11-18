package me.tomthedeveloper.endrace.game.worlds;

import me.tomthedeveloper.endrace.EndRace;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

/**
 * Created by TomVerschueren on 14/09/2017.
 */
public class WorldLoader {

    private EndRace plugin;

    public WorldLoader(EndRace plugin){
        this.plugin = plugin;
    }


    public void copyWorlds(){
        try {
            FileUtils.copyDirectory(new File(plugin.getPluginLoader() + File.separator + "worlds"), new File("."));
        } catch (IOException e) {
            System.out.print("[ENDRACE] Copying worlds failed!");
            e.printStackTrace();
        }

    }

    public void loadWorldsGradually(){

    }


}
