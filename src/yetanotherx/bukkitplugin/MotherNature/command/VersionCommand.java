package yetanotherx.bukkitplugin.MotherNature.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;

public class VersionCommand implements CommandExecutor {

    private MotherNature parent;

    public VersionCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (MotherNaturePermissions.has((Player) sender, "mothernature.command.version", false)) {
            sender.sendMessage("You're running: " + ChatColor.AQUA.toString() + parent.getDescription().getName() + " version " + parent.getDescription().getVersion() );
        }

        return true;
    }

}