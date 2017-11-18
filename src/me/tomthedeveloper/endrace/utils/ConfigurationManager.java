package me.tomthedeveloper.endrace.utils;

import java.io.*;


import me.tomthedeveloper.endrace.EndRace;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class ConfigurationManager {


    public static JavaPlugin plugin;


    public static File getFile(String filename){
        return new File(plugin.getDataFolder()+ File.separator + filename +".yml");
    }

    public static void init(JavaPlugin pl){
        plugin = pl;
    }

    public static FileConfiguration getConfig(String filename){
        File configFile = new File(plugin.getDataFolder()+ File.separator + filename +".yml");
        if(!configFile.exists()){

            try {
                plugin.getLogger().info("Creating "+filename+".yml because it does not exist!");
                configFile.createNewFile();
            } catch (IOException ex) {
                //	Logger.getLogger(PixelVaults.plugin.class.getName()).log(Level.SEVERE, null, ex);
            }


            configFile = new File(plugin.getDataFolder(), filename+".yml");
            YamlConfiguration config = loadConfig(configFile);

            try {
                config.save(configFile);

            } catch (IOException ex) {

            }


        }
        return loadConfig(configFile);
    }


    /**
     *
     * @param configFile
     * @return YamlConfiguration, returns null if InvalidConfigurationException Occurs
     */
    private static YamlConfiguration loadConfig(File configFile) {
        YamlConfiguration config = new YamlConfiguration();
        try {

            config.load(configFile);
        } catch (InvalidConfigurationException e) {
            config = null;
            //TODO: Print error message to inform the users.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public void pasteConfig(String filename) {

        File file = new File(plugin.getDataFolder() + File.separator + "scoreboard.yml");
        if (!file.exists()) {
            System.out.print("Creating new file " + filename +".yml");
            System.out.print("Writing to file " + filename +".yml");

            InputStream inputStream = EndRace.class.getResourceAsStream("/" + filename + ".yml");
            OutputStream outputStream = null;
            try {
                outputStream =
                        new FileOutputStream(file);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                System.out.println("Done!");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        }
    }


    public static void saveLoc(String path, Location loc, FileConfiguration configuration) {
        String location = loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
        configuration.set(path, location);
    }



    public static Location getLocation(String path, FileConfiguration configuration) {
        String[] loc = configuration.getString(path).split("\\,");
        plugin.getServer().createWorld(new WorldCreator(loc[0]));
        World w = plugin.getServer().getWorld(loc[0]);
        Double x = Double.parseDouble(loc[1]);
        Double y = Double.parseDouble(loc[2]);
        Double z = Double.parseDouble(loc[3]);
        float yaw = Float.parseFloat(loc[4]);
        float pitch = Float.parseFloat(loc[5]);
        Location location = new Location(w, x, y, z, yaw, pitch);
        return location;
    }

}