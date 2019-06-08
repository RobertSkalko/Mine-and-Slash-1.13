package com.robertx22.spells.bases;

import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.NoEnergyPacket;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.Chats;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.interfaces.IWeighted;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class BaseSpell implements IWeighted {

    public enum SpellType {
        Single_Target_Projectile,
        Aoe_Projectile,
        Self_Heal,
        Aoe_Bomb_Projectile,
        Restore_Energy,
        Aoe_Damage_Nova
    }

    public String typeString() {

        return this.Type().toString().replaceAll("_", " ");

    }

    public boolean hasScalingValue() {
        return true;
    }

    public boolean baseValueScalesWithLevel() {
        return true;
    }

    public abstract SpellType Type();

    public abstract String GUID();

    public abstract int ManaCost();

    public abstract int useTimeTicks();

    public float getUseDurationInSeconds() {
        return (float) useTimeTicks() / 20;
    }

    // public abstract int Cooldown();

    public abstract int BaseValue();

    public abstract EffectCalculation ScalingValue();

    public int DamageVariance = 50;

    public abstract Elements Element();

    public boolean ScalesWithLevel = true;

    public abstract Item SpellItem();

    public BaseSpell() {

    }

    public abstract ITextComponent GetDescription(SpellItemData data);

    public int Weight() {
        return 1000;
    }

    public abstract boolean cast(World world, PlayerEntity caster, Hand hand,
                                 int ticksInUse, SpellItemData data);

    public boolean CanCast(PlayerEntity caster, SpellItemData data) {

        if (!caster.world.isRemote) {

            UnitData unit = Load.Unit(caster);

            if (unit != null) {

                if (data.level > unit.getLevel()) {
                    caster.sendMessage(Chats.You_are_too_low_level.locName());

                    return false;
                }

                if (unit.hasEnoughMana(data.GetManaCost())) {
                    unit.consumeMana(data.GetManaCost());

                    return true;

                } else {
                    if (caster instanceof ServerPlayerEntity) {
                        MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) caster);

                    }

                }
            }
        }
        return false;

    }

}
