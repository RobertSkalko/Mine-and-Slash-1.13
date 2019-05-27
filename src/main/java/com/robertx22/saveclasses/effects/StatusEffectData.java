package com.robertx22.saveclasses.effects;

import com.robertx22.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.db_lists.StatusEffects;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.Arrays;
import java.util.List;

@Storable
public class StatusEffectData implements IStatsContainer {

    public StatusEffectData() {

    }

    public StatusEffectData(BaseStatusEffect effect) {
        this.GUID = effect.GUID();
    }

    @Store
    public String GUID;

    public BaseStatusEffect GetEffect() {
        return StatusEffects.All.get(GUID);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        return Arrays.asList(new LevelAndStats(GetEffect().Stats(), level));
    }

}
