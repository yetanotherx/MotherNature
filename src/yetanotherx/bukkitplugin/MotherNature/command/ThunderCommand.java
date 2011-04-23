package yetanotherx.bukkitplugin.MotherNature.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureSettings;
import yetanotherx.bukkitplugin.MotherNature.thread.MotherNatureThread;

public class ThunderCommand implements CommandExecutor {

    private MotherNature parent;

    public ThunderCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

	if( MotherNaturePermissions.has( player, "mothernature.command.thunder" ) ) {
            player.getWorld().setStorm(true);
            player.getWorld().setThundering(true);

            MotherNatureThread.thunderSteps = MotherNatureSettings.thunderInterval / 5;
            MotherNatureThread.rainSteps = MotherNatureSettings.rainInterval / 5;

            player.sendMessage( ChatColor.AQUA + "It is now thundering!" );
        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

	return true;

    }

}
