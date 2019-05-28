package com.robertx22.spells.aoe_bomb_proj.bases;

import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.projectile.BaseSpellProjectile;
import com.robertx22.uncommon.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseBombSpell extends BaseSpellProjectile {

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("aoe_bomb_spell");

    }

    @Override
    public SpellType Type() {
        return SpellType.Aoe_Bomb_Projectile;
    }

    @Override
    public int ManaCost() {
        return 30;
    }

    public float damageScaling = 0.5F;

}
