package me.tomthedeveloper.endrace;

import me.tomthedeveloper.endrace.game.ArenaManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by TomVerschueren on 30/07/2017.
 */
public class EndRace extends JavaPlugin {

    private ArenaManager arenaManager;

        public void onDisable(){

            try {
                this.getServer().unloadWorld("world",true);
                FileManager.delete(this.getServer().getWorld("world").getWorldFolder());
                FileManager.delete(this.getServer().getWorld("world_nether").getWorldFolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public ArenaManager getArenaManager(){
            return arenaManager;
        }
}
