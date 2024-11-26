package com.kryptography.newworld.common.items;


import net.minecraft.tags.BlockTags;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;


public class AncientToolTier {
    public static final Tier ANCIENT = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3086, 9.0F, 7.0F, 10, () -> Ingredient.of(Items.FLINT));
}
