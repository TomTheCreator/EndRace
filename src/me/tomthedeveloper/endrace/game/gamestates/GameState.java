package me.tomthedeveloper.endrace.game.gamestates;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public enum GameState {
    WAITING_FOR_PLAYERS,STARTING,INGAME,ENDING,RESTARTING;

    public static GameState fromString(String s){
        if(s.contains("RESTARTING"))
            return RESTARTING;
        if(s.contains("WAITING_FOR"))
            return WAITING_FOR_PLAYERS;
        if(s.contains("STARTING"))
            return STARTING;
        if(s.contains("INGAME"))
            return INGAME;
        if(s.contains("ENDING"))
            return ENDING;
        return WAITING_FOR_PLAYERS;
    }
}
