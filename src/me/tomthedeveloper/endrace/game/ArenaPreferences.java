package me.tomthedeveloper.endrace.game;

/**
 * Created by TomVerschueren on 22/08/2017.
 */
public class ArenaPreferences {


    private int minPlayers;
    private int maxPlayers;
    private String name;


    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
