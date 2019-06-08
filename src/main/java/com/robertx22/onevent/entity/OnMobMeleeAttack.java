package com.robertx22.onevent.entity;

import com.robertx22.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.spells.bases.MyDamageSource;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
                .equals(DamageEffect.dmgSourceName)) {
            return;
        }

        try {
            if (event.getEntityLiving() == null || event.getSource()
                    .getTrueSource() == null || !(event.getSource()
                    .getTrueSource() instanceof LivingEntity)) {
                return;
            }

            LivingEntity source = (LivingEntity) event.getSource()
                    .getTrueSource();
            LivingEntity target = event.getEntityLiving();

            if (target.isAlive() == false) {
                return; // stops attacking dead mobs
            }

            UnitData sourceData = Load.Unit(source);

            if (ModConfig.INSTANCE.Server.USE_ATTACK_COOLDOWN.get()) {
                if (sourceData.attackCooldownAllowsAttack(source, target) == false) {
                    return;
                }
            }
            UnitData targetData = Load.Unit(target);

            if (targetData == null || sourceData == null) {
                return;
            }

            targetData.recalculateStats(target);
            sourceData.recalculateStats(source);

            if (source instanceof PlayerEntity) {

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

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
