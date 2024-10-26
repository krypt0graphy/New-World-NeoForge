package com.kryptography.newworld.common.items;

import com.kryptography.newworld.init.data.tags.NWBlockTags;
import com.kryptography.newworld.init.data.tags.NWItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

import java.util.function.Supplier;

public class AncientToolTier {
    public static final Tier ANCIENT = new SimpleTier(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3086, 9.0F, 7.0F, 10, () -> Ingredient.of(Items.FLINT));


}
