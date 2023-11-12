package com.bloodredtape.dynamicsmod.core;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MobUtils{
    private final Mob mob;
    private final ArrayList<Supplier<Boolean>> suppliers = new ArrayList<Supplier<Boolean>>();
    private final Random rand = new Random();

    public MobUtils(Mob mob){
        this.mob = mob;
    }

    public MobUtils If(Supplier<Boolean> supplier){
        suppliers.add(supplier);
        return this;
    }

    public MobUtils After(long level){
        return If(()->{
            long currentLevel = MobUtils.GetProgressionLevel(mob);
            return currentLevel >= level;
        });
    }

    public MobUtils With(float chance){
        return If(()->(rand.nextFloat(1.f) <= chance));
    }

    public MobUtils WithAccording(LevelToChange map){
        long currentLevel = MobUtils.GetProgressionLevel(mob);
        return With(map.Map(currentLevel));
    }

    public MobUtils Has(EquipmentSlot slot, Item item){
        return If(()->(mob.getItemBySlot(slot).getItem() == item));
    }

    public boolean Valid(){
        for(Supplier<Boolean> supplier: suppliers){
            if(!supplier.get())
                return false;
        }
        return true;
    }

    public void Do(Runnable runnable){
        if(Valid())
            runnable.run();
    }

    public <T extends Mob> void Do(Consumer<T> comsumer){
        if(Valid())
            comsumer.accept((T) mob);
    }

    public static MobUtils If(Mob mob){
        return new MobUtils(mob);
    }

    public static long GetProgressionLevel(Mob mob){
        long daytime = mob.level().getDayTime();
        long day = daytime / 24000;

        return day / 2;
    }
}
