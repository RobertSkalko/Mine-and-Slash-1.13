package com.robertx22.customitems.runes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stats.stat_mods.percent.EnergyRegenPercent;

import net.minecraft.item.Item;

public class RahItem extends BaseRuneItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public RahItem(int rarity) {
	super(rarity);

    }

    @Override
    public String name() {
	return "RAH";
    }

    @Override
    public List<StatMod> weaponStat() {
	return Arrays.asList(new ManaOnHitFlat());
    }

    @Override
    public List<StatMod> armorStat() {
	return this.penePercents();
    }

    @Override
    public List<StatMod> jewerlyStat() {
	return Arrays.asList(new EnergyRegenPercent());
    }

    @Override
    public BaseRuneItem byRarity(int rar) {
	return (BaseRuneItem) Items.get(rar);
    }

}