package me.tomthedeveloper.endrace.events;

import me.tomthedeveloper.endrace.EndRace;
import me.tomthedeveloper.endrace.chat.ChatManager;
import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Sign;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class SignsListener implements Listener {

    private EndRace plugin;

    public SignsListener(EndRace plugin){
        this.plugin = plugin;
    }


    public void onSignClick(PlayerInteractEvent event){
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Block block = event.getClickedBlock();
        if(block.getType() != Material.SIGN || block.getType() != Material.SIGN_POST || block.getType() != Material.WALL_SIGN)
            return;
        if(!(block.getState() instanceof Sign))
            return;
        Arena arena = plugin.getArenaManager().getArenaFromSignLocation(block.getLocation());
        if(arena == null)
            return;
        arena.joinAttempt(event.getPlayer());
    }

    public void onSignCreate(SignChangeEvent event){
        if(event.getLines()[0] == null)
            return;
        if(!event.getLines()[0].equalsIgnoreCase("[EndRace]"))
            return;
        if(event.getLines()[1] == null)
            return;
        if(!event.getPlayer().isOp()){
            ChatManager.sendFailMessage(event.getPlayer(), "You need OP to perform this action! Sign Location not added.");
            return;
        }
        Arena arena = plugin.getArenaManager().getArena(event.getLine(1)) ;
        if(arena == null){
            ChatManager.sendFailMessage(event.getPlayer(), "An arena with the name " + event.getLine(1) + " is non-existent!");

        }else{
            arena.getLocations().addSignLocation(event.getBlock().getLocation());
            ChatManager.sendSuccesMessage(event.getPlayer(),"New sign location added for arena  " + event.getLine(1) + "!");

        }

    }


}
