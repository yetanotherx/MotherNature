package yetanotherx.bukkitplugin.MotherNature;

import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yetanotherx.bukkitplugin.MotherNature.command.HelpCommand;
import yetanotherx.bukkitplugin.MotherNature.command.LightningCommand;
import yetanotherx.bukkitplugin.MotherNature.command.RainCommand;
import yetanotherx.bukkitplugin.MotherNature.command.ReloadCommand;
import yetanotherx.bukkitplugin.MotherNature.command.SunCommand;
import yetanotherx.bukkitplugin.MotherNature.command.ThunderCommand;
import yetanotherx.bukkitplugin.MotherNature.command.VersionCommand;

public class MotherNatureCommand implements CommandExecutor {

    private HashMap<String, CommandExecutor> executors = new HashMap<String, CommandExecutor>();

    public void registerExecutor(String subcmd, CommandExecutor cmd) {
        executors.put(subcmd.toLowerCase(), cmd);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        String commandName = command.getName().toLowerCase();

        if (sender instanceof Player) {
            if (commandName.equals("mn")) {
                if (args.length == 0) {
                    return false;
                }

                String subcommandName = args[0].toLowerCase();

                if (!executors.containsKey(subcommandName)) {
                    return false;
                }

                return executors.get(subcommandName).onCommand(sender, command, commandLabel, args);
            }
        }
        return false;
    }

    public void addCommands(MotherNature parent) {

        this.registerExecutor("version", new VersionCommand(parent));
        this.registerExecutor("reload", new ReloadCommand(parent));
        this.registerExecutor("help", new HelpCommand(parent));
        this.registerExecutor("rain", new RainCommand(parent));
        this.registerExecutor("thunder", new ThunderCommand(parent));
        this.registerExecutor("lightning", new LightningCommand(parent));
        this.registerExecutor("sun", new SunCommand(parent));


        parent.getCommand("mn").setExecutor(this);

    }
}
