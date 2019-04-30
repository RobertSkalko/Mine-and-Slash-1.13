package com.robertx22.onevent.combat;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.stat_calculation.CommonStatUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnMobSpawn {

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

	if (!(event.getEntity() instanceof EntityLivingBase)) {
	    return;
	}

	EntityLivingBase entity = (EntityLivingBase) event.getEntity();

	if (entity.world.isRemote) {
	    return;
	}

	UnitData endata = Load.Unit(entity);
	IWorldData data = Load.World(event.getWorld());

	if (endata != null && data != null) {

	    if (entity instanceof EntityPlayer) {
		if (endata.getUnit() != null) {
		    CommonStatUtils.addMapAffixes(data, entity, endata.getUnit(), endata);
		}

	    } else {

		if (endata.needsToBeGivenStats()) {
		    Unit unit = Unit.Mob(entity, data);
		    endata.forceSetUnit(unit);
		}

	    }

	}
    }

}
