package com.robertx22.saveclasses.gearitem;

import com.robertx22.Styles;
import com.robertx22.database.affixes.BaseAffix;
import com.robertx22.database.affixes.Suffix;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.Suffixes;
import com.robertx22.loot.create.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import net.minecraft.util.text.ITextComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Storable
public class SuffixData extends AffixData implements Serializable, ITooltipList, IRerollable {

    private static final long serialVersionUID = 8802998468539898482L;

    public SuffixData() {

    }

    public SuffixData(GearItemData gear, String affixname, List<Integer> percents) {
        super();
        this.baseAffix = affixname;
        this.percents = percents;
    }

    @Override
    public void RerollFully(GearItemData gear) {

        List<IWeighted> list = ListUtils.CollectionToList(gear.GetBaseGearType()
                .PossibleSuffixes());
        Suffix suffix = (Suffix) RandomUtils.WeightedRandom(list);

        baseAffix = suffix.GUID();

        RerollNumbers(gear);

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

        percents = new ArrayList<Integer>();

        for (StatMod mod : BaseAffix().StatMods()) {
            percents.add(StatGen.GenPercent(gear.GetRarity()));
        }

    }

    @Override
    public BaseAffix BaseAffix() {
        return Suffixes.All().get(baseAffix);
    }

    @Override
    public List<ITextComponent> GetTooltipString(GearItemData gear) {

        BaseAffix affix = BaseAffix();

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        list.add(CLOC.word("suffix")
                .appendText(": ")
                .appendSibling(affix.locName())
                .setStyle(Styles.GRAY));

        for (LevelAndStats part : this.GetAllStats(gear.level)) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(gear.GetRarity()
                        .StatPercents(), part.level, true));
            }
        }

        return list;

    }

}
