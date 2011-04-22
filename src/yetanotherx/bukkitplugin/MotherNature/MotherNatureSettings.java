package yetanotherx.bukkitplugin.MotherNature;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class MotherNatureSettings {

    /**
     * Settings
     */
    public static boolean debugMode = false;

    public static int rainLength = 2*60; //2 minutes
    public static int rainInterval = 10*60; //10 minutes
    //TODO: Add the actual settings code

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
            config.save();
        }

        setSettings();

        MotherNature.log.debug("Settings loaded");


    }

    /**
     * Sets the internal variables
     */
    private static void setSettings() {

        debugMode = config.getBoolean("modtrs.debug", false);
        
    }


}
