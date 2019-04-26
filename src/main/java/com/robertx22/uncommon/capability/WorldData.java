package com.robertx22.uncommon.capability;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.dimensions.MyTeleporter;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.MapWorldData;
import com.robertx22.uncommon.SLOC;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WorldData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "worlddata");

    @CapabilityInject(IWorldData.class)
    public static final Capability<IWorldData> Data = null;

    public interface IWorldData {
	NBTTagCompound getNBT();

	void setNBT(NBTTagCompound value);

	boolean isMapWorld();

	boolean isSetForDelete();

	int getWorldID();

	int getTier();

	int getLevel();

	void setOwner(EntityPlayer player);

	String getOwner();

	void init(BlockPos pos, World world, MapItemData map, int dimensionId, EntityPlayer owner);

	void delete(EntityPlayer player, World mapworld);

	MapItemData getMap();

	boolean isInit();

	boolean didntSetBackPortal();

	void setBackPortal();

	String getSaveName();

	void setSaveName(String name);

	BlockPos getMapDevicePos();

	DimensionType getOriginalDimension();

	void teleportPlayerBack(EntityPlayer player);

	MapWorldData getWorldData();

	void setWorldData(MapWorldData data);

	void passMinute(World world);

	void onPlayerDeath(EntityPlayer victim, World world);

	void setDelete(boolean bool, World world);

	boolean isReserved();

	void setReserved(boolean bool);

	boolean isOwner(EntityPlayer player);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
	@SubscribeEvent
	public static void onWorldConstuct(AttachCapabilitiesEvent<World> event) {

	    event.addCapability(RESOURCE, new ICapabilitySerializable<NBTTagCompound>() {

		IWorldData impl = new DefaultImpl();
		private final LazyOptional<IWorldData> cap = LazyOptional.of(() -> impl);

		@Override
		public NBTTagCompound serializeNBT() {
		    return (NBTTagCompound) Data.getStorage().writeNBT(Data, impl, null);
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
		    Data.getStorage().readNBT(Data, impl, null, nbt);
		}

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
		    if (cap == Data) {
			return this.cap.cast();
		    }
		    return LazyOptional.empty();
		}
	    });

	}

    }

    public static class Storage implements IStorage<IWorldData> {
	@Override
	public INBTBase writeNBT(Capability<IWorldData> capability, IWorldData instance, EnumFacing side) {

	    return instance.getNBT();
	}

	@Override
	public void readNBT(Capability<IWorldData> capability, IWorldData instance, EnumFacing side, INBTBase nbt) {

	    instance.setNBT((NBTTagCompound) nbt);

	}
    }

    static final String SET_FOR_DELETE = "setForDelete";
    static final String IS_MAP_WORLD = "isMap";
    static final String LEVEL = "level";
    static final String OWNER = "owner";
    static final String TIER = "tier";
    static final String MAP_OBJECT = "mapObject";
    static final String IS_INIT = "isInit";
    static final String ORIGINAL_DIM = "original_dimension";
    static final String MAP_DIM = "map_dimension";
    static final String DIDNT_SET_BACK_PORTAL = "didntSetBackPortal";
    static final String SAVE_NAME = "save_name";
    static final String POS_OBJ = "POS_OBJ";
    static final String MAP_WORLD_OBJ = "MAP_WORLD_OBJ";
    static final String MINUTES_PASSED = "minutes_passed";
    static final String ISRESERVED = "is_reserved";

    public static class DefaultImpl implements IWorldData {
	private NBTTagCompound nbt = new NBTTagCompound();

	long mapDevicePos;
	MapItemData mapdata = new MapItemData();
	MapWorldData mapworlddata = new MapWorldData();
	int tier = 0;
	int level = 0;
	boolean isMap = false;
	boolean setForDelete = false;
	String owner = "";
	boolean isInit = false;
	DimensionType originalDimension;
	int mapDimension;
	boolean didntSetBackPortal = true;
	String saveName = "";
	int minutesPassed;
	boolean reserved = false;

	@Override
	public NBTTagCompound getNBT() {
	    nbt.putInt(TIER, tier);
	    nbt.putInt(LEVEL, level);
	    nbt.putBoolean(IS_MAP_WORLD, isMap);
	    nbt.putBoolean(SET_FOR_DELETE, setForDelete);
	    nbt.putString(OWNER, owner);
	    nbt.putBoolean(IS_INIT, isInit);
	    nbt.putString(ORIGINAL_DIM, originalDimension.getRegistryName().toString());
	    nbt.putInt(MAP_DIM, mapDimension);
	    nbt.putBoolean(DIDNT_SET_BACK_PORTAL, didntSetBackPortal);
	    nbt.putString(SAVE_NAME, saveName);
	    nbt.putInt(MINUTES_PASSED, minutesPassed);
	    nbt.putBoolean(ISRESERVED, reserved);

	    if (mapdata != null) {
		NBTTagCompound tag = new NBTTagCompound();
		Writer.write(tag, mapdata);
		nbt.put(MAP_OBJECT, tag);
	    }
	    if (mapworlddata != null) {
		NBTTagCompound tag = new NBTTagCompound();
		Writer.write(tag, mapworlddata);
		nbt.put(MAP_WORLD_OBJ, tag);
	    }

	    nbt.putLong(POS_OBJ, mapDevicePos);

	    return nbt;

	}

	@Override
	public void setNBT(NBTTagCompound value) {
	    this.nbt = value;
	    tier = nbt.getInt(TIER);
	    level = nbt.getInt(LEVEL);
	    isMap = nbt.getBoolean(IS_MAP_WORLD);
	    setForDelete = nbt.getBoolean(SET_FOR_DELETE);
	    owner = nbt.getString(OWNER);
	    isInit = nbt.getBoolean(IS_INIT);
	    this.originalDimension = DimensionType.byName(new ResourceLocation(nbt.getString(ORIGINAL_DIM)));
	    this.mapDimension = nbt.getInt(MAP_DIM);
	    this.didntSetBackPortal = nbt.getBoolean(DIDNT_SET_BACK_PORTAL);
	    this.saveName = nbt.getString(SAVE_NAME);
	    this.minutesPassed = nbt.getInt(MINUTES_PASSED);
	    this.reserved = nbt.getBoolean(ISRESERVED);

	    NBTTagCompound mapnbt = (NBTTagCompound) this.nbt.get(MAP_OBJECT);
	    if (mapnbt != null) {
		Reader.read(mapnbt, mapdata);
	    }

	    NBTTagCompound mapworldnbt = (NBTTagCompound) this.nbt.get(MAP_WORLD_OBJ);
	    if (mapworldnbt != null) {
		Reader.read(mapworldnbt, mapworlddata);
	    }

	    this.mapDevicePos = nbt.getLong(POS_OBJ);

	}

	@Override
	public boolean isMapWorld() {
	    return isMap;
	}

	@Override
	public boolean isSetForDelete() {
	    return setForDelete;
	}

	@Override
	public int getWorldID() {
	    return this.mapDimension;
	}

	@Override
	public int getLevel() {
	    return level;
	}

	@Override
	public void setOwner(EntityPlayer player) {
	    this.owner = player.getUniqueID().toString();

	}

	@Override
	public String getOwner() {
	    return owner;
	}

	@Override
	public void delete(EntityPlayer player, World mapworld) {

	    if (this.isMapWorld()) {

		if (isOwner(player)) {
		    this.setForDelete = true;

		    this.transferPlayersBack(mapworld);
		} else {

		    player.sendMessage(SLOC.chat("cant_delete_world"));
		}
	    }
	}

	@Override
	public void init(BlockPos pos, World world, MapItemData map, int dimensionId, EntityPlayer owner) {

	    this.isMap = true;
	    this.level = map.level;
	    this.tier = map.tier;
	    this.mapdata = map;
	    // this.originalDimension = world.provider.getDimension();
	    this.mapDimension = dimensionId;
	    this.mapDevicePos = pos.toLong();
	    this.isInit = true;
	    this.setOwner(owner);
	    this.reserved = false;

	}

	@Override
	public int getTier() {
	    return tier;
	}

	@Override
	public MapItemData getMap() {
	    return mapdata;
	}

	@Override
	public boolean isInit() {
	    return isInit;
	}

	@Override
	public boolean didntSetBackPortal() {
	    return this.didntSetBackPortal;
	}

	@Override
	public void setBackPortal() {
	    this.didntSetBackPortal = false;
	}

	@Override
	public String getSaveName() {
	    return saveName;
	}

	@Override
	public void setSaveName(String name) {
	    this.saveName = name;
	}

	@Override
	public BlockPos getMapDevicePos() {
	    return BlockPos.fromLong(mapDevicePos).south(3);
	}

	@Override
	public DimensionType getOriginalDimension() {
	    return this.originalDimension;
	}

	private void transferPlayersBack(World world) {

	    if (world.playerEntities != null) {
		List<EntityPlayer> players = new ArrayList<EntityPlayer>(world.playerEntities);
		for (EntityPlayer player : players) {
		    if (player.isAlive()) {
			teleportPlayerBack(player);
		    }
		}
	    }

	}

	@Override
	public void teleportPlayerBack(EntityPlayer player) {

	    BlockPos pos = player.getBedLocation();

	    if (getMapDevicePos() != null) {

		pos = getMapDevicePos();
		pos = pos.north(2);

		if (pos != null) {
		    player.setPosition(pos.getX(), pos.getY(), pos.getZ());
		}

	    }

	    player.changeDimension(this.originalDimension, new MyTeleporter(player.world, pos, player));

	}

	@Override
	public MapWorldData getWorldData() {
	    return this.mapworlddata;
	}

	@Override
	public void setWorldData(MapWorldData data) {
	    this.mapworlddata = data;
	}

	@Override
	public void passMinute(World world) {

	    if (this.isMap) {
		this.minutesPassed++;

		onMinutePassAnnounce(world);

		checkDeletition(world);
	    }

	}

	private void onMinutePassAnnounce(World world) {
	    int minutesLeft = getMinutesLeft();

	    if (minutesLeft > 0) {
		if (minutesLeft < 5 || minutesLeft % 5 == 0) {
		    announceTimeLeft(world);
		}
	    }
	}

	private void checkDeletition(World world) {

	    if (this.getMinutesLeft() < 1) {
		this.setDelete(true, world);

	    }

	}

	private int getMinutesLeft() {
	    return this.mapdata.minutes - this.minutesPassed;

	}

	private void announceDeletition(World world) {
	    for (EntityPlayer player : world.playerEntities) {
		player.sendMessage(SLOC.chat("mapworld_ran_out_of_time"));

	    }
	}

	private void announceTimeLeft(World world) {

	    for (EntityPlayer player : world.playerEntities) {
		player.sendMessage(SLOC.chat("mapworld_time_left").appendText(" " + this.getMinutesLeft() + " ")
			.appendSibling(SLOC.chat("minutes")));

	    }

	}

	@Override
	public void setDelete(boolean bool, World world) {
	    this.setForDelete = bool;

	    if (bool) {

		announceDeletition(world);

		this.transferPlayersBack(world);
	    }

	}

	@Override
	public void onPlayerDeath(EntityPlayer victim, World world) {

	    int punishment = 5;

	    for (EntityPlayer player : world.playerEntities) {
		player.sendMessage(SLOC.chat("player_died_mapworld")
			.appendText(" " + victim.getDisplayName().getFormattedText() + " ")
			.appendSibling(SLOC.chat("activating_mapworld_time_penalty")));
	    }

	    this.minutesPassed += punishment;

	    announceTimeLeft(world);

	    checkDeletition(world);

	}

	@Override
	public boolean isReserved() {
	    return reserved;
	}

	@Override
	public void setReserved(boolean bool) {
	    this.reserved = bool;

	}

	@Override
	public boolean isOwner(EntityPlayer player) {
	    return player.getUniqueID().toString().equals(this.owner);
	}

    }

}
