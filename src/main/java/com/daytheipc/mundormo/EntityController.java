package com.daytheipc.mundormo;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
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

public class EntityController {
    static long Year = 7300L; //7300 minutes IRL
    //DISABLE WANDERER AND VILLAGER
    //SNOW GOLEMS SHOULD BE DISABLED and instead be just a lifeless mob
    //CHICKENS MUST NOT DROP EGGS, instead it should follow the same rules as other animals


    /*KEEEEEEYS*/
    String keyAge = "age";
    String keyMute = "mute";
    String keyInfertile = "infertile";
    String keyGender = "gender";
    String keyHunger = "hunger";

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
    PersistentData persistentData;

    public void InitMob(LivingEntity e){
        persistentData.setMobData(e, keyAge,0);
    }
    private void UpdateMob(LivingEntity e){
        //Normal cycle
        persistentData.setMobData(e, keyAge, (persistentData.getMobDataInt(e,"age")) + 1);
        e.getPersistentDataContainer();

    }

    public void _AreaCheck(){
        for (World w : Bukkit.getWorlds()){for (Chunk c : w.getLoadedChunks()){for (Entity entity : c.getEntities()){
            if (entity instanceof LivingEntity){
                if (entity.getPersistentDataContainer().isEmpty()){this.InitMob((LivingEntity) entity);}
                else{this.UpdateMob((LivingEntity) entity);}
            }
        }}}
    }

    public void MobSpawned(CreatureSpawnEvent e){
        LivingEntity entity = e.getEntity();
        entity.setGlowing(true);
        this.InitMob(entity);
    }
}
