package me.tomthedeveloper.endrace.scoreboard;

import me.tomthedeveloper.endrace.game.Arena;
import me.tomthedeveloper.endrace.game.ArenaPreferences;
import me.tomthedeveloper.endrace.game.gamestates.GameState;
import me.tomthedeveloper.endrace.utils.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class ScoreboardHandler {

    private ScoreboardManager scoreboardManager;
    private Arena arena;

    public ScoreboardHandler(Arena arena){
        this.arena = arena;
        scoreboardManager = Bukkit.getScoreboardManager();
        loadTemplates();
    }


    private HashMap<GameState,ScoreboardTemplate> scoreboardTemplates = new HashMap<>();

    public ScoreboardTemplate getTemplate(GameState gameState){
        if(scoreboardTemplates.containsKey(gameState))
            return scoreboardTemplates.get(gameState);
        return null;
    }

    public void updateScoreboard(){
        updateTemplate(arena.getGameState());
        for(Player player:arena.getPlayers()){
            Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            scoreboard.registerNewObjective("1", "dummy");
            Objective objective = scoreboard.getObjective("1");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

             objective.setDisplayName(formatString(getTemplate(arena.getGameState()).getTitle(),player));
            int i = getTemplate(arena.getGameState()).getLines().size();
            for(String string: getTemplate(arena.getGameState()).getLines()){
                 Score score = objective.getScore(formatString(string,player));
                score.setScore(i);
                 i--;

            }
        }
    }



    private void updateTemplate(GameState gameState){
        LinkedList<String> newLines = new LinkedList<>();
        for(String string: getTemplate(gameState).getLines()){
            newLines.add(formatString(string,gameState));
        }
        getTemplate(gameState).setLines(newLines);
        getTemplate(gameState).setTitle(formatString(getTemplate(gameState).getTitle(),gameState));

    }

    private String formatString(String string,GameState gameState){
        switch (gameState) {
            case WAITING_FOR_PLAYERS:
                break;
            case STARTING:
                string = string.replaceAll("%TIMER%", Integer.toString(arena.getStartingManager().getTimer()));
                break;
            case INGAME:
                break;
        }
        string = string.replace("%MIN_PLAYERS%", Integer.toString(arena.getArenaPreferences().getMinPlayers()))
                    .replace("%MAX_PLAYERS%", Integer.toString(arena.getArenaPreferences().getMaxPlayers()))
                    .replace("%PLAYERS%", Integer.toString(arena.getPlayers().size()));
        return string;
    }

    private String formatString(String string,Player player){
        return string.replace("%PLAYER%", player.getName());
    }

    private void loadTemplates(){
        FileConfiguration config = ConfigurationManager.getConfig("scoreboard");
        for(String section: config.getConfigurationSection("scoreboard").getKeys(false)){
            ScoreboardTemplate scoreboardLines = new ScoreboardTemplate();
            for(String line:config.getStringList("scoreboard." + section + ".lines")){
                scoreboardLines.addLine(line);
            }
            scoreboardLines.setTitle(config.getString("scoreboard." + section + ".title"));
            scoreboardTemplates.put(GameState.fromString(section),scoreboardLines);
        }
    }

}
