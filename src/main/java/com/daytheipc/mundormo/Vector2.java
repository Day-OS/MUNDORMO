package com.daytheipc.mundormo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bukkit.Bukkit.getLogger;

public class Vector2 {
    public double x,y;
    public Vector2(double x, double y){this.x = x; this.y = y;}
    public String toString(){
        return "[x:" + this.x + " y:" + this.y + "]";
    };
    static Vector2 BinarySearch(ArrayList<Vector2> list, Vector2 target){
        Vector2 vec = null;
        for (Vector2 vector2 : list) {
            getLogger().log(Level.WARNING, vector2.toString());
            vec = vector2;
        }
        QuickSort(list, 0, list.size() - 1);
        for (Vector2 vector2 : list) {
            getLogger().log(Level.WARNING, vector2.toString());
            vec = vector2;
        }
        return vec;
    }
    static void Swap(ArrayList<Vector2> arr, int i, int j){
        Vector2 temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }
    static int Partition(ArrayList<Vector2> arr, int start, int end){
        Vector2 pivot = arr.get(end);
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            if(arr.get(j).x < pivot.x){
                i++;
                Swap(arr, i, j);
            }
        }
        Swap(arr, i+1, end);
        return i + 1;
    }
    static void QuickSort(ArrayList<Vector2> arr, int start, int end){
        getLogger().log(Level.WARNING,"STILL AALIVE");
        if (start < end){
            int partitionIndex = Partition(arr, start, end);
            QuickSort(arr, start, partitionIndex - 1);
            QuickSort(arr, partitionIndex + 1, end);
        }
    }
}
