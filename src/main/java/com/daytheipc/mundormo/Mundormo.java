package com.daytheipc.mundormo;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import static org.bukkit.Bukkit.getServer;

public final class Mundormo extends JavaPlugin implements Listener {
    Entities entities = new Entities();

    @Override
    public void onEnable() {
        Logger logger = getLogger();
        logger.log(Level.WARNING, "hiii :33");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                entities._AreaCheck();
            }
        }, 0, 2400L);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e){
        if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG){
            getLogger().log(Level.INFO, "" + e.getEntity().getEntityId());
            entities.MobSpawned(e);
        }

    }
    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e){
        Entity entity = e.getRightClicked();
        PersistentDataContainer data = entity.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(this, "s");
        //data.set(key, PersistentDataType.INTEGER, 1);
        e.getPlayer().sendMessage("" +  entity.getPersistentDataContainer().isEmpty());
    }
}
