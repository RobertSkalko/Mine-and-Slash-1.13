package com.robertx22.mmorpg.registers;

import com.robertx22.mmorpg.Ref;

import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.CuriosAPI;

public class RegisterCurioClient {

    public static void icons() {
	CuriosAPI.registerIcon("charm", new ResourceLocation(Ref.MODID, "item/slots/empty_charm_slot"));
	CuriosAPI.registerIcon("ring", new ResourceLocation(Ref.MODID, "item/slots/empty_ring_slot"));
	CuriosAPI.registerIcon("necklace", new ResourceLocation(Ref.MODID, "item/slots/empty_necklace_slot"));
	CuriosAPI.registerIcon("bracelet", new ResourceLocation(Ref.MODID, "item/slots/empty_bracelet_slot"));
	CuriosAPI.registerIcon("salvage_bag", new ResourceLocation(Ref.MODID, "item/slots/empty_salvage_bag_slot"));

    }
}
