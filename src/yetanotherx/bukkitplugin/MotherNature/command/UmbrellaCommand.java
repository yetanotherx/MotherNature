package yetanotherx.bukkitplugin.MotherNature.command;

import com.Android.magiccarpet.Carpet;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;

public class UmbrellaCommand implements CommandExecutor {

    private MotherNature parent;

    public UmbrellaCommand(MotherNature parent) {
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {

        Player player = (Player) sender;

        if (MotherNaturePermissions.has(player, "mothernature.command.umbrella")) {

            if (MotherNature.umbrellas.get(player.getName()) == null) {
                int size = 5;

                if (args.length > 1) {
                    try {
                        size = Integer.valueOf(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Correct usage is: /mn umbrella (size). The size is optional, and can only be 3, 5, or 7!");
                        return false;
                    }

                    if (size != 3 && size != 5 && size != 7) {
                        player.sendMessage(ChatColor.RED + "The size can only be 3, 5, or 7. Please enter a proper number");
                        return false;
                    }
                }

                Carpet newCarpet = new Carpet(true);

                Location tempLocation = player.getLocation();
                tempLocation.setY(tempLocation.getBlockY() + 4);

                newCarpet.currentBlock = tempLocation.getBlock();
                newCarpet.setSize(size);
                newCarpet.setLights(true);


                player.sendMessage( ChatColor.AQUA + "Your head is now dry!" );

                MotherNature.umbrellas.put(player.getName(), newCarpet);
            } else {
                MotherNature.umbrellas.get(player.getName()).removeCarpet();
                MotherNature.umbrellas.remove(player.getName());

               player.sendMessage( ChatColor.AQUA + "The umbrella is now gone!");
            }


        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
        }

        return true;

    }
}
