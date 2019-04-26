package com.robertx22.onevent.combat;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.stat_calculation.CommonStatUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
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

	if (entity.getCapability(EntityData.Data) != null) {
	    return;
	}

	try {
	    IWorldData data = Load.World(event.getWorld());

	    if (!(entity instanceof EntityPlayer)) {
		if (event.getWorld().getCapability(WorldData.Data).isPresent()) {

		    UnitData endata = Load.Unit(entity);
		    Unit check = endata.getUnit();

		    if (check == null) {

			Unit unit = Unit.Mob(entity, data);

			endata.forceSetUnit(unit);

		    }

		}
	    } else {
		UnitData endata = Load.Unit(entity);
		if (endata != null && endata.getUnit() != null) {
		    CommonStatUtils.addMapAffixes(data, entity, endata.getUnit(), endata);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
