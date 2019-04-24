package com.robertx22.unique_items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.bases.IGearItem;
import com.robertx22.database.IGUID;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.ITiered;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public interface IUnique extends IWeighted, ITiered, IGUID, IGearItem {

    public static HashMap<String, Item> ITEMS = new HashMap<String, Item>();

    @Override
    public default int Weight() {
	return this.UncommonWeight;
    }

    public default String locName() {
	return CLOC.uniqueName(this.GUID());
    }

    public default String locDesc() {
	return CLOC.uniqueDesc(this.GUID());
    }

    List<StatMod> uniqueStats();

    String slot();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	for (Item item : ITEMS.values()) {

	    IUnique uniq = (IUnique) item;

	    item.setRegistryName("uniques/" + uniq.slot().toLowerCase() + "/" + uniq.GUID());

	    event.getRegistry().register(item);
	}
    }

    public static List<IUnique> getAllUniquesOfTier(int tier, Collection<Item> coll) {
	List<IUnique> list = new ArrayList<IUnique>();

	for (Item item : coll) {
	    IUnique baseu = (IUnique) item;

	    if (tier == baseu.Tier()) {
		list.add((IUnique) item);
	    }
	}
	return list;
    }

    public static List<IUnique> getAllPossibleUniqueDrops(int tier, Collection<Item> coll) {
	List<IUnique> list = new ArrayList<IUnique>();

	for (Item item : coll) {
	    IUnique baseu = (IUnique) item;

	    if (tier >= baseu.Tier()) {
		list.add((IUnique) item);
	    }
	}
	return list;
    }

    public static List<IUnique> filterUniquesByType(String type, List<IUnique> coll) {

	List<IUnique> list = new ArrayList<IUnique>();

	for (IUnique item : coll) {
	    if (item.slot().equals(type) || type.equals("random") || type.equals("")) {
		list.add((IUnique) item);
	    }
	}

	return list;
    }

}
