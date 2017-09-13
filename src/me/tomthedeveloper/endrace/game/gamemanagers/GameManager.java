package me.tomthedeveloper.endrace.game.gamemanagers;

import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public abstract class GameManager extends BukkitRunnable {

    protected Arena arena;
    protected int timer;
    protected BukkitTask bukkitTask;

    public abstract void start();

    public GameManager(Arena arena){
        this.arena = arena;
    }



    public int getTimer(){
        return timer;
    }

    public void setTimer(int timer){
        this.timer = timer;
    }

}
