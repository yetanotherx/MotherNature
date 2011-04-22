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

public class RainCommand implements CommandExecutor {

    private MotherNature parent;

    public RainCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

        if (MotherNaturePermissions.has(player, "mothernature.command.rain")) {

            if (args.length > 1) {
                
            } else {
                player.getWorld().setStorm(true);
                MotherNatureThread.rainSteps = MotherNatureSettings.rainInterval;
                player.sendMessage( ChatColor.AQUA + "It is now raining!" );
            }


        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

        return true;

    }
}
