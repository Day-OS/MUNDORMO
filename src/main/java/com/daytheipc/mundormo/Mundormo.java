package com.daytheipc.mundormo;

import com.sun.tools.javac.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import static org.bukkit.Bukkit.getServer;

public final class Mundormo extends JavaPlugin implements Listener {
    Entities entities = new Entities();

    public Plugin getInstance(){
        return this;
    }
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
        LivingEntity entity = (LivingEntity) e.getRightClicked();
        //entities.setMobData(entity, "sus", "sus");
        e.getPlayer().sendMessage("" +  entity.getPersistentDataContainer().isEmpty());
        //e.getPlayer().sendMessage("" +  entities.getMobDataString(entity, "sus"));
    }
}
