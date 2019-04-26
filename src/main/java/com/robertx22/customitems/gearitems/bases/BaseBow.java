package com.robertx22.customitems.gearitems.bases;

public abstract class BaseBow extends net.minecraft.item.ItemBow {

    public abstract String Name();

    public BaseBow() {
	super(new Properties().maxStackSize(1).defaultMaxDamage(1000));

    }
}