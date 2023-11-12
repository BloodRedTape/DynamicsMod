package com.bloodredtape.dynamicsmod.progression;

import com.bloodredtape.dynamicsmod.DynamicsMod;
import com.bloodredtape.dynamicsmod.core.MobUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.MobSpawnEvent;

public class SpiderProgression extends NonArmorMobProgression{

    @Override
    public void onRegister(DynamicsMod mod) {
        mod.addListener(this::onSpider);
    }

    public void onSpider(MobSpawnEvent.FinalizeSpawn mob){
        if(!(mob.getEntity() instanceof Spider spider)){
            return;
        }

        long progressionLevel = MobUtils.GetProgressionLevel(mob.getEntity());

        Equip(mob.getEntity(), progressionLevel);

        MobUtils.If(spider).After(7).DoGive(EquipmentSlot.OFFHAND, Items.IRON_INGOT);
        MobUtils.If(spider).After(11).With(0.5f).DoGive(EquipmentSlot.MAINHAND, Items.DIAMOND);
    }
}

