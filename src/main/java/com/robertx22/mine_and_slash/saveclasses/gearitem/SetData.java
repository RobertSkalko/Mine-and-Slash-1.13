package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.initializers.Sets;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.WornSetData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Storable
public class SetData implements ITooltipList {

    @Store
    public String baseSet;

    public Set GetSet() {

        return Sets.All.get(baseSet);
    }

    public List<StatModData> GetAllStats(int level, Unit unit) {

        Set base = GetSet();

        WornSetData data = unit.wornSets.get(this.baseSet);

        List<StatModData> list = new ArrayList<StatModData>();

        for (int i = 0; i < base.getObtainedMods(data).size(); i++) {

            StatMod mod = base.getObtainedMods(data).get(i);

            list.add(StatModData.Load(mod, 100));
        }

        return list;

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        if (baseSet == null || info.unitdata == null) {
            return list;
        }

        Set set = GetSet();

        if (set != null) {
            list.add(Styles.GREENCOMP()
                    .appendSibling(new StringTextComponent("[Set]: ").appendSibling(set.locName())));

            for (Map.Entry<Integer, StatMod> entry : GetSet().AllMods().entrySet()) {

                TextFormatting color = null;
                if (info.unitdata.getUnit().wornSets.get(baseSet).count >= entry.getKey()) {
                    color = TextFormatting.GREEN;

                } else {
                    color = TextFormatting.DARK_GREEN;
                }

                int avgLvl = info.unitdata.getUnit().wornSets.get(baseSet)
                        .getAverageLevel();

                TooltipInfo setInfo = info.withLevel(avgLvl).setIsSet();
                setInfo.minmax = set.statPercents;

                for (ITextComponent str : StatModData.Load(entry.getValue(), GetSet().StatPercent)
                        .GetTooltipString(setInfo)) {

                    ITextComponent comp = new StringTextComponent(color + "").appendSibling(new StringTextComponent(entry
                            .getKey() + " ").appendSibling(Words.Set.locName()
                            .appendText(": ")
                            .appendSibling(str)));

                    list.add(comp);
                }

            }
            list.add(new StringTextComponent(""));
        }

        return list;

    }
}
