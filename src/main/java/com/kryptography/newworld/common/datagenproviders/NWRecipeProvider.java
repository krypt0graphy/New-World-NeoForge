package com.kryptography.newworld.common.datagenproviders;

import com.kryptography.newworld.NewWorld;
import com.kryptography.newworld.init.NWBlocks;
import com.kryptography.newworld.init.NWItems;
import com.kryptography.newworld.init.data.tags.NWItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;


import java.util.concurrent.CompletableFuture;

public class NWRecipeProvider extends RecipeProvider{


    protected NWRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        this.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', NWBlocks.LOAM)
                .unlockedBy("has_loam", has(NWBlocks.LOAM))
                .save(output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', NWBlocks.LOAM_BRICKS)
                .unlockedBy("has_loam_bricks", has(NWBlocks.LOAM_BRICKS))
                .save(output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM, 4)
                .pattern("CD")
                .pattern("DC")
                .define('C', Items.CLAY)
                .define('D', Items.DIRT)
                .unlockedBy("has:loam", has(NWBlocks.LOAM))
                .save(output);

        this.shaped(RecipeCategory.COMBAT, NWBlocks.TOMBSTONE)
                .pattern("SDS")
                .pattern("SAS")
                .pattern("STS")
                .define('S', Items.POLISHED_DEEPSLATE)
                .define('D', Items.DIAMOND)
                .define('A', NWItems.ILLAGER_TOME)
                .define('T', NWBlocks.TOMBSTONE)
                .unlockedBy("has_tombstone_or_illager_tome", has(NWItemTags.TOMBSTONE_MATERIALS))
                .showNotification(true)
                .save(output);

        planksFromLog(NWBlocks.FIR_PLANKS, NWItemTags.FIR_LOGS, 4);
        stairBuilder(NWBlocks.FIR_STAIRS, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, NWBlocks.FIR_SLAB, NWBlocks.FIR_PLANKS);
        fenceBuilder(NWBlocks.FIR_FENCE, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        fenceGateBuilder(NWBlocks.FIR_FENCE_GATE, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        woodFromLogs(NWBlocks.FIR_WOOD, NWBlocks.FIR_LOG);
        woodFromLogs(NWBlocks.STRIPPED_FIR_WOOD, NWBlocks.STRIPPED_FIR_LOG);
        woodenBoat(NWItems.FIR_BOAT, NWBlocks.FIR_PLANKS);
        chestBoat(NWItems.FIR_CHEST_BOAT, NWBlocks.FIR_PLANKS);
        hangingSign(NWBlocks.FIR_HANGING_SIGN, NWBlocks.STRIPPED_FIR_LOG);
        signBuilder(NWItems.FIR_SIGN, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        doorBuilder(NWBlocks.FIR_DOOR, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        trapdoorBuilder(NWBlocks.FIR_TRAPDOOR, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        buttonBuilder(NWBlocks.FIR_BUTTON, Ingredient.of(NWBlocks.FIR_PLANKS)).unlockedBy("has_fir_planks", has(NWBlocks.FIR_PLANKS)).save(output);
        pressurePlate(NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_PLANKS);

        stoneSetRecipes(output, NWBlocks.LOAM, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM_WALL);
        stoneSetRecipes(output, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICK_WALL);
        stoneSetRecipes(output, NWBlocks.LOAM_TILES, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILE_WALL);

        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_STAIRS, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_SLAB, NWBlocks.LOAM, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_WALL, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICKS, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM);

        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_STAIRS, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_SLAB, NWBlocks.LOAM_BRICKS, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_BRICK_WALL, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILES, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_BRICKS);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_BRICKS, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_BRICKS);

        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_STAIRS, NWBlocks.LOAM_TILES);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_SLAB, NWBlocks.LOAM_TILES, 2);
        stonecutterRecipe(output, RecipeCategory.BUILDING_BLOCKS, NWBlocks.LOAM_TILE_WALL, NWBlocks.LOAM_TILES);

        this.shapeless(RecipeCategory.TOOLS, NWItems.MATTOCK_CRAFTING_TEMPLATE).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD).requires(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT).unlockedBy("has_mattock_crafting_template_piece", has(NWItemTags.MATTOCK_PIECES)).save(output, NWItems.MATTOCK_CRAFTING_TEMPLATE.getRegisteredName() + "_from_piece_combination");

        SmithingTransformRecipeBuilder
                .smithing(Ingredient.of(NWItems.MATTOCK_CRAFTING_TEMPLATE),
                        Ingredient.of(Items.STICK),
                        Ingredient.of(Items.FLINT),
                        RecipeCategory.TOOLS,
                        NWItems.ANCIENT_MATTOCK.asItem())
                .unlocks("has_mattock_crafting_template", has(NWItems.MATTOCK_CRAFTING_TEMPLATE))
                .save(output, NewWorld.id("ancient_mattock_smithing").toString());
    }

    public void stoneSetRecipes(RecipeOutput output, ItemLike base, ItemLike stairs, ItemLike slab, ItemLike wall) {
        stairBuilder(stairs, Ingredient.of(base)).unlockedBy("has_" + base.asItem().getDescriptionId(), has(base)).save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, slab, base);
        wall(RecipeCategory.BUILDING_BLOCKS, wall, base);
    }


    public void stonecutterRecipe(RecipeOutput output, RecipeCategory category, ItemLike itemOut, ItemLike itemIn) {
        this.stonecutterRecipe(output, category, itemOut, itemIn, 1);
    }

    public void stonecutterRecipe(RecipeOutput output, RecipeCategory category, ItemLike itemOut, ItemLike input, int count) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, itemOut, count).unlockedBy(getHasName(input), has(input)).save(output, NewWorld.id( getConversionRecipeName(itemOut, input)) + "_stonecutting");
    }


    public static class Runner extends RecipeProvider.Runner {


        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new NWRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "NewWorld Recipes";
        }
    }
}
