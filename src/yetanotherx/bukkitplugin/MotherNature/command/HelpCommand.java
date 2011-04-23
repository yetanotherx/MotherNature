package yetanotherx.bukkitplugin.MotherNature.command;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureHelp;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;

public class HelpCommand implements CommandExecutor {

    private MotherNature parent;

    public HelpCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

        if (MotherNaturePermissions.has(player, "mothernature.command.help", false)) {

            ArrayList<String> commands = MotherNatureHelp.getMessages(player);

            for (String commandString : commands) {
                player.sendMessage( commandString );
            }

        }
        else {
            player.sendMessage( ChatColor.RED + "You do not have permission to use this command." );
        }


        return true;

    }
}
