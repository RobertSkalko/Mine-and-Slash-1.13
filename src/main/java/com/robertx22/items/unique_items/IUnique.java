package com.robertx22.items.unique_items;

import com.robertx22.database.IGUID;
import com.robertx22.database.stats.StatMod;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.ITiered;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public interface IUnique extends IWeighted, ITiered, IGUID {

    public static HashMap<String, Item> ITEMS = new HashMap<String, Item>();

    static List<IUnique> all = new ArrayList();

    public static List<IUnique> getAll() {
        if (all.isEmpty()) {
            for (Item item : ITEMS.values()) {
                IUnique uniq = (IUnique) item;
                all.add(uniq);
            }

        }
        return all;
    }

    @Override
    public default int Weight() {
        return this.UncommonWeight;
    }

    public default ITextComponent locName() {
        return CLOC.uniqueName(this.GUID());
    }

    public default ITextComponent locDesc() {
        return CLOC.uniqueDesc(this.GUID());
    }

    List<StatMod> uniqueStats();

    String slot();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        UniqueItemRegister.register();

        for (Item item : ITEMS.values()) {

            IUnique uniq = (IUnique) item;

            item.setRegistryName("uniques/" + uniq.slot()
                    .toLowerCase() + "/" + uniq.GUID());

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

    public static List<IUnique> getAllPossibleUniqueDrops(int tier,
                                                          Collection<Item> coll) {
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
