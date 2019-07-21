package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueBlueprint;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

@Storable
public class BlueprintGearReward {

    @Store
    public int level;

    @Store
    public int tier;

    @Store
    public String specificType;

    @Store
    public int rarity;

    @Store
    public String uniqueID;

    public ItemStack create() {

        ItemStack stack = ItemStack.EMPTY;

        GearBlueprint blueprint = null;

        if (uniqueID.isEmpty()) {
            blueprint = new GearBlueprint(level);

        } else {
            blueprint = new UniqueBlueprint(level, uniqueID);

        }

        blueprint.LevelRange = false;
        blueprint.setSpecificRarity(rarity);
        blueprint.SetSpecificType(specificType);

        return stack;

    }
}