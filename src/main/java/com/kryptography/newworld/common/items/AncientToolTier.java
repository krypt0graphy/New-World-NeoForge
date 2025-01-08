package com.kryptography.newworld.common.items;


import net.minecraft.tags.BlockTags;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;



public class AncientToolTier {
    public static final Tier ANCIENT = new Tier() {
        @Override
        public int getUses() {
            return 3086;
        }

        @Override
        public float getSpeed() {
            return 9.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 7.0F;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 10;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.FLINT);
        }
    };

}
