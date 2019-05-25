package com.robertx22.onevent.entity;

import com.robertx22.db_lists.Rarities;
import com.robertx22.loot.LootUtils;
import com.robertx22.loot.MasterLootGen;
import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.DmgNumPacket;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.enumclasses.Elements;
import com.robertx22.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class OnMobDeathDrops {

    @SubscribeEvent
    public static void mobOnDeathDrop(LivingDeathEvent event) {

        if (event.getEntityLiving().world.isRemote) {
            return;
        }

        try {

            EntityLivingBase entity = event.getEntityLiving();

            if (!(entity instanceof EntityPlayer)) {
                if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                    if (Load.hasUnit(entity)) {

                        EntityPlayer player = (EntityPlayer) event.getSource()
                                .getTrueSource();

                        UnitData victim = Load.Unit(entity);
                        UnitData killer = Load.Unit(event.getSource().getTrueSource());

                        if (victim.shouldDropLoot(entity) == false) {
                            return;
                        }
                        float loot_multi = EntityTypeUtils.getLootMulti(entity);
                        float exp_multi = EntityTypeUtils.getExpMulti(entity);

                        if (loot_multi > 0) {

                            MasterLootGen.genAndDrop(victim, killer, entity, player);

                        }

                        if (exp_multi > 0) {
                            int exp = GiveExp(entity, (EntityLivingBase) event.getSource()
                                    .getTrueSource(), killer, victim, exp_multi);

                            DmgNumPacket packet = new DmgNumPacket(entity, Elements.Nature, "+" + DamageEffect
                                    .FormatNumber(exp) + " Exp!");
                            packet.isExp = true;

                            EntityPlayerMP mp = (EntityPlayerMP) event.getSource()
                                    .getTrueSource();

                            MMORPG.sendToClient(packet, mp);
                        }
                    }
                }
            }

        } catch (

                Exception e) {
            e.printStackTrace();
        }

    }

    private static int GiveExp(EntityLivingBase victim, EntityLivingBase entity,
                               UnitData player, UnitData mob, float multi) {

        int exp = (int) (mob.getLevel() * Rarities.Mobs.get(mob.getRarity())
                .ExpOnKill() * multi);

        exp = (int) LootUtils.ApplyLevelDistancePunishment(mob, player, exp);

        exp = player.PostGiveExpEvent(victim, (EntityPlayer) entity, exp);

        return exp;

    }

}
