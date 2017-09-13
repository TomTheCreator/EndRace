package me.tomthedeveloper.endrace.events;

import me.tomthedeveloper.endrace.EndRace;
import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class DragonKillEventListener implements Listener {

    private EndRace plugin;

    public DragonKillEventListener(EndRace plugin){
        this.plugin = plugin;
    }


    public void onDragonDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof EnderDragon))
            return;
        if(event.getEntity().getKiller() == null){
            //TODO: Solve this
        }else{
            Player killer = event.getEntity().getKiller();
            Arena arena = plugin.getArenaManager().getArena(killer);
            if(arena ==null)
                return;
            arena.setWinner(killer);
            arena.getIngameManager().stop();
        }
    }

}
