package com.kryptography.newworld.integration;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.registry.NWBlocks;
import com.kryptography.newworld.core.registry.NWItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.Supplier;

import static com.kryptography.newworld.core.registry.NWBlocks.*;
import static vectorwing.farmersdelight.data.recipe.CuttingRecipes.AXES;
import static vectorwing.farmersdelight.data.recipe.CuttingRecipes.AXES_STRIP;

public class FDIntegration {

	public static final Supplier<Block> FIR_CABINET = () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));

	public static void addBlockEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
				ModBlockEntityTypes.CABINET.get(),
				NWBlocks.FIR_CABINET.get()
		);
	}

	public static void register() {
	}

	public static void fdRecipes(RecipeOutput recipeOutput) {
		salvagePlankFromFurniture(recipeOutput,
			FIR_PLANKS, FIR_DOOR, FIR_TRAPDOOR, FIR_SIGNS.getFirst(), FIR_HANGING_SIGNS.getFirst(), FIR_FENCE, FIR_FENCE_GATE,
			FIR_PRESSURE_PLATE, FIR_BUTTON, NWItems.FIR_BOAT.getFirst(), NWBlocks.FIR_CABINET.get());
		stripLogForBark(recipeOutput, FIR_LOG, STRIPPED_FIR_LOG);
		stripLogForBark(recipeOutput, FIR_WOOD, STRIPPED_FIR_WOOD);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, NWBlocks.FIR_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', FIR_SLAB)
				.define('D', FIR_TRAPDOOR)
				.unlockedBy("has_fir_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(FIR_TRAPDOOR))
				.group("fd_cabinet")
				.save(recipeOutput);
	}


	private static void salvagePlankFromFurniture(RecipeOutput output, ItemLike plank, ItemLike... furniture) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(furniture), AXES, plank, 1, 0.75F)
			.save(output, salvagingRecipe("fir_furniture"));
	}
	private static ResourceLocation salvagingRecipe(String name) {
		return ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, "salvaging/" + name);
	}

	private static void stripLogForBark(RecipeOutput output, ItemLike log, ItemLike strippedLog) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log), AXES_STRIP, strippedLog)
			.addResult(ModItems.TREE_BARK.get())
			.addSound(SoundEvents.AXE_STRIP)
			.saveToFD(output);
	}
}
