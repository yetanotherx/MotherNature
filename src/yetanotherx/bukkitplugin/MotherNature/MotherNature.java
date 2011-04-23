package yetanotherx.bukkitplugin.MotherNature;

//Bukkit imports
import com.Android.magiccarpet.Carpet;
import java.util.HashMap;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import yetanotherx.bukkitplugin.MotherNature.thread.MotherNatureThread;

public class MotherNature extends JavaPlugin {

    /**
     * ModTRS logger class
     */
    public static final MotherNatureLogging log = new MotherNatureLogging();

    /**
     * Command handler instance
     */
    private MotherNatureCommand commandHandler;

    /**
     * Thread process
     */
    private Thread thread;

    /**
     * Umbrella list
     */
    public static HashMap<String, Carpet> umbrellas = new HashMap<String, Carpet>();

    /**
     * Listener registration
     */
    public MotherNatureListeners listeners;

    /**
     * Outputs a message when disabled
     */
    @Override
    public void onDisable() {

        try {
            thread.interrupt();
            thread.join();
            log.info("Thread successfully joined.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("Plugin disabled. (version " + this.getDescription().getVersion() + ")");
    }

    /**
     * Perform some massive loading action
     *
     * 1. Load the config file
     * 2. Load the Permissions/GroupManager plugins
     * 3. Initialize the Help messages
     * 4. Initalize the CommandExecutors
     * 5. Start the weather thread
     * 6. Disable the vanilla Minecraft weather
     * 7. Initialize the event listeners
     * 
     */
    @Override
    public void onEnable() {

        MotherNatureSettings.load();

        MotherNaturePermissions.load(this);

        MotherNatureHelp.load(this);

        this.commandHandler = new MotherNatureCommand();
        this.commandHandler.addCommands(this);

        log.debug("Initiating threads");
        thread = new Thread(new MotherNatureThread(this), "mn_thread");
        thread.start();

        log.debug("Disabling vanilla weather");

        for (World world : getServer().getWorlds()) {
            world.setThunderDuration(0);
            world.setWeatherDuration(0);
            world.setStorm(false);
            world.setThundering(false);
        }

        this.listeners = MotherNatureListeners.load(this);

        //Print that the plugin has been enabled!
        log.info("Plugin enabled! (version " + this.getDescription().getVersion() + ")");
        log.debug("Debug mode enabled!");

    }
}
