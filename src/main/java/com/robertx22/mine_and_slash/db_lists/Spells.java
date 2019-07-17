package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.spells.SpellBonusEleBasicDmg;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellAcidBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellFireBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellIceBomb;
import com.robertx22.mine_and_slash.spells.aoe_bomb_proj.SpellThunderBomb;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellAcidExplosion;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellFlameExplosion;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellFrostExplosion;
import com.robertx22.mine_and_slash.spells.aoe_projectile.SpellLightningExplosion;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.nova.SpellFireNova;
import com.robertx22.mine_and_slash.spells.nova.SpellFrostNova;
import com.robertx22.mine_and_slash.spells.nova.SpellPoisonNova;
import com.robertx22.mine_and_slash.spells.nova.SpellThunderNova;
import com.robertx22.mine_and_slash.spells.projectile.SpellAcidBolt;
import com.robertx22.mine_and_slash.spells.projectile.SpellFireBolt;
import com.robertx22.mine_and_slash.spells.projectile.SpellFrostBolt;
import com.robertx22.mine_and_slash.spells.projectile.SpellThunderBolt;
import com.robertx22.mine_and_slash.spells.self.SpellInstantHeal;
import com.robertx22.mine_and_slash.spells.self.SpellSelfRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spells {
    public static HashMap<String, BaseSpell> All = new HashMap<String, BaseSpell>() {
        {
            {
                put(new SpellFrostBolt().GUID(), new SpellFrostBolt());
                put(new SpellFireBolt().GUID(), new SpellFireBolt());
                put(new SpellAcidBolt().GUID(), new SpellAcidBolt());
                put(new SpellThunderBolt().GUID(), new SpellThunderBolt());

                put(new SpellFrostExplosion().GUID(), new SpellFrostExplosion());
                put(new SpellFlameExplosion().GUID(), new SpellFlameExplosion());
                put(new SpellLightningExplosion().GUID(), new SpellLightningExplosion());
                put(new SpellAcidExplosion().GUID(), new SpellAcidExplosion());

                put(new SpellInstantHeal().GUID(), new SpellInstantHeal());
                put(new SpellSelfRegen().GUID(), new SpellSelfRegen());

                put(new SpellFireBomb().GUID(), new SpellFireBomb());
                put(new SpellThunderBomb().GUID(), new SpellThunderBomb());
                put(new SpellAcidBomb().GUID(), new SpellAcidBomb());
                put(new SpellIceBomb().GUID(), new SpellIceBomb());

                put(new SpellFrostNova().GUID(), new SpellFrostNova());
                put(new SpellFireNova().GUID(), new SpellFireNova());
                put(new SpellThunderNova().GUID(), new SpellThunderNova());
                put(new SpellPoisonNova().GUID(), new SpellPoisonNova());

            }
        }
    };

    private static List<BaseSpell> generated = new ArrayList<BaseSpell>() {
        {
            {
                add(new SpellBonusEleBasicDmg(Elements.Physical));

            }
        }
    };

    public static void init() {

        for (BaseSpell spell : generated) {
            IGenerated<BaseSpell> gen = (IGenerated<BaseSpell>) spell;
            gen.generateAllPossibleStatVariations().forEach(x -> All.put(x.GUID(), x));
        }

    }

}
