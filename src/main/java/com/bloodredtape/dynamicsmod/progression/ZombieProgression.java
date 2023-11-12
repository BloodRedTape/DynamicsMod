package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.MobSpawnEvent;


public class ZombieProgression extends ArmorMobProgression {

    public ZombieProgression(){

        SetRandItemUpgrade(0, EquipmentSlot.MAINHAND)
                .With(0.7f, Items.WOODEN_SWORD)
                .With(0.3f, Items.WOODEN_AXE);
        SetRandItemUpgrade(5, EquipmentSlot.MAINHAND)
                .With(0.7f, Items.STONE_SWORD)
                .With(0.3f, Items.STONE_AXE);
        SetRandItemUpgrade(9, EquipmentSlot.MAINHAND)
                .With(0.7f, Items.IRON_SWORD)
                .With(0.3f, Items.IRON_AXE);
        SetRandItemUpgrade(13, EquipmentSlot.MAINHAND)
                .With(0.7f, Items.DIAMOND_SWORD)
                .With(0.3f, Items.DIAMOND_AXE);

        SetEffect(7, MobEffects.MOVEMENT_SPEED, 1);
    }

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onZombie);
    }

    public void onZombie(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Zombie zombie)){
            return;
        }

        long progressionLevel = MobUtils.GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);
    }

}
