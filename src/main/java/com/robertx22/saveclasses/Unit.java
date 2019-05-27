package com.robertx22.saveclasses;

import com.robertx22.config.ModConfig;
import com.robertx22.config.dimension_configs.DimensionConfig;
import com.robertx22.config.dimension_configs.DimensionsContainer;
import com.robertx22.database.gearitemslots.bases.GearItemSlot.GearSlotType;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stats.Stat;
import com.robertx22.database.stats.stat_types.resources.Energy;
import com.robertx22.database.stats.stat_types.resources.Health;
import com.robertx22.database.stats.stat_types.resources.Mana;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Stats;
import com.robertx22.mmorpg.MMORPG;
import com.robertx22.network.EntityUnitPacket;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.PlayerMapData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.stat_calculation.CommonStatUtils;
import com.robertx22.uncommon.stat_calculation.MobStatUtils;
import com.robertx22.uncommon.stat_calculation.PlayerStatUtils;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

@Storable
public class Unit {

    @Store
    public HashMap<String, StatData> MyStats = null;

    @Store
    public HashMap<String, Integer> WornSets = new HashMap<String, Integer>();

    @Store
    public HashMap<String, StatusEffectData> statusEffects = new HashMap<String, StatusEffectData>();

    //@Store
    //public HashMap<String, MapAffixData> mapAffixes = new HashMap<String, MapAffixData>(); // possibly not needed as i can just get affixes from world?

    @Store
    public String GUID = UUID.randomUUID().toString();

    public Unit() {

    }

    public void InitMobStats() {

        if (MyStats == null) {
            MyStats = new HashMap<String, StatData>();
            // adds all stats
            for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
                MyStats.put(stat.GUID(), new StatData(stat));
            }

        }

    }

    public void InitPlayerStats() {

        if (MyStats == null) {
            MyStats = new HashMap<String, StatData>();

            // adds all stats
            for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
                MyStats.put(stat.GUID(), new StatData(stat));
            }

        } else {
            // adds new stats
            for (Stat stat : com.robertx22.db_lists.Stats.All.values()) {
                if (!MyStats.containsKey(stat.GUID())) {
                    MyStats.put(stat.GUID(), new StatData(stat));
                }
            }
            // removes stats that were deleted or renamed
            HashMap<String, StatData> stats = new HashMap<String, StatData>(MyStats);
            for (Entry<String, StatData> entry : stats.entrySet()) {
                if (!Stats.All.containsKey(entry.getKey())) {
                    MyStats.remove(entry.getKey());
                }
            }

        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Unit) {
            return ((Unit) obj).GUID == this.GUID;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return GUID.hashCode();
    }

    // Stat shortcuts
    public Health health() {
        return (Health) MyStats.get(new Health().GUID()).GetStat();
    }

    public Mana mana() {
        return (Mana) MyStats.get(new Mana().GUID()).GetStat();
    }

    public Energy energy() {
        return (Energy) MyStats.get(new Energy().GUID()).GetStat();
    }

    public StatData healthData() {
        try {
            return MyStats.get(new Health().GUID());
        } catch (Exception e) {
        }
        return null;
    }

    public StatData manaData() {
        try {
            return MyStats.get(new Mana().GUID());
        } catch (Exception e) {

        }
        return null;
    }

    public StatData energyData() {
        try {
            return MyStats.get(new Energy().GUID());
        } catch (Exception e) {

        }
        return null;
    }

    public static Unit Mob(EntityLivingBase entity) {

        Unit mob = new Unit();
        mob.InitMobStats();

        UnitData endata = Load.Unit(entity);

        endata.SetMobLevelAtSpawn(entity);
        endata.setRarity(randomRarity(entity, endata.getLevel()));

        //CommonStatUtils.addMapAffixes(entity, mob, endata);
        MobStatUtils.AddRandomMobStatusEffects(entity, mob, Rarities.Mobs.get(endata.getRarity()));

        mob.RecalculateStats(entity, endata, endata.getLevel());

        return mob;

    }

    private static int randomRarity(EntityLivingBase entity, int level) {

        double y = entity.posY;

        int minRarity = 0;

        if (entity.dimension.equals(DimensionType.OVERWORLD)) {

            if (y < 50) {
                minRarity = 1;
            }
            if (y < 30) {
                minRarity = 2;
            }
        }

        List<MobRarity> rarities = Rarities.Mobs;
        List<MobRarity> after = new ArrayList<MobRarity>();

        DimensionConfig config = DimensionsContainer.INSTANCE.getConfig(entity.world);

        for (MobRarity rar : rarities) {
            if (rar.Rank() >= minRarity) {
                if (rar.Rank() == 4 && config.LEVEL_FOR_MOBS_TO_BE_LEGENDARY > level) {

                } else if (rar.Rank() == 5 && config.LEVEL_FOR_MOBS_TO_BE_MYTHICAL > level) {

                } else {
                    after.add(rar);
                }
            }
        }

        MobRarity finalRarity = (MobRarity) RandomUtils.WeightedRandom(ListUtils.CollectionToList(after));

        return finalRarity.Rank();

    }

    protected void ClearStats() {

        if (MyStats == null) {
            this.InitPlayerStats();
        }

        for (StatData stat : MyStats.values()) {
            stat.Clear();
        }
    }

    protected void CalcStats(UnitData data) {

        MyStats.values().forEach((StatData stat) -> stat.GetStat().CalcVal(stat, data));
    }

    class DirtyCheck {
        int hp;

        public boolean isDirty(DirtyCheck newcheck) {

            if (newcheck.hp != hp) {
                return true;
            }

            return false;

        }

    }

    /**
     * @return checks if it should be synced to clients. Clients currently only see
     * health and status effects
     */
    private DirtyCheck getDirtyCheck() {

        if (MyStats == null || MyStats.isEmpty()) {
            this.InitPlayerStats();
        }

        DirtyCheck check = new DirtyCheck();

        check.hp = (int) MyStats.get(Health.GUID).Value;

        return check;
    }

    private float getHpAdded(EntityLivingBase entity, MobRarity rar, UnitData data) {

        float hpadded = entity.getMaxHealth() * data.getLevel();

        if (entity instanceof EntityPlayer) {
            hpadded *= ModConfig.INSTANCE.Server.PLAYER_HEART_TO_HEALTH_CONVERSION.get();

        } else {
            hpadded *= 2F * rar.HealthMultiplier();
        }

        return hpadded;
    }

    public void RecalculateStats(EntityLivingBase entity, UnitData data, int level) {

        data.setEquipsChanged(false);

        if (data.getUnit() == null) {
            data.setUnit(this, entity);
        }

        DirtyCheck old = getDirtyCheck();

        List<GearItemData> gears = PlayerStatUtils.getEquipsExcludingWeapon(entity); // slow but required

        boolean gearIsValid = this.isGearCombinationValid(gears, entity);

        ItemStack weapon = entity.getHeldItemMainhand();
        if (weapon != null) {
            GearItemData wep = Gear.Load(weapon);
            if (wep != null && wep.GetBaseGearType()
                    .slotType()
                    .equals(GearSlotType.Weapon)) {
                gears.add(wep);
            }

        }

        ItemStack offhand = entity.getHeldItemOffhand();
        if (offhand != null) {
            GearItemData off = Gear.Load(offhand);
            if (off != null && off.GetBaseGearType()
                    .slotType()
                    .equals(GearSlotType.OffHand)) {
                gears.add(off);
            }
        }

        Unit copy = this.Clone();

        PlayerMapData.IPlayerMapData mapdata = null;

        int tier = 0;
        if (entity instanceof EntityPlayer) {
            mapdata = Load.playerMapData((EntityPlayer) entity);
            tier = mapdata.getTier();
        }

        ClearStats();

        MobRarity rar = Rarities.Mobs.get(data.getRarity());

        float hpadded = getHpAdded(entity, rar, data);

        MyStats.get(Health.GUID).Flat += hpadded;

        if (entity instanceof EntityPlayer) {
            PlayerStatUtils.AddPlayerBaseStats(data, this);
            PlayerStatUtils.weakenPlayerPerTiers(this, tier);

        } else {
            MobStatUtils.AddMobcStats(data, data.getLevel());
            MobStatUtils.worldMultiplierStats(entity.world, data);
        }

        if (gearIsValid) {
            PlayerStatUtils.CountWornSets(entity, gears, this);
            PlayerStatUtils.AddAllGearStats(entity, gears, this, level);
            PlayerStatUtils.AddAllSetStats(entity, this, level);

        }

        CommonStatUtils.AddStatusEffectStats(this, level);
        CommonStatUtils.AddMapAffixStats(mapdata, this, level, entity);
        PlayerStatUtils.CalcStatConversionsAndTransfers(copy, this);
        PlayerStatUtils.CalcTraitsAndCoreStats(data);

        CalcStats(data);

        DirtyCheck newcheck = getDirtyCheck();

        if (old.isDirty(newcheck)) {
            MMORPG.sendToTracking(new EntityUnitPacket(entity, data), entity);
        }

    }

    // gear check works on everything but the weapon.
    public boolean isGearCombinationValid(List<GearItemData> gears, Entity en) {

        int unique_items = countUniqueItems(gears);

        if (unique_items > ModConfig.INSTANCE.Server.MAXIMUM_WORN_UNIQUE_ITEMS.get()) {
            if (en instanceof EntityPlayer) {
                en.sendMessage(new TextComponentString("Gear Stats Not Added, reason: you are wearing too many unique items! Maximum Possible Unique items (excluding weapon): " + ModConfig.INSTANCE.Server.MAXIMUM_WORN_UNIQUE_ITEMS));
            }
            return false;
        }

        int runed_items = countRunedItems(gears);

        if (runed_items > ModConfig.INSTANCE.Server.MAXIMUM_WORN_RUNED_ITEMS.get()) {
            if (en instanceof EntityPlayer) {
                en.sendMessage(new TextComponentString("Gear Stats Not Added, reason: you are wearing too many runed items! Maximum Possible Unique items (excluding weapon): " + ModConfig.INSTANCE.Server.MAXIMUM_WORN_RUNED_ITEMS));
            }
            return false;
        }

        // here i can go through all the items and then runewords and check if there is
        // more than

        return true;

    }

    private int countRunedItems(List<GearItemData> gears) {

        int amount = 0;

        for (GearItemData gear : gears) {
            if (gear.isRuned()) {
                amount++;
            }
        }

        return amount;

    }

    private int countUniqueItems(List<GearItemData> gears) {

        int amount = 0;

        for (GearItemData gear : gears) {
            if (gear.isUnique) {
                amount++;
            }
        }

        return amount;

    }

    private Unit Clone() {

        Unit clone = new Unit();
        if (this.MyStats != null) {
            clone.MyStats = new HashMap<String, StatData>(this.MyStats);
        } else {
            clone.MyStats = new HashMap<String, StatData>();
        }

        return clone;

    }

}
