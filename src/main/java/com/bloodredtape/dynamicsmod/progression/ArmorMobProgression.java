package com.bloodredtape.dynamicsmod.progression;

import net.minecraft.world.item.Items;

public abstract class ArmorMobProgression extends MobProgression{

    public ArmorMobProgression(){
        //0 - First Weapon upgrade - Wood
        SetArmorUpgrade(1, Items.LEATHER_CHESTPLATE);

        SetArmorUpgrade(2, Items.LEATHER_LEGGINGS);
        SetArmorUpgrade(2, Items.LEATHER_BOOTS);

        SetArmorUpgrade(3, Items.IRON_BOOTS);

        SetArmorUpgrade(4, Items.LEATHER_HELMET);
        //5 - Second Weapon upgrade - Stone
        SetArmorUpgrade(6, Items.IRON_HELMET);

        //7 - First Speed upgrade

        SetArmorUpgrade(8, Items.IRON_LEGGINGS);
        //9 - Second Weapon upgrade - Iron

        SetArmorUpgrade(10, Items.IRON_CHESTPLATE);

        SetArmorUpgrade(11, Items.DIAMOND_HELMET);

        SetArmorUpgrade(12, Items.DIAMOND_BOOTS);
        //13 - Third Weapon upgrade

        SetArmorUpgrade(14, Items.DIAMOND_LEGGINGS);

        SetArmorUpgrade(15, Items.DIAMOND_CHESTPLATE);
    }
}
