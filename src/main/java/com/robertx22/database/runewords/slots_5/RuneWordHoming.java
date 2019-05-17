package com.robertx22.database.runewords.slots_5;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stats.StatMod;
import com.robertx22.database.stats.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stats.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stats.stat_mods.spell_buffs.HomingFlat;
import com.robertx22.items.runes.*;
import com.robertx22.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordHoming extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HomingFlat(), new CriticalHitFlat(), new CriticalDamageFlat(), new HealthFlat());
    }

    @Override
    public String GUID() {
        return "Follower";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new ItaItem(0), new DosItem(0), new VohItem(0), new BerItem(0), new RahItem(0));
    }

}
