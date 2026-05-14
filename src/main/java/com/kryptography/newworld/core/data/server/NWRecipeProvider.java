package com.kryptography.newworld.core.data.server;

import com.kryptography.newworld.core.NewWorld;
import com.kryptography.newworld.core.other.NWBlockFamilies;
import com.kryptography.newworld.core.other.tags.NWItemTags;
import com.kryptography.newworld.core.registry.NWItems;
import com.kryptography.newworld.integration.BBIntegration;
import com.kryptography.newworld.integration.BLIntegration;
import com.kryptography.newworld.integration.FDIntegration;
import com.kryptography.newworld.integration.NMLIntegration;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

import static com.kryptography.newworld.core.registry.NWBlocks.*;
import static com.kryptography.newworld.core.registry.NWItems.*;

public class NWRecipeProvider extends BlueprintRecipeProvider {
	public static final ModLoadedCondition WOODWORKS_LOADED = new ModLoadedCondition("woodworks");
	public static final ModLoadedCondition BOATLOAD_LOADED = new ModLoadedCondition("boatload");
	public static final ModLoadedCondition FARMERSDELIGHT_LOADED = new ModLoadedCondition("farmersdelight");
	public static final ModLoadedCondition NOMANSLAND_LOADED = new ModLoadedCondition("nomansland");
	public static final ModLoadedCondition BLOCKBOX_LOADED = new ModLoadedCondition("blockbox");

	public NWRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(NewWorld.MOD_ID, output, registries);
	}

	@Override
	public void buildRecipes(RecipeOutput recipeOutput) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOAM_BRICKS, 4)
				.pattern("XX")
				.pattern("XX")
				.define('X', LOAM)
				.unlockedBy("has_loam", has(LOAM))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOAM_TILES, 4)
				.pattern("XX")
				.pattern("XX")
				.define('X', LOAM_BRICKS)
				.unlockedBy("has_loam_bricks", has(LOAM_BRICKS))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOAM, 4)
				.pattern("CD")
				.pattern("DC")
				.define('C', Items.CLAY)
				.define('D', Items.DIRT)
				.unlockedBy("has_loam", has(LOAM))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NWItems.TOMBSTONE)
				.pattern("SDS")
				.pattern("SAS")
				.pattern("STS")
				.define('S', Items.POLISHED_DEEPSLATE)
				.define('D', Items.DIAMOND)
				.define('A', OMINOUS_TOME)
				.define('T', NWItems.TOMBSTONE)
				.unlockedBy("has_tombstone_or_ominous_tome", has(NWItemTags.TOMBSTONE_MATERIALS))
				.showNotification(true)
				.save(recipeOutput);

		generateRecipes(recipeOutput, NWBlockFamilies.FIR_PLANKS_FAMILY, FeatureFlags.DEFAULT_FLAGS);
		planksFromLog(recipeOutput, FIR_PLANKS, NWItemTags.FIR_LOGS, 4);
		woodFromLogs(recipeOutput, FIR_WOOD, FIR_LOG);
		woodFromLogs(recipeOutput, STRIPPED_FIR_WOOD, STRIPPED_FIR_LOG);
		woodenBoat(recipeOutput, FIR_BOAT.getFirst(), FIR_PLANKS);
		chestBoat(recipeOutput, FIR_BOAT.getSecond(), FIR_BOAT.getFirst());
		hangingSign(recipeOutput, FIR_HANGING_SIGNS.getFirst(), STRIPPED_FIR_LOG);
		leafPileRecipes(recipeOutput, FIR_LEAVES.get(), FIR_LEAF_PILE.get());

		stoneSetRecipes(recipeOutput, LOAM, LOAM_STAIRS, LOAM_SLAB, LOAM_WALL);
		stoneSetRecipes(recipeOutput, LOAM_BRICKS, LOAM_BRICK_STAIRS, LOAM_BRICK_SLAB, LOAM_BRICK_WALL);
		stoneSetRecipes(recipeOutput, LOAM_TILES, LOAM_TILE_STAIRS, LOAM_TILE_SLAB, LOAM_TILE_WALL);

		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_STAIRS, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_SLAB, LOAM, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_WALL, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICKS, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICK_STAIRS, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICK_SLAB, LOAM, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICK_WALL, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILES, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_STAIRS, LOAM);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_SLAB, LOAM, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_WALL, LOAM);

		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICK_STAIRS, LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICK_SLAB, LOAM_BRICKS, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_BRICK_WALL, LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILES, LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_STAIRS, LOAM_BRICKS);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_SLAB, LOAM_BRICKS, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_WALL, LOAM_BRICKS);

		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_STAIRS, LOAM_TILES);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_SLAB, LOAM_TILES, 2);
		stonecutterRecipe(recipeOutput, RecipeCategory.BUILDING_BLOCKS, LOAM_TILE_WALL, LOAM_TILES);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, MATTOCK_CRAFTING_TEMPLATE).requires(MATTOCK_CRAFTING_TEMPLATE_HEAD).requires(MATTOCK_CRAFTING_TEMPLATE_SHAFT).unlockedBy("has_mattock_crafting_template_piece", has(NWItemTags.MATTOCK_PIECES)).save(recipeOutput, MATTOCK_CRAFTING_TEMPLATE.getRegisteredName() + "_from_piece_combination");

		SmithingTransformRecipeBuilder
				.smithing(Ingredient.of(MATTOCK_CRAFTING_TEMPLATE),
						Ingredient.of(Items.STICK),
						Ingredient.of(Items.FLINT),
						RecipeCategory.TOOLS,
						ANCIENT_MATTOCK.asItem())
				.unlocks("has_mattock_crafting_template", has(MATTOCK_CRAFTING_TEMPLATE))
				.save(recipeOutput, NewWorld.id("ancient_mattock_smithing"));

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MATTOCK_CRAFTING_TEMPLATE.get(), 2)
				.pattern("#T#")
				.pattern("#C#")
				.pattern("###")
				.define('#', Items.DIAMOND)
				.define('C', Items.COBBLED_DEEPSLATE)
				.define('T', MATTOCK_CRAFTING_TEMPLATE.get())
				.unlockedBy("has_mattock_crafting_template", has(MATTOCK_CRAFTING_TEMPLATE.get()))
				.save(recipeOutput);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FIR_BOOKSHELF.get())
				.define('#', FIR_PLANKS)
				.define('X', Items.BOOK)
				.pattern("###")
				.pattern("XXX")
				.pattern("###")
				.group("wooden_bookshelf")
				.unlockedBy("has_book", has(Items.BOOK))
				.save(recipeOutput.withConditions(NOMANSLAND_LOADED), NewWorld.id("fir_bookshelf_nml"));

		RecipeOutput woodworksOutput = recipeOutput.withConditions(WOODWORKS_LOADED);
		WoodworksRecipeProvider.baseRecipes(woodworksOutput, FIR_PLANKS.get(), FIR_SLAB.get(), FIR_BOARDS.get(), FIR_BOOKSHELF.get(), CHISELED_FIR_BOOKSHELF.get(), FIR_LADDER.get(), FIR_BEEHIVE.get(), FIR_CHEST.get(), TRAPPED_FIR_CHEST.get(), NewWorld.MOD_ID);
		WoodworksRecipeProvider.sawmillRecipes(woodworksOutput, NWBlockFamilies.FIR_PLANKS_FAMILY, NWItemTags.FIR_LOGS, FIR_BOARDS.get(), FIR_LADDER.get(), NewWorld.MOD_ID);

		BLIntegration.blRecipes(recipeOutput.withConditions(BOATLOAD_LOADED));
		FDIntegration.fdRecipes(recipeOutput.withConditions(FARMERSDELIGHT_LOADED));
		NMLIntegration.nmlRecipes(recipeOutput.withConditions(NOMANSLAND_LOADED));
		BBIntegration.bbRecipes(recipeOutput.withConditions(BLOCKBOX_LOADED));
	}

	public void stoneSetRecipes(RecipeOutput pRecipeOutput, ItemLike base, ItemLike stairs, ItemLike slab, ItemLike wall) {
		stairBuilder(stairs, Ingredient.of(base)).unlockedBy("has_" + base.asItem().getDescriptionId(), has(base)).save(pRecipeOutput);
		slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, slab, base);
		wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, wall, base);
	}
}
