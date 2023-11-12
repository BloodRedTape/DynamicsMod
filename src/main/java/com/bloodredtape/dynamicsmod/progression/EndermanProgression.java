package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

public class EndermanProgression extends NonArmorMobProgression{

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onEnderman);
    }

    public void onEnderman(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof EnderMan enderman)){
            return;
        }

        long progressionLevel = Math.max(0, MobUtils.GetProgressionLevel(mob.getEntity()) - 5);

        Equip(mob.getEntity(), progressionLevel);
    }
}
