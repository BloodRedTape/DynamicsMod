package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

import java.util.Dictionary;
import java.util.Hashtable;

public class SpiderProgression extends ProgressionBase {

    private static final Dictionary<Long, MobEffectInstance> effectLevels = new Hashtable<>(){{
        put(2L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 1)); //equals to stone sword
        put(3L, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 1));
        put(4L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 1)); // 1.2x speed
        put(6L, new MobEffectInstance(MobEffects.JUMP, MobEffectInstance.INFINITE_DURATION, 1));
        //put(7L, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 3)); // 0.60 damage consumption == iron
        put(8L, new MobEffectInstance(MobEffects.JUMP, MobEffectInstance.INFINITE_DURATION, 2));
        put(9L, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 2)); // 0.40 damage consumption == leather
        //put(9L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 2)); // 1.4x speed
        //put(11L, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 4)); // 0.80 damage consumption == diamond
        put(15L, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, MobEffectInstance.INFINITE_DURATION, 3)); // 0.60 damage consumption == iron
        //put(20L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 2));//equals to neterite sword
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

        MobUtils.If(spider).After(7).DoGive(EquipmentSlot.OFFHAND, Items.IRON_INGOT);
        MobUtils.If(spider).After(10).With(0.5f).DoGive(EquipmentSlot.MAINHAND, Items.DIAMOND);
    }

    @Override
    public Dictionary<Long, MobEffectInstance> getEffectLevels() {
        return effectLevels;
    }
}

