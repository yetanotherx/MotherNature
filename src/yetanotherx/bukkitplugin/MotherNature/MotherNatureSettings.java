package yetanotherx.bukkitplugin.MotherNature;

import java.io.File;
import java.util.HashMap;

import org.bukkit.util.config.Configuration;

public class MotherNatureSettings {

    /**
     * Settings
     */
    public static boolean debugMode = false;

    public static int rainLength = 2*60; //2 minutes
    public static int rainInterval = 10*60; //10 minutes
    public static int thunderLength = 1*60; //1 minutes
    public static int thunderInterval = 15*60; //16 minutes

    public static int lightningWand = 317;

    /**
     * Bukkit config class
     */
    public static Configuration config = null;

    /**
     * Load and parse the YAML config file
     */
    public static void load() {

        File dataDirectory = new File("plugins" + File.separator + "MotherNature" + File.separator);

        dataDirectory.mkdirs();

        File file = new File("plugins" + File.separator + "MotherNature", "config.yml");

        MotherNature.log.debug("Loading config file: " + file.getPath());

        config = new Configuration(file);
        config.load();

        if (!file.exists()) {
            MotherNature.log.debug("Config file not found, saving bare-bones file");

            config.setProperty("mothernature.debug", debugMode);
            
            HashMap<String, Integer> rainHash = new HashMap<String, Integer>();
            rainHash.put( "interval", rainInterval );
            rainHash.put( "duration", rainLength );
            config.setProperty("mothernature.rain", rainHash);

            HashMap<String, Integer> thunderHash = new HashMap<String, Integer>();
            thunderHash.put( "interval", thunderInterval );
            thunderHash.put( "duration", thunderLength );
            config.setProperty("mothernature.thunder", thunderHash);

            config.setProperty("mothernature.wand", lightningWand);
            
            config.save();
        }

        setSettings();

        MotherNature.log.debug("Settings loaded");


    }

    /**
     * Sets the internal variables
     */
    private static void setSettings() {

        debugMode = config.getBoolean("mothernature.debug", false);
        
        rainInterval = config.getInt("mothernature.rain.interval", rainInterval);
        rainLength = config.getInt("mothernature.rain.duration", rainLength);

        thunderInterval = config.getInt("mothernature.rain.interval", thunderInterval);
        thunderLength = config.getInt("mothernature.rain.duration", thunderLength);

        lightningWand = config.getInt("mothernature.wand", lightningWand);


    }


}
