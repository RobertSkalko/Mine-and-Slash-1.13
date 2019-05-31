package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.sets.Set;
import com.robertx22.database.stats.StatMod;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.EntityData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Storable
public class SetData {

    @Store
    public String baseSet;

    public Set GetSet() {

        return Sets.All.get(baseSet);
    }

    public List<StatModData> GetAllStats(int level, Unit unit) {

        Set base = GetSet();

        List<StatModData> list = new ArrayList<StatModData>();

        for (int i = 0; i < base.GetObtainedMods(unit).size(); i++) {

            StatMod mod = base.GetObtainedMods(unit).get(i);

            list.add(StatModData.Load(mod, 100));
        }

        return list;

    }

    private void BuildSetTooltip(ItemTooltipEvent event, GearItemData gear,
                                 EntityData.UnitData data) {

        Unit unit = data.getUnit();

        event.getToolTip()
                .add(Styles.GREENCOMP()
                        .appendSibling(new TextComponentString("[Set]: ").appendSibling(GetSet()
                                .locName())));

        for (Map.Entry<Integer, StatMod> entry : GetSet().AllMods().entrySet()) {

            boolean has = false;

            TextFormatting color = null;
            if (unit.WornSets.containsKey(baseSet) && unit.WornSets.get(baseSet) >= entry.getKey()) {
                color = TextFormatting.GREEN;
                has = true;
            } else {
                color = TextFormatting.DARK_GREEN;
            }

            TooltipInfo info = new TooltipInfo(GetSet().statPercents, data.getLevel()).setIsSet();

            for (ITextComponent str : StatModData.Load(entry.getValue(), GetSet().StatPercent)
                    .GetTooltipString(info)) {

                ITextComponent comp = new TextComponentString(color + "").appendSibling(new TextComponentString(entry
                        .getKey() + " ").appendSibling(CLOC.word("set")
                        .appendText(": ")
                        .appendSibling(str)));

                event.getToolTip().add(comp);
            }

        }
        event.getToolTip().add(new TextComponentString(""));

    }

}
