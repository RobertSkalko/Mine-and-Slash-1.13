package com.robertx22.spells.aoe_projectile;

import com.robertx22.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.DamageData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.SpellEffectDamage;
import com.robertx22.spells.entities.EntityElementalBolt;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseAoeSpellProjectile extends BaseBoltAOE {

    @Override
    public int useTimeTicks() {
        return 20;
    }

    public BaseAoeSpellProjectile() {
        super();
    }

    @Override
    public SpellType Type() {
        return SpellType.Aoe_Projectile;
    }

    @Override
    public boolean cast(World world, EntityPlayer caster, EnumHand hand, int ticksInUse,
                        SpellItemData data) {

        if (!world.isRemote) {

            EntityElementalBolt projectile = this.projectile(world);
            projectile.SpawnAndShoot(new SpellEffectDamage(this.Element()), new DamageData(caster, data), caster);

        }

        SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
        caster.swingArm(hand);
        return true;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(this.Element()).GUID(), 0.25F);
    }

}