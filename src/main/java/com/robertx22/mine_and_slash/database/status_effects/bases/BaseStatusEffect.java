package com.robertx22.mine_and_slash.database.status_effects.bases;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import net.minecraft.item.Item;

import java.util.List;

public abstract class BaseStatusEffect implements IWeighted, ISlashRegistryEntry<BaseStatusEffect> {

    public abstract Item ItemModel();

    public abstract String GUID();

    public abstract List<StatModData> Stats();

    @Override
    public int Weight() {
        return this.UncommonWeight;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.STATUS_EFFECT;
    }
}
