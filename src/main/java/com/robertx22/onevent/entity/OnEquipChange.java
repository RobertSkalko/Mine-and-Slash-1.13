package com.robertx22.onevent.entity;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Load;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OnEquipChange {

    // on change, notify my capability so i know stats need to be recalculated
    @SubscribeEvent
    public static void onEquipChange(LivingEquipmentChangeEvent event) {

        EntityLivingBase entity = event.getEntityLiving();
        if (entity != null) {
            EntityData.UnitData data = Load.Unit(entity);
            data.setEquipsChanged(true);
            data.recalculateStats(entity);

            if (entity instanceof EntityPlayer) {
                data.syncToClient((EntityPlayer) entity);
            }
        }

    }

}
