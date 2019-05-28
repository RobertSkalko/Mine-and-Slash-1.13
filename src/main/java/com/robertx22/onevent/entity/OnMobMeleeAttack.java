package com.robertx22.onevent.entity;

import com.robertx22.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnMobMeleeAttack {

    /**
     * On attack, cancel and spawn the real attack with my damage source, mobs don't
     * use energy but players do
     *
     * @param event
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMobMeleeAttack(LivingAttackEvent event) {

        if (event.getEntityLiving().world.isRemote) {
            return;
        }

        if (event.getSource() instanceof MyDamageSource || event.getSource()
                .getDamageType()
                .equals(DamageEffect.DmgSourceName)) {
            return;
        }

        try {
            if (event.getEntityLiving() == null || event.getSource()
                    .getTrueSource() == null || !(event.getSource()
                    .getTrueSource() instanceof EntityLivingBase)) {
                return;
            }

            EntityLivingBase source = (EntityLivingBase) event.getSource()
                    .getTrueSource();
            EntityLivingBase target = event.getEntityLiving();

            if (target.isAlive() == false) {
                return; // stops attacking dead mobs
            }

            if (ModConfig.INSTANCE.Server.USE_ATTACK_COOLDOWN.get()) {
                if ((float) target.hurtResistantTime > (float) target.maxHurtResistantTime / 2.0F) {
                    return;
                }
            }

            UnitData targetData = Load.Unit(target);
            UnitData sourceData = Load.Unit(source);

            if (targetData == null || sourceData == null) {
                return;
            }

            Unit targetUnit = targetData.getUnit();
            Unit sourceUnit = sourceData.getUnit();

            if (targetUnit == null || sourceUnit == null) {
                return;
            }

            targetData.recalculateStats(target);
            sourceData.recalculateStats(source);

            if (source instanceof EntityPlayer) {

                GearItemData weapondata = sourceData.getWeaponData(source);

                if (sourceData.isWeapon(weapondata)) {
                    if (sourceData.tryUseWeapon(weapondata, source)) {
                        sourceData.attackWithWeapon(source.getHeldItemMainhand(), weapondata, source, target, targetData);
                    }

                } else {
                    sourceData.unarmedAttack(source, target, targetData);
                }

            } else { // if its a mob

                sourceData.mobBasicAttack(source, target, sourceData, targetData, event.getAmount());

                if (event.getSource().getTrueSource() instanceof EntityLivingBase) {
                    EntityLivingBase defender = event.getEntityLiving();
                    EntityLivingBase attacker = (EntityLivingBase) event.getSource()
                            .getTrueSource();
                    defender.knockBack(attacker, 0.3F, attacker.posX - defender.posX, attacker.posZ - defender.posZ);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
