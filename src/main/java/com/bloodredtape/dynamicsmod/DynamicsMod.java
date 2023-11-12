package com.bloodredtape.dynamicsmod;

import com.bloodredtape.dynamicsmod.core.Subsystem;
import com.bloodredtape.dynamicsmod.events.DayCycle;
import com.bloodredtape.dynamicsmod.events.DayCycleEvent;
import com.bloodredtape.dynamicsmod.progression.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod(DynamicsMod.MOD_ID)
public class DynamicsMod
{
    public static final String MOD_ID= "dynamicsmod";
    private final List<Subsystem> registeredSubsystems = new ArrayList<>();
    public DynamicsMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        MinecraftForge.EVENT_BUS.addListener(this::onLevelTick);

        register(ZombieProgression.class);
        register(SpiderProgression.class);
        register(SkeletonProgression.class);
        register(CreeperProgression.class);
        register(EndermanProgression.class);
    }

    public <T extends Event> void addListener(Consumer<T> consumer){
        MinecraftForge.EVENT_BUS.addListener(consumer);
    }

    private void onLevelTick(TickEvent.LevelTickEvent event) {
        if(event.level.isClientSide()){
            return;
        }

        if (event.phase == TickEvent.Phase.START) {
            long dayTime = event.level.getDayTime();
            int day = (int)(dayTime/24000);
            long inDayTime = dayTime % 24000;

            if(inDayTime == 12000 || inDayTime == 0){
                MinecraftForge.EVENT_BUS.post(new DayCycleEvent(inDayTime == 0 ? DayCycle.NIGHT : DayCycle.DAY, event.level, day));
            }
        }
    }

    public <T extends Subsystem> void register(Class<T> clazz){
        try {
            Subsystem subsystem = clazz.newInstance();
            subsystem.onRegister(this);
            registeredSubsystems.add(subsystem);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
