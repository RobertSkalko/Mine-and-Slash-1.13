package com.robertx22.saveclasses.gearitem;

import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.uncommon.CLOC;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class SocketsListData implements IStatsContainer, ITooltipList {

    @Store
    public List<SocketData> sockets = new ArrayList();

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();

        if (sockets.size() > 0) {

            list.add(CLOC.word("socket").appendText(":"));

            for (SocketData socket : sockets) {
                list.addAll(socket.GetTooltipString(info));
            }
        }

        return list;
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

        List<LevelAndStats> mods = new ArrayList();

        for (SocketData socket : sockets) {
            mods.addAll(socket.GetAllStats(level));
        }

        return mods;

    }

}
