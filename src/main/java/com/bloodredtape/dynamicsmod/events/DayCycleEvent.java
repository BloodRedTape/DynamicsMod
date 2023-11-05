package com.bloodredtape.dynamicsmod.events;


import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public class DayCycleEvent extends Event {
    public DayCycle dayCycle;
    public Level level;
    public int day;

    public DayCycleEvent(DayCycle dayCycle, Level level, int day){
        this.dayCycle = dayCycle;
        this.level = level;
        this.day = day;
    }
}
