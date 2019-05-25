package com.robertx22.config.compatible_items;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.items.unique_items.IUnique;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.blueprints.RunedGearBlueprint;
import com.robertx22.loot.blueprints.UniqueBlueprint;
import com.robertx22.loot.create.GearGen;
import com.robertx22.loot.create.RunedGearGen;
import com.robertx22.loot.create.UniqueGearGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.interfaces.IWeighted;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

public class ConfigItem implements IWeighted {

    @Override
    public int Weight() {
        return this.dropWeight;
    }

    public transient String registryName = "";

    public enum creationTypes {
        NORMAL, RUNED, UNIQUE
    }

    public String itemType = "Sword";

    public int dropWeight = 1000;
    public int uniqueItemWeight = 0;
    public int normalItemWeight = 80;
    public int runedItemWeight = 20;

    public int randomUniqueUpToTier = 10;

    public int minRarity = 0;
    public int maxRarity = 5;

    public int levelVariance = 0;

    public String uniqueId = "";
    public boolean uniqueIsRandom = true;

    public int minLevel = 1;
    public int maxLevel = 100;

    public boolean statsAddedOnlyOnDrop = false;

    public ConfigItem setUniqueId(IUnique uniq) {
        this.uniqueId = uniq.GUID();
        this.uniqueIsRandom = false;
        return this;
    }

    public ConfigItem setMaxUniqueTier(int tier) {
        this.randomUniqueUpToTier = tier;
        return this;
    }

    public ConfigItem setGenerationWeights(int normalItemWeight, int runedItemWeight,
                                           int uniqueItemWeight) {
        this.normalItemWeight = normalItemWeight;
        this.uniqueItemWeight = uniqueItemWeight;
        this.runedItemWeight = runedItemWeight;
        return this;
    }

    public ConfigItem setAlwaysNormal() {
        this.normalItemWeight = 1;
        this.uniqueItemWeight = 0;
        this.runedItemWeight = 0;
        return this;
    }

    public ConfigItem setAlwaysRuned() {
        this.normalItemWeight = 0;
        this.uniqueItemWeight = 0;
        this.runedItemWeight = 1;
        return this;
    }

    public ConfigItem setAlwaysUnique() {
        this.normalItemWeight = 0;
        this.uniqueItemWeight = 1;
        this.runedItemWeight = 0;
        return this;
    }

    public ConfigItem setType(GearItemSlot type) {
        this.itemType = type.GUID();
        return this;
    }

    public ConfigItem setType(String type) {
        this.itemType = type;
        return this;
    }

    public ConfigItem setDropWeight(int weight) {
        this.dropWeight = weight;
        return this;
    }

    public ConfigItem setMinRarity(int rar) {
        this.minRarity = rar;
        return this;
    }

    public ConfigItem setMaxRarity(int rar) {
        this.maxRarity = rar;
        return this;
    }

    public boolean isValid() throws Exception {

        if (uniqueIsRandom == false) {
            boolean matches = false;
            for (IUnique uniq : IUnique.getAll()) {
                if (uniq.GUID().equals(this.uniqueId)) {
                    matches = true;
                }
            }
            if (matches == false) {
                throw new Exception("Unique Id doesn't exist: " + this.uniqueId);
            }
        }

        boolean matchtype = false;
        for (GearItemSlot slot : GearTypes.All.values()) {
            if (slot.GUID().equals(this.itemType)) {
                matchtype = true;
            }
        }
        if (matchtype == false) {
            throw new Exception("Gear Type doesn't exist: " + this.itemType);
        }
        if (normalItemWeight < 1 && this.runedItemWeight < 1 && this.uniqueItemWeight < 1) {
            throw new Exception("Weights can't all be 0 on an item: ");
        }

        return true;

    }

    public ItemStack create(ItemStack stack, int level) {

        level = this.getLevel(level);

        switch (getCreationType()) {
            case NORMAL:
                createNormal(stack, level);
                break;
            case UNIQUE:
                createUnique(stack, level);
                break;
            case RUNED:
                createRuned(stack, level);
                break;

        }

        return stack;

    }

    private creationTypes getCreationType() {

        WeightedType result = (WeightedType) RandomUtils.WeightedRandom(Arrays.asList(new WeightedType(normalItemWeight, creationTypes.NORMAL), new WeightedType(uniqueItemWeight, creationTypes.UNIQUE), new WeightedType(runedItemWeight, creationTypes.RUNED)));

        return result.type;
    }

    private int getLevel(int playerlevel) {
        return MathHelper.clamp(playerlevel, minLevel, maxLevel);
    }

    private ItemStack createNormal(ItemStack stack, int level) {

        GearBlueprint blueprint = new GearBlueprint(level);
        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;
        blueprint.minRarity = this.minRarity;
        blueprint.maxRarity = this.maxRarity;

        GearItemData gear = GearGen.CreateData(blueprint);
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }

    private ItemStack createUnique(ItemStack stack, int level) {

        UniqueBlueprint blueprint = new UniqueBlueprint(level, this.uniqueId);
        blueprint.uniqueIsRandom = this.uniqueIsRandom;
        blueprint.tier = randomUniqueUpToTier;
        blueprint.map_tier = this.randomUniqueUpToTier;

        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;

        GearItemData gear = UniqueGearGen.CreateData(blueprint);
        gear.isNotFromMyMod = true;

        if (gear.uniqueGUID != null || !IUnique.ITEMS.containsKey(gear.uniqueGUID)) {
            return createNormal(stack, level);
        } else {
            Gear.Save(stack, gear);
        }

        return stack;

    }

    private ItemStack createRuned(ItemStack stack, int level) {

        RunedGearBlueprint blueprint = new RunedGearBlueprint(level);
        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;
        blueprint.minRarity = this.minRarity;
        blueprint.maxRarity = this.maxRarity;

        GearItemData gear = RunedGearGen.CreateData(blueprint);
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }
}