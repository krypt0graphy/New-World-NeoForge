package com.kryptography.newworld.integration;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import static com.kryptography.newworld.init.NWBlocks.*;

public class FDIntegration {

	public static final DeferredBlock<Block> FIR_CABINET = NWBlocks.register("fir_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

	public static void addBlockEntities(BlockEntityTypeAddBlocksEvent event) {
		event.modify(
				ModBlockEntityTypes.CABINET.get(),
				FIR_CABINET.get()
		);
	}

	public static void register() {
	}

	public static void fdRecipes(RecipeOutput recipeOutput) {
		salvagePlankFromFurniture(recipeOutput, FIR_PLANKS, FIR_DOOR, FIR_TRAPDOOR, FIR_SIGN, FIR_HANGING_SIGN);
		stripLogForBark(recipeOutput, FIR_LOG, STRIPPED_FIR_LOG);
		stripLogForBark(recipeOutput, FIR_WOOD, STRIPPED_FIR_WOOD);
		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FIR_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', FIR_SLAB)
				.define('D', FIR_TRAPDOOR)
				.unlockedBy("has_fir_trapdoor", InventoryChangeTrigger.TriggerInstance.hasItems(FIR_TRAPDOOR))
				.group("fd_cabinet")
				.save(recipeOutput);
	}


	private static void salvagePlankFromFurniture(RecipeOutput output, ItemLike plank, ItemLike door, ItemLike trapdoor, ItemLike sign, ItemLike hangingSign) {
		cuttingRecipe(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(door), new ItemAbilityIngredient(ItemAbilities.AXE_DIG).toVanilla(), plank), output);
		cuttingRecipe(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(trapdoor), new ItemAbilityIngredient(ItemAbilities.AXE_DIG).toVanilla(), plank), output);
		cuttingRecipe(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(sign), new ItemAbilityIngredient(ItemAbilities.AXE_DIG).toVanilla(), plank), output);
		cuttingRecipe(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(hangingSign), new ItemAbilityIngredient(ItemAbilities.AXE_DIG).toVanilla(), plank), output);

	}

	private static void stripLogForBark(RecipeOutput output, ItemLike log, ItemLike strippedLog) {
		cuttingRecipe(CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log), new ItemAbilityIngredient(ItemAbilities.AXE_STRIP).toVanilla(), strippedLog)
				.addResult(ModItems.TREE_BARK.get())
				.addSound(SoundEvents.AXE_STRIP), output);
	}

	private static void cuttingRecipe(CuttingBoardRecipeBuilder builder, RecipeOutput output) {
		ResourceLocation location = BuiltInRegistries.ITEM.getKey(builder.getResult());
		builder.save(output, ResourceLocation.fromNamespaceAndPath(NewWorld.MOD_ID, location.getPath()));
	}
}