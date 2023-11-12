package com.bloodredtape.dynamicsmod.progression;

import net.minecraft.world.effect.MobEffects;

public abstract class NonArmorMobProgression extends MobProgression{

    public NonArmorMobProgression(){
        SetEffect(3, MobEffects.DAMAGE_RESISTANCE, 1); // 0.20 damage == leather
        SetEffect(4, MobEffects.MOVEMENT_SPEED, 1); // 1.2x speed
        SetEffect(7, MobEffects.DAMAGE_BOOST, 1);
        SetEffect(7, MobEffects.DAMAGE_RESISTANCE, 2); // 0.40 damage == golden
        SetEffect(11, MobEffects.DAMAGE_RESISTANCE, 3); // 0.6 damage == iron set
        SetEffect(14, MobEffects.DAMAGE_BOOST, 2); // equals to neterite sword
    }
}
