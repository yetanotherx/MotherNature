package yetanotherx.bukkitplugin.MotherNature;

//Bukkit imports
import org.bukkit.plugin.java.JavaPlugin;
import yetanotherx.bukkitplugin.MotherNature.exception.ShutdownException;
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
    private Thread thread;

    /**
     * Outputs a message when disabled
     */
    @Override
    public void onDisable() {

        try {
        thread.interrupt();
            thread.join();
            log.info("Thread successfully joined.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


        log.info("Plugin disabled. (version " + this.getDescription().getVersion() + ")");
    }

    /**
     * Perform some massive loading action
     */
    @Override
    public void onEnable() {

        //try {
        MotherNatureSettings.load();

        MotherNaturePermissions.load(this);

        MotherNatureHelp.load(this);

        this.commandHandler = new MotherNatureCommand();
        this.commandHandler.addCommands(this);

        log.debug("Initiating threads");
        thread = new Thread( new MotherNatureThread(this), "mn_thread" );
        thread.start();

        //Print that the plugin has been enabled!
        log.info("Plugin enabled! (version " + this.getDescription().getVersion() + ")");
        log.debug("Debug mode enabled!");

        //}
	/*catch( ShutdownException e ) {
        log.severe("Caught a shutdown command! " + e.getMessage() );
        e.printStackTrace();
        this.getServer().getPluginManager().disablePlugin(this);
        return;
        }*/
    }
}
