package me.tomthedeveloper.endrace.game.gameevents;

import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;



public class EndRacePlayerJoinEvent extends Event implements Cancellable {


    private final Arena arena;
    private boolean cancelled;
    private Player player;


    private static final HandlerList HANDLERS = new HandlerList();


    public EndRacePlayerJoinEvent(Arena arena, Player player) {
        this.arena = arena;
        this.player = player;
    }



    public Arena getArena() {
        return arena;
    }



    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }


    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}