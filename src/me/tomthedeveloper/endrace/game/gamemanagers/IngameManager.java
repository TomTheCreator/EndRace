package me.tomthedeveloper.endrace.game.gamemanagers;

import me.tomthedeveloper.endrace.chat.Messages;
import me.tomthedeveloper.endrace.game.Arena;
import me.tomthedeveloper.endrace.game.gamestates.GameState;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class IngameManager extends GameManager {


    public IngameManager(Arena arena) {
        super(arena);
    }

    @Override
    public void run() {

    }

    public void start(){

    }


    //triggered by DragonKillEventListener
    public void stop(){
        Messages.PLAYER_WON_THE_GAME.sendMessage(arena,arena.getWinner().toString());
        arena.setGameState(GameState.ENDING);
        arena.update();

    }
}
