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
import java.util.Random;

import static org.bukkit.Bukkit.*;

public class EntityController {
    //DISABLE WANDERER AND VILLAGER
    //SNOW GOLEMS SHOULD BE DISABLED and instead be just a lifeless mob
    //CHICKENS MUST NOT DROP EGGS, instead it should follow the same rules as other animals
    Plugin plugin;
    private final PersistentData persistentData = new PersistentData();
    Random random = new Random();
    static long Year = 7300L; //7300 minutes IRL



    /*KEEEEEEYS*/
    String keyAge = "age"; // Int
    String keyGender = "gender"; // Enum
    String keyMute = "mute"; // Bool
    String keyInfertile = "infertile"; // Bool
    String keyHunger = "hunger"; // Int
    String keyMaxHealthMultiplier = "maxhealth"; // Float
    String keySpeedMultiplier = "speed";// Float
    String keyAttackDamageMultiplier = "attackdmg"; // Float
    String keyJumpHeightMultiplier = "jmp"; // Float
    String keyXPMultiplier = "xp"; // Float
    String keySpecialDropMultiplier = "SDrop"; // Float //cat returning item at night, wool quantity, milk quantity
    String keyWeightMultiplier = "Drop"; // Float //HOW MUCH MEAT does it return after getting killed

    enum gender{
        male,
        female
    };


    public void InitMob(LivingEntity e){
        persistentData.setMobData(e, keyAge,0);
        persistentData.setMobData(e, keyGender, gender.values()[random.nextInt(gender.values().length)].ordinal());
        //persistentData.setMobData(e, keyGender, gender.female.ordinal() );
    }
    private void UpdateMob(LivingEntity e){
        //Updates age
        persistentData.setMobData(e, keyAge, (persistentData.getMobDataInt(e,"age")) + 1);


    }

    public void _AreaCheck(){
        for (World w : Bukkit.getWorlds()){for (Chunk c : w.getLoadedChunks()){for (Entity entity : c.getEntities()){
            if (entity instanceof LivingEntity){
                if (entity.getPersistentDataContainer().isEmpty() || persistentData.getMobDataInt(entity, keyAge) == null){this.InitMob((LivingEntity) entity);}
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
