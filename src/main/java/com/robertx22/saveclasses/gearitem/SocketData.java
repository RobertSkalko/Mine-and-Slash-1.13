package com.robertx22.saveclasses.gearitem;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.uncommon.CLOC;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

@Storable
public class SocketData extends StatGroupData implements ITooltipList {

    public SocketData() {

    }

    public boolean isEmpty() {
        return this.Mods.size() == 0;
    }

    public SocketData(SocketData socket) {
        this.rarity = socket.rarity;
        this.Mods = socket.Mods;
    }

    @Store
    int rarity = 0;

    public ItemRarity GetRarity() {
        return Rarities.Items.get(rarity);
    }

    public ITextComponent getPrefix() {
        return new TextComponentString(" ");
    }

    @Override
    public List<ITextComponent> GetTooltipString(GearItemData gear) {

        List<ITextComponent> list = new ArrayList<ITextComponent>();

        if (isEmpty()) {
            list.add(getPrefix().appendSibling(CLOC.word("empty")
                    .appendText(" ")
                    .appendSibling(CLOC.word("socket"))));
        } else {

            for (LevelAndStats part : this.GetAllStats(gear.level)) {
                for (StatModData data : part.mods) {
                    list.addAll(data.GetTooltipString(gear.GetRarity()
                            .StatPercents(), part.level, true));
                }
            }

        }

        return list;

    }

}
