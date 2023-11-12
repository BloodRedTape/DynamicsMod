package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.core.Subsystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

public abstract class MobProgression implements Subsystem {

    private final Hashtable<Long, ArrayList<Pair<EquipmentSlot, ItemStack>>> items = new Hashtable<>();
    private final Hashtable<Long, ArrayList<Pair<MobEffect, Integer>>> effects = new Hashtable<>();

    public void SetItemUpgrade(long level, EquipmentSlot slot, Item item, ArrayList<Pair<Enchantment, Integer>> enchantments){
        if(!items.contains(level))
            items.put(level, new ArrayList<>());

        ArrayList<Pair<EquipmentSlot, ItemStack>> levelUpgrades = items.get(level);

        ItemStack stack = new ItemStack(item);

        for(var enchantment: enchantments){
            stack.enchant(enchantment.getFirst(), enchantment.getSecond());
        }
        levelUpgrades.add(new Pair<>(slot, stack));
    }

    public void SetItemUpgrade(long level, EquipmentSlot slot, Item item, Enchantment enchantment, int enchantmentLevel){
        SetItemUpgrade(level, slot, item, new ArrayList<>(){{
            add(new Pair<>(enchantment, enchantmentLevel));
        }});
    }

    public void SetItemUpgrade(long level, EquipmentSlot slot, Item item) {
        SetItemUpgrade(level, slot, item, new ArrayList<>());
    }

    public void SetArmorUpgrade(long level, Item item, ArrayList<Pair<Enchantment, Integer>> enchantments){
        if(!(item instanceof ArmorItem armorItem))
            return;

        SetItemUpgrade(level, armorItem.getType().getSlot(), item, enchantments);
    }
    public void SetArmorUpgrade(long level, Item item, Enchantment enchantment, int enchantmentLevel){
        SetArmorUpgrade(level, item, new ArrayList<>(){{
            add(new Pair<>(enchantment, enchantmentLevel));
        }});
    }

    public void SetArmorUpgrade(long level, Item item){
        SetArmorUpgrade(level, item, new ArrayList<>());
    }

    public void SetEffect(long level, MobEffect effect, int amplifier){
        if(!effects.contains(level))
            effects.put(level, new ArrayList<>());

        ArrayList<Pair<MobEffect, Integer>> levelUpgrades = effects.get(level);

        levelUpgrades.add(new Pair<>(effect, amplifier));
    }

    public void Equip(Mob mob, long level){
        var equip = GetEquipmentBasedOnLevel(level);

        for (Iterator<EquipmentSlot> it = equip.keys().asIterator(); it.hasNext(); ) {

            EquipmentSlot slot = it.next();
            ItemStack item = equip.get(slot);

            mob.setItemSlot(slot, item);
        }

        var effects = GetEffectBasedOnLevel(level);

        for (Iterator<MobEffect> it = effects.keys().asIterator(); it.hasNext(); ) {

            MobEffect effect = it.next();
            int amplifier = effects.get(effect);

            mob.addEffect(new MobEffectInstance(effect, MobEffectInstance.INFINITE_DURATION, amplifier));
        }
    }


    Dictionary<EquipmentSlot, ItemStack> GetEquipmentBasedOnLevel(long level){
        Hashtable<EquipmentSlot, ItemStack> equip = new Hashtable<>();

        for(long i = 0; i<=level; i++){
            var upgrades = items.get(i);
            if(upgrades == null)
                continue;

            for(var upgrade: upgrades) {
                equip.put(upgrade.getFirst(), upgrade.getSecond());
            }
        }

        return equip;
    }

    Dictionary<MobEffect, Integer> GetEffectBasedOnLevel(long level){
        Hashtable<MobEffect, Integer> finalEffects = new Hashtable<>();

        for(long i = 0; i <= level; i++){
            var levelEffects = effects.get(i);
            if(levelEffects == null)
                continue;

            for(var effect: levelEffects) {
                finalEffects.put(effect.getFirst(), effect.getSecond());
            }
        }

        return finalEffects;
    }
}