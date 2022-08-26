package com.daytheipc.mundormo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public final class Mundormo extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = getLogger();
        logger.log(Level.WARNING, "you sheeet ya pants");
        System.out.println("hiii :33");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreatureSpawn(EntitySpawnEvent e){
        getLogger().log(Level.INFO, "" + e.getEntity().getEntityId());
        Player player = e.getLocation().getWorld().getPlayers().get(0);
        player.sendMessage("aaa" +  e.getEntity().getEntityId());

    }
}
