package me.tomthedeveloper.endrace.chat;

import me.tomthedeveloper.endrace.game.Arena;
import org.bukkit.entity.Player;

/**
 * Created by TomVerschueren on 23/08/2017.
 */
public enum  Messages {
    WAITING_FOR_MORE_PLAYERS_TO_JOIN("Waiting for % more players to join"),
    STARTING("The game has started! Good luck!"),
    PLAYER_WON_THE_GAME("Player % won the game!"),
    PLAYER_JOINED_THE_GAME("Player % joined the game!"),
    PLAYER_DIED("Player % died!");



    String sentence;

    Messages(String sentence, String... args){
        this.sentence = sentence;
    }


    public void sendMessage(Arena arena,String... args){
        for(String string: args){
            sentence = sentence.replaceFirst("%", string);
        }
        for(Player player:arena.getPlayers()){
            player.sendMessage(sentence);
        }
    }

    public void sendMessage(Player player,String... args){
        for(String string: args){
            sentence = sentence.replaceFirst("%", string);
        }
        player.sendMessage(sentence);
    }



}
