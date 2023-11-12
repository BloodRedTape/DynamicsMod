package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.core.MobUtils;
import com.bloodredtape.dynamicsmod.core.Subsystem;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public abstract class ProgressionBase implements Subsystem {
    public ArrayList<ArrayList<Pair<EquipmentSlot, ItemStack>>> getUpgradeLevels(){
        return new ArrayList<>();
    }

    public Dictionary<Long, MobEffectInstance> getEffectLevels(){
        return new Hashtable<>();
    }
    private final Random rand = new Random();

    public void Equip(Mob mob, long level){

        var equip = GetEquipmentBasedOnLevel(level);

        for (Iterator<EquipmentSlot> it = equip.keys().asIterator(); it.hasNext(); ) {

            EquipmentSlot slot = it.next();
            ItemStack item = equip.get(slot);

            mob.setItemSlot(slot, item);
        }

        var effects= GetEffectBasedOnLevel(level);

        for(var effect: effects){
            mob.addEffect(effect);
        }
    }

    public void RideSpider(Mob mob){
        Level level = mob.level();

        Spider spider = new Spider(EntityType.SPIDER, level);
        spider.setPos(new Vec3(mob.getX(), mob.getY(), mob.getZ()));

        long progressionLevel = MobUtils.GetProgressionLevel(mob);
        var spiderProgression = new SpiderProgression();
        spiderProgression.Equip(spider, progressionLevel);

        level.addFreshEntity(spider);

        mob.startRiding(spider);
    }

    Dictionary<EquipmentSlot, ItemStack> GetEquipmentBasedOnLevel(long level){

        Hashtable<EquipmentSlot, ItemStack> equip = new Hashtable<>();

        for(long i = 0; i<=Math.min(level, getUpgradeLevels().size() - 1); i++){
            for(var upgrade: getUpgradeLevels().get((int)i)) {
                equip.put(upgrade.getFirst(), upgrade.getSecond());
            }
        }

        return equip;
    }

    ArrayList<MobEffectInstance> GetEffectBasedOnLevel(long level){
        ArrayList<MobEffectInstance> effects = new ArrayList<>();

        for(long i = 0; i <= Math.min(level, getEffectLevels().size() - 1); i++){
            var value = getEffectLevels().get(i);

            if(value != null){
                effects.add(value);
            }
        }
        return effects;
    }

}
