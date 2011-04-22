package yetanotherx.bukkitplugin.MotherNature.listener;

import java.util.ArrayList;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNaturePermissions;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureSettings;

public class MotherNaturePlayerListener extends PlayerListener{

    public MotherNaturePlayerListener(MotherNature parent) {
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {

        if( event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR ) {
            if( event.hasItem() && event.getItem().getTypeId() == MotherNatureSettings.lightningWand ) {
                if( MotherNaturePermissions.has(event.getPlayer(), "mothernature.wand")) {
                    event.getPlayer().getWorld().strikeLightning(event.getPlayer().getTargetBlock(null, 500).getLocation());
                }
            }
        }
    }



}

