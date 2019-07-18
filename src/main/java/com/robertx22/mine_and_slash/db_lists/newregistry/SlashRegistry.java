package com.robertx22.mine_and_slash.db_lists.newregistry;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.particle_gens.ParticleGen;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.status_effects.bases.BaseStatusEffect;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.world_providers.BaseWorldProvider;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;

import java.util.HashMap;

public class SlashRegistry {

    private static HashMap<SlashRegistryType, SlashRegistryContainer> map = new HashMap<>();

    public static SlashRegistryContainer getRegistry(SlashRegistryType type) {
        return map.get(type);

    }

    public static ISlashRegistryEntry get(SlashRegistryType type, String guid) {
        return getRegistry(type).get(guid);

    }

    public static void init() {
        createRegistries();

    }

    private static void createRegistries() {

        map.put(SlashRegistryType.GEAR_TYPE, new SlashRegistryContainer<GearItemSlot>());
        map.put(SlashRegistryType.STAT, new SlashRegistryContainer<Stat>());
        map.put(SlashRegistryType.STATMOD, new SlashRegistryContainer<StatMod>());
        map.put(SlashRegistryType.SET, new SlashRegistryContainer<Set>());
        map.put(SlashRegistryType.SPELL, new SlashRegistryContainer<BaseSpell>());
        map.put(SlashRegistryType.UNIQUE_ITEM, new SlashRegistryContainer<IUnique>());
        map.put(SlashRegistryType.SUFFIX, new SlashRegistryContainer<Suffix>());
        map.put(SlashRegistryType.PREFIX, new SlashRegistryContainer<Prefix>());
        map.put(SlashRegistryType.RUNE, new SlashRegistryContainer<BaseRuneItem>());
        map.put(SlashRegistryType.RUNEWORD, new SlashRegistryContainer<RuneWord>());
        map.put(SlashRegistryType.MAP_AFFIX, new SlashRegistryContainer<BaseMapAffix>());
        map.put(SlashRegistryType.STATUS_EFFECT, new SlashRegistryContainer<BaseStatusEffect>());
        map.put(SlashRegistryType.PARTICLE_GEN, new SlashRegistryContainer<ParticleGen>());
        map.put(SlashRegistryType.WORLD_PROVIDER, new SlashRegistryContainer<BaseWorldProvider>());

    }

}
