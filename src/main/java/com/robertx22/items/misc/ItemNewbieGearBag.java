package com.robertx22.items.misc;

import com.robertx22.database.gearitemslots.Sword;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.items.BaseItem;
import com.robertx22.loot.blueprints.GearBlueprint;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.List;

public class ItemNewbieGearBag extends BaseItem {

    @ObjectHolder(Ref.MODID + ":newbie_gear_bag")
    public static final Item ITEM = null;

    public ItemNewbieGearBag() {
        super(new Properties().group(CreativeTabs.MyModTab));

        RegisterItemUtils.RegisterItemName(this, "newbie_gear_bag");
    }

    public static int ITEMS_AMOUNT = 6;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,
                                                    EnumHand handIn) {

        if (!worldIn.isRemote) {
            try {

                GearBlueprint weaponPrint = new GearBlueprint(1);
                weaponPrint.SetSpecificType(new Sword().GUID());
                weaponPrint.LevelRange = false;

                GearBlueprint print = new GearBlueprint(1);
                print.LevelRange = false;

                GearItemData wepData = GearLootGen.CreateData(weaponPrint);
                wepData.isSalvagable = false;

                ItemStack weaponStack = GearLootGen.CreateStack(wepData);
                playerIn.dropItem(weaponStack, false, true);

                for (int i = 0; i < ITEMS_AMOUNT; i++) {

                    GearItemData data = GearLootGen.CreateData(print);
                    data.isSalvagable = false;
                    ItemStack stack = GearLootGen.CreateStack(data);

                    playerIn.dropItem(stack, false, true);
                }

                return new ActionResult<ItemStack>(EnumActionResult.PASS, EmptyOrDecrease(playerIn
                        .getHeldItem(handIn)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        Tooltip.add(CLOC.tooltip("newbie_gear_bag"), tooltip);

        Tooltip.add(CLOC.lore("newbie_gear_bag1"), tooltip);
        Tooltip.add(CLOC.lore("newbie_gear_bag2"), tooltip);

    }

}
