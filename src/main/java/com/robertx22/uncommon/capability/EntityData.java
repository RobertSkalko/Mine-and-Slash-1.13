package com.robertx22.uncommon.capability;

import com.robertx22.api.MineAndSlashEvents;
import com.robertx22.config.ModConfig;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.database.stats.stat_types.misc.BonusExp;
import com.robertx22.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.db_lists.Rarities;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mmorpg.MMORPG;
import com.robertx22.mmorpg.Ref;
import com.robertx22.network.PlayerUnitPacket;
import com.robertx22.onevent.player.OnLogin;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.Styles;
import com.robertx22.uncommon.capability.bases.BaseProvider;
import com.robertx22.uncommon.capability.bases.BaseStorage;
import com.robertx22.uncommon.capability.bases.ICommonCapability;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.UnitNbt;
import com.robertx22.uncommon.effectdatas.DamageEffect;
import com.robertx22.uncommon.effectdatas.EffectData;
import com.robertx22.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.uncommon.enumclasses.EntitySystemChoice;
import com.robertx22.uncommon.utilityclasses.AttackUtils;
import com.robertx22.uncommon.utilityclasses.HealthUtils;
import com.robertx22.uncommon.utilityclasses.LevelUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.UUID;

@EventBusSubscriber
public class EntityData {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "entitydata");

    @CapabilityInject(UnitData.class)
    public static final Capability<UnitData> Data = null;

    private static final String LEVEL = "level";
    private static final String RARITY = "rarity";
    private static final String EXP = "exp";
    private static final String UUID = "uuid";
    private static final String MOB_SAVED_ONCE = "mob_saved_once";
    private static final String MANA = "current_mana";
    private static final String ENERGY = "current_energy";
    private static final String CURRENT_MAP_ID = "current_map_resource_loc";
    private static final String SET_MOB_STATS = "set_mob_stats";
    private static final String NEWBIE_STATUS = "is_a_newbie";
    private static final String DMG_DONE_BY_NON_PLAYERS = "DMG_DONE_BY_NON_PLAYERS";
    private static final String EQUIPS_CHANGED = "EQUIPS_CHANGED";
    private static final String TIER = "TIER";

    public interface UnitData extends ICommonCapability {

        void syncToClient(EntityPlayer player);

        GearItemData getWeaponData(EntityLivingBase entity);

        void setEquipsChanged(boolean bool);

        void onDamagedByNonPlayer(float dmg);

        boolean shouldDropLoot(EntityLivingBase entity);

        int PostGiveExpEvent(EntityLivingBase killed, EntityPlayer player, int exp);

        boolean isNewbie();

        void setNewbieStatus(boolean bool);

        boolean needsToBeGivenStats();

        void freelySetLevel(int lvl);

        int getLevel();

        void mobBasicAttack(EntityLivingBase source, EntityLivingBase target,
                            UnitData sourcedata, UnitData targetdata, float event_damage);

        void setLevel(int lvl, EntityLivingBase entity);

        boolean increaseRarity(EntityLivingBase entity);

        int getExp();

        void setExp(int exp);

        int GiveExp(EntityPlayer player, int i);

        int GetExpRequiredForLevelUp();

        boolean CheckIfCanLevelUp();

        boolean LevelUp(EntityPlayer player);

        boolean CheckLevelCap();

        void SetMobLevelAtSpawn(EntityLivingBase entity);

        Unit getUnit();

        void setUnit(Unit unit, EntityLivingBase entity);

        void setRarity(int rarity);

        int getRarity();

        String getUUID();

        void setUUID(UUID id);

        ITextComponent getName(EntityLivingBase entity);

        void HandleCloneEvent(UnitData old);

        void recalculateStats(EntityLivingBase entity);

        void forceRecalculateStats(EntityLivingBase entity);

        void forceSetUnit(Unit unit);

        boolean tryUseWeapon(GearItemData gear, EntityLivingBase entity);

        void attackWithWeapon(ItemStack weapon, GearItemData gear,
                              EntityLivingBase source, EntityLivingBase target,
                              UnitData targetdata);

        void onLogin(EntityPlayer player);

        float getCurrentMana();

        float getCurrentEnergy();

        void setCurrentEnergy(float i);

        void setCurrentMana(float i);

        boolean hasEnoughMana(float i);

        boolean hasEnoughEnergy(float i);

        void restoreMana(float i);

        void restoreEnergy(float i);

        void consumeMana(float i);

        void consumeEnergy(float i);

        void heal(EntityLivingBase entity, int healthrestored);

        String getCurrentMapId();

        void setCurrentMapId(String res);

        boolean hasCurrentMapId();

        void clearCurrentMapId();

        void unarmedAttack(EntityLivingBase source, EntityLivingBase target,
                           UnitData targetdata);

        boolean decreaseRarity(EntityLivingBase entity);

        boolean isWeapon(GearItemData gear);

        void setTier(int tier);

        int getTier();

        float getStatMultiplierIncreaseByTier();
    }

    @EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {

            boolean can = false;

            if (ModConfig.INSTANCE.Server.ENTITIES_UNDER_SYSTEM.get()
                    .equals(EntitySystemChoice.All_Entities) && event.getObject() instanceof EntityLivingBase) {
                can = true;

            } else if (ModConfig.INSTANCE.Server.ENTITIES_UNDER_SYSTEM.get()
                    .equals(EntitySystemChoice.Mobs_And_Players)) {
                if (event.getObject() instanceof IMob || event.getObject() instanceof EntityPlayer) {
                    can = true;
                }
            }

            if (can) {
                event.addCapability(RESOURCE, new Provider());
            }
        }

    }

    public static class Provider extends BaseProvider<UnitData> {

        @Override
        public UnitData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<UnitData> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements UnitData {
        private NBTTagCompound nbt = new NBTTagCompound();

        boolean setMobStats = false;
        Unit unit = null;
        int level = 1;
        int exp = 0;
        int rarity = 0;
        String uuid = "";
        String currentMapResourceLoc = "";
        boolean isNewbie = true;
        boolean equipsChanged = true;
        int tier = 0;

        float dmgByNonPlayers = 0;

        float energy;
        float mana;

        @Override
        public NBTTagCompound getNBT() {
            nbt.putFloat(MANA, mana);
            nbt.putFloat(ENERGY, energy);
            nbt.putFloat(DMG_DONE_BY_NON_PLAYERS, dmgByNonPlayers);
            nbt.putInt(LEVEL, level);
            nbt.putInt(EXP, exp);
            nbt.putInt(RARITY, rarity);
            nbt.putInt(TIER, tier);
            nbt.putString(UUID, uuid);
            nbt.putBoolean(MOB_SAVED_ONCE, true);
            nbt.putString(CURRENT_MAP_ID, currentMapResourceLoc);
            nbt.putBoolean(SET_MOB_STATS, setMobStats);
            nbt.putBoolean(NEWBIE_STATUS, this.isNewbie);
            nbt.putBoolean(EQUIPS_CHANGED, equipsChanged);

            if (unit != null) {
                UnitNbt.Save(this.nbt, unit);
            }

            return nbt;

        }

        @Override
        public void setNBT(NBTTagCompound value) {
            this.nbt = value;
            this.level = value.getInt(LEVEL);
            this.exp = value.getInt(EXP);
            this.rarity = value.getInt(RARITY);
            this.tier = value.getInt(TIER);
            this.uuid = value.getString(UUID);
            this.energy = value.getFloat(ENERGY);
            this.dmgByNonPlayers = value.getFloat(DMG_DONE_BY_NON_PLAYERS);
            this.mana = value.getFloat(MANA);
            this.currentMapResourceLoc = value.getString(CURRENT_MAP_ID);
            this.setMobStats = value.getBoolean(SET_MOB_STATS);
            this.isNewbie = value.getBoolean(NEWBIE_STATUS);
            this.equipsChanged = value.getBoolean(EQUIPS_CHANGED);

            Unit newunit = UnitNbt.Load(this.nbt);
            if (newunit != null) {
                this.unit = newunit;
            }

        }

        @Override
        public void mobBasicAttack(EntityLivingBase source, EntityLivingBase target,
                                   UnitData sourcedata, UnitData targetdata,
                                   float event_damage) {

            MobRarity rar = Rarities.Mobs.get(sourcedata.getRarity());

            float vanilla = event_damage * sourcedata.getLevel() * sourcedata.getStatMultiplierIncreaseByTier();

            float num = vanilla * rar.DamageMultiplier();

            DamageEffect dmg = new DamageEffect(source, target, (int) num, sourcedata, targetdata, EffectData.EffectTypes.NORMAL, WeaponTypes.None);

            dmg.Activate();

        }

        @Override
        public int GetExpRequiredForLevelUp() {

            int lvl = getLevel();

            int tens = lvl / 10;

            if (lvl < 5) {
                return 150 * lvl;
            }

            if (lvl < 8) {
                return 200 * lvl;
            }

            return lvl * 500 + (tens * 2000);

        }

        @Override
        public void SetMobLevelAtSpawn(EntityLivingBase entity) {

            this.setMobStats = true;
            this.level = LevelUtils.determineLevel(entity.world, entity.getPosition());

        }

        @Override
        public void setEquipsChanged(boolean bool) {
            this.equipsChanged = bool;
        }

        @Override
        public void onDamagedByNonPlayer(float dmg) {

            this.dmgByNonPlayers += dmg;

        }

        @Override
        public boolean shouldDropLoot(EntityLivingBase entity) {

            if (entity.getMaxHealth() * ModConfig.INSTANCE.Server.STOP_DROPS_IF_NON_PLAYER_DOES_DMG_PERCENT
                    .get() >= this.dmgByNonPlayers) {
                return true;
            }

            return false;
        }

        @Override
        public int PostGiveExpEvent(EntityLivingBase killed, EntityPlayer player, int i) {

            i *= ModConfig.INSTANCE.Server.EXPERIENCE_MULTIPLIER.get();

            i *= (double) this.getUnit().MyStats.get(new BonusExp().GUID()).Value / 100 + 1;

            MinecraftForge.EVENT_BUS.post(new MineAndSlashEvents.GiveExpEvent(killed, player, this, i));

            return i;
        }

        @Override
        public int GiveExp(EntityPlayer player, int i) {

            setExp(exp + i);

            if (exp > this.GetExpRequiredForLevelUp()) {

                if (ModConfig.INSTANCE.Server.LEVEL_UPS_COST_TOKEN.get() == false) {

                    if (this.CheckIfCanLevelUp() && this.CheckLevelCap()) {
                        this.LevelUp(player);
                    }
                }

                return i;
            }
            return i;
        }

        @Override
        public boolean CheckIfCanLevelUp() {

            return getExp() >= GetExpRequiredForLevelUp();

        }

        public int getRemainingExp() {
            int num = getExp() - GetExpRequiredForLevelUp();

            if (num < 0) {
                num = 0;
            }
            return num;
        }

        @Override
        public boolean CheckLevelCap() {
            return getLevel() + 1 <= ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get();
        }

        @Override
        public boolean LevelUp(EntityPlayer player) {

            if (!CheckIfCanLevelUp()) {
                player.sendMessage(SLOC.chat("not_enough_experience"));
            } else if (!CheckLevelCap()) {
                player.sendMessage(SLOC.chat("cannot_over_maximum_level"));
            }

            if (CheckIfCanLevelUp() && CheckLevelCap()) {

                this.setLevel(level + 1, player);

                setExp(getRemainingExp());

                player.sendMessage(SLOC.chat("levelup_success"));

                return true;
            }
            return false;
        }

        @Override
        public int getLevel() {

            return level;

        }

        @Override
        public void setLevel(int lvl, EntityLivingBase entity) {

            level = MathHelper.clamp(lvl, 1, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                    .get());

            this.equipsChanged = true;

        }

        @Override
        public int getExp() {
            return exp;
        }

        @Override
        public void setExp(int exp) {
            this.exp = exp;
        }

        @Override
        public void syncToClient(EntityPlayer player) {
            if (unit != null) {
                PlayerUnitPacket packet = new PlayerUnitPacket(this.getNBT());
                EntityPlayerMP mp = (EntityPlayerMP) player;
                MMORPG.sendToClient(packet, mp);
            }
        }

        @Override
        public Unit getUnit() {
            return unit;

        }

        @Override
        public void setUnit(Unit unit, EntityLivingBase entity) {

            this.unit = unit;

        }

        @Override
        public void setRarity(int rarity) {
            this.rarity = rarity;
            this.equipsChanged = true;

        }

        @Override
        public int getRarity() {
            return rarity;
        }

        @Override
        public String getUUID() {
            return uuid;
        }

        @Override
        public void setUUID(UUID id) {
            uuid = id.toString();
        }

        @Override
        public ITextComponent getName(EntityLivingBase entity) {

            if (entity instanceof EntityPlayer) {

                return new TextComponentString("[Lv:").appendText(this.getLevel() + "] " + " ")
                        .appendSibling(entity.getDisplayName());

            } else {
                MobRarity rarity = Rarities.Mobs.get(getRarity());
                ITextComponent rarityprefix = rarity.locName();
                ITextComponent name = entity.getDisplayName();

                ITextComponent lvlcomp = Styles.YELLOWCOMP()
                        .appendSibling(new TextComponentString("[Lv:" + this.getLevel() + "] "));

                ITextComponent suffix = new TextComponentString(rarity.textFormatColor() + "")
                        .appendSibling(rarityprefix.appendText(" ").appendSibling(name));

                return lvlcomp.appendSibling(suffix);

            }
        }

        @Override
        public void HandleCloneEvent(UnitData old) {
            this.setNBT(old.getNBT());
        }

        @Override
        public void recalculateStats(EntityLivingBase entity) {

            if (unit == null) {
                unit = new Unit();
            }

            if (needsToRecalcStats()) {
                unit.RecalculateStats(entity, this, level);
            }
        }

        @Override
        public void forceRecalculateStats(EntityLivingBase entity) {

            if (unit == null) {
                unit = new Unit();
            }
            unit.RecalculateStats(entity, this, level);
        }

        // This reduces stat calculation by about 4 TIMES!
        private boolean needsToRecalcStats() {

            return this.equipsChanged;
        }

        @Override
        public void forceSetUnit(Unit unit) {
            this.unit = unit;
        }

        @Override
        public GearItemData getWeaponData(EntityLivingBase entity) {
            return Gear.Load(entity.getHeldItemMainhand());
        }

        @Override
        public boolean tryUseWeapon(GearItemData weaponData, EntityLivingBase source) {

            try {

                if (weaponData != null && weaponData.GetBaseGearType() instanceof IWeapon) {

                    IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();

                    float energyCost = iwep.mechanic().GetEnergyCost();

                    if (hasEnoughEnergy(energyCost) == false) {
                        AttackUtils.NoEnergyMessage(source);
                        return false;

                    } else {
                        consumeEnergy(energyCost);
                        //weapon.damageItem(1, source);

                        return true;

                    }

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void attackWithWeapon(ItemStack weapon, GearItemData weaponData,
                                     EntityLivingBase source, EntityLivingBase target,
                                     UnitData targetdata) {

            if (weaponData.GetBaseGearType() instanceof IWeapon) {

                if (weapon != null) {
                    weapon.damageItem(1, source);
                }

                IWeapon iwep = (IWeapon) weaponData.GetBaseGearType();
                WeaponMechanic iWep = iwep.mechanic();
                iWep.Attack(source, target, this, targetdata);

            }
        }

        @Override
        public void onLogin(EntityPlayer player) {

            try {

                if (unit == null) {
                    unit = new Unit();
                }
                unit.InitPlayerStats();

                // check if newbie
                if (isNewbie()) {
                    setNewbieStatus(false);
                    OnLogin.GiveStarterItems(player);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public float getCurrentMana() {
            return mana;
        }

        @Override
        public float getCurrentEnergy() {
            return energy;
        }

        @Override
        public void setCurrentEnergy(float i) {
            energy = i;

        }

        @Override
        public void setCurrentMana(float i) {
            mana = i;

        }

        @Override
        public boolean hasEnoughMana(float i) {
            return mana >= i;
        }

        @Override
        public boolean hasEnoughEnergy(float i) {
            return energy >= i;
        }

        @Override
        public void restoreMana(float i) {
            float max = unit.manaData().Value;

            mana += i;
            if (mana > max) {
                mana = (int) max;
            }

        }

        @Override
        public void restoreEnergy(float i) {
            float max = unit.energyData().Value;

            energy += i;
            if (energy > max) {
                energy = (int) max;
            }

        }

        @Override
        public void consumeMana(float i) {
            mana -= i;
            if (mana < 0) {
                mana = 0;
            }

        }

        @Override
        public void consumeEnergy(float i) {
            energy -= i;
            if (energy < 0) {
                energy = 0;
            }

        }

        @Override
        public void heal(EntityLivingBase entity, int healthrestored) {

            if (entity.isAlive()) {
                entity.heal(HealthUtils.DamageToMinecraftHealth(healthrestored / ModConfig.INSTANCE.Server.NON_MOD_HEAL_MULTI
                        .get()
                        .floatValue(), entity));
            }
        }

        @Override
        public boolean increaseRarity(EntityLivingBase entity) {

            if (rarity == 5) {
                return false;
            } else {
                rarity = rarity + 1;

                return true;

            }
        }

        @Override
        public boolean decreaseRarity(EntityLivingBase entity) {

            if (rarity - 1 < 0) {
                return false;
            } else {
                rarity = rarity - 1;

                return true;

            }
        }

        @Override
        public String getCurrentMapId() {
            return this.currentMapResourceLoc;
        }

        @Override
        public void setCurrentMapId(String id) {
            this.currentMapResourceLoc = id;
        }

        @Override
        public boolean hasCurrentMapId() {
            return this.currentMapResourceLoc.isEmpty() == false;
        }

        @Override
        public void clearCurrentMapId() {
            this.currentMapResourceLoc = "";
        }

        @Override
        public void unarmedAttack(EntityLivingBase source, EntityLivingBase target,
                                  UnitData targetdata) {

            float cost = ModConfig.INSTANCE.Server.UNARMED_ENERGY_COST.get().floatValue();

            if (this.hasEnoughEnergy(cost)) {

                this.consumeEnergy(cost);
                int num = (int) unit.MyStats.get(PhysicalDamage.GUID).Value;
                DamageEffect dmg = new DamageEffect(source, target, num, this, targetdata, EffectData.EffectTypes.NORMAL, WeaponTypes.None);

                dmg.Activate();
            }
        }

        @Override
        public boolean isWeapon(GearItemData gear) {
            try {

                if (gear == null) {
                    return false;
                }
                if (gear.GetBaseGearType() instanceof IWeapon) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public void setTier(int tier) {
            this.tier = tier;
        }

        @Override
        public int getTier() {
            return tier;
        }

        @Override
        public float getStatMultiplierIncreaseByTier() {
            return 1 + tier * 0.1F;
        }

        @Override
        public void freelySetLevel(int lvl) {
            this.level = lvl;
        }

        @Override
        public boolean isNewbie() {
            return isNewbie;
        }

        @Override
        public void setNewbieStatus(boolean bool) {
            isNewbie = bool;
        }

        @Override
        public boolean needsToBeGivenStats() {
            return this.setMobStats == false;
        }
    }

    public static class Storage extends BaseStorage<UnitData> {

    }

}
