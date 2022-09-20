package com.daytheipc.mundormo;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChunkManager {
    File file;
    FileConfiguration config;
    ArrayList<Vector2> chunks;
    public ChunkManager(File dataFolder){
        chunks = new ArrayList<Vector2>();
        chunks.add(new Vector2(0,1));
        chunks.add(new Vector2(1,2));
        chunks.add(new Vector2(3,3));
        chunks.add(new Vector2(5,4));
        chunks.add(new Vector2(2,4));
        Vector2.BinarySearch(chunks, new Vector2(1,2));

        dataFolder.mkdirs();
        file = new File(dataFolder + File.separator+"chunks.yml");

        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {try {file.createNewFile();} catch (IOException e) {e.printStackTrace();}}
        config.options().setHeader(Collections.singletonList("DO NOT MODIFY OR DELETE THIS FILE!"));

        config.set("c|"+Long.toHexString(0)+"|"+Long.toHexString(0), true);
    }

    public void Save(){
        try {config.save(file);} catch (IOException e) {e.printStackTrace();}
    }
}
