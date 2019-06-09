package com.mmorpg_libraries.curios;

import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AddCurioCapability {
/*
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
                                                         @Nullable Direction side) {

                    return CuriosCapability.ITEM.orEmpty(cap, curioOpt);

                }
            });
        }
    }


 */
}
