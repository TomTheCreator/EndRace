package me.tomthedeveloper.endrace.scoreboard;

import me.tomthedeveloper.endrace.game.gamestates.GameState;
import org.bukkit.ChatColor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by TomVerschueren on 13/09/2017.
 */
public class ScoreboardTemplate {

    private LinkedList<String> lines = new LinkedList<String>();
    private String title = null;


    public List<String> getLines() {
        return lines;
    }

    public void setLines(LinkedList<String> lines) {
        this.lines = lines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = ChatColor.translateAlternateColorCodes('&',title);
    }

    public void addLine(String string){
        lines.add(ChatColor.translateAlternateColorCodes('&', string));
    }

    public void clearLines(){
        lines.clear();
    }


}
