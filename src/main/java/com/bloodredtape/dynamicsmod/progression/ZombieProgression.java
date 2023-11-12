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
        SetItemUpgrade(0, EquipmentSlot.MAINHAND, Items.WOODEN_SWORD);
        SetItemUpgrade(5, EquipmentSlot.MAINHAND, Items.STONE_SWORD);
        SetEffect(7, MobEffects.MOVEMENT_SPEED, 1);
        SetItemUpgrade(9, EquipmentSlot.MAINHAND, Items.IRON_SWORD);
        SetItemUpgrade(13, EquipmentSlot.MAINHAND, Items.DIAMOND_SWORD);
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
