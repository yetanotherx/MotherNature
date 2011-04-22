package yetanotherx.bukkitplugin.MotherNature.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;

public class LightningCommand implements CommandExecutor {

    private MotherNature parent;

    public LightningCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

	if( MotherNaturePermissions.has( player, "mothernature.command.thunder" ) ) {
            return true;

            //player.sendMessage( ChatColor.AQUA + "It is now thundering!" );
        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

	return true;

    }

}
