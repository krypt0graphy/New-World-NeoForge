package com.kryptography.newworld.integration;


import com.farcr.nomansland.common.block.TrimmedPlankBlock;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.kryptography.newworld.init.NWBlocks.*;

public class NMLIntegration {
	public static final DeferredBlock<Block> FIR_BOOKSHELF = NWBlocks.register("fir_bookshelf", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BOOKSHELF)));

	public static final DeferredBlock<Block> TRIMMED_FIR_PLANKS = NWBlocks.register("trimmed_fir_planks", () -> new TrimmedPlankBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

	public static void register() {
	}

	public static void nmlRecipes(RecipeOutput recipeOutput) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, TRIMMED_FIR_PLANKS, 3)
				.pattern(" # ")
				.pattern(" # ")
				.pattern(" # ")
				.define('#', FIR_PLANKS)
				.group("trimmed_planks")
				.showNotification(false)
				.unlockedBy("has_fir_planks", InventoryChangeTrigger.TriggerInstance.hasItems(FIR_PLANKS))
				.save(recipeOutput);
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FIR_BOOKSHELF)
				.pattern("###")
				.pattern("XXX")
				.pattern("###")
				.define('#', FIR_PLANKS)
				.define('X', Items.BOOK)
				.group("bookshelves")
				.unlockedBy("has_book", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOOK))
				.showNotification(false)
				.save(recipeOutput);
	}
}