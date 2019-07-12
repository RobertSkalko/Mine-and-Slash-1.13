package com.robertx22.mmorpg.registers.common;

import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.mmorpg.Ref;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantRegisters {

    public static EnchantmentType WEAPON_ENCHANT = EnchantmentType.create(Ref.MODID + ":weapon", x -> x instanceof IWeapon);

    public static void register() {

        for (Enchantment enchant : ForgeRegistries.ENCHANTMENTS) {

            if (enchant.type.equals(EnchantmentType.WEAPON)) {
                EnchantOverride override = new EnchantOverride(enchant, WEAPON_ENCHANT);
                register(override.getRegistryName().toString(), override);
            }
        }
    }

    private static Enchantment register(String key, Enchantment ench) {
        return Registry.register(Registry.ENCHANTMENT, key, ench);
    }

    private static class EnchantOverride extends Enchantment {

        public EnchantOverride(Enchantment e, EnchantmentType type) {
            super(e.getRarity(), type, e.applicableEquipmentTypes);
            this.setRegistryName(Ref.MODID, e.getRegistryName().getPath());

        }

    }

}
