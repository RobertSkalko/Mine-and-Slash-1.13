package com.robertx22.uncommon.effectdatas;

import com.robertx22.config.ClientContainer;
import com.robertx22.config.ModConfig;
import com.robertx22.mmorpg.MMORPG;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.Words;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.effectdatas.interfaces.*;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.HealthUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.HashMap;
import java.util.Map.Entry;

public class DamageEffect extends EffectData implements IArmorReducable, IPenetrable, IDamageEffect, IElementalResistable, IElementalPenetrable, ICrittable {

    public DamageEffect(EntityLivingBase source, EntityLivingBase target, int dmg,
                        UnitData sourceData, UnitData targetData, EffectTypes effectType,
                        WeaponTypes weptype) {
        super(source, target, sourceData, targetData);

        this.setEffectType(effectType, weptype);
        this.number = dmg;
    }

    public HashMap<Elements, Integer> bonusElementDamageMap = new HashMap();

    public static String dmgSourceName = Ref.MODID + ".custom_damage";
    public Elements element = Elements.Physical;
    public int armorPene;
    public int elementalPene;

    public float damageMultiplier = 1;

    public float healthHealed;
    public float manaRestored;
    public boolean isFullyBlocked = false;
    public boolean isPartiallyBlocked = false;
    public boolean isDodged = false;

    public boolean isBlocked() {

        if (isFullyBlocked || isPartiallyBlocked) {
            return true;
        }
        return false;
    }

    @Override
    protected void activate() {

        if (this.canceled) {
            return;
        }

        this.number *= damageMultiplier; // this way axes can do double damage instead of doing double attacks

        MyDamageSource dmgsource = new MyDamageSource(dmgSourceName, this.source, element, (int) number);
        float dmg = HealthUtils.DamageToMinecraftHealth(number + 1, target);

        if (this.isPartiallyBlocked) {
            dmgsource.setDamageBypassesArmor();
        }

        if (this.isFullyBlocked == false) {

            this.sourceData.onAttackEntity(source, target);

            int hurtResistantTime = 0;

            if (ModConfig.INSTANCE.Server.USE_ATTACK_COOLDOWN.get()) {
                hurtResistantTime = target.hurtResistantTime;
            } else {
                hurtResistantTime = 3;
            }

            target.hurtResistantTime = 0;   // set to 0 so my attack can work (cus it comes after a vanilla atk) and then set it back to what it was before

            target.attackEntityFrom(dmgsource, dmg);
            target.hurtResistantTime = hurtResistantTime;
            //

            addBonusElementDamage();
            Heal();
            RestoreMana();

            if (ClientContainer.INSTANCE.RENDER_CHAT_COMBAT_LOG.get()) {
                LogCombat();
            }

            if ((int) number > 0 && source instanceof EntityPlayerMP) {

                EntityPlayerMP player = (EntityPlayerMP) source;
                DmgNumPacket packet = new DmgNumPacket(target, this.element, FormatDamageNumber(this));
                MMORPG.sendToClient(packet, player);

            }

        }

    }

    private void RestoreMana() {
        int restored = (int) manaRestored;
        if (restored > 0) {
            this.sourceData.restoreMana(restored);
        }
    }

    private void Heal() {
        int healed = (int) healthHealed;
        if (healed > 0) {
            sourceData.heal(source, healed);
        }
    }

    public DamageEffect setMultiplier(float multi) {
        this.damageMultiplier = multi;
        return this;
    }

    private void addBonusElementDamage() {
        for (Entry<Elements, Integer> entry : bonusElementDamageMap.entrySet()) {
            if (entry.getValue() > 0) {
                DamageEffect bonus = new DamageEffect(source, target, entry.getValue(), this.sourceData, this.targetData, EffectTypes.BONUS_ATTACK, this.weaponType);
                bonus.element = entry.getKey();
                bonus.damageMultiplier = this.damageMultiplier;
                bonus.Activate();
            }
        }
    }

    private void LogCombat() {

        if (this.getEffectType()
                .equals(EffectTypes.BONUS_ATTACK)) { // don't spam chat with bonus damaages
            return;
        }

        if (this.source instanceof EntityPlayer) {

            String s = Words.Dealt.translate() + LogDamage() + Words.To.translate() + " " + this.target
                    .getName() + " " + LogCurrentHP(this.target, this.targetUnit);
            this.source.sendMessage(new TextComponentString(s));

        }

        if (this.target instanceof EntityPlayer) {

            String s = Words.Took.translate() + LogDamage() + Words.From.translate() + " " + this.source
                    .getName() + " " + LogCurrentHP(this.target, this.targetUnit);
            this.target.sendMessage(new TextComponentString(s));

        }

    }

    private String LogCurrentHP(EntityLivingBase entity, Unit unit) {

        String str = TextFormatting.LIGHT_PURPLE + "[" + unit.health()
                .CurrentValue(entity, unit) + "/" + (int) unit.healthData().Value + "]";

        return str;

    }

    public static String FormatNumber(int Number) {

        String num = "";
        if (Number > 1000) {
            int thousands = (int) (Number / 1000);

            int leftover = (int) ((Number - thousands * 1000) / 100);

            num = thousands + "." + leftover + "k";
        } else {
            num = Number + "";
        }

        return num;
    }

    public static String FormatDamageNumber(DamageEffect data) {
        String num = FormatNumber((int) data.number);

        if (data.crit) {
            num += "!";
        }

        return num;
    }

    private String LogDamage() {

        String num = FormatDamageNumber(this);

        String str = " " + num + " " + Words.Damage.translate() + " ";

        if (element != null) {
            str = element.format + str;

        }

        return str;

    }

    @Override
    public EntityLivingBase Source() {
        return source;
    }

    @Override
    public EntityLivingBase Target() {
        return target;
    }

    @Override
    public float Number() {
        return number;
    }

    @Override
    public Elements GetElement() {
        return element;
    }

    @Override
    public void SetArmorPenetration(int val) {
        this.armorPene = val;

    }

    @Override
    public void addElementalPenetration(int val) {
        this.elementalPene += val;
    }

    @Override
    public int GetArmorPenetration() {
        return this.armorPene;
    }

    public boolean crit = false;

    @Override
    public void SetCrit(boolean bool) {
        crit = bool;
    }

    @Override
    public boolean GetCrit() {
        return crit;
    }

    @Override
    public int GetElementalPenetration() {
        return this.elementalPene;
    }

}
