package com.daytheipc.mundormo;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.*;

public class Entities {
    //DISABLE WANDERER AND VILLAGER
    //SNOW GOLEMS SHOULD BE DISABLED and instead be just a lifeless mob
    //CHICKENS MUST NOT DROP EGGS, instead it should follow the same rules as other animals
    boolean mute;
    boolean infertile;
    enum gender{
        male,
        female
    };
    int hunger;
    float lifeMultiplier;
    float speedMultiplier;
    float attackDamageMultiplier;
    float jumpHeightMultiplier;
    float xpMultiplier;
    float specialDropMultiplier; //cat returning item at night, wool quantity, milk quantity
    float breedingMultiplier;
    float weightMultiplier; //HOW MUCH MEAT does it return after getting killed
    Plugin plugin;

    private <T> void setMobData(Entity mob, String key,T data){
        PersistentDataContainer persistentDataContainer = mob.getPersistentDataContainer();
        NamespacedKey _key = new NamespacedKey("mundormo", key);
        if (data instanceof Integer){persistentDataContainer.set(_key, PersistentDataType.INTEGER, (int)data);}
        else if (data instanceof String){persistentDataContainer.set(_key, PersistentDataType.STRING, (String) data);}
    }

    public <T> Object getMobDataString(Entity mob, String key){
        NamespacedKey _key = new NamespacedKey("mundormo", key);
        return mob.getPersistentDataContainer().get(_key, PersistentDataType.STRING);
    }

    public <T> Object getMobDataInt(Entity mob, String key){
        NamespacedKey _key = new NamespacedKey("mundormo", key);
        return mob.getPersistentDataContainer().get(_key, PersistentDataType.INTEGER);
    }
    public void InitMob(LivingEntity e){
        this.setMobData(e, "age",0);
    }

    public void _AreaCheck(){
        for (World w : Bukkit.getWorlds()){for (Chunk c : w.getLoadedChunks()){for (Entity entity : c.getEntities()){
            if (entity instanceof LivingEntity){
                //FIRST TIME BEING SEEN BY CHECKER
                if (entity.getPersistentDataContainer().isEmpty()){this.InitMob((LivingEntity) entity);} else{
                    //Normal cycle
                    this.setMobData((LivingEntity)entity, "age", ((int)this.getMobDataInt(entity,"age")) + 1);
                    entity.getPersistentDataContainer();
                }
            }
        }}}
    }

    public void MobSpawned(CreatureSpawnEvent e){
        LivingEntity entity = e.getEntity();
        entity.setGlowing(true);
        this.InitMob(entity);
    }
}
