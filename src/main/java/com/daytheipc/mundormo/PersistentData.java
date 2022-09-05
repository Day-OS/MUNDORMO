package com.daytheipc.mundormo;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class PersistentData {
    static String namespace = "mundormo";
    public  <T> void setMobData(@NotNull Entity mob, String key, T data){
        PersistentDataContainer persistentDataContainer = mob.getPersistentDataContainer();
        NamespacedKey _key = new NamespacedKey(namespace, key);
        if (data instanceof Integer){persistentDataContainer.set(_key, PersistentDataType.INTEGER, (int)data);}
        else if (data instanceof String){persistentDataContainer.set(_key, PersistentDataType.STRING, (String) data);}
        else if (data instanceof Byte){persistentDataContainer.set(_key, PersistentDataType.BYTE, (Byte) data);}
        else if (data instanceof Boolean){persistentDataContainer.set(_key, PersistentDataType.BYTE, ((Boolean) data)? (byte)1 : 0);}
    }

    public String getMobDataString(@NotNull Entity mob, String key){
        NamespacedKey _key = new NamespacedKey(namespace, key);
        return mob.getPersistentDataContainer().get(_key, PersistentDataType.STRING);
    }

    public Integer getMobDataInt(@NotNull Entity mob, String key){
        NamespacedKey _key = new NamespacedKey(namespace, key);
        return mob.getPersistentDataContainer().get(_key, PersistentDataType.INTEGER);
    }
    public Boolean getMobDataBool(@NotNull Entity mob, String key){
        NamespacedKey _key = new NamespacedKey(namespace, key);
        return mob.getPersistentDataContainer().get(_key, PersistentDataType.BYTE) != 0;
    }
    public Byte getMobDataByte(@NotNull Entity mob, String key){
        NamespacedKey _key = new NamespacedKey(namespace, key);
        return mob.getPersistentDataContainer().get(_key, PersistentDataType.BYTE);
    }
}
