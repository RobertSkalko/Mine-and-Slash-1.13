package com.robertx22.uncommon.capability;

import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.PlayersCapBackup;
import com.robertx22.uncommon.capability.bases.BaseProvider;
import com.robertx22.uncommon.capability.bases.BaseStorage;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.PlayersCapBackupSaving;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerCapBackupData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_cap_backup_data");

    @CapabilityInject(IPlayerCapBackupData.class)
    public static final Capability<IPlayerCapBackupData> Data = null;

    static final String BACKUP = "players_capa_backup";

    public interface IPlayerCapBackupData extends ICommonCapability {

        PlayersCapBackup getBackup();

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onWorld(AttachCapabilitiesEvent<World> event) {
            if (event.getObject().getDimension().getType() == DimensionType.OVERWORLD) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<IPlayerCapBackupData> {

        @Override
        public IPlayerCapBackupData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IPlayerCapBackupData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IPlayerCapBackupData {

        PlayersCapBackup backup = new PlayersCapBackup();

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();

            if (backup != null) {
                PlayersCapBackupSaving.Save(nbt, backup);
            }

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT value) {

            backup = PlayersCapBackupSaving.Load(value);

        }

        @Override
        public PlayersCapBackup getBackup() {
            return this.backup;
        }
    }

    public static class Storage extends BaseStorage<IPlayerCapBackupData> {

    }

}
