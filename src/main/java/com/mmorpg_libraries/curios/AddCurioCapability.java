package com.mmorpg_libraries.curios;

import com.mmorpg_libraries.curios.interfaces.ICuriosType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AddCurioCapability {

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> evt) {

        if (evt.getObject().getItem() instanceof ICuriosType) {

            ICuriosType type = (ICuriosType) evt.getObject().getItem();

            ICurio curio = new ICurio() {

                @Override
                public boolean canRightClickEquip() {
                    return type.rightClickEquip();
                }
            };

            evt.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
                private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

                @Nonnull
                @Override
                public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
                                                         @Nullable EnumFacing side) {

                    return CuriosCapability.ITEM.orEmpty(cap, curioOpt);

                }
            });
        }
    }

}
