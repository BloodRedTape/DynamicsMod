package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.LevelToChange;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import com.bloodredtape.dynamicsmod.core.SpawnUtils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

public class SkeletonProgression extends ArmorMobProgression {

    public SkeletonProgression(){
        SetItemUpgrade(0, EquipmentSlot.MAINHAND, Items.BOW, Enchantments.POWER_ARROWS, 1);
        SetItemUpgrade(5, EquipmentSlot.MAINHAND, Items.BOW, Enchantments.POWER_ARROWS, 2);
        SetEffect(7, MobEffects.MOVEMENT_SPEED, 1);
        SetItemUpgrade(9, EquipmentSlot.MAINHAND, Items.BOW, Enchantments.POWER_ARROWS, 3);
        SetItemUpgrade(13, EquipmentSlot.MAINHAND, Items.BOW, Enchantments.POWER_ARROWS, 4);
        SetItemUpgrade(16, EquipmentSlot.MAINHAND, Items.BOW, Enchantments.POWER_ARROWS, 4);
    }

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onSkeleton);
    }

    public void onSkeleton(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Skeleton skeleton)){
            return;
        }

        long progressionLevel = MobUtils.GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);

        LevelToChange map = (long level)->{
            if(level <= 5)
                return 0.0f;
            return level > 9 ? 0.4f : 0.2f;
        };

        MobUtils.If(skeleton).WithAccording(map).Do(SpawnUtils::RideSpider);
    }
}
