package yetanotherx.bukkitplugin.MotherNature.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureSettings;

public class ReloadCommand implements CommandExecutor {

    private MotherNature parent;

    public ReloadCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

	if( MotherNaturePermissions.has( player, "mothernature.command.reload" ) ) {
            MotherNatureSettings.load();
            player.sendMessage( ChatColor.AQUA + "Mother Nature Settings reloaded" );
        }

	return true;

    }

}