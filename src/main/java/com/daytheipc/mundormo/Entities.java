package com.daytheipc.mundormo;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

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


    public void _AreaCheck(){
        Location a = getServer().getWorld("world").getPlayers().get(0).getLocation();
        getLogger().log(Level.WARNING, "ATIVADO OAAAA BUNDA");
    }
    public void MobSpawned(CreatureSpawnEvent e){
        LivingEntity entity = e.getEntity();
        entity.setGlowing(true);
    }
}
