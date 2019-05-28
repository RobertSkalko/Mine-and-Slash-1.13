package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.stats.StatMod;
import com.robertx22.database.unique_items.IUnique;
import com.robertx22.db_lists.UniqueItems;
import com.robertx22.loot.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.Styles;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public class UniqueStatsData implements ITooltipList, IRerollable, IStatsContainer {

    public UniqueStatsData() {

    }

    @Store
    public String uniqueGUID;
    @Store
    public List<Integer> percents = new ArrayList<Integer>();

    public UniqueStatsData(String GUID) {
        this.uniqueGUID = GUID;
    }

    @Override
    public void RerollFully(GearItemData gear) {
        this.RerollNumbers(gear);
    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        percents.clear();

        // wont ever have more than 10 unique stats.
        for (int i = 0; i < 10; i++) {
            percents.add(StatGen.GenPercent(gear.GetRarity()));
        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(new TextComponentString(Styles.YELLOW + "").appendSibling(CLOC.word("unique_stats")
                .appendText(":")));

        for (LevelAndStats part : this.GetAllStats(info.level)) {

            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

    public IUnique getUniqueItem() {
        return (IUnique) UniqueItems.ITEMS.get(this.uniqueGUID);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

        IUnique unique = getUniqueItem();

        List<StatModData> list = new ArrayList<StatModData>();

        for (int i = 0; i < unique.uniqueStats().size(); i++) {
            StatMod mod = unique.uniqueStats().get(i);
            list.add(StatModData.Load(mod, percents.get(i)));
        }

        return Arrays.asList(new LevelAndStats(list, level));
    }

}
