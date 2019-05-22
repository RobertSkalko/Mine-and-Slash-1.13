package com.robertx22.items.bags;

import com.mmorpg_libraries.curios.interfaces.ISalvageBag;
import com.robertx22.Styles;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.CreativeTabs;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.ISalvagable;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.datasaving.Rune;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AutoSalvageBag extends Item implements ISalvageBag {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public int rarity = 0;

    private final int defaul_gear_rarity_salvage = 0;
    private final int default_spell_rarity_salvage = 0;
    private final int default_map_rarity_salvage = -1;
    private final int default_rune_rarity_salvage = 1;

    public AutoSalvageBag(int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab));

        this.rarity = rarity;

        RegisterItemUtils.RegisterItemName(this, "auto_salvage_bag" + rarity);
    }

    private List<Float> BonusSalvageValues = Arrays.asList(5F, 10F, 20F, 30F, 40F, 50F, 100F);

    public float getBonusSalvageChance() {
        return BonusSalvageValues.get(rarity);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player,
                                                    EnumHand hand) {

        if (!world.isRemote) {
            ItemStack bag = player.getHeldItem(hand);

            NBTTagCompound nbt = bag.getTag();

            if (nbt == null) {
                nbt = new NBTTagCompound();
            }

            ItemStack stack = player.getHeldItemOffhand();

            if (stack != null && !stack.isEmpty()) {

                GearItemData gear = Gear.Load(stack);
                if (gear != null) {
                    nbt.putInt("gear", gear.Rarity);
                    successChat(player);
                }
                SpellItemData spell = Spell.Load(stack);
                if (spell != null) {
                    nbt.putInt("spell", spell.rarity);
                    successChat(player);
                }
                MapItemData map = Map.Load(stack);
                if (map != null) {
                    nbt.putInt("map", map.rarity);
                    successChat(player);
                }
                RuneItemData rune = Rune.Load(stack);
                if (rune != null) {
                    nbt.putInt("rune", rune.rarity);
                    successChat(player);
                }

            } else {
                nbt.putInt("gear", -1);
                nbt.putInt("spell", -1);
                nbt.putInt("map", -1);
                nbt.putInt("rune", -1);

                player.sendMessage(new TextComponentString("Bag Config Cleared"));
            }
            bag.setTag(nbt);
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    private void successChat(EntityPlayer player) {
        player.sendMessage(new TextComponentString("You Have Successfully Modified your Automatic Salvaging Bag."));
    }

    public ItemStack getSalvageResultForItem(ISalvagable sal) {

        float salvageBonus = this.getBonusSalvageChance();
        return sal.getSalvageResult(salvageBonus);
    }

    public static ISalvagable getSalvagable(ItemStack st) {

        GearItemData gear = Gear.Load(st);
        if (gear != null && gear.isUnique == false) { // DO NOT SALVAGE UNIQUE ITEMS, their rarity was -1. should
            // probably change that
            return gear;
        }

        SpellItemData spell = Spell.Load(st);
        if (spell != null) {
            return spell;
        }

        MapItemData map = Map.Load(st);
        if (map != null) {
            return map;
        }

        RuneItemData rune = Rune.Load(st);
        if (rune != null) {
            return rune;
        }
        return null;

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        NBTTagCompound nbt = stack.getTag();

        if (nbt == null) {
            nbt = new NBTTagCompound();
        }

        Tooltip.add(CLOC.tooltip("auto_salvage_items").appendText("!"), tooltip);

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(CLOC.word("gears").appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Items), this.getGear(nbt)), tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(CLOC.word("spells").appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Spells), this.getSpell(nbt)), tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(CLOC.word("maps").appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Maps), this.getMap(nbt)), tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(CLOC.word("runes").appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Runes), this.getRune(nbt)), tooltip);

        Tooltip.add("", tooltip);

        Tooltip.add(CLOC.tooltip("bonus_salvage_chance")
                .appendText(": " + this.getBonusSalvageChance() + "%"), tooltip);

        Tooltip.add("", tooltip);

        Tooltip.add(CLOC.tooltip("works_when_in_baubles_slot"), tooltip);
        Tooltip.add("", tooltip);

        if (GuiScreen.isShiftKeyDown() == false) {

            Tooltip.add(Styles.GREENCOMP()
                    .appendSibling(CLOC.tooltip("sal_info")), tooltip);

        } else {
            Tooltip.add(CLOC.tooltip("sal1"), tooltip);
            Tooltip.add(CLOC.tooltip("sal2"), tooltip);
            Tooltip.add(CLOC.tooltip("sal3"), tooltip);
            Tooltip.add(CLOC.tooltip("sal4"), tooltip);
            Tooltip.add(CLOC.tooltip("sal5"), tooltip);
        }

    }

    public ITextComponent getSalvagedRarities(List<Rarity> rarities, int rarity) {

        ITextComponent text = new TextComponentString("");

        for (ItemRarity rar : Rarities.Items) {
            if (rar.Rank() <= rarity) {

                if (text.getSiblings().size() > 0) {
                    text.appendText(TextFormatting.GRAY + ", ");
                }
                text.appendText(rar.textFormatColor() + "").appendSibling(rar.locName());
            }
        }

        if (text.getSiblings().size() < 1) {
            text.appendSibling(CLOC.word("none"));
        }

        return text;

    }

    public boolean shouldSalvageItem(ISalvagable sal, NBTTagCompound nbt) {

        int rarity = sal.getSalvagedRarity();

        if (sal.isSalvagable()) {
            if (sal instanceof GearItemData) {
                if (rarity <= getGear(nbt)) {
                    return true;
                }
            } else if (sal instanceof SpellItemData) {
                if (rarity <= getSpell(nbt)) {
                    return true;
                }
            } else if (sal instanceof MapItemData) {
                if (rarity <= getMap(nbt)) {
                    return true;
                }
            } else if (sal instanceof RuneItemData) {
                if (rarity <= getRune(nbt)) {
                    return true;
                }

            }
        }

        return false;
    }

    private int getGear(NBTTagCompound nbt) {

        if (nbt != null) {
            if (nbt.contains("gear")) {
                return nbt.getInt("gear");
            }
        }
        return this.defaul_gear_rarity_salvage;

    }

    private int getSpell(NBTTagCompound nbt) {
        if (nbt != null) {
            if (nbt.contains("spell")) {
                return nbt.getInt("spell");
            }
        }
        return this.default_spell_rarity_salvage;

    }

    private int getRune(NBTTagCompound nbt) {
        if (nbt != null) {
            if (nbt.contains("rune")) {
                return nbt.getInt("rune");
            }
        }
        return this.default_rune_rarity_salvage;

    }

    private int getMap(NBTTagCompound nbt) {
        if (nbt != null) {

            if (nbt.contains("map")) {
                return nbt.getInt("map");
            }
        }
        return this.default_map_rarity_salvage;

    }

}