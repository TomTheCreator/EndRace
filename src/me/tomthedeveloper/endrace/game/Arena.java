package me.tomthedeveloper.endrace.game;

import me.tomthedeveloper.endrace.EndRace;
import me.tomthedeveloper.endrace.chat.Messages;
import me.tomthedeveloper.endrace.game.gamemanagers.IngameManager;
import me.tomthedeveloper.endrace.game.gamemanagers.StartingManager;
import me.tomthedeveloper.endrace.game.gamemanagers.WaitingManager;
import me.tomthedeveloper.endrace.game.gamestates.GameState;
import me.tomthedeveloper.endrace.game.managers.ArenaLocations;
import me.tomthedeveloper.endrace.game.managers.TeleportationManager;
import me.tomthedeveloper.endrace.scoreboard.ScoreboardHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TomVerschueren on 22/08/2017.
 */
public class Arena {

    private String id;
    private List<Player> players = new ArrayList<>();
    private ArenaPreferences arenaPreferences;
    private EndRace plugin;
    private GameState gameState;
    private Player winner;

    private WaitingManager waitingManager;
    private StartingManager startingManager;
    private IngameManager ingameManager;
    private ScoreboardHandler scoreboardHandler;
    private TeleportationManager teleportationManager;
    private ArenaLocations arenaLocations;


    public Arena(EndRace endRace){
        this.plugin = endRace;
        scoreboardHandler = new ScoreboardHandler(this);
        arenaLocations = new ArenaLocations();
    }

    public void start(){
        waitingManager = new WaitingManager(this);
        startingManager = new StartingManager(this);
        this.update();

    }

    public void update() {
        switch (getGameState()){
            case WAITING_FOR_PLAYERS:
                waitingManager.start();
                break;
            case STARTING:
                startingManager.start();
                break;
            case INGAME:
                ingameManager.start();
        }

    }

    public TeleportationManager getTeleportationManager(){
        return teleportationManager;
    }

    public ScoreboardHandler getScoreboardHandler(){
        return scoreboardHandler;
    }

    public WaitingManager getWaitingManager() {
        return waitingManager;
    }

    public ArenaLocations getLocations(){
        return arenaLocations;
    }

    public StartingManager getStartingManager() {
        return startingManager;
    }

    public IngameManager getIngameManager() {
        return ingameManager;
    }

    public EndRace getPlugin(){
        return plugin;
    }

    public String getID(){
        return id;
    }

    public void joinAttempt(Player player){
        Messages.PLAYER_JOINED_THE_GAME.sendMessage(this,player.getName());
        players.add(player);
        getScoreboardHandler().updateScoreboard();
    }


    public void leaveAttempt(Player player){
        players.remove(player);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void clearPlayers(){
        players.clear();
    }

    public ArenaPreferences getArenaPreferences() {
        return arenaPreferences;
    }

    public void setArenaPreferences(ArenaPreferences arenaPreferences) {
        this.arenaPreferences = arenaPreferences;
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public GameState getGameState(){
        return gameState;
    }

    public void sendMessage(String message){
        for(Player player:getPlayers()){
            player.sendMessage(message);
        }
    }

    public void setWinner(Player player){
        this.winner = player;
    }

    public void resetWinner(){
        this.winner = null;
    }

    public Player getWinner(){
        return winner;
    }

    public List<Player> getPlayersLeft(){
        List<Player> returnList = new ArrayList<>();
        for(Player player:getPlayers()){
            if(player.getGameMode() != GameMode.SPECTATOR)
                returnList.add(player);
        }
        return returnList;
    }

}
