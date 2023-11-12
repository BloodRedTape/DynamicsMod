package com.bloodredtape.dynamicsmod.core;

import com.bloodredtape.dynamicsmod.progression.SpiderProgression;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SpawnUtils {
    public static void RideSpider(Mob mob){
        Level level = mob.level();

        Spider spider = new Spider(EntityType.SPIDER, level);
        spider.setPos(new Vec3(mob.getX(), mob.getY(), mob.getZ()));

        long progressionLevel = MobUtils.GetProgressionLevel(mob);
        var spiderProgression = new SpiderProgression();
        spiderProgression.Equip(spider, progressionLevel);

        level.addFreshEntity(spider);

        mob.startRiding(spider);
    }
}
