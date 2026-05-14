package com.kryptography.newworld.integration;


import com.farcr.nomansland.common.block.TrimmedPlankBlock;
import com.kryptography.newworld.core.registry.NWBlocks;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.kryptography.newworld.core.registry.NWBlocks.*;

public class NMLIntegration {
	public static final Supplier<Block> TRIMMED_FIR_PLANKS = () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

	public static void register() {
	}

	public static void nmlRecipes(RecipeOutput recipeOutput) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.TRIMMED_FIR_PLANKS, 3)
				.pattern(" # ")
				.pattern(" # ")
				.pattern(" # ")
				.define('#', FIR_PLANKS)
				.group("trimmed_planks")
				.showNotification(false)
				.unlockedBy("has_fir_planks", InventoryChangeTrigger.TriggerInstance.hasItems(FIR_PLANKS))
				.save(recipeOutput);
	}
}