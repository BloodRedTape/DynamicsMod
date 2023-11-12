package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Spider;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

import java.util.Dictionary;
import java.util.Hashtable;

public class SpiderProgression extends ProgressionBase {

    private static final Dictionary<Long, MobEffectInstance> effectLevels = new Hashtable<>(){{
        put(2L, new MobEffectInstance(MobEffects.ABSORPTION, MobEffectInstance.INFINITE_DURATION, 4));
        put(3L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 1));
        put(4L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 1));
        put(5L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 2));
        put(6L, new MobEffectInstance(MobEffects.ABSORPTION, MobEffectInstance.INFINITE_DURATION, 10));
        put(7L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 3));
        put(8L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 2));
        put(9L, new MobEffectInstance(MobEffects.ABSORPTION, MobEffectInstance.INFINITE_DURATION, 15));
    }};

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onSpider);
    }

    public void onSpider(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Spider spider)){
            return;
        }

        long progressionLevel = MobUtils.GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);
    }

    @Override
    public Dictionary<Long, MobEffectInstance> getEffectLevels() {
        return effectLevels;
    }
}

