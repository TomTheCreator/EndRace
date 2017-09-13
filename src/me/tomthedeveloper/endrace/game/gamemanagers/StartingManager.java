package me.tomthedeveloper.endrace.game.gamemanagers;

import me.tomthedeveloper.endrace.chat.Messages;
import me.tomthedeveloper.endrace.game.Arena;
import me.tomthedeveloper.endrace.game.gameconfig.GamePreferences;
import me.tomthedeveloper.endrace.game.gamestates.GameState;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class StartingManager extends GameManager {


    public StartingManager(Arena arena) {
        super(arena);
        timer = GamePreferences.STARTING_TIMER;
    }

    public void start(){
        bukkitTask = this.runTaskTimer(arena.getPlugin(),20L,20L);
    }

    @Override
    public void run() {
        if(getTimer() <= 0){
            Messages.STARTING.sendMessage(arena);
            this.stop();
        }
        arena.getScoreboardHandler().updateScoreboard();
        timer--;
    }


    public void stop(){
        bukkitTask.cancel();
        arena.setGameState(GameState.INGAME);
        arena.update();
        arena.getTeleportationManager().teleportPlayers("start");
    }
}
