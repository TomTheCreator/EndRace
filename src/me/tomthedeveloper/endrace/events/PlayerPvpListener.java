package me.tomthedeveloper.endrace.events;

import me.tomthedeveloper.endrace.EndRace;
import me.tomthedeveloper.endrace.chat.Messages;
import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class PlayerPvpListener implements Listener {

    private EndRace plugin;

    public PlayerPvpListener(EndRace plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event){
        Arena arena = plugin.getArenaManager().getArena(event.getEntity());
        if(arena == null)
            return;
        arena.getTeleportationManager().teleportPlayer(event.getEntity(),"start");
        Messages.PLAYER_DIED.sendMessage(arena,event.getEntity().getName());
        event.getEntity().setHealth(event.getEntity().getMaxHealth());
    }
}
