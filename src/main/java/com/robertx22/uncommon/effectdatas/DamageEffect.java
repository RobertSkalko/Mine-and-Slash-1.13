package com.robertx22.uncommon.effectdatas;

import com.robertx22.Words;
import com.robertx22.config.ClientContainer;
import com.robertx22.mmorpg.MMORPG;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
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
        this.Number = dmg;
    }

    public HashMap<Elements, Integer> BonusElementDamageMap = new HashMap();

    public static String DmgSourceName = Ref.MODID + ".custom_damage";
    public Elements Element = Elements.None;
    public int ArmorPene;
    public int ElementalPene;

    public float damageMultiplier = 1;

    public float healthHealed;
    public float manaRestored;
    public boolean isFullyBlocked = false;
    public boolean isPartiallyBlocked = false;

    @Override
    protected void activate() {

        this.Number *= damageMultiplier; // this way axes can do double damage instead of doing double attacks

        MyDamageSource dmgsource = new MyDamageSource(DmgSourceName, this.Source, Element, (int) Number);
        float dmg = HealthUtils.DamageToMinecraftHealth(Number + 1, Target);

        if (this.isPartiallyBlocked) {
            dmgsource.setDamageBypassesArmor();
        }

        if (this.isFullyBlocked == false) {

            this.sourceData.onAttackEntity(Source, Target);

            // set to 0 so my attack can work (cus it comes after a vanilla atk) and then set it back to what it was before
            int hurttime = Target.hurtResistantTime;
            Target.hurtResistantTime = 0;
            Target.attackEntityFrom(dmgsource, dmg);
            Target.hurtResistantTime = hurttime;
            //

            addBonusElementDamage();
            Heal();
            RestoreMana();

            if (ClientContainer.INSTANCE.RENDER_CHAT_COMBAT_LOG.get()) {
                LogCombat();
            }

            if ((int) Number > 0 && Source instanceof EntityPlayerMP) {

                EntityPlayerMP player = (EntityPlayerMP) Source;
                DmgNumPacket packet = new DmgNumPacket(Target, this.Element, FormatDamageNumber(this));
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
            sourceData.heal(Source, healed);
        }
    }

    public DamageEffect setMultiplier(float multi) {
        this.damageMultiplier = multi;
        return this;
    }

    private void addBonusElementDamage() {
        for (Entry<Elements, Integer> entry : BonusElementDamageMap.entrySet()) {
            if (entry.getValue() > 0) {
                DamageEffect bonus = new DamageEffect(Source, Target, entry.getValue(), this.sourceData, this.targetData, EffectTypes.BONUS_ATTACK, this.weaponType);
                bonus.Element = entry.getKey();
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

        if (this.Source instanceof EntityPlayer) {

            String s = Words.Dealt.locNameForLangFile() + LogDamage() + Words.To.locNameForLangFile() + " " + this.Target
                    .getName() + " " + LogCurrentHP(this.Target, this.targetUnit);
            this.Source.sendMessage(new TextComponentString(s));

        }

        if (this.Target instanceof EntityPlayer) {

            String s = Words.Took.locNameForLangFile() + LogDamage() + Words.From.locNameForLangFile() + " " + this.Source
                    .getName() + " " + LogCurrentHP(this.Target, this.targetUnit);
            this.Target.sendMessage(new TextComponentString(s));

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
        String num = FormatNumber((int) data.Number);

        if (data.crit) {
            num += "!";

        }

        return num;
    }

    private String LogDamage() {

        String num = FormatDamageNumber(this);

        String str = " " + num + " " + Words.Damage.locNameForLangFile() + " ";

        if (Element == null || Element.equals(Elements.None)) {
            str = TextFormatting.GRAY + str;
        } else {
            if (Element.equals(Elements.Fire)) {
                str = TextFormatting.RED + str;
            }
            if (Element.equals(Elements.Water)) {
                str = TextFormatting.BLUE + str;
            }
            if (Element.equals(Elements.Thunder)) {
                str = TextFormatting.YELLOW + str;
            }
            if (Element.equals(Elements.Nature)) {
                str = TextFormatting.GREEN + str;
            }
        }

        return str;

    }

    @Override
    public EntityLivingBase Source() {
        return Source;
    }

    @Override
    public EntityLivingBase Target() {
        return Target;
    }

    @Override
    public float Number() {
        return Number;
    }

    @Override
    public Elements GetElement() {
        return Element;
    }

    @Override
    public void SetArmorPenetration(int val) {
        this.ArmorPene = val;

    }

    @Override
    public void addElementalPenetration(int val) {
        this.ElementalPene += val;
    }

    @Override
    public int GetArmorPenetration() {
        return this.ArmorPene;
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
        return this.ElementalPene;
    }

}
