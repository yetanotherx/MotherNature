package yetanotherx.bukkitplugin.MotherNature;

import java.util.HashMap;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import yetanotherx.bukkitplugin.MotherNature.listener.MotherNaturePlayerListener;

public class MotherNatureListeners {

    /**
     * Main plugin class
     */
    private MotherNature parent;
    /**
     * List of all event listeners
     */
    public HashMap<String, Listener> listeners = new HashMap<String, Listener>();

    public MotherNatureListeners(MotherNature parent) {
        this.parent = parent;
    }

    /**
     * Initialize the listeners and connect them to events
     */
    public static MotherNatureListeners load(MotherNature parent) {
        MotherNature.log.debug("Loading events and listeners");

        MotherNatureListeners listener = new MotherNatureListeners(parent);

        listener.registerListener("player", new MotherNaturePlayerListener(parent));

        listener.registerEvent(Event.Type.PLAYER_INTERACT, "player", Event.Priority.Monitor);
        listener.registerEvent(Event.Type.PLAYER_QUIT, "player", Event.Priority.Monitor);
        listener.registerEvent(Event.Type.PLAYER_TELEPORT, "player", Event.Priority.Monitor);
        listener.registerEvent(Event.Type.PLAYER_MOVE, "player", Event.Priority.Monitor);

        return listener;
    }

    /**
     * Add a new listener
     */
    public void registerListener(String name, Listener listener) {
        this.listeners.put(name, listener);
    }

    /**
     * Add a new event
     */
    public void registerEvent(Event.Type type, String listener, Event.Priority priority) {
        parent.getServer().getPluginManager().registerEvent(type, listeners.get(listener), priority, parent);
    }
}
