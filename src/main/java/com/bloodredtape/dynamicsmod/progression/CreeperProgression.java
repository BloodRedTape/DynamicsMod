package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.ExplosionEvent;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class CreeperProgression extends ProgressionBase {

    private final Dictionary<Long, MobEffectInstance> effectLevels = new Hashtable<>(){{
        put(3L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 1));
        put(4L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 1));
        put(5L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 2));
        put(6L, new MobEffectInstance(MobEffects.ABSORPTION, MobEffectInstance.INFINITE_DURATION, 8));
        put(7L, new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 3));
        put(8L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 2));
        put(9L, new MobEffectInstance(MobEffects.ABSORPTION, MobEffectInstance.INFINITE_DURATION, 15));
    }};

    private final Random rand = new Random();

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onCreeper);
        mod.addListener((ExplosionEvent explosionEvent)->{
            var source = explosionEvent.getExplosion().getDirectSourceEntity();

            if(source instanceof Creeper creeper){
                creeper.removeAllEffects();

                if(creeper.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == Items.TNT && rand.nextFloat() < 0.3){
                    var explosion = new Explosion(creeper.level(), null, creeper.getX(), creeper.getY(), creeper.getZ(), 8.f, true, Explosion.BlockInteraction.KEEP);
                    explosion.explode();
                    explosion.finalizeExplosion(true);
                }
            }
        });
    }

    public void onCreeper(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Creeper creeper)){
            return;
        }

        long progressionLevel = GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);

        if(progressionLevel >= 5){
            creeper.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TNT));
            creeper.setDropChance(EquipmentSlot.MAINHAND, 0.4f);
        }
    }

    @Override
    public Dictionary<Long, MobEffectInstance> getEffectLevels() {
        return effectLevels;
    }
}

