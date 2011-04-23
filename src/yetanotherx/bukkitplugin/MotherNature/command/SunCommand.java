package yetanotherx.bukkitplugin.MotherNature.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;
import yetanotherx.bukkitplugin.MotherNature.thread.MotherNatureThread;

public class SunCommand implements CommandExecutor {

    private MotherNature parent;

    public SunCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

	if( MotherNaturePermissions.has( player, "mothernature.command.sun" ) ) {

            player.getWorld().setStorm(false);
            player.getWorld().setThundering(false);

            MotherNatureThread.thunderSteps = 0;
            MotherNatureThread.rainSteps = 0;
            MotherNatureThread.thunderIntSteps = 0;
            MotherNatureThread.rainIntSteps = 0;

            player.sendMessage( ChatColor.AQUA + "The clouds clear!" );
            
        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

	return true;

    }

}
