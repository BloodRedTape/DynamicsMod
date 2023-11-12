package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.entity.monster.Witch;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

public class WitchProgression extends NonArmorMobProgression{

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onWitch);
    }

    public void onWitch(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Witch witch)){
            return;
        }

        long progressionLevel = MobUtils.GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);
    }
}
