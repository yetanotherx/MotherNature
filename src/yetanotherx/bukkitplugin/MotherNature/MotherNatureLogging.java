package yetanotherx.bukkitplugin.MotherNature;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MotherNatureLogging {

    public static final Logger logger = Logger.getLogger("Minecraft");

    public void info( String s ) {
	logger.log(Level.INFO, "[MotherNature] " + s);
    }

    public void debug( String s ) {
	if( MotherNatureSettings.debugMode ) {
	    logger.log(Level.INFO, "[MotherNature DEBUG] " + s);
	}
    }

    public void severe( String s ) {
	logger.log(Level.SEVERE, "[MotherNature] " + s);
    }

    public void warning( String s ) {
	logger.log(Level.WARNING, "[MotherNature] " + s);
    }

}
