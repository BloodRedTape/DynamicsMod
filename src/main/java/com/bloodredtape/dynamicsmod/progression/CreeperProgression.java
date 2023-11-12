package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.level.ExplosionEvent;

public class CreeperProgression extends NonArmorMobProgression {

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onCreeper);
        mod.addListener(this::onCreeperExplosion);
    }

    public void onCreeper(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Creeper creeper)){
            return;
        }

        long progressionLevel = MobUtils.GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);

        MobUtils.If(creeper).After(5).With(0.8f)
                .DoGive(EquipmentSlot.MAINHAND, Items.TNT)
                .DoSetChance(EquipmentSlot.MAINHAND, 0.5f);
    }

    public void onCreeperExplosion(ExplosionEvent explosionEvent) {
        if (!(explosionEvent.getExplosion().getDirectSourceEntity() instanceof Creeper creeper))
            return;

        creeper.removeAllEffects();

        if(MobUtils.If(creeper).Has(EquipmentSlot.MAINHAND, Items.TNT).With(0.3f).Valid()){

            var explosion = new Explosion(creeper.level(), null, creeper.getX(), creeper.getY(), creeper.getZ(), 8.f, true, Explosion.BlockInteraction.KEEP);
            explosion.explode();
            explosion.finalizeExplosion(true);
        }

        if(MobUtils.If(creeper).Has(EquipmentSlot.MAINHAND, Items.TNT).With(0.1f).Valid()){

        }
    }
}

