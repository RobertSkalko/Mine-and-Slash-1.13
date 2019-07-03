package com.robertx22.uncommon;

import com.robertx22.mmorpg.Ref;
import net.minecraft.util.ResourceLocation;

public class Res {
    public static final ResourceLocation loc(String id) {
        return new ResourceLocation(Ref.MODID, id);
    }
}
