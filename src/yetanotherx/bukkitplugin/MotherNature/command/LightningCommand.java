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

        if (MotherNaturePermissions.has(player, "mothernature.command.lightning")) {

            if (args.length > 1) {
                Player target = player.getServer().getPlayer(args[1]);
                
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "That player is not online.");
                }

                player.getWorld().strikeLightning(target.getLocation());
                player.sendMessage(ChatColor.AQUA + "Boom! A bolt of lightning shoots from the sky and hits " + target.getName());
                target.sendMessage(ChatColor.DARK_RED + "You have been smited by " + player.getName() + "!");

            } else {
                return false;
            }


        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

        return true;

    }
}
