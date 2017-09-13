package me.tomthedeveloper.endrace.game.gamemanagers;

import me.tomthedeveloper.endrace.game.Arena;
import me.tomthedeveloper.endrace.game.gameconfig.GamePreferences;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class EndingManager extends GameManager {


    public EndingManager(Arena arena) {
        super(arena);
    }

    @Override
    public void start() {
        setTimer(GamePreferences.ENDING_TELEPORT_TIMER);
        this.runTaskTimer(arena.getPlugin(),20L,20L);
    }

    @Override
    public void run() {
        if(timer <= 0){
            arena.getTeleportationManager().teleportPlayers("ending");
        }

        timer--;
    }
}
