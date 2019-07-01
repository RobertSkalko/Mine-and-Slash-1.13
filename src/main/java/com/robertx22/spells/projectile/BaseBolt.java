package com.robertx22.spells.projectile;

import com.robertx22.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.SpellEffectDamage;
import com.robertx22.spells.entities.bases.EntityElementalBolt;
import com.robertx22.uncommon.localization.CLOC;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class BaseBolt extends BaseSpell {

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(this.Element()), 0.5F);
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, Hand hand, int ticksInUse,
                        SpellItemData data) {

        if (!world.isRemote) {
            EntityElementalBolt projectile = this.projectile(world);
            projectile.SpawnAndShoot(new SpellEffectDamage(this.Element()), new DamageData(caster, data), caster);

        }

        SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SPLASH_POTION_THROW, 1, 1);
        caster.swingArm(hand);
        return true;
    }

    @Override
    public int ManaCost() {
        return 10;
    }

    @Override
    public int BaseValue() {
        return 6;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("single_target_spell_bolt");

    }

    public abstract EntityElementalBolt projectile(World world);
}
