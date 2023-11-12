package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class ZombieProgression extends ProgressionBase {

    private final ArrayList<ArrayList<Pair<EquipmentSlot, ItemStack>>> upgradeLevels = new ArrayList<>(){{
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD)));
            add(new Pair<>(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS)));
            add(new Pair<>(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET)));
            add(new Pair<>(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE)));
        }});
    }};

    private final Dictionary<Long, MobEffectInstance> effectLevels = new Hashtable<>(){{
        put(3L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 1));
        put(6L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 2));
        put(7L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 3));
    }};

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

    @Override
    public Dictionary<Long, MobEffectInstance> getEffectLevels() {
        return effectLevels;
    }

    @Override
    public ArrayList<ArrayList<Pair<EquipmentSlot, ItemStack>>> getUpgradeLevels() {
        return upgradeLevels;
    }
}
