package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.LevelToChange;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class SkeletonProgression extends ProgressionBase {

    private ItemStack Enchant(Item item, Enchantment enchantment, int level){
        ItemStack stack = new ItemStack(item);
        stack.enchant(enchantment, level);
        return stack;
    }

    private final ArrayList<ArrayList<Pair<EquipmentSlot, ItemStack>>> upgradeLevels = new ArrayList<>(){{
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS)));
            add(new Pair<>(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS)));
            add(new Pair<>(EquipmentSlot.MAINHAND, Enchant(Items.BOW, Enchantments.POWER_ARROWS, 1)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET)));
            add(new Pair<>(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE)));
            add(new Pair<>(EquipmentSlot.MAINHAND, Enchant(Items.BOW, Enchantments.POWER_ARROWS, 2)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET)));
            add(new Pair<>(EquipmentSlot.MAINHAND, Enchant(Items.BOW, Enchantments.POWER_ARROWS, 3)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS)));
            add(new Pair<>(EquipmentSlot.MAINHAND, Enchant(Items.BOW, Enchantments.POWER_ARROWS, 4)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE)));
        }});
        add(new ArrayList<>(){{
            add(new Pair<>(EquipmentSlot.MAINHAND, Enchant(Items.BOW, Enchantments.POWER_ARROWS, 5)));
        }});
    }};

    private final Dictionary<Long, MobEffectInstance> effectLevels = new Hashtable<>(){{
        put(8L, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 1));
    }};

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

        MobUtils.If(skeleton).WithAccording(map).Do(this::RideSpider);
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
