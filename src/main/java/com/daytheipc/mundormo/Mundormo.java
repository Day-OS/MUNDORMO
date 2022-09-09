package com.daytheipc.mundormo;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public final class Mundormo extends JavaPlugin implements Listener {
    EntityController entityController = new EntityController();
    PersistentData persistentData = new PersistentData();

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
                entityController._AreaCheck();
                getLogger().log(Level.INFO, "HeartBeat tick!");
            }
        }, 0, 1200L*3L / Bukkit.getWorlds().get(0).getGameRuleValue(GameRule.RANDOM_TICK_SPEED));
        //1200L equals 1 entire minute, it multiplies by 3 as it is the default random tick speed, then it is divided by the actual tick speed
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e){
        if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER_EGG){
            entityController.MobSpawned(e);
        }

    }
    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e){
        LivingEntity entity = (LivingEntity) e.getRightClicked();
        //entities.setMobData(entity, "sus", "sus");
        e.getPlayer().sendMessage("Empty: " +  entity.getPersistentDataContainer().isEmpty());
        e.getPlayer().sendMessage("Age: " +  persistentData.getMobDataInt(entity, entityController.keyAge));
        e.getPlayer().sendMessage("Gender: " +  EntityController.gender.values()[persistentData.getMobDataInt(entity, entityController.keyGender)].name());
    }
}
