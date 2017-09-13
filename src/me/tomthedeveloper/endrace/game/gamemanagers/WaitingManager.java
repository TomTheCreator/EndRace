package me.tomthedeveloper.endrace.game.gamemanagers;

import com.mojang.authlib.GameProfile;
import me.tomthedeveloper.endrace.chat.Messages;
import me.tomthedeveloper.endrace.game.Arena;
import me.tomthedeveloper.endrace.game.gameconfig.GamePreferences;
import me.tomthedeveloper.endrace.game.gamestates.GameState;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class WaitingManager extends GameManager {



    public WaitingManager(Arena arena){
        super(arena);
    }


    public void start(){
        bukkitTask = this.runTaskTimer(arena.getPlugin(),5L,5L);
    }


    @Override
    public void run() {
        if(arena.getArenaPreferences().getMinPlayers() <= arena.getPlayers().size()){
            this.stop();
        }
        if(timer >= GamePreferences.ANNOUNCE_WAITING_MESSAGE_SECONDS){
            Messages.WAITING_FOR_MORE_PLAYERS_TO_JOIN.sendMessage(arena, Integer.toString(arena.getArenaPreferences().getMinPlayers()-arena.getPlayers().size()));
            timer =0;
        }
        setTimer(timer +5);

    }

    public void stop(){
        bukkitTask.cancel();
        arena.setGameState(GameState.STARTING);
        arena.update();
    }
}
