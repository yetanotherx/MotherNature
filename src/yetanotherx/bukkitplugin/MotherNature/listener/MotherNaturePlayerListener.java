package yetanotherx.bukkitplugin.MotherNature.listener;

import com.Android.magiccarpet.Carpet;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureSettings;

public class MotherNaturePlayerListener extends PlayerListener {

    public MotherNaturePlayerListener(MotherNature parent) {
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.hasItem() && event.getItem().getTypeId() == MotherNatureSettings.lightningWand) {
                if (MotherNaturePermissions.has(event.getPlayer(), "mothernature.wand")) {
                    event.getPlayer().getWorld().strikeLightning(event.getPlayer().getTargetBlock(null, 500).getLocation());
                }
            }
        }
        
    }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        Carpet carpet = MotherNature.umbrellas.get(event.getPlayer().getName());
        
        if (carpet != null) {
            carpet.removeCarpet();
        }

    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {

        Location to = event.getTo().clone();
        Location from = event.getFrom().clone();
        Player player = event.getPlayer();

        Carpet carpet = (Carpet) MotherNature.umbrellas.get(player.getName());
        if (carpet == null) {
            return;
        }

        to.setY(to.getY() + 4);
        from.setY(from.getY() + 4);

        if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ()) {
            return;
        }

        carpet.removeCarpet();
        
        if (MotherNaturePermissions.has(player, "mothernature.command.umbrella")) {
            carpet.currentBlock = to.getBlock();
            carpet.drawCarpet();
        } else {
            MotherNature.umbrellas.remove(player.getName());
        }
    }

    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event) {

        Location to = event.getTo().clone();
        Player player = event.getPlayer();
        
        Carpet carpet = (Carpet) MotherNature.umbrellas.get(player.getName());
        if (carpet == null) {
            return;
        }

        to.setY(to.getY() + 4);
        Location last = carpet.currentBlock.getLocation();

        if (last.getBlockX() == to.getBlockX() && last.getBlockY() == to.getBlockY() && last.getBlockZ() == to.getBlockZ()) {
            return;
        }

        // Move the carpet
        carpet.removeCarpet();
        carpet.currentBlock = to.getBlock();
        carpet.drawCarpet();
        
    }

}
