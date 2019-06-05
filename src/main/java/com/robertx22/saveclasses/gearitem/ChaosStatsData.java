package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.stats.StatMod;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.Words;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Storable
public class ChaosStatsData extends StatGroupData implements Serializable, ITooltipList, IRerollable {

    private static final long serialVersionUID = -8272316157157669116L;

    public ChaosStatsData() {

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(Styles.REDCOMP()
                .appendSibling(Words.Chaos_Stats.locName().appendText(":")));

        for (LevelAndStats part : this.GetAllStats(info.level)) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info));
            }
        }

        return list;

    }

    @Override
    public void RerollFully(GearItemData gear) {

        this.Mods = new ArrayList<StatModData>();

        StatMod mod = RandomUtils.weightedRandom(gear.GetBaseGearType().ChaosStats());

        StatModData moddata = StatModData.NewRandom(gear.GetRarity(), mod);

        this.Mods.add(moddata);

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

    }

}
