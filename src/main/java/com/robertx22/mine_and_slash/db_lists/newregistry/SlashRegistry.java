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

    private static HashMap<RegistryEntryType, SlashRegistryEntry> map = new HashMap<>();

    public static SlashRegistryEntry getRegistry(RegistryEntryType type) {
        return map.get(type);

    }

    public static IRegistryEntry get(RegistryEntryType type, String guid) {
        return getRegistry(type).get(guid);

    }

    public static void createRegistries() {

        map.put(RegistryEntryType.GEAR_TYPE, new SlashRegistryEntry<GearItemSlot>());
        map.put(RegistryEntryType.STAT, new SlashRegistryEntry<Stat>());
        map.put(RegistryEntryType.STATMOD, new SlashRegistryEntry<StatMod>());
        map.put(RegistryEntryType.SET, new SlashRegistryEntry<Set>());
        map.put(RegistryEntryType.SPELL, new SlashRegistryEntry<BaseSpell>());
        map.put(RegistryEntryType.UNIQUE_ITEM, new SlashRegistryEntry<IUnique>());
        map.put(RegistryEntryType.SUFFIX, new SlashRegistryEntry<Suffix>());
        map.put(RegistryEntryType.PREFIX, new SlashRegistryEntry<Prefix>());
        map.put(RegistryEntryType.RUNE, new SlashRegistryEntry<BaseRuneItem>());
        map.put(RegistryEntryType.RUNEWORD, new SlashRegistryEntry<RuneWord>());
        map.put(RegistryEntryType.MAP_AFFIX, new SlashRegistryEntry<BaseMapAffix>());
        map.put(RegistryEntryType.MAP_AFFIX, new SlashRegistryEntry<BaseStatusEffect>());
        map.put(RegistryEntryType.PARTICLE_GEN, new SlashRegistryEntry<ParticleGen>());
        map.put(RegistryEntryType.WORLD_PROVIDER, new SlashRegistryEntry<BaseWorldProvider>());

    }

}
