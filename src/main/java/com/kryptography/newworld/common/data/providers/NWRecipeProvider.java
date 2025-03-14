package com.kryptography.newworld.common.data.providers;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.tags.NWItemTags;
import com.kryptography.newworld.integration.BBIntegration;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.NMLIntegration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

public class NWRecipeProvider extends RecipeProvider implements IConditionBuilder {
	public NWRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
		super(pOutput, pRegistries);
	}

	@Override
	protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, 4)
				.pattern("XX")
				.pattern("XX")
				.define('X', NWBlocks.LOAM)
				.unlockedBy("has_loam", has(NWBlocks.LOAM))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, 4)
				.pattern("XX")
				.pattern("XX")
				.define('X', NWBlocks.LOAM_BRICKS)
				.unlockedBy("has_loam_bricks", has(NWBlocks.LOAM_BRICKS))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM, 4)
				.pattern("CD")
				.pattern("DC")
				.define('C', Items.CLAY)
				.define('D', Items.DIRT)
				.unlockedBy("has:loam", has(NWBlocks.LOAM))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NWBlocks.TOMBSTONE)
				.pattern("SDS")
				.pattern("SAS")
				.pattern("STS")
				.define('S', Items.POLISHED_DEEPSLATE)
				.define('D', Items.DIAMOND)
				.define('A', NWItems.OMINOUS_TOME)
				.define('T', NWBlocks.TOMBSTONE)
				.unlockedBy("has_tombstone_or_ominous_tome", has(NWItemTags.TOMBSTONE_MATERIALS))
				.showNotification(true)
				.save(recipeOutput);

		planksFromLog(recipeOutput, NWBlocks.FIR_PLANKS, NWItemTags.FIR_LOGS, 4);
		stairBuilder(NWBlocks.FIR_STAIRS, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.FIR_SLAB, NWBlocks.FIR_PLANKS);
		fenceBuilder(NWBlocks.FIR_FENCE, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		fenceGateBuilder(NWBlocks.FIR_FENCE_GATE, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		woodFromLogs(recipeOutput, NWBlocks.FIR_WOOD, NWBlocks.FIR_LOG);
		woodFromLogs(recipeOutput, NWBlocks.STRIPPED_FIR_WOOD, NWBlocks.STRIPPED_FIR_LOG);
		woodenBoat(recipeOutput, NWItems.FIR_BOAT, NWBlocks.FIR_PLANKS);
		chestBoat(recipeOutput, NWItems.FIR_CHEST_BOAT, NWItems.FIR_BOAT);
		hangingSign(recipeOutput, NWBlocks.FIR_HANGING_SIGN, NWBlocks.STRIPPED_FIR_LOG);
		signBuilder(NWItems.FIR_SIGN, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		doorBuilder(NWBlocks.FIR_DOOR, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		trapdoorBuilder(NWBlocks.FIR_TRAPDOOR, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		buttonBuilder(NWBlocks.FIR_BUTTON, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(recipeOutput);
		pressurePlate(recipeOutput, NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_PLANKS);

		stoneSetRecipes(recipeOutput, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
		stoneSetRecipes(recipeOutput, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
		stoneSetRecipes(recipeOutput, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);

		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_WALL, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM);

		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICKS, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_BRICKS, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_BRICKS);

		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILES);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILES, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_TILES);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, NWItems.MATTOCK_CRAFTING_TEMPLATE).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT).unlockedBy("has_mattock_crafting_template_piece", has(NWItemTags.MATTOCK_PIECES)).save(recipeOutput, NWItems.MATTOCK_CRAFTING_TEMPLATE.getRegisteredName() + "_from_piece_combination");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(NWItems.MATTOCK_CRAFTING_TEMPLATE),
						Ingredient.of(Items.STICK),
						Ingredient.of(Items.FLINT),
						RecipeCategory.TOOLS,
						NWItems.ANCIENT_MATTOCK.asItem())
				.unlocks("has_mattock_crafting_template", has(NWItems.MATTOCK_CRAFTING_TEMPLATE))
				.save(recipeOutput, NewWorld.id("ancient_mattock_smithing"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NWItems.MATTOCK_CRAFTING_TEMPLATE.get(), 2)
				.pattern("#T#")
				.pattern("#C#")
				.pattern("###")
				.define('#', Items.DIAMOND)
				.define('C', Items.COBBLED_DEEPSLATE)
				.define('T', NWItems.MATTOCK_CRAFTING_TEMPLATE.get())
				.unlockedBy("has_mattock_crafting_template", has(NWItems.MATTOCK_CRAFTING_TEMPLATE.get()))
				.save(recipeOutput);
		FDIntegration.fdRecipes(recipeOutput.withConditions(new ModLoadedCondition("farmersdelight")));
		NMLIntegration.nmlRecipes(recipeOutput.withConditions(new ModLoadedCondition("nomansland")));
		BBIntegration.bbRecipes(recipeOutput.withConditions(new ModLoadedCondition("blockbox")));
	}

	public void stoneSetRecipes(RecipeOutput pRecipeOutput, ItemLike base, ItemLike stairs, ItemLike slab, ItemLike wall) {
		stairBuilder(stairs, Ingredient.of(base)).unlockedBy("has_" + base.asItem().getDescriptionId(), has(base)).save(pRecipeOutput);
		slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, base);
		wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, wall, base);
	}


	public void stonecutterRecipe(RecipeOutput pRecipeOutput, RecipeCategory category, ItemLike output, ItemLike input) {
		this.stonecutterRecipe(pRecipeOutput, category, output, input, 1);
	}

	public void stonecutterRecipe(RecipeOutput pRecipeOutput, RecipeCategory category, ItemLike output, ItemLike input, int count) {
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, output, count).unlockedBy(getHasName(input), has(input)).save(pRecipeOutput, NewWorld.id( getConversionRecipeName(output, input)) + "_stonecutting");
	}
}
