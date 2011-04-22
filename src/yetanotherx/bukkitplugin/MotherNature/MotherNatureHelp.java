package yetanotherx.bukkitplugin.MotherNature;

import java.util.ArrayList;
import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

/**
 * Fix help to only show commands the user has permission to use.
 */
public class MotherNatureHelp {

    /**
     * List of all help messages
     *
     * String[0] = Command syntax
     * String[1] = Description
     * String[2] = Necessary permission
     */
    private static ArrayList<String[]> helpCommands = new ArrayList<String[]>();

    /**
     * Registers all the help commands
     */
    public static void load(MotherNature parent) {

        MotherNature.log.debug("Loading help messages");

        helpCommands.add(new String[]{"mn help", "Show this message", "mothernature.command.help", "anyone"});
        helpCommands.add(new String[]{"mn version", "Show the current MotherNature version", "mothernature.command.version", "anyone"});
        helpCommands.add(new String[]{"mn reload", "Reload the MotherNature config", "mothernature.command.reload"});
        helpCommands.add(new String[]{"mn rain", "Tells the current world to rain", "mothernature.command.rain"});
        helpCommands.add(new String[]{"mn thunder", "Tells the current world to thunder", "mothernature.command.thunder"});
        helpCommands.add(new String[]{"mn lightning", "Make a bolt of lightning!", "mothernature.command.lightning"});
        helpCommands.add(new String[]{"mn sun", "Tells the current world to be sunny", "mothernature.command.sun"});

    }

    /**
     * Returns a list of commands that a player can use.
     */
    public static ArrayList<String> getMessages(Player player) {

        ArrayList<String> messages = new ArrayList<String>();

        for (String[] command : helpCommands) {
            //The fourth field is added to ones that non-ops can use when using ops.txt
            if (command.length > 3) {
                if (MotherNaturePermissions.has(player, command[2], false)) {
                    messages.add(ChatColor.WHITE + "/" + command[0] + ChatColor.GRAY + " - " + ChatColor.YELLOW + command[1]);
                }
            } else {
                if (MotherNaturePermissions.has(player, command[2])) {
                    messages.add(ChatColor.WHITE + "/" + command[0] + ChatColor.GRAY + " - " + ChatColor.YELLOW + command[1]);
                }
            }
        }

        return messages;

    }
}
