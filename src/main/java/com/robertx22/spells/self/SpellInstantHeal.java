package com.robertx22.spells.self;

import com.robertx22.database.stats.stat_types.resources.Health;
import com.robertx22.items.spells.self.ItemInstantHeal;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.EffectCalculation;
import com.robertx22.spells.bases.SpellBuffCheck;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.SpellBuffEffect;
import com.robertx22.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class SpellInstantHeal extends BaseSpellHeal {

    @Override
    public SpellType Type() {
        return SpellType.Self_Heal;
    }

    @Override
    public String GUID() {
        return "instant_heal";
    }

    @Override
    public int ManaCost() {
        return 30;
    }

    @Override
    public int BaseValue() {
        return 6;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new Health(), 0.25F);

    }

    @Override
    public Item SpellItem() {
        return ItemInstantHeal.ITEM;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("spell_instant_heal");
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, Hand hand, int ticksInUse,
                        SpellItemData data) {

        try {

            if (!world.isRemote) {

                UnitData unit = Load.Unit(caster);
                unit.heal(caster, data.GetDamage(unit.getUnit()));
                SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);
                // spell buffs
                SpellBuffCheck check = new SpellBuffCheck(this.Type());
                SpellBuffEffect spelleffect = new SpellBuffEffect(caster, check);
                spelleffect.Activate();
                checkSpellBuffs(caster, check);
                //
            } else {

                ParticleUtils.spawnHealParticles(caster, 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
