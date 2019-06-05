package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.stats.StatMod;
import com.robertx22.loot.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.Words;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Storable
public class SecondaryStatsData extends StatGroupData implements Serializable, ITooltipList, IRerollable {

    private static final long serialVersionUID = 6149243047165372987L;

    public SecondaryStatsData() {

    }

    @Store
    public boolean AddedStat = false;

    @Override
    public void RerollFully(GearItemData gear) {

        this.Mods = new ArrayList<StatModData>();

        int Stats = RandomUtils.RandomRange(1, 3);

        while (Stats > 0) {
            StatMod mod = RandomUtils.weightedRandom(gear.GetBaseGearType()
                    .PossibleSecondaryStats());
            this.Mods.add(StatModData.NewRandom(gear.GetRarity(), mod));
            Stats--;

        }

    }

    public void AddStat(GearItemData gear) {
        StatMod mod = RandomUtils.weightedRandom(gear.GetBaseGearType()
                .PossibleSecondaryStats());

        gear.secondaryStats.Mods.add(StatModData.NewRandom(gear.GetRarity(), mod));

        this.AddedStat = true;

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        for (StatModData data : this.Mods) {
            data.setPercent(StatGen.GenPercent(gear.GetRarity()));
        }

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(Styles.GRAYCOMP()
                .appendSibling(Words.Secondary_Stats.locName().appendText(":")));

        for (LevelAndStats part : this.GetAllStats(info.level)) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

}
